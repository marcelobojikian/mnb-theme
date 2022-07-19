package br.com.mnb.theme.core.xml.converter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;

import br.com.mnb.theme.core.xml.Content;

public class ContentXmlConverter extends XmlConverter<Content> {

	@Override
	public XStream getXStream() {

		XStream xstream = new XStream();

		xstream.autodetectAnnotations(true);
		xstream.ignoreUnknownElements();

		xstream.addPermission(NoTypePermission.NONE);

		xstream.registerConverter(new ContentXStreamConverter());
		xstream.allowTypes(new Class[] { Content.class });
		
		xstream.alias("element", Content.class);
		
		return xstream;
	}

}
