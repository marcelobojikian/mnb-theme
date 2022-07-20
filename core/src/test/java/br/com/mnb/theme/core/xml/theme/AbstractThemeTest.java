package br.com.mnb.theme.core.xml.theme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;

import br.com.mnb.theme.core.factory.InstanceFactory;
import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.model.SecondElement;
import br.com.mnb.theme.core.model.Theme;
import br.com.mnb.theme.core.model.View;
import br.com.mnb.theme.core.xml.Content;
import br.com.mnb.theme.core.xml.converter.ContentXStreamConverter;
import br.com.mnb.theme.core.xml.converter.ElementXStreamConverter;
import br.com.mnb.theme.core.xml.element.ElementConverter;
import br.com.mnb.theme.core.xml.view.ViewElement;

class AbstractThemeTest {
	
	XStream xstream;
	
	@BeforeEach
	public void setup() {

		ElementConverter converter = new ElementConverter(new InstanceFactory());
		converter.registerElement("element", Element.class);
		converter.registerElement("second", SecondElement.class);
		
		ElementXStreamConverter xmlConverter = new ElementXStreamConverter(converter);

		xstream = new XStream();

		xstream.autodetectAnnotations(true);
		xstream.ignoreUnknownElements();

		xstream.registerConverter(new ContentXStreamConverter());
		xstream.registerConverter(xmlConverter);

		xstream.processAnnotations(View.class);
		xstream.processAnnotations(Element.class);
		xstream.processAnnotations(SecondElement.class);
		xstream.processAnnotations(Content.class);
		
		xstream.addPermission(NoTypePermission.NONE);
		
		xstream.allowTypes(new Class[] {
				View.class,
				Element.class,
				SecondElement.class,
				Content.class });
		
		xstream.processAnnotations(Theme.class);
		xstream.allowTypes(new Class[] { Theme.class });
		
	}

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

		ThemeElement themeObj = (ThemeElement) xstream.fromXML(result);

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
