package br.com.mnb.theme.emulationstation.xml.converter;

import com.thoughtworks.xstream.XStream;

import br.com.mnb.theme.core.factory.ExtensionFactory;
import br.com.mnb.theme.core.factory.SimpleFactory;
import br.com.mnb.theme.core.xml.Content;
import br.com.mnb.theme.core.xml.converter.NamedTagConverter;
import br.com.mnb.theme.core.xml.converter.XStreamConfigure;
import br.com.mnb.theme.core.xml.converter.XmlConverter;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.element.CommonElement;
import br.com.mnb.theme.emulationstation.xml.element.Datetime;
import br.com.mnb.theme.emulationstation.xml.element.HelpSystem;
import br.com.mnb.theme.emulationstation.xml.element.Image;
import br.com.mnb.theme.emulationstation.xml.element.Ninepatch;
import br.com.mnb.theme.emulationstation.xml.element.Rating;
import br.com.mnb.theme.emulationstation.xml.element.Sound;
import br.com.mnb.theme.emulationstation.xml.element.Text;
import br.com.mnb.theme.emulationstation.xml.element.TextList;
import br.com.mnb.theme.emulationstation.xml.element.Video;
import br.com.mnb.theme.emulationstation.xml.view.View;

public class ElementXmlConverter implements XStreamConfigure {
	
	private XmlConverter converter;
	
	public ElementXmlConverter() {
		this(new SimpleFactory<AbstractElement>());
	}
	
	public ElementXmlConverter(ExtensionFactory<AbstractElement> factory) {
		converter = new XmlConverter(this);
		converter.setElementConverter(new NamedTagConverter<AbstractElement>(factory));
		converter.putTag("text", Text.class);
		converter.putTag("image", Image.class);
		converter.putTag("datetime", Datetime.class);
		converter.putTag("helpsystem", HelpSystem.class);
		converter.putTag("ninepatch", Ninepatch.class);
		converter.putTag("rating", Rating.class);
		converter.putTag("sound", Sound.class);
		converter.putTag("textlist", TextList.class);
		converter.putTag("video", Video.class);
	}

	public String toXML(Object object) {
		return converter.toXML(object);
	}

	public CommonElement fromXML(String string) {
		return (CommonElement) converter.fromXML(string);
	}

	@Override
	public void defineAllowTypes(XStream xstream) {		
		xstream.allowTypes(new Class[] {
				Rating.class,
				Datetime.class,
				HelpSystem.class,
				TextList.class,
				Video.class,
				Sound.class,
				View.class,
				Text.class,
				Image.class,
				Ninepatch.class,
				Content.class });
	}

	@Override
	public void defineProcessAnnotations(XStream xstream) {
		xstream.processAnnotations(Rating.class);
		xstream.processAnnotations(Datetime.class);
		xstream.processAnnotations(HelpSystem.class);
		xstream.processAnnotations(TextList.class);
		xstream.processAnnotations(Video.class);
		xstream.processAnnotations(Sound.class);
		xstream.processAnnotations(View.class);
		xstream.processAnnotations(Text.class);
		xstream.processAnnotations(Image.class);
		xstream.processAnnotations(Ninepatch.class);
		xstream.processAnnotations(Content.class);
	}

}
