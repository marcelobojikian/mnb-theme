package br.com.mnb.theme.emulationstation.xml.converter;

import java.io.File;

import br.com.mnb.theme.core.xml.tag.converter.TagThemeConverter;
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
	
	private TagThemeConverter converter;
	
	public ESConverter() {
		converter = new TagThemeConverter();
		converter.setTheme(EmulationStationTheme.class);
		
		converter.addElement("text", Text.class);
		converter.addElement("image", Image.class);
		converter.addElement("datetime", Datetime.class);
		converter.addElement("helpsystem", HelpSystem.class);
		converter.addElement("ninepatch", Ninepatch.class);
		converter.addElement("rating", Rating.class);
		converter.addElement("sound", Sound.class);
		converter.addElement("textlist", TextList.class);
		converter.addElement("video", Video.class);
	}

	public String toXML(EmulationStationTheme element) {
		return converter.toXML(element);
	}

	public EmulationStationTheme fromXML(String xml) {
		return (EmulationStationTheme) converter.fromXML(xml);
	}

	public EmulationStationTheme fromXML(File xml) {
		return (EmulationStationTheme) converter.fromXML(xml);
	}

}
