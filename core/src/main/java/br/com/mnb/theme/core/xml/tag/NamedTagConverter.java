package br.com.mnb.theme.core.xml.tag;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import br.com.mnb.theme.core.factory.ExtensionFactory;
import br.com.mnb.theme.core.factory.SimpleFactory;

public class NamedTagConverter<T> implements TagConverter<T, String>{
	
	private ExtensionFactory<T> factory;
	private Map<Class<? extends T>, String> tags;
	
	public NamedTagConverter() {
		this(new SimpleFactory<T>());
	}
	
	public NamedTagConverter(ExtensionFactory<T> factory) {
		this.factory = factory;
		this.tags = new HashMap<>();
	}
	
	public void put(String tagName, Class<? extends T> clazz) {
		tags.put(clazz, tagName);
	}

	@Override
	public String toString(T element) {
		if (tags.containsKey(element.getClass())) {
			return tags.get(element.getClass());
		}
		throw new IllegalArgumentException("Unmapped element " + element);
	}

	@Override
	public T toComponent(String tagName) {
		for (Entry<Class<? extends T>, String> entry : tags.entrySet()) {
			if (tagName.equals(entry.getValue())) {
				return factory.create(entry.getKey());
			}
		}
		throw new IllegalArgumentException("Unmapped tag " + tagName);
	}

}
