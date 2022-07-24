package br.com.mnb.theme.core.xml.xstream.configure;

import com.thoughtworks.xstream.XStream;

import br.com.mnb.theme.core.xml.Content;
import br.com.mnb.theme.core.xml.xstream.XStreamConfigure;
import br.com.mnb.theme.core.xml.xstream.converter.ContentXstreamConverter;

public class TagContentConfigure implements XStreamConfigure {
	
	public static final String CONTENT_ELEMENT_NAME = "element";

	@Override
	public void defineAllowTypes(XStream xstream) {
		xstream.allowTypes(new Class[] { Content.class });
	}
	
	@Override
	public void defineAlias(XStream xstream) {
		xstream.alias(CONTENT_ELEMENT_NAME, Content.class);
	}
	
	@Override
	public void addConverter(XStream xstream) {
		xstream.registerConverter(new ContentXstreamConverter());
	}
	
}
