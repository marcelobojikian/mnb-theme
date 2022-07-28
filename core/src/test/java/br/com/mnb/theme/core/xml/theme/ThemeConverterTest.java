package br.com.mnb.theme.core.xml.theme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mnb.theme.core.factory.SimpleFactory;
import br.com.mnb.theme.core.model.Theme;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;

class ThemeConverterTest {
	
	NamedTagConverter<AbstractTheme> converter;
	
	@BeforeEach
	public void setup() {
		
		@SuppressWarnings("unchecked")
		SimpleFactory<AbstractTheme> factory = mock(SimpleFactory.class);
		when(factory.create(Theme.class)).thenReturn(new Theme());
		
		converter = new NamedTagConverter<AbstractTheme>(factory);
		converter.put(Theme.class);
	}

	@Test
	void sucessWhenConvertTagNameToTheme() {
		
		AbstractTheme view = converter.toComponent("theme");
		assertNotNull(view);
		assertInstanceOf(Theme.class, view);
		
	}

	@Test
	void sucessWhenConvertThemeToTagName() {
		
		String result = converter.toString(new Theme());
		assertNotNull(result);
		assertEquals("theme", result);
		
	}

	@Test
	void failWhenConvertTagNameToThemeWithTagUnmapped() {
		assertThrows(IllegalArgumentException.class, () -> {
			converter.toComponent("Other");
		});
	}

	@Test
	void failWhenConvertThemeToTagNameWithComponentUnmapped() {
		assertThrows(IllegalArgumentException.class, () -> {
			converter.toString(new UnmappedTheme());
		});
	}
	
	class UnmappedTheme extends AbstractTheme { 
		private static final long serialVersionUID = 1L;
	}

}
