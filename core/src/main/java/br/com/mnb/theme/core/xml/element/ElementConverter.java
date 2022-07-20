package br.com.mnb.theme.core.xml.element;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import br.com.mnb.theme.core.factory.ElementFactory;
import br.com.mnb.theme.core.xml.converter.TagConverter;

public class ElementConverter implements TagConverter<AbstractElement, String>{
	
	private ElementFactory factory;
	private Map<Class<? extends AbstractElement>, String> registers;
	
	public ElementConverter(ElementFactory factory) {
		this.factory = factory;
		this.registers = new HashMap<>();
	}
	
	public void registerElement(String tagName, Class<? extends AbstractElement> clazz) {
		registers.put(clazz, tagName);
	}

	@Override
	public String toString(AbstractElement element) {
		if (registers.containsKey(element.getClass())) {
			return registers.get(element.getClass());
		}
		throw new IllegalArgumentException("Unmapped element " + element);
	}

	@Override
	public AbstractElement toComponent(String tagName) {
		for (Entry<Class<? extends AbstractElement>, String> entry : registers.entrySet()) {
			if (tagName.equals(entry.getValue())) {
				return factory.createElement(entry.getKey());
			}
		}
		throw new IllegalArgumentException("Unmapped tag " + tagName);
	}

}
