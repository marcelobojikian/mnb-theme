package br.com.mnb.theme.core.xml.feature;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mnb.theme.core.model.Feature;
import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.model.SecondElement;
import br.com.mnb.theme.core.model.SecondFeature;
import br.com.mnb.theme.core.xml.tag.converter.TagFeatureConverter;
import br.com.mnb.theme.core.xml.view.View;


class AbstractFeatureTest {

	TagFeatureConverter converter;
	
	@BeforeEach
	public void setup() {
		converter = new TagFeatureConverter();
		converter.addAlias("feature", FeatureElement.class);
		converter.addFeature("first", Feature.class);
		converter.addFeature("second", SecondFeature.class);
		converter.addElement("element", Element.class);
		converter.addElement("second", SecondElement.class);
	}

	@Test
	public void whenCreateFeature_DefaultValues() {

		Feature carousel = new Feature();

		assertNull(carousel.getView());
		assertEquals(carousel.getSupported(), "first");
		assertNull(carousel.getView());
		
		SecondFeature video = new SecondFeature();

		assertNull(video.getView());
		assertEquals(video.getSupported(), "second");
		assertNull(video.getView());

	}
	
	@Test
	public void sucessWhenCreateXml_FeatureWithoutView() {
		
		Feature carousel = new Feature();
		
		String featureXml = converter.toXML(carousel);
		String result = "<feature supported=\"first\"/>";

		assertEquals(featureXml, result);
		
		SecondFeature video = new SecondFeature();
		
		featureXml = converter.toXML(video);
		result = "<feature supported=\"second\"/>";

		assertEquals(featureXml, result);
		
	}

	@Test
	public void sucessWhenCreateXml_FeatureWithView() {

		View view = new View();
		view.setName("ViewElement");
		
		Feature carousel = new Feature();
		carousel.setView(view);

		String featureXml = converter.toXML(carousel);
		// @formatter:off
		String result = "<feature supported=\"first\">\n"
					  + "  <view name=\"ViewElement\"/>\n"
					  + "</feature>";
		// @formatter:on

		assertEquals(featureXml, result);
		
		SecondFeature video = new SecondFeature();
		video.setView(view);

		featureXml = converter.toXML(video);
		// @formatter:off
		result = "<feature supported=\"second\">\n"
					  + "  <view name=\"ViewElement\"/>\n"
					  + "</feature>";
		// @formatter:on

		assertEquals(featureXml, result);

	}
	
	@Test
	public void errorWhenFeatureAddElement_WithoutView() {
		
		Feature feature = new Feature();

		assertThrows(NullPointerException.class, () -> {
			feature.addElement(new Element());
		});
		
		SecondFeature video = new SecondFeature();

		assertThrows(NullPointerException.class, () -> {
			video.addElement(new Element());
		});
		
	}

	@Test
	public void sucessWhenCreateXml_FeatureWithOneElements() {

		Feature feature = new Feature();

		Element element = new Element();
		element.setName("TextElement");

		View view = new View();
		view.setName("ViewElement");

		feature.setView(view);
		feature.addElement(element);

		String featureXml = converter.toXML(feature);
		// @formatter:off
		String result = "<feature supported=\"first\">\n"
					  + "  <view name=\"ViewElement\">\n"
					  + "    <element name=\"TextElement\"/>\n"
					  + "  </view>\n"
					  + "</feature>";
		// @formatter:on

		assertEquals(featureXml, result);
		
		SecondFeature video = new SecondFeature();

		view = new View();
		view.setName("ViewElement");

		video.setView(view);
		video.addElement(element);

		featureXml = converter.toXML(video);
		// @formatter:off
		result = "<feature supported=\"second\">\n"
					  + "  <view name=\"ViewElement\">\n"
					  + "    <element name=\"TextElement\"/>\n"
					  + "  </view>\n"
					  + "</feature>";
		// @formatter:on

		assertEquals(featureXml, result);
		
	}

	@Test
	public void sucessWhenCreateXml_FeatureWithManyElements() {

		Element element = new Element();
		element.setName("TextElement");

		SecondElement second = new SecondElement();
		second.setName("ImageElement");

		View view = new View();
		view.setName("ViewElement");
		
		Feature feature = new Feature();

		feature.setView(view);
		feature.addElements(element, second);

		String featureXml = converter.toXML(feature);
		// @formatter:off
		String result = "<feature supported=\"first\">\n"
					  + "  <view name=\"ViewElement\">\n"
					  + "    <element name=\"TextElement\"/>\n"
					  + "    <second name=\"ImageElement\"/>\n"
					  + "  </view>\n"
					  + "</feature>";
		// @formatter:on

		assertEquals(featureXml, result);
		
		SecondFeature video = new SecondFeature();

		view = new View();
		view.setName("ViewElement");

		video.setView(view);
		video.addElements(element, second);

		featureXml = converter.toXML(video);
		// @formatter:off
		result = "<feature supported=\"second\">\n"
					  + "  <view name=\"ViewElement\">\n"
					  + "    <element name=\"TextElement\"/>\n"
					  + "    <second name=\"ImageElement\"/>\n"
					  + "  </view>\n"
					  + "</feature>";
		// @formatter:on

		assertEquals(featureXml, result);
		
	}

	@Test
	public void sucessWhenCreateXml_FeatureWithExtraElements() {

		Element element = new Element();
		element.setName("TextElement");
		element.setExtra(true);

		View view = new View();
		view.setName("ViewElement");

		Feature feature = new Feature();
		
		feature.setView(view);
		feature.addElement(element);

		String featureXml = converter.toXML(feature);
		// @formatter:off
		String result = "<feature supported=\"first\">\n"
					  + "  <view name=\"ViewElement\">\n"
					  + "    <element extra=\"true\" name=\"TextElement\"/>\n"
					  + "  </view>\n"
					  + "</feature>";
		// @formatter:on

		assertEquals(featureXml, result);

		view = new View();
		view.setName("ViewElement");

		SecondFeature video = new SecondFeature();
		
		video.setView(view);
		video.addElement(element);

		featureXml = converter.toXML(video);
		// @formatter:off
		result = "<feature supported=\"second\">\n"
					  + "  <view name=\"ViewElement\">\n"
					  + "    <element extra=\"true\" name=\"TextElement\"/>\n"
					  + "  </view>\n"
					  + "</feature>";
		// @formatter:on

		assertEquals(featureXml, result);

	}

	@Test
	void sucessWhenConvertToJava_Feature() {

		// @formatter:off
		String result = "<feature supported=\"first\">\n"
					  + "  <view name=\"ViewElement\">\n"
					  + "    <text extra=\"true\" name=\"TextElement\"/>\n"
					  + "  </view>\n"
					  + "</feature>";
		// @formatter:on

		FeatureElement featureObj = converter.fromXML(result);

		assertNotNull(featureObj);
		assertEquals(featureObj.getSupported(), "first");
		assertInstanceOf(Feature.class, featureObj);

		// @formatter:off
		result = "<feature supported=\"second\">\n"
			   + "  <view name=\"ViewElement\">\n"
			   + "    <text extra=\"true\" name=\"TextElement\"/>\n"
			   + "  </view>\n"
			   + "</feature>";
		// @formatter:on

		featureObj = converter.fromXML(result);

		assertNotNull(featureObj);
		assertEquals(featureObj.getSupported(), "second");
		assertInstanceOf(SecondFeature.class, featureObj);

	}

}
