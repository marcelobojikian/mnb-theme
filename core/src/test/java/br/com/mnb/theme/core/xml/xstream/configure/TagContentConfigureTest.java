package br.com.mnb.theme.core.xml.xstream.configure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;

import br.com.mnb.theme.core.xml.Content;

class TagContentConfigureTest {
	
	final TagContentConfigure configure = new TagContentConfigure();
	XStream xstream;
	
	@BeforeEach
	public void setup() {
		xstream = configure.getXStream();
	}

	@Test
	@DisplayName("Should convert to XML")
	public void sucessWhenConvertToXml() {		
		String resultExpected = "<"+TagContentConfigure.CONTENT_ELEMENT_NAME+"/>";
		String result = xstream.toXML(new Content());
		assertEquals(resultExpected, result);
	}

	@Test
	@DisplayName("Should convert to Java")
	public void sucessWhenConvertToJava() {
		Content content = (Content) xstream.fromXML("<"+TagContentConfigure.CONTENT_ELEMENT_NAME+"/>");
		assertNotNull(content);
		assertTrue(content.getAttributes().isEmpty());
	}

}
