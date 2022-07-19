package br.com.mnb.theme.core.factory;

import java.lang.reflect.Constructor;

import br.com.mnb.theme.core.xml.view.AbstractViewElement;

public interface ViewFactory {

	default <T extends AbstractViewElement> T createView(Class<T> clazz) {
		try {
			Constructor<T> ctor = clazz.getDeclaredConstructor();
			ctor.setAccessible(true);
			return ctor.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Fail to instance View class " + clazz.getName(), e);
		}
	}

}
