package br.com.mnb.theme.core.factory;

import java.lang.reflect.Constructor;

import br.com.mnb.theme.core.xml.element.AbstractElement;

public interface ElementFactory {
	
	default <T extends AbstractElement> T createElement(Class<T> clazz) {
		try {
			Constructor<T> ctor = clazz.getDeclaredConstructor();
			ctor.setAccessible(true);
			return ctor.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Fail to instance Element class " + clazz.getName(), e);
		}
	}

}
