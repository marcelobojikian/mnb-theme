package br.com.mnb.theme.recalbox.xml.feature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;

import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.feature.AbstractFeature;
import br.com.mnb.theme.core.xml.feature.FeatureElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;
import br.com.mnb.theme.core.xml.view.View;
import br.com.mnb.theme.core.xml.xstream.XStreamBuilder;
import br.com.mnb.theme.recalbox.xml.element.Datetime;
import br.com.mnb.theme.recalbox.xml.element.HelpSystem;
import br.com.mnb.theme.recalbox.xml.element.Image;
import br.com.mnb.theme.recalbox.xml.element.Ninepatch;
import br.com.mnb.theme.recalbox.xml.element.Rating;
import br.com.mnb.theme.recalbox.xml.element.RecalboxCarousel;
import br.com.mnb.theme.recalbox.xml.element.Sound;
import br.com.mnb.theme.recalbox.xml.element.Text;
import br.com.mnb.theme.recalbox.xml.element.TextList;
import br.com.mnb.theme.recalbox.xml.element.Video;
import br.com.mnb.theme.recalbox.xml.theme.RecalboxTheme;

public class BatoceraFeatureTest {

	XStream xstream;
	
	@BeforeEach
	public void setup() {
		// @formatter:off
		xstream = XStreamBuilder
				.create()
					.configTheme(RecalboxTheme.class)
					.configContent()
					.configElement(new NamedTagConverter<AbstractElement>())
					.configView(new NamedTagConverter<AbstractViewElement>())
					.configFeature(new NamedTagConverter<AbstractFeature>())
					
					.addAlias("feature", FeatureElement.class)
					.addFeature(CarouselFeature.class)
					
					.addView(View.class)
					.addElement(Text.class)
					.addElement(Image.class)
					.addElement(Datetime.class)
					.addElement(HelpSystem.class)
					.addElement(Ninepatch.class)
					.addElement(Rating.class)
					.addElement(Sound.class)
					.addElement(TextList.class)
					.addElement(Video.class)
					.addElement(RecalboxCarousel.class)
				.build();
		// @formatter:on
	}

	@Test
	public void whenCreateFeature_DefaultValues() {

		CarouselFeature carousel = new CarouselFeature();

		assertNull(carousel.getView());
		assertEquals(carousel.getSupported(), "carousel");
		assertNull(carousel.getView());

	}
	
	@Test
	public void sucessWhenCreateXml_FeatureWithoutView() {
		
		CarouselFeature carousel = new CarouselFeature();
		
		String featureXml = xstream.toXML(carousel);
		String result = "<feature supported=\"carousel\"/>";

		assertEquals(featureXml, result);
		
	}

	@Test
	public void sucessWhenCreateXml_FeatureWithView() {

		View view = new View();
		view.setName("ViewElement");
		
		CarouselFeature carousel = new CarouselFeature();
		carousel.setView(view);

		String featureXml = xstream.toXML(carousel);
		// @formatter:off
		String result = "<feature supported=\"carousel\">\n"
					  + "  <view name=\"ViewElement\"/>\n"
					  + "</feature>";
		// @formatter:on

		assertEquals(featureXml, result);

	}
	
	@Test
	public void errorWhenFeatureAddElement_WithoutView() {
		
		CarouselFeature carousel = new CarouselFeature();

		assertThrows(NullPointerException.class, () -> {
			carousel.addElement(new Text());
		});
		
	}

	@Test
	public void sucessWhenCreateXml_FeatureWithOneElements() {

		CarouselFeature carousel = new CarouselFeature();

		Text text = new Text();
		text.setName("TextElement");

		View view = new View();
		view.setName("ViewElement");

		carousel.setView(view);
		carousel.addElement(text);

		String featureXml = xstream.toXML(carousel);
		// @formatter:off
		String result = "<feature supported=\"carousel\">\n"
					  + "  <view name=\"ViewElement\">\n"
					  + "    <text name=\"TextElement\"/>\n"
					  + "  </view>\n"
					  + "</feature>";
		// @formatter:on

		assertEquals(featureXml, result);
		
	}

	@Test
	public void sucessWhenCreateXml_FeatureWithManyElements() {

		Text text = new Text();
		text.setName("TextElement");

		Image image = new Image();
		image.setName("ImageElement");

		View view = new View();
		view.setName("ViewElement");
		
		CarouselFeature carousel = new CarouselFeature();

		carousel.setView(view);
		carousel.addElements(text, image);

		String featureXml = xstream.toXML(carousel);
		// @formatter:off
		String result = "<feature supported=\"carousel\">\n"
					  + "  <view name=\"ViewElement\">\n"
					  + "    <text name=\"TextElement\"/>\n"
					  + "    <image name=\"ImageElement\"/>\n"
					  + "  </view>\n"
					  + "</feature>";
		// @formatter:on

		assertEquals(featureXml, result);
		
	}

	@Test
	public void sucessWhenCreateXml_FeatureWithExtraElements() {

		Text text = new Text();
		text.setName("TextElement");
		text.setExtra(true);

		View view = new View();
		view.setName("ViewElement");

		CarouselFeature carousel = new CarouselFeature();
		
		carousel.setView(view);
		carousel.addElement(text);

		String featureXml = xstream.toXML(carousel);
		// @formatter:off
		String result = "<feature supported=\"carousel\">\n"
					  + "  <view name=\"ViewElement\">\n"
					  + "    <text extra=\"true\" name=\"TextElement\"/>\n"
					  + "  </view>\n"
					  + "</feature>";
		// @formatter:on

		assertEquals(featureXml, result);

		view = new View();
		view.setName("ViewElement");

	}

	@Test
	void sucessWhenConvertToJava_Feature() {

		// @formatter:off
		String result = "<feature supported=\"carousel\">\n"
					  + "  <view name=\"ViewElement\">\n"
					  + "    <text extra=\"true\" name=\"TextElement\"/>\n"
					  + "  </view>\n"
					  + "</feature>";
		// @formatter:on

		FeatureElement featureObj = (FeatureElement) xstream.fromXML(result);

		assertNotNull(featureObj);
		assertEquals(featureObj.getSupported(), "carousel");
		assertInstanceOf(CarouselFeature.class, featureObj);

	}

}
