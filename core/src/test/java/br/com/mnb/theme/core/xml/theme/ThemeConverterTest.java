package br.com.mnb.theme.core.xml.theme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mnb.theme.core.factory.ThemeFactory;
import br.com.mnb.theme.core.model.Theme;

class ThemeConverterTest {
	
	ThemeConverter converter;
	
	@BeforeEach
	public void setup() {
		ThemeFactory factory = mock(ThemeFactory.class);
		when(factory.createTheme(Theme.class)).thenReturn(new Theme());
		
		converter = new ThemeConverter(factory);
		converter.registerTheme("theme", Theme.class);
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
