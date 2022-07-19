package br.com.mnb.theme.core.factory;

import java.lang.reflect.Constructor;

import br.com.mnb.theme.core.xml.theme.AbstractTheme;

public interface ThemeFactory {

	default <T extends AbstractTheme> T createTheme(Class<T> clazz) {
		try {
			Constructor<T> ctor = clazz.getDeclaredConstructor();
			ctor.setAccessible(true);
			return ctor.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Fail to instance Theme class " + clazz.getName(), e);
		}
	}

}
