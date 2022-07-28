package br.com.mnb.theme.core.xml.xstream.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.xml.Content;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.tag.TagConverter;

@ExtendWith(MockitoExtension.class)
class ElementXstreamConverterTest {

	ElementXstreamConverter converter;

	@Mock
	HierarchicalStreamWriter mockHierarchicalStreamWriter;
	@Mock
	MarshallingContext mockMarshallingContext;
	@Mock
	HierarchicalStreamReader mockHierarchicalStreamReader;
	@Mock
	UnmarshallingContext mockUnmarshallingContext;
	@Mock
	TagConverter<AbstractElement, String> mockTagConverter;

	@BeforeEach
	public void setup() {
		converter = new ElementXstreamConverter(mockTagConverter);		
	}

	@Test
	@DisplayName("Should allow Element")
	public void shouldAllowElement() {
		assertTrue(converter.canConvert(AbstractElement.class));
	}

	@Test
	@DisplayName("Should not allow other classes")
	public void shouldNotAllowOtherClasses() {
		assertFalse(converter.canConvert(String.class));
	}

	@Test
	@DisplayName("Should call StreamWriter without element extra")
	public void shouldCallStreamWriterWithoutElementExtra() {

		Element element = new Element();
		element.setName("");
		element.setExtra(false);
		
		converter.marshal(element, mockHierarchicalStreamWriter, mockMarshallingContext);
		verify(mockHierarchicalStreamWriter, times(1)).addAttribute("name", element.getName());
		verify(mockMarshallingContext, times(1)).convertAnother(element.getContent());
		
	}

	@Test
	@DisplayName("Should call StreamWriter with element extra")
	public void shouldCallStreamWriterWithElementExtra() {

		Element element = new Element();
		element.setName("");
		element.setExtra(true);
		
		converter.marshal(element, mockHierarchicalStreamWriter, mockMarshallingContext);
		verify(mockHierarchicalStreamWriter, times(1)).addAttribute("extra", "true");
		verify(mockHierarchicalStreamWriter, times(1)).addAttribute("name", element.getName());
		verify(mockMarshallingContext, times(1)).convertAnother(element.getContent());
		
	}

	@Test
	@DisplayName("Should call methods StreamReader return Element")
	public void shouldCallMethodsStreamReaderReturnElement() {
		
		when(mockHierarchicalStreamReader.getNodeName()).thenReturn("");
		when(mockHierarchicalStreamReader.getAttribute("name")).thenReturn("newName");
		when(mockHierarchicalStreamReader.getAttribute("extra")).thenReturn("false");
		
		Element elementResult = new Element();
		when(mockTagConverter.toComponent("")).thenReturn(elementResult);
		when(mockUnmarshallingContext.convertAnother(elementResult, Content.class)).thenReturn(new Content());
		
		
		Element obj = (Element) converter.unmarshal(mockHierarchicalStreamReader, mockUnmarshallingContext);

		assertNotNull(obj);
		assertInstanceOf(Element.class, obj);
		assertEquals(obj.getName(), "newName");
		assertFalse(obj.isExtra());
		
	}

}
