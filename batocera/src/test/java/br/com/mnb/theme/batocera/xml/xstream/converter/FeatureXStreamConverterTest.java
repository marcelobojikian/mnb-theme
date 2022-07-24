package br.com.mnb.theme.batocera.xml.xstream.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import br.com.mnb.theme.batocera.xml.feature.AbstractFeature;
import br.com.mnb.theme.batocera.xml.feature.CarouselFeature;
import br.com.mnb.theme.batocera.xml.feature.VideoFeature;
import br.com.mnb.theme.batocera.xml.view.View;
import br.com.mnb.theme.core.xml.tag.TagConverter;

class FeatureXStreamConverterTest {

	FeatureXStreamConverter converter;

	HierarchicalStreamWriter mockHierarchicalStreamWriter;
	MarshallingContext mockMarshallingContext;
	HierarchicalStreamReader mockHierarchicalStreamReader;
	UnmarshallingContext mockUnmarshallingContext;
	TagConverter<AbstractFeature, String> mockTagConverter;

	@BeforeEach
	@SuppressWarnings("unchecked")
	public void setup() {
		mockHierarchicalStreamWriter = mock(HierarchicalStreamWriter.class);
		mockMarshallingContext = mock(MarshallingContext.class);
		mockHierarchicalStreamReader = mock(HierarchicalStreamReader.class);
		mockUnmarshallingContext = mock(UnmarshallingContext.class);
		
		mockTagConverter = mock(TagConverter.class);
		
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

		CarouselFeature feature = new CarouselFeature();
		
		converter.marshal(feature, mockHierarchicalStreamWriter, mockMarshallingContext);
		verify(mockHierarchicalStreamWriter, times(1)).addAttribute("supported", feature.getSupported());
		verify(mockHierarchicalStreamWriter, times(0)).startNode("view");
		verify(mockHierarchicalStreamWriter, times(0)).endNode();
		verify(mockMarshallingContext, times(0)).convertAnother(feature.getView());
		
	}

	@Test
	@DisplayName("Should call StreamWriter with view")
	public void shouldCallStreamWriterWithView() {

		CarouselFeature feature = new CarouselFeature();
		feature.setView(new View());
		
		converter.marshal(feature, mockHierarchicalStreamWriter, mockMarshallingContext);
		verify(mockHierarchicalStreamWriter, times(1)).addAttribute("supported", feature.getSupported());
		verify(mockHierarchicalStreamWriter, times(1)).startNode("view");
		verify(mockHierarchicalStreamWriter, times(1)).endNode();
		verify(mockMarshallingContext, times(1)).convertAnother(feature.getView());
		
	}

	@Test
	@DisplayName("Should call methods StreamReader return CarouselFeature")
	public void shouldCallMethodsStreamReaderReturnCarouselFeature() {
		
		CarouselFeature carousel = new CarouselFeature();
		
		when(mockHierarchicalStreamReader.getAttribute("supported")).thenReturn(carousel.getSupported());
		when(mockTagConverter.toComponent(anyString())).thenReturn(carousel);
		when(mockUnmarshallingContext.convertAnother(carousel, View.class)).thenReturn(new View());
		
		Object obj = converter.unmarshal(mockHierarchicalStreamReader, mockUnmarshallingContext);

		assertNotNull(obj);
		assertInstanceOf(CarouselFeature.class, obj);
		
		CarouselFeature feature = (CarouselFeature) obj;
		assertEquals(feature.getSupported(), "carousel");
		assertNotNull(feature.getView());
		
	}

}
