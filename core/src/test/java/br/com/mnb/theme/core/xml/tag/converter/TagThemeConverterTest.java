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

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;

import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.model.Feature;
import br.com.mnb.theme.core.model.Theme;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.feature.AbstractFeature;
import br.com.mnb.theme.core.xml.feature.FeatureElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;
import br.com.mnb.theme.core.xml.xstream.configure.TagThemeConfigure;

class TagThemeConverterTest {
	
	TagThemeConverter converter;
	
	NamedTagConverter<AbstractElement> mockElementConverter;
	NamedTagConverter<AbstractViewElement> mockViewConverter;
	NamedTagConverter<AbstractFeature> mockFeatureConverter;
	TagThemeConfigure mockConfigure;
	
	@BeforeEach
	@SuppressWarnings("unchecked")
	public void setup() {
		XStream xstream = mock(XStream.class);
		mockElementConverter = mock(NamedTagConverter.class);
		mockViewConverter = mock(NamedTagConverter.class);
		mockFeatureConverter = mock(NamedTagConverter.class);
		mockConfigure = mock(TagThemeConfigure.class, withSettings().useConstructor(mockFeatureConverter, mockViewConverter, mockElementConverter));
		
		when(mockConfigure.getXStream()).thenReturn(xstream);
	}
	
	@Test
	public void sucessWhenUseDefaultConstructor() {
		converter = new TagThemeConverter();
		assertNotNull(converter);
		assertNotNull(converter.getXStream());

		assertNotNull(converter.getConfigure());
		assertInstanceOf(TagThemeConfigure.class, converter.getConfigure());
	}
	
	@Test
	public void sucessWhenUseConstructorWithCustomNamedTagConverter() {
		converter = new TagThemeConverter(mockFeatureConverter, mockViewConverter, mockElementConverter);
		assertNotNull(converter);
		assertNotNull(converter.getXStream());

		assertNotNull(converter.getConfigure());
		assertInstanceOf(TagThemeConfigure.class, converter.getConfigure());
	}
	
	@Test
	public void sucessWhenUseConstructorWithCustomConfigure() {
		converter = new TagThemeConverter(mockConfigure);
		assertNotNull(converter);
		assertNotNull(converter.getXStream());

		assertNotNull(converter.getConfigure());
		assertInstanceOf(TagThemeConfigure.class, converter.getConfigure());
		assertSame(converter.getConfigure(), mockConfigure);
	}

	@Test
	public void sucessWhenSetTheme() {
		converter = spy(new TagThemeConverter(mockConfigure));
		assertSame(converter.getConfigure(), mockConfigure);
		
		converter.setTheme(Theme.class);
		
		verify(converter, times(1)).notifyChange();
		verify(mockConfigure, times(1)).setTheme(Theme.class);
	}

	@Test
	public void sucessWhenAddElement() {
		converter = spy(new TagThemeConverter(mockConfigure));
		assertSame(converter.getConfigure(), mockConfigure);
		
		converter.addElement("element", Element.class);
		
		verify(converter, times(1)).notifyChange();
		verify(mockConfigure, times(1)).addElement("element", Element.class);
	}

	@Test
	public void sucessWhenAddAlias() {
		converter = spy(new TagThemeConverter(mockConfigure));
		assertSame(converter.getConfigure(), mockConfigure);
		
		converter.addAlias("feature", FeatureElement.class);
		
		verify(converter, times(1)).notifyChange();
		verify(mockConfigure, times(1)).addAlias("feature", FeatureElement.class);
	}

	@Test
	public void sucessWhenAddFeature() {
		converter = spy(new TagThemeConverter(mockConfigure));
		assertSame(converter.getConfigure(), mockConfigure);
		
		converter.addFeature("first", Feature.class);
		
		verify(converter, times(1)).notifyChange();
		verify(mockConfigure, times(1)).addFeature("first", Feature.class);
	}

	@Test
	public void sucessWhenElementToXML() {
		converter = spy(new TagThemeConverter(mockConfigure));
		
		Theme theme= new Theme();
		converter.toXML(theme);

		verify(converter.getXStream(), times(1)).toXML(theme);
	}

	@Test
	public void sucessWhenElementFromXml() {	
		converter = spy(new TagThemeConverter(mockConfigure));
		
		String xml = "<theme/>";
		converter.fromXML(xml);

		verify(converter.getXStream(), times(1)).fromXML(xml);
	}

	@Test
	public void sucessWhenElementFromXmlFile() {	
		converter = spy(new TagThemeConverter(mockConfigure));
		
		File xml = new File("TagThemeFileTest.xml");
		converter.fromXML(xml);

		verify(converter.getXStream(), times(1)).fromXML(xml);
	}

}
