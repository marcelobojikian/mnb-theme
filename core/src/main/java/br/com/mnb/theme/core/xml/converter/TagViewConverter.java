package br.com.mnb.theme.core.xml.converter;

import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;
import br.com.mnb.theme.core.xml.view.ViewElement;
import br.com.mnb.theme.core.xml.xstream.configure.TagViewConfigure;

public class TagViewConverter extends TypeConverterCached<TagViewConfigure> {
	
	public TagViewConverter() {		
		this(new NamedTagConverter<AbstractViewElement>(), new NamedTagConverter<AbstractElement>());
	}
	
	public TagViewConverter(NamedTagConverter<AbstractViewElement> viewConverter, NamedTagConverter<AbstractElement> elementConverter) {
		this(new TagViewConfigure(viewConverter, elementConverter));
	}
	
	public TagViewConverter(TagViewConfigure configure) {
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

	public String toXML(ViewElement element) {
		return getXStream().toXML(element);
	}

	public ViewElement fromXML(String xml) {
		return (ViewElement) getXStream().fromXML(xml);
	}

}
