package io.github.mportilho.mathsentenceparser.syntaxtree.function;

@FunctionalInterface
public interface DynamicFunction {

	public Object call(DynamicFunctionContext context, Object[] parameters);

}
