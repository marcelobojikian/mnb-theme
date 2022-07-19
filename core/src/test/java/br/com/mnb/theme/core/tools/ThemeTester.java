package br.com.mnb.theme.core.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import br.com.mnb.theme.core.builder.ThemeBuilder;
import br.com.mnb.theme.core.xml.theme.AbstractTheme;

public class ThemeTester {
	
	ThemeBuilder builder;
	
	public ThemeTester(ThemeBuilder builder){
		this.builder = builder;
	}

	public <T extends AbstractTheme> void testTheme(Class<T> clazz) {

		testTheme(clazz, 2);
		testTheme(clazz, 3, "FirstInclude");
		testTheme(clazz, 4, "FirstInclude", "SecondInclude");
		
	}
	
	<T extends AbstractTheme> void testTheme(Class<T> clazz, Integer versionExpected) {
		AbstractTheme theme = builder.createTheme(clazz, versionExpected);
		assertInstanceOf(clazz, theme);
		testThemeContent(theme, versionExpected);
	}
	
	<T extends AbstractTheme> void testTheme(Class<T> clazz, Integer versionExpected, String... includesExpected) {
		AbstractTheme theme = builder.createTheme(clazz, versionExpected, includesExpected);
		assertInstanceOf(clazz, theme);
		testThemeContent(theme, versionExpected, includesExpected);
	}
	
	public static <T extends AbstractTheme> void testThemeContent(T theme, Integer versionExpected, String... includesExpected) {
		assertNotNull(theme);
		assertNotNull(includesExpected);
		assertEquals(versionExpected, theme.getFormatVersion());
		List<String> themeIncludes = theme.getIncludes();
		if(includesExpected.length == 0) {
			assertTrue(themeIncludes.isEmpty());
		} else {
			for (int i = 0; i < includesExpected.length; i++) {
				assertTrue(themeIncludes.contains(includesExpected[i]));
			}
		}
	}

}
