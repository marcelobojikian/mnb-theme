package br.com.mnb.theme.batocera.xml.xstream.configure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;

import br.com.mnb.theme.batocera.xml.element.BatoceraCarousel;
import br.com.mnb.theme.batocera.xml.feature.AbstractFeature;
import br.com.mnb.theme.batocera.xml.feature.BatoceraFeature;
import br.com.mnb.theme.batocera.xml.feature.CarouselFeature;
import br.com.mnb.theme.batocera.xml.feature.VideoFeature;
import br.com.mnb.theme.batocera.xml.view.View;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;

class TagFeatureConfigureTest {
	
	XStream xstream;
	
	@BeforeEach
	public void setup() {
		NamedTagConverter<AbstractFeature> featureConverter = new NamedTagConverter<AbstractFeature>();
		NamedTagConverter<AbstractViewElement> viewConverter = new NamedTagConverter<AbstractViewElement>();
		NamedTagConverter<AbstractElement> elementConverter = new NamedTagConverter<AbstractElement>();
		TagFeatureConfigure configure = new TagFeatureConfigure(featureConverter, viewConverter, elementConverter);

		configure.addAlias("feature", BatoceraFeature.class);
		
		configure.addFeature("carousel", CarouselFeature.class);
		configure.addFeature("video", VideoFeature.class);
		
		configure.addView("view", View.class);
		
		configure.addElement("carousel", BatoceraCarousel.class);
		
		xstream = configure.getXStream();
	}

	@Test
	@DisplayName("Should convert to XML Carousel")
	public void sucessWhenConvertToXmlCarousel() {		
		String resultExpected = "<feature supported=\"carousel\"/>";
		String result = xstream.toXML(new CarouselFeature());
		assertEquals(resultExpected, result);
	}

	@Test
	@DisplayName("Should convert to Java Carousel")
	public void sucessWhenConvertToJavaCarousel() {
		CarouselFeature feature = (CarouselFeature) xstream.fromXML("<feature supported=\"carousel\"/>");
		assertNotNull(feature);
		assertEquals("carousel", feature.getSupported());
	}

	@Test
	@DisplayName("Should convert to XML Video")
	public void sucessWhenConvertToXmlVideo() {		
		String resultExpected = "<feature supported=\"video\"/>";
		String result = xstream.toXML(new VideoFeature());
		assertEquals(resultExpected, result);
	}

	@Test
	@DisplayName("Should convert to Java Video")
	public void sucessWhenConvertToJavaVideo() {
		VideoFeature feature = (VideoFeature) xstream.fromXML("<feature supported=\"video\"/>");
		assertNotNull(feature);
		assertEquals("video", feature.getSupported());
	}

}
