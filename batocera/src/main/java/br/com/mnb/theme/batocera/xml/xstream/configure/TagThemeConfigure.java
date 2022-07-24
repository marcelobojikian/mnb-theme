package br.com.mnb.theme.batocera.xml.xstream.configure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.thoughtworks.xstream.XStream;

import br.com.mnb.theme.batocera.xml.feature.AbstractFeature;
import br.com.mnb.theme.batocera.xml.feature.BatoceraFeature;
import br.com.mnb.theme.batocera.xml.xstream.converter.FeatureXStreamConverter;
import br.com.mnb.theme.core.xml.Content;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.theme.AbstractTheme;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;
import br.com.mnb.theme.core.xml.xstream.XStreamConfigure;
import br.com.mnb.theme.core.xml.xstream.converter.ContentXstreamConverter;
import br.com.mnb.theme.core.xml.xstream.converter.ElementXstreamConverter;

public class TagThemeConfigure implements XStreamConfigure {
	
	public static final String THEME_ELEMENT_NAME = "theme";
	private Class<? extends AbstractTheme> themeClass;

	Set<Class<?>> allowTypes = new HashSet<>();
	Map<String, Class<?>> aliasMap = new HashMap<>();
	NamedTagConverter<AbstractElement> elementConverter;
	NamedTagConverter<AbstractViewElement> viewConverter;
	NamedTagConverter<AbstractFeature> featureConverter;
	
	public TagThemeConfigure(NamedTagConverter<AbstractFeature> featureConverter, NamedTagConverter<AbstractViewElement> viewConverter, NamedTagConverter<AbstractElement> elementConverter) {
		this.featureConverter = featureConverter;
		this.elementConverter = elementConverter;
		this.viewConverter = viewConverter;

		this.allowTypes.add(Content.class);
		this.allowTypes.add(BatoceraFeature.class);
	}

	@Override
	public void defineAllowTypes(XStream xstream) {
		Class<?>[] classes = new Class[allowTypes.size()];
		xstream.allowTypes(allowTypes.toArray(classes));
	}

	@Override
	public void defineProcessAnnotations(XStream xstream) {
		for (Class<?> clazz : allowTypes) {
			xstream.processAnnotations(clazz);
		}
	}
	
	@Override
	public void defineAlias(XStream xstream) {
		for (Entry<String, Class<?>> entry : aliasMap.entrySet()) {
			xstream.alias(entry.getKey(), entry.getValue());
		}
	}
	
	@Override
	public void addConverter(XStream xstream) {
		xstream.registerConverter(new ContentXstreamConverter());
		xstream.registerConverter(new ElementXstreamConverter(elementConverter));
		xstream.registerConverter(new FeatureXStreamConverter(featureConverter));
	}

	public void addView(String name, Class<? extends AbstractViewElement> clazz) {
		allowTypes.add(clazz);
		viewConverter.put(name, clazz);
	}

	public void addElement(String name, Class<? extends AbstractElement> clazz) {
		allowTypes.add(clazz);
		elementConverter.put(name, clazz);
	}
	
	public void addAlias(String name, Class<?> clazz) {
		allowTypes.add(clazz);
		aliasMap.put(name, clazz);
	}
	
	public void addFeature(String name, Class<? extends AbstractFeature> clazz) {
		allowTypes.add(clazz);
		featureConverter.put(name, clazz);
	}

	public void setTheme(Class<? extends AbstractTheme> clazz) {		
		if(themeClass != null) {
			allowTypes.remove(themeClass);
		}
		allowTypes.add(clazz);
		themeClass = clazz;
	}

}
