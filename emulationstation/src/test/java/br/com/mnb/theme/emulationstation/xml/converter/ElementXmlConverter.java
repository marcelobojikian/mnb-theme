package br.com.mnb.theme.emulationstation.xml.converter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;

import br.com.mnb.theme.core.factory.ExtensionFactory;
import br.com.mnb.theme.core.factory.SimpleFactory;
import br.com.mnb.theme.core.xml.Content;
import br.com.mnb.theme.core.xml.converter.ContentXStreamConverter;
import br.com.mnb.theme.core.xml.converter.ElementXStreamConverter;
import br.com.mnb.theme.core.xml.converter.SimpleConverter;
import br.com.mnb.theme.core.xml.converter.XmlConverter;
import br.com.mnb.theme.core.xml.element.AbstractElement;
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

public class ElementXmlConverter extends XmlConverter<AbstractElement> {
	
	ElementXStreamConverter xmlConverter;
	
	public ElementXmlConverter() {
		this(new SimpleFactory<AbstractElement>());
	}
	
	public ElementXmlConverter(ExtensionFactory<AbstractElement> factory) {
		xmlConverter = getElementXmlConverter(factory);
	}
	
	public ElementXStreamConverter getElementXmlConverter(ExtensionFactory<AbstractElement> factory) {
		SimpleConverter<AbstractElement> converter = new SimpleConverter<AbstractElement>(factory);
		converter.registerElement("text", Text.class);
		converter.registerElement("image", Image.class);
		converter.registerElement("datetime", Datetime.class);
		converter.registerElement("helpsystem", HelpSystem.class);
		converter.registerElement("ninepatch", Ninepatch.class);
		converter.registerElement("rating", Rating.class);
		converter.registerElement("sound", Sound.class);
		converter.registerElement("textlist", TextList.class);
		converter.registerElement("video", Video.class);
		return new ElementXStreamConverter(converter);
	}

	@Override
	public XStream getXStream() {

		XStream  xstream = new XStream();

		xstream.autodetectAnnotations(true);
		xstream.ignoreUnknownElements();

		xstream.registerConverter(new ContentXStreamConverter());
		xstream.registerConverter(xmlConverter);

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
		
		xstream.addPermission(NoTypePermission.NONE);
		
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
		
		return xstream;

	}

}
