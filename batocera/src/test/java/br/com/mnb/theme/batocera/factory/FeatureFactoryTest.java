package br.com.mnb.theme.batocera.factory;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mnb.theme.batocera.xml.feature.AbstractFeature;
import br.com.mnb.theme.core.factory.SimpleFactory;

class FeatureFactoryTest {

	SimpleFactory<AbstractFeature> factory;

	@BeforeEach
	public void init() {
		factory = new SimpleFactory<AbstractFeature>();
	}

	@Test
	void sucessWhenCreateFeature() {
		FeatureTest feature = factory.create(FeatureTest.class);
		assertNotNull(feature);
	}

	@Test
	void failWhenCreateFeatureWithNullParameter() {
		assertThrows(NullPointerException.class, () -> {
			factory.create(null);
		});
	}

	@Test
	void failWhenCreateFeatureWithInvalidParameter() {
		assertThrows(RuntimeException.class, () -> {
			factory.create(AbstractFeature.class);
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
