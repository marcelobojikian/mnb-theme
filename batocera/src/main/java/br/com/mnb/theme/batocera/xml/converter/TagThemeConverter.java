package br.com.mnb.theme.batocera.xml.converter;

import br.com.mnb.theme.batocera.xml.feature.AbstractFeature;
import br.com.mnb.theme.batocera.xml.xstream.configure.TagThemeConfigure;
import br.com.mnb.theme.core.xml.converter.TypeConverterCached;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.theme.AbstractTheme;
import br.com.mnb.theme.core.xml.theme.ThemeElement;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;

public class TagThemeConverter extends TypeConverterCached<TagThemeConfigure> {
	
	public TagThemeConverter() {		
		this(new NamedTagConverter<AbstractFeature>(), new NamedTagConverter<AbstractViewElement>(),
				new NamedTagConverter<AbstractElement>());
	}
	
	public TagThemeConverter(NamedTagConverter<AbstractFeature> featureConverter, NamedTagConverter<AbstractViewElement> viewConverter, NamedTagConverter<AbstractElement> elementConverter) {
		this(new TagThemeConfigure(featureConverter, viewConverter, elementConverter));
	}
	
	public TagThemeConverter(TagThemeConfigure configure) {
		super(configure);
	}

	public <T extends AbstractTheme> void setTheme(Class<T> clazz) {
		notifyChange();
		getConfigure().setTheme(clazz);
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

	public String toXML(ThemeElement element) {
		return getXStream().toXML(element);
	}

	public ThemeElement fromXML(String xml) {
		return (ThemeElement) getXStream().fromXML(xml);
	}

}
