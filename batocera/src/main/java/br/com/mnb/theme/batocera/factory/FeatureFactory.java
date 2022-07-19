package br.com.mnb.theme.batocera.factory;

import java.lang.reflect.Constructor;

import br.com.mnb.theme.batocera.xml.feature.AbstractFeature;

public interface FeatureFactory {

	default <T extends AbstractFeature> T createFeature(Class<T> clazz) {
		try {
			Constructor<T> ctor = clazz.getDeclaredConstructor();
			ctor.setAccessible(true);
			return ctor.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Fail to instance Feature class " + clazz.getName(), e);
		}
	}
	
}
