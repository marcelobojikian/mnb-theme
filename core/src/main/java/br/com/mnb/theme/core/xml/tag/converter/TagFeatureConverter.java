package br.com.mnb.theme.core.xml.tag.converter;

import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.feature.AbstractFeature;
import br.com.mnb.theme.core.xml.feature.FeatureElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;
import br.com.mnb.theme.core.xml.view.View;
import br.com.mnb.theme.core.xml.xstream.configure.TagFeatureConfigure;

public class TagFeatureConverter extends TypeConverterCached<TagFeatureConfigure> {
	
	public TagFeatureConverter() {		
		this(new NamedTagConverter<AbstractFeature>(), new NamedTagConverter<AbstractViewElement>(), new NamedTagConverter<AbstractElement>());
	}
	
	public TagFeatureConverter(NamedTagConverter<AbstractFeature> featureConverter, NamedTagConverter<AbstractViewElement> viewConverter, NamedTagConverter<AbstractElement> elementConverter) {
		this(new TagFeatureConfigure(featureConverter, viewConverter, elementConverter));
	}
	
	public TagFeatureConverter(TagFeatureConfigure configure) {
		super(configure);
		configure.addView("view", View.class);
	}

	public <T extends AbstractElement> void addElement(String name, Class<T> clazz) {
		notifyChange();
		getConfigure().addElement(name, clazz);
	}

	public void addAlias(String name, Class<?> clazz) {
		notifyChange();
		getConfigure().addAlias(name, clazz);
	}

	public <T extends AbstractFeature> void addFeature(String name, Class<T> clazz) {
		notifyChange();
		getConfigure().addFeature(name, clazz);
	}

	public String toXML(FeatureElement element) {
		return getXStream().toXML(element);
	}

	public FeatureElement fromXML(String xml) {
		return (FeatureElement) getXStream().fromXML(xml);
	}

}
