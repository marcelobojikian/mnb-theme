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

import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.tag.converter.TagElementConverter;
import br.com.mnb.theme.core.xml.xstream.configure.TagElementConfigure;

class TagElementConverterTest {

	TagElementConverter converter;

	NamedTagConverter<AbstractElement> mockConverter;
	TagElementConfigure mockConfigure;

	@BeforeEach
	@SuppressWarnings("unchecked")
	public void setup() {
		XStream xstream = mock(XStream.class);
		mockConverter = mock(NamedTagConverter.class);
		mockConfigure = mock(TagElementConfigure.class, withSettings().useConstructor(mockConverter));

		when(mockConfigure.getXStream()).thenReturn(xstream);
	}

	@Test
	public void sucessWhenUseDefaultConstructor() {
		converter = new TagElementConverter();
		assertNotNull(converter);
		assertNotNull(converter.getXStream());

		assertNotNull(converter.getConfigure());
		assertInstanceOf(TagElementConfigure.class, converter.getConfigure());
	}

	@Test
	public void sucessWhenUseConstructorWithCustomNamedTagConverter() {
		converter = new TagElementConverter(mockConverter);
		assertNotNull(converter);
		assertNotNull(converter.getXStream());

		assertNotNull(converter.getConfigure());
		assertInstanceOf(TagElementConfigure.class, converter.getConfigure());
	}

	@Test
	public void sucessWhenUseConstructorWithCustomConfigure() {
		converter = new TagElementConverter(mockConfigure);
		assertNotNull(converter);
		assertNotNull(converter.getXStream());

		assertNotNull(converter.getConfigure());
		assertInstanceOf(TagElementConfigure.class, converter.getConfigure());
		assertSame(converter.getConfigure(), mockConfigure);

	}

	@Test
	public void sucessWhenAddElement() {
		converter = spy(new TagElementConverter(mockConfigure));
		assertSame(converter.getConfigure(), mockConfigure);

		converter.add("element", Element.class);

		verify(converter, times(1)).notifyChange();
		verify(mockConfigure, times(1)).addElement("element", Element.class);
	}

	@Test
	public void sucessWhenElementToXML() {
		converter = spy(new TagElementConverter(mockConfigure));

		Element element = new Element();
		converter.toXML(element);

		verify(converter.getXStream(), times(1)).toXML(element);
	}

	@Test
	public void sucessWhenElementFromXml() {
		converter = spy(new TagElementConverter(mockConfigure));

		String xml = "<element name=\"\">";
		converter.fromXML(xml);

		verify(converter.getXStream(), times(1)).fromXML(xml);
	}

}
