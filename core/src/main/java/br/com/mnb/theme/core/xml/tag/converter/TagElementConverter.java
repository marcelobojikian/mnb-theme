package br.com.mnb.theme.core.xml.tag.converter;

import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.element.CommonElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.xstream.configure.TagElementConfigure;

public class TagElementConverter extends TypeConverterCached<TagElementConfigure> {
	
	public TagElementConverter() {		
		this(new NamedTagConverter<AbstractElement>());
	}
	
	public TagElementConverter(NamedTagConverter<AbstractElement> converter) {
		this(new TagElementConfigure(converter));
	}
	
	public TagElementConverter(TagElementConfigure configure) {
		super(configure);
	}
	
	public <T extends AbstractElement> void add(String name, Class<T> clazz) {
		notifyChange();
		getConfigure().addElement(name, clazz);
	}

	public String toXML(CommonElement element) {
		return getXStream().toXML(element);
	}

	public CommonElement fromXML(String xml) {
		return (CommonElement) getXStream().fromXML(xml);
	}

}
