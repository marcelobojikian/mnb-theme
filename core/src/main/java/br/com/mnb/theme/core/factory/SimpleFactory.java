package br.com.mnb.theme.core.factory;

import java.lang.reflect.Constructor;

public class SimpleFactory<X> implements ExtensionFactory<X> {

	@Override	
	public <T extends X> T create(Class<T> clazz) {
		try {
			Constructor<T> ctor = clazz.getDeclaredConstructor();
			ctor.setAccessible(true);
			return ctor.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Fail to instance class " + clazz.getName(), e);
		}
	}

}
