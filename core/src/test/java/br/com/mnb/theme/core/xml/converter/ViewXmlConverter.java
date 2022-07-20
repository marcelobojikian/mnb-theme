package br.com.mnb.theme.core.xml.converter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;

import br.com.mnb.theme.core.factory.ElementFactory;
import br.com.mnb.theme.core.factory.InstanceFactory;
import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.model.SecondElement;
import br.com.mnb.theme.core.model.View;
import br.com.mnb.theme.core.xml.Content;
import br.com.mnb.theme.core.xml.element.ElementConverter;

public class ViewXmlConverter extends XmlConverter<View> {
	
	ElementXStreamConverter xmlConverter;
	
	public ViewXmlConverter() {
		this(new InstanceFactory());
	}
	
	public ViewXmlConverter(ElementFactory instanceFactory) {
		
		ElementConverter converter = new ElementConverter(instanceFactory);
		converter.registerElement("element", Element.class);
		converter.registerElement("second", SecondElement.class);
		
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
		xstream.processAnnotations(SecondElement.class);
		xstream.processAnnotations(Content.class);
		
		xstream.addPermission(NoTypePermission.NONE);
		
		xstream.allowTypes(new Class[] {
				View.class,
				Element.class,
				SecondElement.class,
				Content.class });
		
		return xstream;

	}

}
