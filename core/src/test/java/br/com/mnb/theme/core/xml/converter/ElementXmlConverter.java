package br.com.mnb.theme.core.xml.converter;

import com.thoughtworks.xstream.XStream;

import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.model.SecondElement;
import br.com.mnb.theme.core.xml.Content;
import br.com.mnb.theme.core.xml.element.CommonElement;

public class ElementXmlConverter implements XStreamConfigure {
	
	XmlConverter converter;
	
	public ElementXmlConverter() {
		converter = new XmlConverter(this);
		converter.putTag("element", Element.class);
		converter.putTag("second", SecondElement.class);
	}

	@Override
	public void defineAllowTypes(XStream xstream) {
		xstream.allowTypes(new Class[] {
				Element.class,
				SecondElement.class,
				Content.class });
	}

	@Override
	public void defineProcessAnnotations(XStream xstream) {
		xstream.processAnnotations(Element.class);
		xstream.processAnnotations(SecondElement.class);
		xstream.processAnnotations(Content.class);
	}

	public String toXML(CommonElement element) {
		return converter.toXML(element);
	}

	public CommonElement fromXML(String string) {
		return (CommonElement) converter.fromXML(string);
	}

}
