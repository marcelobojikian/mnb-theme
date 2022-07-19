package br.com.mnb.theme.core.xml.converter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;

import br.com.mnb.theme.core.factory.ElementFactory;
import br.com.mnb.theme.core.factory.InstanceFactory;
import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.xml.Content;
import br.com.mnb.theme.core.xml.converter.tag.ElementTagConverter;
import br.com.mnb.theme.core.xml.element.CommonElement;

public class ElementXmlConverter extends XmlConverter<CommonElement> {
	
	ElementTagConverter converter;
	ElementXStreamConverter xmlConverter;
	
	public ElementXmlConverter() {
		this(new InstanceFactory());
	}
	
	public ElementXmlConverter(ElementFactory instanceFactory) {	
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

		xstream.processAnnotations(Element.class);
		xstream.processAnnotations(Content.class);
		
		xstream.addPermission(NoTypePermission.NONE);
		
		xstream.allowTypes(new Class[] {
				Element.class,
				Content.class });
		
		return xstream;
	}

}
