package br.com.mnb.theme.core.xml.xstream.configure;

import java.util.HashSet;
import java.util.Set;

import com.thoughtworks.xstream.XStream;

import br.com.mnb.theme.core.xml.Content;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;
import br.com.mnb.theme.core.xml.xstream.XStreamConfigure;
import br.com.mnb.theme.core.xml.xstream.converter.ContentXstreamConverter;
import br.com.mnb.theme.core.xml.xstream.converter.ElementXstreamConverter;

public class TagViewConfigure implements XStreamConfigure {
	
	Set<Class<?>> allowTypes = new HashSet<>();
	NamedTagConverter<AbstractElement> elementConverter;
	NamedTagConverter<AbstractViewElement> viewConverter;
	
	public TagViewConfigure(NamedTagConverter<AbstractViewElement> viewConverter, NamedTagConverter<AbstractElement> elementConverter) {
		this.allowTypes.add(Content.class);
		this.elementConverter = elementConverter;
		this.viewConverter = viewConverter;
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
	public void addConverter(XStream xstream) {
		xstream.registerConverter(new ContentXstreamConverter());
		xstream.registerConverter(new ElementXstreamConverter(elementConverter));
	}

	public void addView(Class<? extends AbstractViewElement> clazz) {
		allowTypes.add(clazz);
		viewConverter.put(clazz);
	}

	public void addElement(Class<? extends AbstractElement> clazz) {
		allowTypes.add(clazz);
		elementConverter.put(clazz);
	}

}
