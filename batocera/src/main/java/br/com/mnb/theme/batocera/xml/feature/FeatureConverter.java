package br.com.mnb.theme.batocera.xml.feature;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import br.com.mnb.theme.batocera.factory.FeatureFactory;
import br.com.mnb.theme.core.xml.converter.TagConverter;

public class FeatureConverter implements TagConverter<AbstractFeature, String>{
	
	private FeatureFactory factory;
	private Map<Class<? extends AbstractFeature>, String> registers;
	
	public FeatureConverter(FeatureFactory factory) {
		this.factory = factory;
		this.registers = new HashMap<>();
	}
	
	public void registerElement(String tagName, Class<? extends AbstractFeature> clazz) {
		registers.put(clazz, tagName);
	}

	@Override
	public String toString(AbstractFeature element) {
		if (registers.containsKey(element.getClass())) {
			return registers.get(element.getClass());
		}
		throw new IllegalArgumentException("Unmapped element " + element);
	}

	@Override
	public AbstractFeature toComponent(String tagName) {
		for (Entry<Class<? extends AbstractFeature>, String> entry : registers.entrySet()) {
			if (tagName.equals(entry.getValue())) {
				return factory.createFeature(entry.getKey());
			}
		}
		throw new IllegalArgumentException("Unmapped tag " + tagName);
	}

}
