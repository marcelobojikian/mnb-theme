package br.com.mnb.theme.core.xml.converter;

import com.thoughtworks.xstream.XStream;

import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.model.SecondElement;
import br.com.mnb.theme.core.model.View;
import br.com.mnb.theme.core.xml.Content;
import br.com.mnb.theme.core.xml.view.ViewElement;

public class ViewXmlConverter implements XStreamConfigure {
	
	XmlConverter converter;
	
	public ViewXmlConverter() {
		converter = new XmlConverter(this);
		converter.putTag("element", Element.class);
		converter.putTag("second", SecondElement.class);
	}

	@Override
	public void defineAllowTypes(XStream xstream) {		
		xstream.allowTypes(new Class[] {
				View.class,
				Element.class,
				SecondElement.class,
				Content.class });
	}

	@Override
	public void defineProcessAnnotations(XStream xstream) {
		xstream.processAnnotations(View.class);
		xstream.processAnnotations(Element.class);
		xstream.processAnnotations(SecondElement.class);
		xstream.processAnnotations(Content.class);
	}

	public String toXML(ViewElement element) {
		return converter.toXML(element);
	}

	public ViewElement fromXML(String string) {
		return (ViewElement) converter.fromXML(string);
	}

}
