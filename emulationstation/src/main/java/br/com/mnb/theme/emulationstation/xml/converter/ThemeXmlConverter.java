package br.com.mnb.theme.emulationstation.xml.converter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;

import br.com.mnb.theme.core.factory.ElementFactory;
import br.com.mnb.theme.core.factory.InstanceFactory;
import br.com.mnb.theme.core.xml.Content;
import br.com.mnb.theme.core.xml.converter.ContentXStreamConverter;
import br.com.mnb.theme.core.xml.converter.ElementXStreamConverter;
import br.com.mnb.theme.core.xml.converter.XmlConverter;
import br.com.mnb.theme.emulationstation.xml.converter.tag.ElementTagConverter;
import br.com.mnb.theme.emulationstation.xml.element.Datetime;
import br.com.mnb.theme.emulationstation.xml.element.HelpSystem;
import br.com.mnb.theme.emulationstation.xml.element.Image;
import br.com.mnb.theme.emulationstation.xml.element.Ninepatch;
import br.com.mnb.theme.emulationstation.xml.element.Rating;
import br.com.mnb.theme.emulationstation.xml.element.Sound;
import br.com.mnb.theme.emulationstation.xml.element.Text;
import br.com.mnb.theme.emulationstation.xml.element.TextList;
import br.com.mnb.theme.emulationstation.xml.element.Video;
import br.com.mnb.theme.emulationstation.xml.theme.EmulationStationTheme;
import br.com.mnb.theme.emulationstation.xml.view.View;

public class ThemeXmlConverter extends XmlConverter<EmulationStationTheme> {
	
	private ElementXStreamConverter elementXmlConverter;
	private ContentXStreamConverter contentXmlConverter;
	
	public ThemeXmlConverter() {
		this(new InstanceFactory()); 
	}
	
	public ThemeXmlConverter(ElementFactory instaceFactory) {
		contentXmlConverter = new ContentXStreamConverter();
		ElementTagConverter elementConverter = new ElementTagConverter(instaceFactory);
		elementXmlConverter = new ElementXStreamConverter(elementConverter);
	}
	
	@Override
	public XStream getXStream() {

		XStream  xstream = new XStream();

		xstream.autodetectAnnotations(true);
		xstream.ignoreUnknownElements();

		xstream.registerConverter(contentXmlConverter);
		xstream.registerConverter(elementXmlConverter);

		xstream.processAnnotations(EmulationStationTheme.class);
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
