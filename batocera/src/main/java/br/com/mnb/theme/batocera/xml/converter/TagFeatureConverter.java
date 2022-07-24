package br.com.mnb.theme.batocera.xml.converter;

import br.com.mnb.theme.batocera.xml.feature.AbstractFeature;
import br.com.mnb.theme.batocera.xml.feature.BatoceraFeature;
import br.com.mnb.theme.batocera.xml.xstream.configure.TagFeatureConfigure;
import br.com.mnb.theme.core.xml.converter.TypeConverterCached;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;

public class TagFeatureConverter extends TypeConverterCached<TagFeatureConfigure> {
	
	public TagFeatureConverter() {		
		this(new NamedTagConverter<AbstractFeature>(), new NamedTagConverter<AbstractViewElement>(), new NamedTagConverter<AbstractElement>());
	}
	
	public TagFeatureConverter(NamedTagConverter<AbstractFeature> featureConverter, NamedTagConverter<AbstractViewElement> viewConverter, NamedTagConverter<AbstractElement> elementConverter) {
		this(new TagFeatureConfigure(featureConverter, viewConverter, elementConverter));
	}
	
	public TagFeatureConverter(TagFeatureConfigure configure) {
		super(configure);
	}

	public <T extends AbstractViewElement> void addView(String name, Class<T> clazz) {
		notifyChange();
		getConfigure().addView(name, clazz);
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

	public String toXML(BatoceraFeature element) {
		return getXStream().toXML(element);
	}

	public BatoceraFeature fromXML(String xml) {
		return (BatoceraFeature) getXStream().fromXML(xml);
	}

}
