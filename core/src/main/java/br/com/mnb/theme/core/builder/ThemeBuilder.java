package br.com.mnb.theme.core.builder;

import br.com.mnb.theme.core.xml.theme.AbstractTheme;

public interface ThemeBuilder {
	
	public <T extends AbstractTheme> T createTheme(Class<T> clazz, Integer version);
	
	public <T extends AbstractTheme> T createTheme(Class<T> clazz, Integer version, String... includes);

}
