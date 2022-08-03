package br.com.mnb.theme.core.xml.element;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;

import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.model.SecondElement;
import br.com.mnb.theme.core.xml.Content;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.xstream.XStreamBuilder;

class AbstractElementTest {

	XStream xstream;
	
	@BeforeEach
	public void setup() {
		// @formatter:off
		xstream = XStreamBuilder
				.create()
					.configContent()
					.configElement(new NamedTagConverter<AbstractElement>())
					.addElement(Element.class)
					.addElement(SecondElement.class)
				.build();
		// @formatter:on
	}

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
	
	@Test
	void sucessWhenCreateXmlElementWithSameNameInContent() {

		Element element = new Element();
		element.setName("nameElement");
		Content content = new Content();
		content.getAttributes().put("element", "123");
		element.setContent(content);

		String elementXml = xstream.toXML(element);
		// @formatter:off
		String result = "<element name=\"nameElement\">\n"
					  + "  <element>123</element>\n"
					  + "</element>";
		// @formatter:on
		
		assertEquals(elementXml, result.toString());
		
	}
	
	@Test
	void successWhenConvertToJavaElementWithSameNameInContent() {

		// @formatter:off
		String result = "<element name=\"nameElement\">\n"
					  + "  <element>123</element>\n"
					  + "</element>";
		// @formatter:on

		CommonElement elementObj = (CommonElement) xstream.fromXML(result.toString());

		assertNotNull(elementObj);
		assertInstanceOf(Element.class, elementObj);
		assertEquals(elementObj.getName(), "nameElement");
		assertFalse(elementObj.isExtra());
		
		Content content = elementObj.getContent();
		assertNotNull(content.getAttributes());
		assertEquals(content.getAttributes().size(),1);
		assertTrue(content.getAttributes().containsKey("element"));
		String value = content.getAttributes().get("element");
		assertEquals(value, "123");
		
	}

}
