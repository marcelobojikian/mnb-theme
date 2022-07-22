package br.com.mnb.theme.core.xml.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.model.View;
import br.com.mnb.theme.core.xml.converter.ViewXmlConverter;
import br.com.mnb.theme.core.xml.element.CommonElement;

class AbstractViewElementTest {

	ViewXmlConverter converter = new ViewXmlConverter();

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

		String contentXml = converter.toXML(view);
		String result = "<view name=\"View\"/>";

		assertEquals(contentXml, result);

	}

	@Test
	public void sucessWhenCreateXml_ViewWithOneElement() {

		Element element = new Element();
		element.setName("TextElement");

		View view = new View();
		view.setName("ViewElement");

		view.getElements().add(element);

		String viewXml = converter.toXML(view);
		// @formatter:off
		String result = "<view name=\"ViewElement\">\n"
					  + "  <element name=\"TextElement\"/>\n"
					  + "</view>";
		// @formatter:on

		assertEquals(viewXml, result);

	}

	@Test
	public void sucessWhenCreateXml_ViewWithManyElements() {

		Element firstElement = new Element();
		firstElement.setName("TextElement");

		Element secondElement = new Element();
		secondElement.setName("ImageElement");

		View view = new View();
		view.setName("ViewElement");

		view.setElements(Arrays.asList(firstElement, secondElement));

		String viewXml = converter.toXML(view);
		// @formatter:off
		String result = "<view name=\"ViewElement\">\n"
					  + "  <element name=\"TextElement\"/>\n"
					  + "  <element name=\"ImageElement\"/>\n"
					  + "</view>";
		// @formatter:on

		assertEquals(viewXml, result);

	}

	@Test
	public void sucessWhenCreateXml_ViewWithExtraElements() {

		Element element = new Element();
		element.setName("TextElement");
		element.setExtra(true);

		View view = new View();
		view.setName("ViewElement");

		view.getElements().add(element);

		String viewXml = converter.toXML(view);
		// @formatter:off
		String result = "<view name=\"ViewElement\">\n"
					  + "  <element extra=\"true\" name=\"TextElement\"/>\n"
					  + "</view>";
		// @formatter:on

		assertEquals(viewXml, result);

	}

	@Test
	void sucessWhenConvertToJava_View() {

		// @formatter:off
		String result = "<view name=\"ViewElement\">\n"
				  	  + "  <element name=\"TextElement\"/>\n"
				  	  + "  <element name=\"ImageElement\"/>\n"
				  	  + "</view>";
		// @formatter:on

		View viewObj = (View) converter.fromXML(result);

		assertNotNull(viewObj);
		assertEquals(viewObj.getName(), "ViewElement");

		CommonElement first = viewObj.getElements().get(0);
		CommonElement second = viewObj.getElements().get(1);

		assertInstanceOf(Element.class, first);
		assertInstanceOf(Element.class, second);

	}


}
