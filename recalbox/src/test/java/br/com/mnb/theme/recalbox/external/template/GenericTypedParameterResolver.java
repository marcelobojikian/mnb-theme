package br.com.mnb.theme.recalbox.external.template;

import java.lang.reflect.Parameter;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class GenericTypedParameterResolver<T> implements ParameterResolver {
	
	T data;

	public GenericTypedParameterResolver(T data) {
		this.data = data;
	}

	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
		Parameter parameter = parameterContext.getParameter();
		System.out.println("Support: " + parameter);
		return parameter.getType().isInstance(data);
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
		return data;
	}

}
