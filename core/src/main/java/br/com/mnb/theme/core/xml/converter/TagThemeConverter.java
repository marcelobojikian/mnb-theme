package br.com.mnb.theme.core.xml.converter;

import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.theme.AbstractTheme;
import br.com.mnb.theme.core.xml.theme.ThemeElement;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;
import br.com.mnb.theme.core.xml.xstream.configure.TagThemeConfigure;

public class TagThemeConverter extends TypeConverterCached<TagThemeConfigure> {
	
	public TagThemeConverter() {		
		this(new NamedTagConverter<AbstractViewElement>(), new NamedTagConverter<AbstractElement>());
	}
	
	public TagThemeConverter(NamedTagConverter<AbstractViewElement> viewConverter, NamedTagConverter<AbstractElement> elementConverter) {
		this(new TagThemeConfigure(viewConverter, elementConverter));
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

	public String toXML(ThemeElement element) {
		return getXStream().toXML(element);
	}

	public ThemeElement fromXML(String xml) {
		return (ThemeElement) getXStream().fromXML(xml);
	}

}
