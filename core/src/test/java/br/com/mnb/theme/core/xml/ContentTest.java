package br.com.mnb.theme.core.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.mnb.theme.core.xml.converter.ContentXmlConverter;

public class ContentTest {

	ContentXmlConverter xstream = new ContentXmlConverter();

	@Test
	@DisplayName("Should convert to XML")
	public void sucessWhenConvertToXml() {

		Map<String, String> map = new HashMap<>();
		map.put("key", "value");

		Content content = new Content();
		content.setAttributes(map);

		String contentXml = xstream.toXML(content);
		// @formatter:off
		String result = "<element>\n"
				      + "  <key>value</key>\n"
				      + "</element>";
		// @formatter:on

		assertEquals(contentXml, result);

	}

	@Test
	@DisplayName("Should convert to Java")
	public void sucessWhenConvertToJava() {

		// @formatter:off
		String result = "<element>\n"
				      + "  <key>value</key>\n"
				      + "</element>";
		// @formatter:on

		Content contentObj = xstream.fromXML(result);

		assertNotNull(contentObj);
		assertEquals(contentObj.getAttributes().get("key"), "value");

	}

	@Test
	@DisplayName("Should clone the Content object")
	public void sucessWhenCloneContent() {

		Map<String, String> map = new HashMap<>();
		map.put("key", "value");

		Content content = new Content();
		content.setAttributes(map);

		Content clone = content.clone();

		assertNotSame(content, clone);

	}

}
