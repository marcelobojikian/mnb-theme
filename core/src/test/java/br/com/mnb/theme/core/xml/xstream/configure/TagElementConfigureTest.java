package br.com.mnb.theme.core.xml.xstream.configure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;

import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;

class TagElementConfigureTest {
	
	XStream xstream;
	
	@BeforeEach
	public void setup() {
		NamedTagConverter<AbstractElement> converter = new NamedTagConverter<AbstractElement>();
		TagElementConfigure configure = new TagElementConfigure(converter);
		configure.addElement("element", Element.class);
		xstream = configure.getXStream();
	}

	@Test
	@DisplayName("Should convert to XML")
	public void sucessWhenConvertToXml() {		
		String resultExpected = "<element name=\"tagname\"/>";
		
		Element element = new Element();
		element.setName("tagname");
		
		String result = xstream.toXML(element);
		assertEquals(resultExpected, result);
	}

	@Test
	@DisplayName("Should convert to Java")
	public void sucessWhenConvertToJava() {
		Element element = (Element) xstream.fromXML("<element name=\"tagname\"/>");
		assertNotNull(element);
		assertEquals("tagname", element.getName());
		assertFalse(element.isExtra());
	}

}
