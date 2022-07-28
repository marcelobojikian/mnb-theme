package br.com.mnb.theme.emulationstation.xml.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import br.com.mnb.theme.core.xml.view.View;
import br.com.mnb.theme.emulationstation.xml.theme.EmulationStationTheme;

class ESConverterTest {
	
	ESConverter converter = new ESConverter();	

	@Test
	public void sucessWhenCreateXml_ThemeWithOneView() {

		EmulationStationTheme theme = new EmulationStationTheme();

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

		EmulationStationTheme theme = new EmulationStationTheme();

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
	public void sucessWhenFromXml() {
		String xml = "<theme>\n"
				  + "  <view name=\"ViewElement\"/>\n"
				  + "  <view name=\"SecondViewElement\"/>\n"
				  + "</theme>";
		EmulationStationTheme theme = converter.fromXML(xml);
		assertNotNull(theme);
	}

	@Test
	public void sucessWhenFromXmlFile() throws FileNotFoundException {
		File xml = new File(this.getClass().getResource("ESFileTest.xml").getFile());
		EmulationStationTheme theme = converter.fromXML(xml);
		assertNotNull(theme);
	}

}
