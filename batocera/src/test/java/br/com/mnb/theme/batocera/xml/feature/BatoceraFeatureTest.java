package br.com.mnb.theme.batocera.xml.feature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;

import br.com.mnb.theme.batocera.xml.element.BatoceraCarousel;
import br.com.mnb.theme.batocera.xml.element.Datetime;
import br.com.mnb.theme.batocera.xml.element.HelpSystem;
import br.com.mnb.theme.batocera.xml.element.Image;
import br.com.mnb.theme.batocera.xml.element.Ninepatch;
import br.com.mnb.theme.batocera.xml.element.Rating;
import br.com.mnb.theme.batocera.xml.element.Sound;
import br.com.mnb.theme.batocera.xml.element.Text;
import br.com.mnb.theme.batocera.xml.element.TextList;
import br.com.mnb.theme.batocera.xml.element.Video;
import br.com.mnb.theme.batocera.xml.theme.BatoceraTheme;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.feature.AbstractFeature;
import br.com.mnb.theme.core.xml.feature.FeatureElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;
import br.com.mnb.theme.core.xml.view.View;
import br.com.mnb.theme.core.xml.xstream.XStreamBuilder;

public class BatoceraFeatureTest {

	XStream xstream;
	
	@BeforeEach
	public void setup() {
		// @formatter:off
		xstream = XStreamBuilder
				.create()
					.configTheme(BatoceraTheme.class)
					.configContent()
					.configElement(new NamedTagConverter<AbstractElement>())
					.configView(new NamedTagConverter<AbstractViewElement>())
					.configFeature(new NamedTagConverter<AbstractFeature>())
					
					.addAlias("feature", FeatureElement.class)
					.addFeature(CarouselFeature.class)
					.addFeature(VideoFeature.class)
					
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
					.addElement(BatoceraCarousel.class)
				.build();
		// @formatter:on
	}

	@Test
	public void whenCreateFeature_DefaultValues() {

		CarouselFeature carousel = new CarouselFeature();

		assertNull(carousel.getView());
		assertEquals(carousel.getSupported(), "carousel");
		assertNull(carousel.getView());
		
		VideoFeature video = new VideoFeature();

		assertNull(video.getView());
		assertEquals(video.getSupported(), "video");
		assertNull(video.getView());

	}
	
	@Test
	public void sucessWhenCreateXml_FeatureWithoutView() {
		
		CarouselFeature carousel = new CarouselFeature();
		
		String featureXml = xstream.toXML(carousel);
		String result = "<feature supported=\"carousel\"/>";

		assertEquals(featureXml, result);
		
		VideoFeature video = new VideoFeature();
		
		featureXml = xstream.toXML(video);
		result = "<feature supported=\"video\"/>";

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
		
		VideoFeature video = new VideoFeature();
		video.setView(view);

		featureXml = xstream.toXML(video);
		// @formatter:off
		result = "<feature supported=\"video\">\n"
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
		
		VideoFeature video = new VideoFeature();

		assertThrows(NullPointerException.class, () -> {
			video.addElement(new Text());
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
		
		VideoFeature video = new VideoFeature();

		view = new View();
		view.setName("ViewElement");

		video.setView(view);
		video.addElement(text);

		featureXml = xstream.toXML(video);
		// @formatter:off
		result = "<feature supported=\"video\">\n"
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
		
		VideoFeature video = new VideoFeature();

		view = new View();
		view.setName("ViewElement");

		video.setView(view);
		video.addElements(text, image);

		featureXml = xstream.toXML(video);
		// @formatter:off
		result = "<feature supported=\"video\">\n"
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

		VideoFeature video = new VideoFeature();
		
		video.setView(view);
		video.addElement(text);

		featureXml = xstream.toXML(video);
		// @formatter:off
		result = "<feature supported=\"video\">\n"
					  + "  <view name=\"ViewElement\">\n"
					  + "    <text extra=\"true\" name=\"TextElement\"/>\n"
					  + "  </view>\n"
					  + "</feature>";
		// @formatter:on

		assertEquals(featureXml, result);

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

		// @formatter:off
		result = "<feature supported=\"video\">\n"
			   + "  <view name=\"ViewElement\">\n"
			   + "    <text extra=\"true\" name=\"TextElement\"/>\n"
			   + "  </view>\n"
			   + "</feature>";
		// @formatter:on

		featureObj = (FeatureElement) xstream.fromXML(result);

		assertNotNull(featureObj);
		assertEquals(featureObj.getSupported(), "video");
		assertInstanceOf(VideoFeature.class, featureObj);

	}

}
