package br.com.mnb.theme.core.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import br.com.mnb.theme.core.builder.ElementBuilder;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.element.CommonElement;

public class ElementTester {
	
	final ElementBuilder builder;
	
	public ElementTester(ElementBuilder builder){
		this.builder = builder;
	}
	
	public <T extends AbstractElement> void testElement(Class<T> clazz) {

		testElement(clazz, "NameElement");
		testElement(clazz, "NameElement", true);
		testElement(clazz, "NameElement", false);
		testElement(clazz, "NameElement", new HashMap<>());
		testElement(clazz, "NameElement", true, new HashMap<>());
		
		Map<String, String> attributes = new HashMap<>();
		attributes.put("key", "value");
		
		testElement(clazz, "NameElement", attributes);
		testElement(clazz, "NameElement", true, attributes);
		
	}
	
	<T extends AbstractElement> void testElement(Class<T> clazz, String nameExpected) {
		AbstractElement element = builder.createElement(clazz, nameExpected);
		assertInstanceOf(clazz, element);
		testElementContent(element, nameExpected, false, null);
	}
	
	<T extends AbstractElement> void testElement(Class<T> clazz, String nameExpected, boolean extraExpected) {
		AbstractElement element = builder.createElement(clazz, nameExpected, extraExpected);
		assertInstanceOf(clazz, element);
		testElementContent(element, nameExpected, extraExpected, null);
	}
	
	<T extends AbstractElement> void testElement(Class<T> clazz, String nameExpected, Map<String, String> attributesExpected) {
		AbstractElement element = builder.createElement(clazz, nameExpected, attributesExpected);
		assertInstanceOf(clazz, element);
		testElementContent(element, nameExpected, false, attributesExpected);
	}
	
	<T extends AbstractElement> void testElement(Class<T> clazz, String nameExpected, boolean extraExpected, Map<String, String> attributesExpected) {
		AbstractElement element = builder.createElement(clazz, nameExpected, extraExpected, attributesExpected);
		assertInstanceOf(clazz, element);
		testElementContent(element, nameExpected, extraExpected, attributesExpected);
	}
	
	public static <T extends CommonElement> void testElementContent(T element, String nameExpected, boolean extraExpected, Map<String, String> attributesExpected) {
		assertNotNull(element);
		assertEquals(nameExpected, element.getName());
		assertEquals(extraExpected, element.isExtra());
		if(!element.getContent().getAttributes().isEmpty()) {
			assertNotNull(attributesExpected);
			Map<String, String> contentAttr = element.getContent().getAttributes();
			Iterator<String> keyIterator = contentAttr.keySet().iterator();
			while (keyIterator.hasNext()) {
				String key = keyIterator.next();
				assertTrue(attributesExpected.containsKey(key));
				assertEquals(attributesExpected.get(key), contentAttr.get(key));
			}
		}
	}

}
