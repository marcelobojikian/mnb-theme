package br.com.mnb.theme.core.xml.theme;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import br.com.mnb.theme.core.factory.ThemeFactory;
import br.com.mnb.theme.core.xml.converter.TagConverter;

public class ThemeConverter implements TagConverter<AbstractTheme, String> {

	private ThemeFactory factory;
	private Map<Class<? extends AbstractTheme>, String> registers;

	public ThemeConverter(ThemeFactory factory) {
		this.factory = factory;
		this.registers = new HashMap<>();
	}

	public void registerTheme(String tagName, Class<? extends AbstractTheme> clazz) {
		registers.put(clazz, tagName);
	}

	@Override
	public String toString(AbstractTheme element) {
		if (registers.containsKey(element.getClass())) {
			return registers.get(element.getClass());
		}
		throw new IllegalArgumentException("Unmapped element " + element);
	}

	@Override
	public AbstractTheme toComponent(String tagName) {
		for (Entry<Class<? extends AbstractTheme>, String> entry : registers.entrySet()) {
			if (tagName.equals(entry.getValue())) {
				return factory.createTheme(entry.getKey());
			}
		}
		throw new IllegalArgumentException("Unmapped tag " + tagName);
	}

}
