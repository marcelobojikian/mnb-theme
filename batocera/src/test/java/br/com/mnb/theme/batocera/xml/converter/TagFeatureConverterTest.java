package br.com.mnb.theme.batocera.xml.converter;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;

import br.com.mnb.theme.batocera.xml.element.Text;
import br.com.mnb.theme.batocera.xml.feature.AbstractFeature;
import br.com.mnb.theme.batocera.xml.feature.CarouselFeature;
import br.com.mnb.theme.batocera.xml.view.View;
import br.com.mnb.theme.batocera.xml.xstream.configure.TagFeatureConfigure;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;

class TagFeatureConverterTest {
	
	TagFeatureConverter converter;
	
	NamedTagConverter<AbstractElement> mockElementConverter;
	NamedTagConverter<AbstractViewElement> mockViewConverter;
	NamedTagConverter<AbstractFeature> mockFeatureConverter;
	TagFeatureConfigure mockConfigure;
	
	@BeforeEach
	@SuppressWarnings("unchecked")
	public void setup() {
		XStream xstream = mock(XStream.class);
		mockFeatureConverter = mock(NamedTagConverter.class);
		mockViewConverter = mock(NamedTagConverter.class);
		mockElementConverter = mock(NamedTagConverter.class);
		mockConfigure = mock(TagFeatureConfigure.class, withSettings().useConstructor(mockFeatureConverter, mockViewConverter, mockElementConverter));
		
		when(mockConfigure.getXStream()).thenReturn(xstream);
	}
	
	@Test
	public void sucessWhenUseDefaultConstructor() {
		converter = new TagFeatureConverter();
		assertNotNull(converter);
		assertNotNull(converter.getXStream());

		assertNotNull(converter.getConfigure());
		assertInstanceOf(TagFeatureConfigure.class, converter.getConfigure());
	}
	
	@Test
	public void sucessWhenUseConstructorWithCustomNamedTagConverter() {
		converter = new TagFeatureConverter(mockFeatureConverter, mockViewConverter, mockElementConverter);
		assertNotNull(converter);
		assertNotNull(converter.getXStream());

		assertNotNull(converter.getConfigure());
		assertInstanceOf(TagFeatureConfigure.class, converter.getConfigure());
	}
	
	@Test
	public void sucessWhenUseConstructorWithCustomConfigure() {
		converter = new TagFeatureConverter(mockConfigure);
		assertNotNull(converter);
		assertNotNull(converter.getXStream());

		assertNotNull(converter.getConfigure());
		assertInstanceOf(TagFeatureConfigure.class, converter.getConfigure());
		assertSame(converter.getConfigure(), mockConfigure);
	}

	@Test
	public void sucessWhenAddView() {
		converter = spy(new TagFeatureConverter(mockConfigure));
		assertSame(converter.getConfigure(), mockConfigure);
		
		converter.addView("view", View.class);
		
		verify(converter, times(1)).notifyChange();
		verify(mockConfigure, times(1)).addView("view", View.class);
	}

	@Test
	public void sucessWhenAddElement() {
		converter = spy(new TagFeatureConverter(mockConfigure));
		assertSame(converter.getConfigure(), mockConfigure);
		
		converter.addElement("text", Text.class);
		
		verify(converter, times(1)).notifyChange();
		verify(mockConfigure, times(1)).addElement("text", Text.class);
	}

	@Test
	public void sucessWhenElementToXML() {
		converter = spy(new TagFeatureConverter(mockConfigure));
		
		CarouselFeature feature = new CarouselFeature();
		converter.toXML(feature);

		verify(converter.getXStream(), times(1)).toXML(feature);
	}

	@Test
	public void sucessWhenElementFromXml() {	
		converter = spy(new TagFeatureConverter(mockConfigure));
		
		String xml = "<feature supported=\"carousel\"/>";
		converter.fromXML(xml);

		verify(converter.getXStream(), times(1)).fromXML(xml);
	}

}
