package br.com.mnb.theme.emulationstation.xml.converter;

import java.io.File;

import com.thoughtworks.xstream.XStream;

import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;
import br.com.mnb.theme.core.xml.view.View;
import br.com.mnb.theme.core.xml.xstream.XStreamBuilder;
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

public class ESConverter {
	
	private XStream xstream;
	
	public ESConverter() {
		// @formatter:off
		xstream = XStreamBuilder
				.create()
					.configTheme(EmulationStationTheme.class)
					.configContent()
					.configElement(new NamedTagConverter<AbstractElement>())
					.configView(new NamedTagConverter<AbstractViewElement>())
					.addView(View.class)
					.addElement(Text.class)
					.addElement(Image.class)
					.addElement(Datetime.class)
					.addElement(HelpSystem.class)
					.addElement(Ninepatch.class)
					.addElement(Rating.class)
					.addElement(Sound.class)
					.addElement(TextList.class)
					.addElement(Video.class)
				.build();
		// @formatter:on
	}

	public String toXML(EmulationStationTheme element) {
		return xstream.toXML(element);
	}

	public EmulationStationTheme fromXML(String xml) {
		return (EmulationStationTheme) xstream.fromXML(xml);
	}

	public EmulationStationTheme fromXML(File xml) {
		return (EmulationStationTheme) xstream.fromXML(xml);
	}

}
