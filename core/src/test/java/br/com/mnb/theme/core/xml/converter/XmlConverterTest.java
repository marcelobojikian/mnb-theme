package br.com.mnb.theme.core.xml.converter;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;

import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.theme.AbstractTheme;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;

class XmlConverterTest {
	
	XmlConverter converter;
	
	XStreamConfigure mockConfigure;	
	NamedTagConverter<AbstractElement> mockElementConverter;
	NamedTagConverter<AbstractViewElement> mockViewConverter;
	NamedTagConverter<AbstractTheme> mockThemeConverter;
	
	@BeforeEach
	@SuppressWarnings("unchecked")
	public void setup() {
		
		XStream xtream = mock(XStream.class);
		mockConfigure = mock(XStreamConfigure.class);
		
		when(mockConfigure.getXStream()).thenReturn(xtream);		
		
		mockElementConverter = mock(NamedTagConverter.class);
		mockViewConverter = mock(NamedTagConverter.class);
		mockThemeConverter = mock(NamedTagConverter.class);
		
		converter = new XmlConverter(mockConfigure);
		
		converter.setElementConverter(mockElementConverter);
		converter.setViewConverter(mockViewConverter);
		converter.setThemeConverter(mockThemeConverter);
		
	}
	
	@Test
	public void whenInstanceWithoutConfigureAllConverterNotNull() {	
		XmlConverter converter = new XmlConverter();
		assertNotNull(converter.getElementConverter());
		assertNotNull(converter.getViewConverter());
		assertNotNull(converter.getThemeConverter());
	}
	
	@Test
	public void whenInstanceWithConfigureAllConverterNotNull() {		
		assertNotNull(converter.getElementConverter());
		assertNotNull(converter.getViewConverter());
		assertNotNull(converter.getThemeConverter());
	}

	@Test
	public void whenPutTagElementCallElementConverter() {
		String name = "element";
		Class<AbstractElement> clazz = AbstractElement.class;
		converter.putTag(name, clazz);
		verify(mockElementConverter, times(1)).put(name, clazz);
	}

	@Test
	public void whenPutTagViewCallViewConverter() {
		String name = "view";
		Class<AbstractViewElement> clazz = AbstractViewElement.class;
		converter.putTag(name, clazz);
		verify(mockViewConverter, times(1)).put(name, clazz);
	}

	@Test
	public void whenPutTagThemeCallThemeConverter() {
		String name = "view";
		Class<AbstractTheme> clazz = AbstractTheme.class;
		converter.putTag(name, clazz);
		verify(mockThemeConverter, times(1)).put(name, clazz);
	}

	@Test
	public void errorWhenPutTagUnmapped() {
		assertThrows(IllegalArgumentException.class, () -> {
			converter.putTag("unmapped", Exception.class);
		});
	}
	
	@Test
	public void sucessWhenGetXStreamCalledOneTime() {
		converter.toXML(new Object());		
		verify(mockConfigure, times(1).description("getXStream should be called 1 time")).getXStream();		
		converter.toXML(new Object());
		converter.toXML(new Object());		
		verify(mockConfigure, times(1).description("getXStream should be called 1 time")).getXStream();		
	}

	@Test
	public void sucessWhenGetXStreamCalledTwoTimeBecauseChangedOneTime() {
		converter.toXML(new Object());
		converter.toXML(new Object());		
		verify(mockConfigure, times(1).description("getXStream should be called 1 time")).getXStream();
		converter.putTag("", AbstractElement.class);
		converter.toXML(new Object());
		converter.toXML(new Object());		
		verify(mockConfigure, times(2).description("getXStream should be called 2 time")).getXStream();
	}

}
