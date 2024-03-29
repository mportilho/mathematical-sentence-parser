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

package io.github.mportilho.mathsentenceparser.syntaxtree.function;

import static java.lang.reflect.Modifier.isStatic;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class FunctionExtractor {

	/**
	 * Collect methods that are public, have return type
	 * 
	 * @param functionProvider
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 */
	public static Map<String, Function<Object[], Object>> extractFunctions(Object functionProvider)
			throws IntrospectionException, IllegalAccessException {
		Map<String, Function<Object[], Object>> functionPool = new HashMap<>();
		MethodHandles.Lookup lookup = MethodHandles.publicLookup();
		boolean isStaticFunctionProvider = functionProvider instanceof Class<?>;
		Class<?> clazz = isStaticFunctionProvider ? (Class<?>) functionProvider : functionProvider.getClass();
		BeanInfo beanInfo = Introspector.getBeanInfo(clazz, Object.class);

		for (MethodDescriptor methodDescriptor : beanInfo.getMethodDescriptors()) {
			Method method = methodDescriptor.getMethod();
			boolean isStaticMethod = isStatic(method.getModifiers());

			if (!void.class.equals(method.getReturnType()) && Modifier.isPublic(method.getModifiers())
					&& !(isStaticFunctionProvider && !isStaticMethod)) {
				MethodHandle methodHandle = isStaticMethod ? lookup.unreflect(method) : lookup.unreflect(method).bindTo(functionProvider);
				String methodLabel = generateMethodSignature(method.getName(), methodHandle.type());
				String key = method.getName() + methodHandle.type().parameterCount();

				MethodHandle callableMethodHandle = methodHandle.asType(methodHandle.type().generic()).asSpreader(Object[].class,
						methodHandle.type().parameterCount());
				functionPool.put(key, params -> {
					try {
						return callableMethodHandle.invokeExact(params);
					} catch (Throwable e) {
						throw new IllegalStateException(String.format("Error calling function '%s' from sentence", methodLabel), e);
					}
				});
			}
		}
		return functionPool;
	}

	private static String generateMethodSignature(String methodName, MethodType type) {
		StringBuilder sb = new StringBuilder();
		sb.append(type.returnType().getSimpleName());
		sb.append(' ').append(methodName).append('(');
		int count = type.parameterCount();
		Class<?>[] ptypes = type.parameterArray();
		for (int i = 0; i < count; i++) {
			if (i > 0) {
				sb.append(",");
			}
			sb.append(ptypes[i].getSimpleName());
		}
		sb.append(')');
		return sb.toString();
	}

}
