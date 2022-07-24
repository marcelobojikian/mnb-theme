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

import br.com.mnb.theme.batocera.xml.element.BatoceraCarousel;
import br.com.mnb.theme.batocera.xml.feature.AbstractFeature;
import br.com.mnb.theme.batocera.xml.feature.BatoceraFeature;
import br.com.mnb.theme.batocera.xml.feature.CarouselFeature;
import br.com.mnb.theme.batocera.xml.theme.BatoceraTheme;
import br.com.mnb.theme.batocera.xml.view.View;
import br.com.mnb.theme.batocera.xml.xstream.configure.TagThemeConfigure;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;


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
		
		converter.setTheme(BatoceraTheme.class);
		
		verify(converter, times(1)).notifyChange();
		verify(mockConfigure, times(1)).setTheme(BatoceraTheme.class);
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
		
		converter.addElement("carousel", BatoceraCarousel.class);
		
		verify(converter, times(1)).notifyChange();
		verify(mockConfigure, times(1)).addElement("carousel", BatoceraCarousel.class);
	}

	@Test
	public void sucessWhenAddAlias() {
		converter = spy(new TagThemeConverter(mockConfigure));
		assertSame(converter.getConfigure(), mockConfigure);
		
		converter.addAlias("feature", BatoceraFeature.class);
		
		verify(converter, times(1)).notifyChange();
		verify(mockConfigure, times(1)).addAlias("feature", BatoceraFeature.class);
	}

	@Test
	public void sucessWhenAddFeature() {
		converter = spy(new TagThemeConverter(mockConfigure));
		assertSame(converter.getConfigure(), mockConfigure);
		
		converter.addFeature("carousel", CarouselFeature.class);
		
		verify(converter, times(1)).notifyChange();
		verify(mockConfigure, times(1)).addFeature("carousel", CarouselFeature.class);
	}

	@Test
	public void sucessWhenElementToXML() {
		converter = spy(new TagThemeConverter(mockConfigure));
		
		BatoceraTheme theme= new BatoceraTheme();
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
