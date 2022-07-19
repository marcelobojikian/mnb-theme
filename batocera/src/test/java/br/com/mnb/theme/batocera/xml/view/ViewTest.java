package br.com.mnb.theme.batocera.xml.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import br.com.mnb.theme.batocera.xml.converter.ViewXmlConverter;
import br.com.mnb.theme.batocera.xml.element.Image;
import br.com.mnb.theme.batocera.xml.element.Text;
import br.com.mnb.theme.core.xml.element.CommonElement;

class ViewTest {

	ViewXmlConverter xstream = new ViewXmlConverter();

	@Test
	public void whenCreateView_DefaultValues() {

		View view = new View();

		assertNull(view.getName());
		assertTrue(view.getElements().isEmpty());

	}

	@Test
	public void whenConvertElement_OnlyWithName() {

		View view = new View();
		view.setName("View");

		String contentXml = xstream.toXML(view);
		String result = "<view name=\"View\"/>";

		assertEquals(contentXml, result);

	}

	@Test
	public void sucessWhenCreateXml_ViewWithOneElement() {

		Text text = new Text();
		text.setName("TextElement");

		View view = new View();
		view.setName("ViewElement");

		view.addElement(text);

		String viewXml = xstream.toXML(view);
		// @formatter:off
		String result = "<view name=\"ViewElement\">\n"
					  + "  <text name=\"TextElement\"/>\n"
					  + "</view>";
		// @formatter:on

		assertEquals(viewXml, result);

	}

	@Test
	public void sucessWhenCreateXml_ViewWithManyElements() {

		Text text = new Text();
		text.setName("TextElement");

		Image image = new Image();
		image.setName("ImageElement");

		View view = new View();
		view.setName("ViewElement");

		view.addElements(text, image);

		String viewXml = xstream.toXML(view);
		// @formatter:off
		String result = "<view name=\"ViewElement\">\n"
					  + "  <text name=\"TextElement\"/>\n"
					  + "  <image name=\"ImageElement\"/>\n"
					  + "</view>";
		// @formatter:on

		assertEquals(viewXml, result);

	}

	@Test
	public void sucessWhenCreateXml_ViewWithExtraElements() {

		Text text = new Text();
		text.setName("TextElement");
		text.setExtra(true);

		View view = new View();
		view.setName("ViewElement");

		view.addElement(text);

		String viewXml = xstream.toXML(view);
		// @formatter:off
		String result = "<view name=\"ViewElement\">\n"
					  + "  <text extra=\"true\" name=\"TextElement\"/>\n"
					  + "</view>";
		// @formatter:on

		assertEquals(viewXml, result);

	}

	@Test
	void sucessWhenConvertToJava_View() {

		// @formatter:off
		String result = "<view name=\"ViewElement\">\n"
				  	  + "  <text name=\"TextElement\"/>\n"
				  	  + "  <image name=\"ImageElement\"/>\n"
				  	  + "</view>";
		// @formatter:on

		View viewObj = xstream.fromXML(result);

		assertNotNull(viewObj);
		assertEquals(viewObj.getName(), "ViewElement");

		CommonElement text = viewObj.getElements().get(0);
		CommonElement image = viewObj.getElements().get(1);

		assertInstanceOf(Text.class, text);
		assertInstanceOf(Image.class, image);

	}

}
