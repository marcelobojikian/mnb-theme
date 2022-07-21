package br.com.mnb.theme.core.xml.converter;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import br.com.mnb.theme.core.factory.ExtensionFactory;

public class SimpleConverter<T> implements TagConverter<T, String>{
	
	private ExtensionFactory<T> factory;
	private Map<Class<? extends T>, String> registers;
	
	public SimpleConverter(ExtensionFactory<T> factory) {
		this.factory = factory;
		this.registers = new HashMap<>();
	}
	
	public void registerElement(String tagName, Class<? extends T> clazz) {
		registers.put(clazz, tagName);
	}

	@Override
	public String toString(T element) {
		if (registers.containsKey(element.getClass())) {
			return registers.get(element.getClass());
		}
		throw new IllegalArgumentException("Unmapped element " + element);
	}

	@Override
	public T toComponent(String tagName) {
		for (Entry<Class<? extends T>, String> entry : registers.entrySet()) {
			if (tagName.equals(entry.getValue())) {
				return factory.create(entry.getKey());
			}
		}
		throw new IllegalArgumentException("Unmapped tag " + tagName);
	}

}
