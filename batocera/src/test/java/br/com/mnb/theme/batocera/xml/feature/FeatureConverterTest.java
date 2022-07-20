package br.com.mnb.theme.batocera.xml.feature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mnb.theme.batocera.factory.FeatureFactory;

class FeatureConverterTest {

	FeatureConverter converter;
	
	@BeforeEach
	public void setup() {
		
		FeatureFactory factory = mock(FeatureFactory.class);
		when(factory.createFeature(CarouselFeature.class)).thenReturn(new CarouselFeature());
		when(factory.createFeature(VideoFeature.class)).thenReturn(new VideoFeature());
		
		converter = new FeatureConverter(factory);
		converter.registerElement("carousel", CarouselFeature.class);
		converter.registerElement("video", VideoFeature.class);
	}

	@Test
	void sucessWhenConvertTagNameToFeature() {

		AbstractFeature feature = converter.toComponent("carousel");
		assertNotNull(feature);
		assertInstanceOf(CarouselFeature.class, feature);

		feature = converter.toComponent("video");
		assertNotNull(feature);
		assertInstanceOf(VideoFeature.class, feature);

	}

	@Test
	void sucessWhenConvertFeatureToTagName() {

		String result = converter.toString(new CarouselFeature());
		assertNotNull(result);
		assertEquals("carousel", result);

		result = converter.toString(new VideoFeature());
		assertNotNull(result);
		assertEquals("video", result);

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
