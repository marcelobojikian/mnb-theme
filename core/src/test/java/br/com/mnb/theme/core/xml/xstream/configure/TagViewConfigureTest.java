package br.com.mnb.theme.core.xml.xstream.configure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;

import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;
import br.com.mnb.theme.core.xml.view.View;

class TagViewConfigureTest {
	
	XStream xstream;
	
	@BeforeEach
	public void setup() {
		NamedTagConverter<AbstractViewElement> viewConverter = new NamedTagConverter<AbstractViewElement>();
		NamedTagConverter<AbstractElement> elementConverter = new NamedTagConverter<AbstractElement>();
		TagViewConfigure configure = new TagViewConfigure(viewConverter, elementConverter);
		configure.addView(View.class);
		xstream = configure.getXStream();
	}

	@Test
	@DisplayName("Should convert to XML")
	public void sucessWhenConvertToXml() {		
		String resultExpected = "<view/>";
		String result = xstream.toXML(new View());
		assertEquals(resultExpected, result);
	}

	@Test
	@DisplayName("Should convert to Java")
	public void sucessWhenConvertToJava() {
		View view = (View) xstream.fromXML("<view/>");
		assertNotNull(view);
		assertEquals(null, view.getName());
	}

}
