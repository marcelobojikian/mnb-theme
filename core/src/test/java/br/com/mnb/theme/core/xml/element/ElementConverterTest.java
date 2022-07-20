package br.com.mnb.theme.core.xml.element;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mnb.theme.core.factory.ElementFactory;
import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.model.SecondElement;

class ElementConverterTest {

	ElementConverter converter;
	
	@BeforeEach
	public void setup() {
		
		ElementFactory factory = mock(ElementFactory.class);
		when(factory.createElement(Element.class)).thenReturn(new Element());
		when(factory.createElement(SecondElement.class)).thenReturn(new SecondElement());
		
		converter = new ElementConverter(factory);
		converter.registerElement("element", Element.class);
		converter.registerElement("second", SecondElement.class);
	}

	@Test
	void sucessWhenConvertTagNameToElement() {
		
		AbstractElement element = converter.toComponent("element");
		assertNotNull(element);
		assertInstanceOf(Element.class, element);
		
		element = converter.toComponent("second");
		assertNotNull(element);
		assertInstanceOf(SecondElement.class, element);
		
	}

	@Test
	void sucessWhenConvertElementToTagName() {
		
		String result = converter.toString(new Element());
		assertNotNull(result);
		assertEquals("element", result);
		
		result = converter.toString(new SecondElement());
		assertNotNull(result);
		assertEquals("second", result);
		
	}

	@Test
	void failWhenConvertTagNameToElementWithTagUnmapped() {
		assertThrows(IllegalArgumentException.class, () -> {
			converter.toComponent("Other");
		});
	}

	@Test
	void failWhenConvertElementToTagNameWithComponentUnmapped() {
		assertThrows(IllegalArgumentException.class, () -> {
			converter.toString(new UnmappedElement());
		});
	}
	
	class UnmappedElement extends AbstractElement { 
		private static final long serialVersionUID = 1L;
	}

}
