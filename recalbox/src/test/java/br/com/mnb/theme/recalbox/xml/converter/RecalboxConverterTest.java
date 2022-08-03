package br.com.mnb.theme.recalbox.xml.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mnb.theme.core.xml.feature.AbstractFeature;
import br.com.mnb.theme.core.xml.view.View;
import br.com.mnb.theme.recalbox.xml.feature.CarouselFeature;
import br.com.mnb.theme.recalbox.xml.theme.RecalboxTheme;

class RecalboxConverterTest {

	RecalboxConverter converter;
	
	@BeforeEach
	public void setup() {
		converter = new RecalboxConverter();
	}

	@Test
	public void sucessWhenCreateXml_ThemeWithOneView() {

		RecalboxTheme theme = new RecalboxTheme();

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

		RecalboxTheme theme = new RecalboxTheme();

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

		RecalboxTheme theme = new RecalboxTheme();

		AbstractFeature feature = new CarouselFeature();

		theme.addFeatures(feature);

		String contentXml = converter.toXML(theme);
		// @formatter:off
		String result = "<theme>\n"
					  + "  <feature supported=\"carousel\"/>\n"
					  + "</theme>";
		// @formatter:on

		assertEquals(contentXml, result);

	}

	@Test
	public void sucessWhenCreateXml_ThemeWithTwoFeature() {

		RecalboxTheme theme = new RecalboxTheme();

		AbstractFeature feature = new CarouselFeature();

		theme.addFeatures(feature);

		String contentXml = converter.toXML(theme);
		// @formatter:off
		String result = "<theme>\n"
					  + "  <feature supported=\"carousel\"/>\n"
					  + "</theme>";
		// @formatter:on

		assertEquals(contentXml, result);

	}

	@Test
	public void sucessWhenFromXml() {
		// @formatter:off
		String xml = "<theme>\n"
				  + "  <view name=\"ViewElement\"/>\n"
				  + "  <view name=\"SecondViewElement\"/>\n"
				  + "  <feature class=\"feature\" supported=\"carousel\"/>\n"
				  + "</theme>";
		// @formatter:on
		RecalboxTheme theme = converter.fromXML(xml);
		assertNotNull(theme);
	}

	@Test
	public void sucessWhenFromXmlComplex() {
		// @formatter:off
		String xml =  "<theme>\n"
					+ "  <formatVersion>4</formatVersion>\n"
					+ "  <view name=\"system, basic, detailed, video\">\n"
					+ "    <image extra=\"true\" name=\"background_all\"/>\n"
					+ "    <helpsystem name=\"help\"/>\n"
					+ "  </view>\n"
					+ "  <view name=\"basic, detailed, video\">\n"
					+ "    <image name=\"logo\"/>\n"
					+ "    <image extra=\"true\" name=\"help_seperator\"/>\n"
					+ "  </view>\n"
					+ "  <view name=\"basic\">\n"
					+ "    <textlist name=\"gamelist\"/>\n"
					+ "  </view>\n"
					+ "  <view name=\"detailed, video\">\n"
					+ "    <textlist name=\"gamelist\"/>\n"
					+ "    <image extra=\"true\" name=\"bezel\"/>\n"
					+ "    <text name=\"md_description,md_lbl_rating, md_lbl_releasedate\"/>\n"
					+ "    <text name=\"md_lbl_rating\"/>\n"
					+ "    <rating name=\"md_rating\"/>\n"
					+ "  </view>\n"
					+ "  <feature supported=\"carousel\">\n"
					+ "    <view name=\"system\">\n"
					+ "      <carousel name=\"systemcarousel\">\n"
					+ "        <TESTE__123>true</TESTE__123>\n"
					+ "      </carousel>\n"
					+ "      <text name=\"logoText\"/>\n"
					+ "      <image name=\"seperator\"/>\n"
					+ "      <text name=\"systemInfo\"/>\n"
					+ "    </view>\n"
					+ "  </feature>\n"
					+ "</theme>";
		// @formatter:on
		RecalboxTheme theme = converter.fromXML(xml);
		assertNotNull(theme);
	}

	@Test
	public void sucessWhenFromXmlFile() throws FileNotFoundException {
		File xml = new File(this.getClass().getResource("RecalboxFileTest.xml").getFile());
		RecalboxTheme theme = converter.fromXML(xml);
		assertNotNull(theme);
	}

}
