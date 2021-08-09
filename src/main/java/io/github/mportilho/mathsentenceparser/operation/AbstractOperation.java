/*MIT License

Copyright (c) 2021 Marcelo Portilho

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.*/

package io.github.mportilho.mathsentenceparser.operation;

import io.github.mportilho.mathsentenceparser.operation.value.variable.AbstractVariableValueOperation;
import io.github.mportilho.mathsentenceparser.syntaxtree.MathSentenceComputingException;
import io.github.mportilho.mathsentenceparser.syntaxtree.visitor.OperationVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Default behavior for all operations
 *
 * @author Marcelo Portilho
 */
public abstract class AbstractOperation {

    private Object cache;
    private boolean caching;
    private boolean cachingForever;

    private List<AbstractOperation> parents;
    private boolean applyingParenthesis;

    public AbstractOperation() {
        this.caching = true;
    }

    protected abstract Object resolve(OperationContext context);

    protected abstract AbstractOperation createClone(CloningContext context);

    protected abstract String getOperationToken();

    public abstract <T> T accept(OperationVisitor<T> visitor);

    protected abstract void composeTextualRepresentation(StringBuilder builder);

    /**
     * Builds a String representation of the current operation
     *
     * @param builder the {@link StringBuilder} object where the representation will
     *                be placed
     */
    public final void generateRepresentation(StringBuilder builder) {
        if (this.applyingParenthesis) {
            builder.append('(');
            composeTextualRepresentation(builder);
            builder.append(')');
        } else {
            composeTextualRepresentation(builder);
        }
    }

    /**
     * Makes a copy of the current operation, with it's properties and cache value
     *
     * @param cloningContext a holder of the cloning operation temporary properties
     * @return a new copy of this object
     */
    public final AbstractOperation copy(CloningContext cloningContext) {
        AbstractOperation copy;
        if (cloningContext.getClonedOperationMap().containsKey(this)) {
            copy = cloningContext.getClonedOperationMap().get(this);
        } else {
            copy = createClone(cloningContext).copyAtributes(this);
            cloningContext.getClonedOperationMap().put(this, copy);
        }
        return copy;
    }

    /**
     * Evaluates the current operation for it's resulting value. If the current
     * operation was previously evaluated, returns it's cached value.
     *
     * @param <T>     Dynamic return type
     * @param context a holder of the evaluation proccess properties
     * @return the operation's evaluation result
     */
    @SuppressWarnings("unchecked")
    public final <T> T evaluate(OperationContext context) {
        Object result;
        try {
            result = readValue(context);
        } catch (ClassCastException e) {
            throw new IllegalStateException(String.format("Wrong operands type for expression %s", this), e);
        } catch (ArithmeticException e) {
            ArithmeticException newException = new ArithmeticException(
                    String.format("Arithmetic error for expression '%s': %s", this, e.getMessage()));
            newException.setStackTrace(e.getStackTrace());
            throw newException;
        } catch (MathSentenceComputingException e) {
            throw new IllegalStateException(e.getMessage(), e.getCause());
        } catch (IllegalArgumentException | IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException(String.format("Error during calculation of expression '%s'", this), e);
        }
        if (result == null && !context.isAllowingNull()) {
            if (this instanceof AbstractVariableValueOperation) {
                throw new IllegalArgumentException(String.format("The variable '%s' does not have any provided value", this));
            } else {
                throw new NullPointerException(String.format("Not expecting null for expression value '%s' ", this));
            }
        }
        return (T) result;
    }

    private Object readValue(OperationContext context) {
        Object result;
        if (caching || cachingForever) {
            if (cache == null) {
                result = resolve(context); // resolve method can disable cache
                cache = caching || cachingForever ? result : null;
            } else {
                result = cache;
            }
        } else {
            result = resolve(context);
        }
        return result;
    }

    private AbstractOperation copyAtributes(AbstractOperation sourceOperation) {
        this.cache = sourceOperation.cache;
        this.caching = sourceOperation.caching;
        this.cachingForever = sourceOperation.cachingForever;
        this.applyingParenthesis = sourceOperation.applyingParenthesis;
        return this;
    }

    /**
     * Indicates that the representation of the current operation must be wrapped by
     * parenthesis
     */
    public void applyingParenthesis() {
        this.applyingParenthesis = true;
    }

    /**
     * Adds a parent operation node for this current operation
     *
     * @param operation the parent of this operation
     */
    public void addParent(AbstractOperation operation) {
        if (operation == null) {
            return;
        }
        if (parents == null) {
            parents = new ArrayList<>();
        }
        parents.add(operation);
    }

    /**
     * Sets the caching behavior for this operation
     *
     * @param value a indication if the current operation must be cached
     */
    public void caching(boolean value) {
        this.caching = value;
    }

    protected boolean isCaching() {
        return caching || cachingForever;
    }

    protected void cachingForever() {
        this.cachingForever = true;
    }

    protected boolean isCachingForever() {
        return cachingForever;
    }

    protected void clearCache() {
        clearCache(null);
    }

    protected final void clearCache(Set<Class<? extends AbstractOperation>> stopOnOperationTypes) {
        if (!isCaching() || this.cache == null) {
            return;
        }
        this.cache = null;
        if (parents != null) {
            for (int i = 0; i < parents.size(); i++) {
                AbstractOperation currParent = parents.get(i);
                if (currParent.cache != null && (stopOnOperationTypes == null || !stopOnOperationTypes.contains(getClass()))) {
                    currParent.clearCache(stopOnOperationTypes);
                }
            }
        }
    }

    /**
     * Retrieves the current cached value for this operation
     *
     * @return The current cache for this operation. Returns <code>null</code> if
     * the operation was not evaluated or cache is disabled
     */
    public Object getCache() {
        return cache;
    }

    /**
     * Verify
     *
     * @return a indication if the current operation must have it's text
     * representation wrapped by parenthesis
     */
    public boolean isApplyingParenthesis() {
        return applyingParenthesis;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        generateRepresentation(builder);
        return builder.toString();
    }

}
