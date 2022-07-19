package br.com.mnb.theme.batocera.xml.converter.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.mnb.theme.batocera.xml.feature.AbstractFeature;
import br.com.mnb.theme.batocera.xml.feature.CarouselFeature;
import br.com.mnb.theme.batocera.xml.feature.VideoFeature;

class FeatureTagConverterTest {

	private FeatureTagConverter featureConverter = new FeatureTagConverter();

	@Test
	void sucessWhenConvertTagNameToFeature() {

		AbstractFeature feature = featureConverter.toComponent("carousel");
		assertNotNull(feature);
		assertInstanceOf(CarouselFeature.class, feature);

		feature = featureConverter.toComponent("video");
		assertNotNull(feature);
		assertInstanceOf(VideoFeature.class, feature);

	}

	@Test
	void sucessWhenConvertFeatureToTagName() {

		String result = featureConverter.toString(new CarouselFeature());
		assertNotNull(result);
		assertEquals("carousel", result);

		result = featureConverter.toString(new VideoFeature());
		assertNotNull(result);
		assertEquals("video", result);

	}

	@Test
	void failWhenConvertTagToFeatureWithTagNameUnmapped() {
		assertThrows(IllegalArgumentException.class, () -> {
			featureConverter.toComponent("Other");
		});
	}

	@Test
	void failWhenConvertFeatureToTagNameWithComponentUnmapped() {
		assertThrows(IllegalArgumentException.class, () -> {
			featureConverter.toString(new UnmappedFeature());
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
