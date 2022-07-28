package br.com.mnb.theme.core.xml.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import br.com.mnb.theme.core.xml.exception.TagAliasNotFoundException;
import br.com.mnb.theme.core.xml.feature.AbstractFeature;

public class XmlValueAnnotation<T> {
	
	public String getElementName(Class<? extends T> clazz) {
		evaluateClass(clazz);
		String value= null;
		if(AbstractFeature.class.isAssignableFrom(clazz)) {
			value = getSupported(clazz);
		} else {
			value = getValue(clazz);
		}
		return value;
	}

	private void evaluateClass(Class<? extends T> clazz) {
        if (!clazz.isAnnotationPresent(XStreamAlias.class)) {
            throw new TagAliasNotFoundException("The class " + clazz.getSimpleName() + " is not annotated with XStreamAlias");
        }
	}

	private String getValue(Class<? extends T> clazz) {
		XStreamAlias element = null;
		for (Annotation annotation : clazz.getDeclaredAnnotations()) {
            if (annotation.annotationType().isAssignableFrom(XStreamAlias.class)) {
            	element = (XStreamAlias) annotation;
            }
        }
		return element.value();
	}

	private String getSupported(Class<?> clazz) {
		String value = null;
		for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(XStreamAlias.class) && field.getName().equals("supported")) {
				try {
					Constructor<?> ctor = clazz.getDeclaredConstructor();
					ctor.setAccessible(true);
					Object obj = ctor.newInstance();
					field.setAccessible(true);
					value = field.get(obj).toString();
				} catch (Exception e) {
					throw new TagAliasNotFoundException("The class " + clazz.getSimpleName() + " could not be create");
				}
            	
            }
        }
		return value;
	}

}
