package br.com.mnb.theme.core.xml.xstream.converter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import br.com.mnb.theme.core.xml.Content;

@ExtendWith(MockitoExtension.class)
class ContentXstreamConverterTest {

	Converter converter = new ContentXstreamConverter();

	@Mock
	HierarchicalStreamWriter mockHierarchicalStreamWriter;
	@Mock
	MarshallingContext mockMarshallingContext;
	@Mock
	HierarchicalStreamReader mockHierarchicalStreamReader;
	@Mock
	UnmarshallingContext mockUnmarshallingContext;

	@Test
	@DisplayName("Should allow Content")
	public void shouldAllowContent() {
		assertTrue(converter.canConvert(Content.class));
	}

	@Test
	@DisplayName("Should not allow other classes")
	public void shouldNotAllowOtherClasses() {
		assertFalse(converter.canConvert(String.class));
	}

	@Test
	@DisplayName("Should call methods StreamWriter one time")
	public void shouldCallMethodsStreamWriterOneTime() {

		Content content = new Content();
		content.getAttributes().put("key", "value");
		
		converter.marshal(content, mockHierarchicalStreamWriter, mockMarshallingContext);
		verify(mockHierarchicalStreamWriter, times(1)).startNode("key");
		verify(mockHierarchicalStreamWriter, times(1)).setValue("value");
		verify(mockHierarchicalStreamWriter, times(1)).endNode();
		
	}

	@Test
	@DisplayName("Should call methods StreamWriter two time")
	public void shouldCallMethodsStreamWriterTwoTime() {

		Content content = new Content();
		content.getAttributes().put("firstKey", "firstValue");
		content.getAttributes().put("secondKey", "secondValue");
		
		converter.marshal(content, mockHierarchicalStreamWriter, mockMarshallingContext);
		verify(mockHierarchicalStreamWriter, times(1)).startNode("firstKey");
		verify(mockHierarchicalStreamWriter, times(1)).setValue("firstValue");
		verify(mockHierarchicalStreamWriter, times(1)).startNode("secondKey");
		verify(mockHierarchicalStreamWriter, times(1)).setValue("secondValue");
		verify(mockHierarchicalStreamWriter, times(2)).endNode();
		
	}

	@Test
	@DisplayName("Should call methods StreamReader one time with return Content")
	public void shouldCallMethodsStreamReaderOneTimeWithReturnContent() {
		
		when(mockHierarchicalStreamReader.hasMoreChildren()).thenReturn(true, false);
		
		Object obj = converter.unmarshal(mockHierarchicalStreamReader, mockUnmarshallingContext);

		assertNotNull(obj);
		assertInstanceOf(Content.class, obj);
		verify(mockHierarchicalStreamReader, times(2)).hasMoreChildren();
		verify(mockHierarchicalStreamReader, times(1)).moveDown();
		verify(mockHierarchicalStreamReader, times(1)).getNodeName();
		verify(mockHierarchicalStreamReader, times(1)).getValue();
		verify(mockHierarchicalStreamReader, times(1)).moveUp();
		
	}

	@Test
	@DisplayName("Should call methods StreamReader two time with return Content")
	public void shouldCallMethodsStreamReaderTwoTimeWithReturnContent() {
		
		when(mockHierarchicalStreamReader.hasMoreChildren()).thenReturn(true, true, false);
		
		Object obj = converter.unmarshal(mockHierarchicalStreamReader, mockUnmarshallingContext);

		assertNotNull(obj);
		assertInstanceOf(Content.class, obj);
		verify(mockHierarchicalStreamReader, times(3)).hasMoreChildren();
		verify(mockHierarchicalStreamReader, times(2)).moveDown();
		verify(mockHierarchicalStreamReader, times(2)).getNodeName();
		verify(mockHierarchicalStreamReader, times(2)).getValue();
		verify(mockHierarchicalStreamReader, times(2)).moveUp();
		
	}

}
