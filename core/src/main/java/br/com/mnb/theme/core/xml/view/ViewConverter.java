package br.com.mnb.theme.core.xml.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import br.com.mnb.theme.core.factory.ViewFactory;
import br.com.mnb.theme.core.xml.converter.TagConverter;

public class ViewConverter implements TagConverter<AbstractViewElement, String>{
	
	private ViewFactory factory;
	private Map<Class<? extends AbstractViewElement>, String> registers;
	
	public ViewConverter(ViewFactory factory) {
		this.factory = factory;
		this.registers = new HashMap<>();
	}
	
	public void registerView(String tagName, Class<? extends AbstractViewElement> clazz) {
		registers.put(clazz, tagName);
	}

	@Override
	public String toString(AbstractViewElement element) {
		if (registers.containsKey(element.getClass())) {
			return registers.get(element.getClass());
		}
		throw new IllegalArgumentException("Unmapped element " + element);
	}

	@Override
	public AbstractViewElement toComponent(String tagName) {
		for (Entry<Class<? extends AbstractViewElement>, String> entry : registers.entrySet()) {
			if (tagName.equals(entry.getValue())) {
				return factory.createView(entry.getKey());
			}
		}
		throw new IllegalArgumentException("Unmapped tag " + tagName);
	}

}
