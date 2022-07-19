package br.com.mnb.theme.batocera.factory;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mnb.theme.batocera.xml.feature.AbstractFeature;

class FeatureFactoryTest {

	FeatureFactory instaceFactory;

	@BeforeEach
	public void init() {
		instaceFactory = new BatoceraInstanceFactory();
	}

	@Test
	void sucessWhenCreateFeature() {
		FeatureTest feature = instaceFactory.createFeature(FeatureTest.class);
		assertNotNull(feature);
	}

	@Test
	void failWhenCreateFeatureWithNullParameter() {
		assertThrows(NullPointerException.class, () -> {
			instaceFactory.createFeature(null);
		});
	}

	@Test
	void failWhenCreateFeatureWithInvalidParameter() {
		assertThrows(RuntimeException.class, () -> {
			instaceFactory.createFeature(AbstractFeature.class);
		});
	}

}

class FeatureTest extends AbstractFeature {
	private static final long serialVersionUID = 1L;
	@Override
	public String getSupported() {
		return null;
	}
	
}
