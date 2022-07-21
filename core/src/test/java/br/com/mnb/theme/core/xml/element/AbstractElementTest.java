package br.com.mnb.theme.core.xml.element;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.xml.Content;
import br.com.mnb.theme.core.xml.converter.ElementXmlConverter;

class AbstractElementTest {

	ElementXmlConverter xstream = new ElementXmlConverter();

	@Test
	public void whenCreateElement_DefaultValues() {
		
		Element element = new Element();
		
		assertNull(element.getName());
		assertEquals(element.isExtra(), false);
		assertNotNull(element.getContent());
		
	}

	@Test
	public void whenConvertElement_WithoutName() {
		
		Element element = new Element();

		assertThrows(NullPointerException.class, () -> {
			xstream.toXML(element);
		});
		
	}

	@Test
	public void whenConvertElement_OnlyWithName() {
		
		Element element = new Element();
		element.setName("TextElement");
		
		String contentXml = xstream.toXML(element);
		String result = "<element name=\"TextElement\"/>";

		assertEquals(contentXml, result);
		
	}

	@Test
	public void sucessWhenCreateXml_Element() {
		sucessWhenCreateXml(new Element(), "element", true, "TextElement");
	}

	@Test
	void sucessWhenConvertToJava_Element() {
		successWhenConvertToJava(Element.class, "element", true, "TextElement");
	}

	void sucessWhenCreateXml(AbstractElement element, String tagName, boolean attributeExtra,  String attributeName) {

		element.setName(attributeName);
		element.setExtra(attributeExtra);
		element.setContent(new Content());

		String elementXml = xstream.toXML(element);
		
		StringBuilder result = new StringBuilder("<"+tagName);
		if(attributeExtra) {
			result.append(" extra=\"true\"");
		}
		result.append(" name=\""+attributeName+"\"/>");
		
		assertEquals(elementXml, result.toString());
		
	}
	
	void successWhenConvertToJava(Class<? extends CommonElement> clazz, String tagName, boolean attributeExtra, String attributeName) {

		StringBuilder result = new StringBuilder("<"+tagName);
		if(attributeExtra) {
			result.append(" extra=\"true\"");
		}
		result.append(" name=\""+attributeName+"\"/>");

		CommonElement elementObj = (CommonElement) xstream.fromXML(result.toString());

		assertNotNull(elementObj);
		assertInstanceOf(clazz, elementObj);
		assertEquals(elementObj.getName(), attributeName);
		assertTrue(elementObj.isExtra());
		
	}

}