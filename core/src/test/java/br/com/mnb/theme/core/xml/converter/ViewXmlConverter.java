package br.com.mnb.theme.core.xml.converter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;

import br.com.mnb.theme.core.factory.SimpleFactory;
import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.model.SecondElement;
import br.com.mnb.theme.core.model.View;
import br.com.mnb.theme.core.xml.Content;
import br.com.mnb.theme.core.xml.element.AbstractElement;

public class ViewXmlConverter extends XmlConverter<View> {
	
	ElementXStreamConverter xmlConverter;
	
	public ViewXmlConverter() {

		SimpleFactory<AbstractElement> factory = new SimpleFactory<AbstractElement>();
		SimpleConverter<AbstractElement> converter = new SimpleConverter<AbstractElement>(factory);
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
