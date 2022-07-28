package br.com.mnb.theme.core.xml.feature;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mnb.theme.core.factory.SimpleFactory;
import br.com.mnb.theme.core.model.Feature;
import br.com.mnb.theme.core.model.SecondFeature;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;

class FeatureElementTest {

	NamedTagConverter<AbstractFeature> converter;
	
	@BeforeEach
	public void setup() {
		
		@SuppressWarnings("unchecked")
		SimpleFactory<AbstractFeature> factory = mock(SimpleFactory.class);
		when(factory.create(Feature.class)).thenReturn(new Feature());
		when(factory.create(SecondFeature.class)).thenReturn(new SecondFeature());
		
		converter = new NamedTagConverter<AbstractFeature>(factory);
		converter.put(Feature.class);
		converter.put(SecondFeature.class);
	}

	@Test
	void sucessWhenConvertTagNameToFeature() {

		AbstractFeature feature = converter.toComponent("first");
		assertNotNull(feature);
		assertInstanceOf(Feature.class, feature);

		feature = converter.toComponent("second");
		assertNotNull(feature);
		assertInstanceOf(SecondFeature.class, feature);

	}

	@Test
	void sucessWhenConvertFeatureToTagName() {

		String result = converter.toString(new Feature());
		assertNotNull(result);
		assertEquals("first", result);

		result = converter.toString(new SecondFeature());
		assertNotNull(result);
		assertEquals("second", result);

	}

	@Test
	void failWhenConvertTagToFeatureWithTagNameUnmapped() {
		assertThrows(IllegalArgumentException.class, () -> {
			converter.toComponent("Other");
		});
	}

	@Test
	void failWhenConvertFeatureToTagNameWithComponentUnmapped() {
		assertThrows(IllegalArgumentException.class, () -> {
			converter.toString(new UnmappedFeature());
		});
	}
	
	class UnmappedFeature extends AbstractFeature {
		private static final long serialVersionUID = 1L;
		@Override
		public String getSupported() {
			return null;
		}
	}

}
