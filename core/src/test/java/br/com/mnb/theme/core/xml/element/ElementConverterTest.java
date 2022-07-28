package br.com.mnb.theme.core.xml.element;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mnb.theme.core.factory.SimpleFactory;
import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.model.SecondElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;

class ElementConverterTest {

	NamedTagConverter<AbstractElement> converter;
	
	@BeforeEach
	public void setup() {
		
		@SuppressWarnings("unchecked")
		SimpleFactory<AbstractElement> factory = mock(SimpleFactory.class);
		when(factory.create(Element.class)).thenReturn(new Element());
		when(factory.create(SecondElement.class)).thenReturn(new SecondElement());
		
		converter = new NamedTagConverter<AbstractElement>(factory);
		converter.put(Element.class);
		converter.put(SecondElement.class);
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
