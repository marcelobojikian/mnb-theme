package br.com.mnb.theme.batocera.xml.feature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mnb.theme.core.factory.SimpleFactory;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;

class FeatureConverterTest {

	NamedTagConverter<AbstractFeature> converter;
	
	@BeforeEach
	public void setup() {
		
		@SuppressWarnings("unchecked")
		SimpleFactory<AbstractFeature> factory = mock(SimpleFactory.class);
		when(factory.create(CarouselFeature.class)).thenReturn(new CarouselFeature());
		when(factory.create(VideoFeature.class)).thenReturn(new VideoFeature());
		
		converter = new NamedTagConverter<AbstractFeature>(factory);
		converter.put("carousel", CarouselFeature.class);
		converter.put("video", VideoFeature.class);
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
