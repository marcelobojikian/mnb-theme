package br.com.mnb.theme.core.xml.xstream.configure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;

import br.com.mnb.theme.core.model.Feature;
import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.model.SecondFeature;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.feature.AbstractFeature;
import br.com.mnb.theme.core.xml.feature.FeatureElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;
import br.com.mnb.theme.core.xml.view.View;

class TagFeatureConfigureTest {
	
	XStream xstream;
	
	@BeforeEach
	public void setup() {
		NamedTagConverter<AbstractFeature> featureConverter = new NamedTagConverter<AbstractFeature>();
		NamedTagConverter<AbstractViewElement> viewConverter = new NamedTagConverter<AbstractViewElement>();
		NamedTagConverter<AbstractElement> elementConverter = new NamedTagConverter<AbstractElement>();
		TagFeatureConfigure configure = new TagFeatureConfigure(featureConverter, viewConverter, elementConverter);

		configure.addAlias("feature", FeatureElement.class);
		
		configure.addFeature("first", Feature.class);
		configure.addFeature("second", SecondFeature.class);
		
		configure.addView("view", View.class);
		
		configure.addElement("element", Element.class);
		
		xstream = configure.getXStream();
	}

	@Test
	@DisplayName("Should convert to XML Carousel")
	public void sucessWhenConvertToXmlCarousel() {		
		String resultExpected = "<feature supported=\"first\"/>";
		String result = xstream.toXML(new Feature());
		assertEquals(resultExpected, result);
	}

	@Test
	@DisplayName("Should convert to Java Carousel")
	public void sucessWhenConvertToJavaCarousel() {
		Feature feature = (Feature) xstream.fromXML("<feature supported=\"first\"/>");
		assertNotNull(feature);
		assertEquals("first", feature.getSupported());
	}

	@Test
	@DisplayName("Should convert to XML Video")
	public void sucessWhenConvertToXmlVideo() {		
		String resultExpected = "<feature supported=\"second\"/>";
		String result = xstream.toXML(new SecondFeature());
		assertEquals(resultExpected, result);
	}

	@Test
	@DisplayName("Should convert to Java Video")
	public void sucessWhenConvertToJavaVideo() {
		SecondFeature feature = (SecondFeature) xstream.fromXML("<feature supported=\"second\"/>");
		assertNotNull(feature);
		assertEquals("second", feature.getSupported());
	}

}
