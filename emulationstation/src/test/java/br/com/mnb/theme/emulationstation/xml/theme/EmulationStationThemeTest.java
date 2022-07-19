package br.com.mnb.theme.emulationstation.xml.theme;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.mnb.theme.emulationstation.xml.converter.ThemeXmlConverter;
import br.com.mnb.theme.emulationstation.xml.view.View;

class EmulationStationThemeTest {

	ThemeXmlConverter xstream = new ThemeXmlConverter();

	@Test
	public void sucessWhenCreateXml_ThemeWithOneView() {

		EmulationStationTheme theme = new EmulationStationTheme();

		View view = new View();
		view.setName("ViewElement");

		theme.addViews(view);

		String contentXml = xstream.toXML(theme);
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

		String contentXml = xstream.toXML(theme);
		// @formatter:off
		String result = "<theme>\n"
					  + "  <view name=\"ViewElement\"/>\n"
					  + "  <view name=\"SecondViewElement\"/>\n"
					  + "</theme>";
		// @formatter:on

		assertEquals(contentXml, result);

	}

}
