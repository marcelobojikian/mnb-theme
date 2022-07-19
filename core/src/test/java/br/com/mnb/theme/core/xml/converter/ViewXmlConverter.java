package br.com.mnb.theme.core.xml.converter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;

import br.com.mnb.theme.core.factory.ElementFactory;
import br.com.mnb.theme.core.factory.InstanceFactory;
import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.model.View;
import br.com.mnb.theme.core.xml.Content;
import br.com.mnb.theme.core.xml.converter.tag.ElementTagConverter;

public class ViewXmlConverter extends XmlConverter<View> {
	
	ElementTagConverter converter;
	ElementXStreamConverter xmlConverter;
	
	public ViewXmlConverter() {
		this(new InstanceFactory());
	}
	
	public ViewXmlConverter(ElementFactory instanceFactory) {	
		converter = new ElementTagConverter(instanceFactory);
		xmlConverter = new ElementXStreamConverter(converter);
	}

	@Override
	public XStream getXStream() {

		XStream  xstream = new XStream();

		xstream.autodetectAnnotations(true);
		xstream.ignoreUnknownElements();

		xstream.registerConverter(new ContentXStreamConverter());
		xstream.registerConverter(xmlConverter);

		xstream.processAnnotations(View.class);
		xstream.processAnnotations(Element.class);
		xstream.processAnnotations(Content.class);
		
		xstream.addPermission(NoTypePermission.NONE);
		
		xstream.allowTypes(new Class[] {
				View.class,
				Element.class,
				Content.class });
		
		return xstream;

	}

}
