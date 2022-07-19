package br.com.mnb.theme.core.xml.theme;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import br.com.mnb.theme.core.model.Theme;
import br.com.mnb.theme.core.model.View;
import br.com.mnb.theme.core.xml.converter.ThemeXmlConverter;
import br.com.mnb.theme.core.xml.view.ViewElement;

class AbstractThemeTest {

	ThemeXmlConverter xstream = new ThemeXmlConverter();

	@Test
	public void whenCreateTheme_DefaultValues() {

		Theme theme = new Theme();

		assertNull(theme.getFormatVersion());
		assertTrue(theme.getViewElements().isEmpty());
		assertTrue(theme.getIncludes().isEmpty());

	}

	@Test
	public void sucessWhenCreateXml() {

		Theme theme = new Theme();

		String contentXml = xstream.toXML(theme);
		String result = "<theme/>";

		assertEquals(contentXml, result);

	}

	@Test
	public void sucessWhenCreateXml_ViewWithFormatVersion() {

		Theme theme = new Theme();
		theme.setFormatVersion(4);

		String contentXml = xstream.toXML(theme);
		// @formatter:off
		String result = "<theme>\n"
					  + "  <formatVersion>4</formatVersion>\n"
					  + "</theme>";
		// @formatter:on

		assertEquals(contentXml, result);

	}

	@Test
	public void sucessWhenCreateXml_ThemeWithOneView() {

		Theme theme = new Theme();

		View view = new View();
		view.setName("ViewElement");

		theme.getViewElements().add(view);

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

		Theme theme = new Theme();

		View firstView = new View();
		firstView.setName("ViewElement");

		View secondView = new View();
		secondView.setName("SecondViewElement");

		theme.setViewElements(Arrays.asList(firstView, secondView));

		String contentXml = xstream.toXML(theme);
		// @formatter:off
		String result = "<theme>\n"
					  + "  <view name=\"ViewElement\"/>\n"
					  + "  <view name=\"SecondViewElement\"/>\n"
					  + "</theme>";
		// @formatter:on

		assertEquals(contentXml, result);

	}

	@Test
	public void sucessWhenCreateXml_ThemeWithOneInclude() {

		Theme theme = new Theme();

		theme.getIncludes().add("IncludeName");

		String contentXml = xstream.toXML(theme);
		// @formatter:off
		String result = "<theme>\n"
					  + "  <include>IncludeName</include>\n"
					  + "</theme>";
		// @formatter:on

		assertEquals(contentXml, result);

	}

	@Test
	public void sucessWhenCreateXml_ThemeWithTwoInclude() {

		Theme theme = new Theme();

		theme.setIncludes(Arrays.asList("IncludeName", "SecondIncludeName"));

		String contentXml = xstream.toXML(theme);
		// @formatter:off
		String result = "<theme>\n"
					  + "  <include>IncludeName</include>\n"
					  + "  <include>SecondIncludeName</include>\n"
					  + "</theme>";
		// @formatter:on

		assertEquals(contentXml, result);

	}

	@Test
	void sucessWhenConvertToJava_Theme() {

		// @formatter:off
		String result = "<theme>\n"
					  + "  <formatVersion>4</formatVersion>\n"
					  + "  <include>Include One</include>\n"
					  + "  <include>Include Two</include>\n"
					  + "  <view name=\"ViewOne\"/>\n"
					  + "  <view name=\"ViewTwo\"/>\n"
					  + "</theme>";
		// @formatter:on

		ThemeElement themeObj = xstream.fromXML(result);

		assertNotNull(themeObj);
		assertEquals(themeObj.getFormatVersion(), 4);

		ViewElement viewOne = themeObj.getViewElements().get(0);
		assertInstanceOf(View.class, viewOne);
		assertEquals(viewOne.getName(), "ViewOne");

		ViewElement viewTwo = themeObj.getViewElements().get(1);
		assertInstanceOf(View.class, viewTwo);
		assertEquals(viewTwo.getName(), "ViewTwo");

	}


}
