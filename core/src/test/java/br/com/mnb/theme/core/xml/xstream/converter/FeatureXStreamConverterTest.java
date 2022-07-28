package br.com.mnb.theme.core.xml.xstream.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
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

import br.com.mnb.theme.core.model.Feature;
import br.com.mnb.theme.core.xml.feature.AbstractFeature;
import br.com.mnb.theme.core.xml.tag.TagConverter;
import br.com.mnb.theme.core.xml.view.View;

@ExtendWith(MockitoExtension.class)
class FeatureXStreamConverterTest {

	FeatureXStreamConverter converter;

	@Mock
	HierarchicalStreamWriter mockHierarchicalStreamWriter;
	@Mock
	MarshallingContext mockMarshallingContext;
	@Mock
	HierarchicalStreamReader mockHierarchicalStreamReader;
	@Mock
	UnmarshallingContext mockUnmarshallingContext;
	@Mock
	TagConverter<AbstractFeature, String> mockTagConverter;

	@BeforeEach
	public void setup() {		
		converter = new FeatureXStreamConverter(mockTagConverter);		
	}

	@Test
	@DisplayName("Should allow Feature")
	public void shouldAllowFeature() {
		assertTrue(converter.canConvert(AbstractFeature.class));
	}

	@Test
	@DisplayName("Should not allow other classes")
	public void shouldNotAllowOtherClasses() {
		assertFalse(converter.canConvert(String.class));
	}

	@Test
	@DisplayName("Should call StreamWriter without view")
	public void shouldCallStreamWriterWithoutView() {

		Feature feature = new Feature();
		
		converter.marshal(feature, mockHierarchicalStreamWriter, mockMarshallingContext);
		verify(mockHierarchicalStreamWriter, times(1)).addAttribute("supported", feature.getSupported());
		verify(mockHierarchicalStreamWriter, times(0)).startNode("view");
		verify(mockHierarchicalStreamWriter, times(0)).endNode();
		verify(mockMarshallingContext, times(0)).convertAnother(feature.getView());
		
	}

	@Test
	@DisplayName("Should call StreamWriter with view")
	public void shouldCallStreamWriterWithView() {

		Feature feature = new Feature();
		feature.setView(new View());
		
		converter.marshal(feature, mockHierarchicalStreamWriter, mockMarshallingContext);
		verify(mockHierarchicalStreamWriter, times(1)).addAttribute("supported", feature.getSupported());
		verify(mockHierarchicalStreamWriter, times(1)).startNode("view");
		verify(mockHierarchicalStreamWriter, times(1)).endNode();
		verify(mockMarshallingContext, times(1)).convertAnother(feature.getView());
		
	}

	@Test
	@DisplayName("Should call methods StreamReader return Feature")
	public void shouldCallMethodsStreamReaderReturnFeature() {
		
		Feature featureResult = new Feature();
		
		when(mockHierarchicalStreamReader.getAttribute("supported")).thenReturn(featureResult.getSupported());
		when(mockTagConverter.toComponent(anyString())).thenReturn(featureResult);
		when(mockUnmarshallingContext.convertAnother(featureResult, View.class)).thenReturn(new View());
		
		Object obj = converter.unmarshal(mockHierarchicalStreamReader, mockUnmarshallingContext);

		assertNotNull(obj);
		assertInstanceOf(Feature.class, obj);
		
		Feature feature = (Feature) obj;
		assertEquals(feature.getSupported(), "first");
		assertNotNull(feature.getView());
		
	}

}
