package br.com.mnb.theme.batocera.xml.theme;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.mnb.theme.batocera.xml.converter.BatoceraXmlConverter;
import br.com.mnb.theme.batocera.xml.feature.AbstractFeature;
import br.com.mnb.theme.batocera.xml.feature.CarouselFeature;
import br.com.mnb.theme.batocera.xml.feature.VideoFeature;
import br.com.mnb.theme.batocera.xml.view.View;

class BatoceraThemeTest {

	BatoceraXmlConverter converter = new BatoceraXmlConverter();

	@Test
	public void sucessWhenCreateXml_ThemeWithOneView() {

		BatoceraTheme theme = new BatoceraTheme();

		View view = new View();
		view.setName("ViewElement");

		theme.addViews(view);

		String contentXml = converter.toXML(theme);
		// @formatter:off
		String result = "<theme>\n"
					  + "  <view name=\"ViewElement\"/>\n"
					  + "</theme>";
		// @formatter:on

		assertEquals(contentXml, result);

	}

	@Test
	public void sucessWhenCreateXml_ThemeWithTwoView() {

		BatoceraTheme theme = new BatoceraTheme();

		View view = new View();
		view.setName("ViewElement");

		View viewSecond = new View();
		viewSecond.setName("SecondViewElement");

		theme.addViews(view, viewSecond);

		String contentXml = converter.toXML(theme);
		// @formatter:off
		String result = "<theme>\n"
					  + "  <view name=\"ViewElement\"/>\n"
					  + "  <view name=\"SecondViewElement\"/>\n"
					  + "</theme>";
		// @formatter:on

		assertEquals(contentXml, result);

	}

	@Test
	public void sucessWhenCreateXml_ThemeWithOneFeature() {

		BatoceraTheme theme = new BatoceraTheme();

		AbstractFeature feature = new CarouselFeature();

		theme.addFeatures(feature);

		String contentXml = converter.toXML(theme);
		// @formatter:off
		String result = "<theme>\n"
					  + "  <feature class=\"feature\" supported=\"carousel\"/>\n"
					  + "</theme>";
		// @formatter:on

		assertEquals(contentXml, result);

	}

	@Test
	public void sucessWhenCreateXml_ThemeWithTwoFeature() {

		BatoceraTheme theme = new BatoceraTheme();

		AbstractFeature feature = new CarouselFeature();
		AbstractFeature featureSecond = new VideoFeature();

		theme.addFeatures(feature, featureSecond);

		String contentXml = converter.toXML(theme);
		// @formatter:off
		String result = "<theme>\n"
					  + "  <feature class=\"feature\" supported=\"carousel\"/>\n"
					  + "  <feature class=\"feature\" supported=\"video\"/>\n"
					  + "</theme>";
		// @formatter:on

		assertEquals(contentXml, result);

	}

}
