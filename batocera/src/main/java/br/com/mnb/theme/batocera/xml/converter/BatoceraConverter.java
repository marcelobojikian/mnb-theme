package br.com.mnb.theme.batocera.xml.converter;

import br.com.mnb.theme.batocera.xml.element.BatoceraCarousel;
import br.com.mnb.theme.batocera.xml.element.Datetime;
import br.com.mnb.theme.batocera.xml.element.HelpSystem;
import br.com.mnb.theme.batocera.xml.element.Image;
import br.com.mnb.theme.batocera.xml.element.Ninepatch;
import br.com.mnb.theme.batocera.xml.element.Rating;
import br.com.mnb.theme.batocera.xml.element.Sound;
import br.com.mnb.theme.batocera.xml.element.Text;
import br.com.mnb.theme.batocera.xml.element.TextList;
import br.com.mnb.theme.batocera.xml.element.Video;
import br.com.mnb.theme.batocera.xml.feature.CarouselFeature;
import br.com.mnb.theme.batocera.xml.feature.VideoFeature;
import br.com.mnb.theme.batocera.xml.theme.BatoceraTheme;
import br.com.mnb.theme.core.xml.feature.FeatureElement;
import br.com.mnb.theme.core.xml.tag.converter.TagThemeConverter;

public class BatoceraConverter {
	
	private TagThemeConverter converter;
	
	public BatoceraConverter() {
		converter = new TagThemeConverter();
		converter.setTheme(BatoceraTheme.class);
		
		converter.addAlias("feature", FeatureElement.class);
		converter.addFeature("carousel", CarouselFeature.class);
		converter.addFeature("video", VideoFeature.class);
		
		converter.addElement("text", Text.class);
		converter.addElement("image", Image.class);
		converter.addElement("datetime", Datetime.class);
		converter.addElement("helpsystem", HelpSystem.class);
		converter.addElement("ninepatch", Ninepatch.class);
		converter.addElement("rating", Rating.class);
		converter.addElement("sound", Sound.class);
		converter.addElement("textlist", TextList.class);
		converter.addElement("video", Video.class);
		converter.addElement("carousel", BatoceraCarousel.class);
	}

	public String toXML(BatoceraTheme element) {
		return converter.toXML(element);
	}

	public BatoceraTheme fromXML(String xml) {
		return (BatoceraTheme) converter.fromXML(xml);
	}

}
