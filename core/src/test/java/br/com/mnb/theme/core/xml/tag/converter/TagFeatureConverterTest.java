package br.com.mnb.theme.core.xml.tag.converter;

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

import br.com.mnb.theme.core.model.Feature;
import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.feature.AbstractFeature;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.tag.converter.TagFeatureConverter;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;
import br.com.mnb.theme.core.xml.view.View;
import br.com.mnb.theme.core.xml.xstream.configure.TagFeatureConfigure;

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
	public void sucessWhenAddElement() {
		converter = spy(new TagFeatureConverter(mockConfigure));
		assertSame(converter.getConfigure(), mockConfigure);
		
		converter.addElement("element", Element.class);
		
		verify(converter, times(1)).notifyChange();
		verify(mockConfigure, times(1)).addElement("element", Element.class);
	}

	@Test
	public void sucessWhenElementToXML() {
		converter = spy(new TagFeatureConverter(mockConfigure));
		
		Feature feature = new Feature();
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
