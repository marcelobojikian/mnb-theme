package br.com.mnb.theme.core.xml.converter;

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

import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.model.Theme;
import br.com.mnb.theme.core.model.View;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;
import br.com.mnb.theme.core.xml.xstream.configure.TagThemeConfigure;

class TagThemeConverterTest {
	
	TagThemeConverter converter;
	
	NamedTagConverter<AbstractElement> mockElementConverter;
	NamedTagConverter<AbstractViewElement> mockViewConverter;
	TagThemeConfigure mockConfigure;
	
	@BeforeEach
	@SuppressWarnings("unchecked")
	public void setup() {
		XStream xstream = mock(XStream.class);
		mockElementConverter = mock(NamedTagConverter.class);
		mockViewConverter = mock(NamedTagConverter.class);
		mockConfigure = mock(TagThemeConfigure.class, withSettings().useConstructor(mockViewConverter, mockElementConverter));
		
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
		converter = new TagThemeConverter(mockViewConverter, mockElementConverter);
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
	public void sucessWhenAddView() {
		converter = spy(new TagThemeConverter(mockConfigure));
		assertSame(converter.getConfigure(), mockConfigure);
		
		converter.addView("view", View.class);
		
		verify(converter, times(1)).notifyChange();
		verify(mockConfigure, times(1)).addView("view", View.class);
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

}
