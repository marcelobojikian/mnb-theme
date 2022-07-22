package br.com.mnb.theme.core.xml.converter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;

public interface XStreamConfigure {
	
	default XStream getXStream() {

		XStream  xstream = new XStream();

		xstream.autodetectAnnotations(true);
		xstream.ignoreUnknownElements();
		
		xstream.addPermission(NoTypePermission.NONE);
		
		defineProcessAnnotations(xstream);
		defineAlias(xstream);		
		addConverter(xstream);		
		defineAllowTypes(xstream);
		
		return xstream;
		
	}

	default void defineAllowTypes(XStream xstream) { }

	default void defineProcessAnnotations(XStream xstream) { }

	default void addConverter(XStream xstream) { }

	default void defineAlias(XStream xstream) { }

}

class DefaultConfigure implements XStreamConfigure {}
