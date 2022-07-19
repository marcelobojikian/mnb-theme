package br.com.mnb.theme.batocera.xml.converter.tag;

import br.com.mnb.theme.batocera.factory.BatoceraInstanceFactory;
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
import br.com.mnb.theme.core.factory.ElementFactory;
import br.com.mnb.theme.core.xml.converter.TagConverter;
import br.com.mnb.theme.core.xml.element.AbstractElement;

public class ElementTagConverter implements TagConverter<AbstractElement, String>{
	
	private ElementFactory instaceFactory;
	
	public ElementTagConverter() {
		this(new BatoceraInstanceFactory());
	}
	
	public ElementTagConverter(ElementFactory instaceFactory) {
		this.instaceFactory = instaceFactory;
	}

	@Override
	public String toString(AbstractElement element) {
		if(element instanceof BatoceraCarousel) {
			return "carousel";
		} else if(element instanceof Text) {
			return "text";
		} else if(element instanceof Image) {
			return "image";			
		} else if(element instanceof Datetime) {
			return "datetime";
		} else if(element instanceof HelpSystem) {
			return "helpsystem";
		} else if(element instanceof Ninepatch) {
			return "ninepatch";
		} else if(element instanceof Rating) {
			return "rating";			
		} else if(element instanceof Sound) {
			return "sound";
		} else if(element instanceof TextList) {
			return "textlist";			
		} else if(element instanceof Video) {
			return "video";
		} else {
			throw new IllegalArgumentException("Unmapped element " + element);
		}
	}

	@Override
	public AbstractElement toComponent(String tagName) {
		switch (tagName) {
		case "carousel":
			return instaceFactory.createElement(BatoceraCarousel.class);
		case "text":
			return instaceFactory.createElement(Text.class);
		case "image":
			return instaceFactory.createElement(Image.class);
		case "datetime":
			return instaceFactory.createElement(Datetime.class);
		case "helpsystem":
			return instaceFactory.createElement(HelpSystem.class);
		case "ninepatch":
			return instaceFactory.createElement(Ninepatch.class);
		case "rating":
			return instaceFactory.createElement(Rating.class);
		case "sound":
			return instaceFactory.createElement(Sound.class);
		case "textlist":
			return instaceFactory.createElement(TextList.class);
		case "video":
			return instaceFactory.createElement(Video.class);
		default:
			throw new IllegalArgumentException("Unmapped tag " + tagName);
		}
	}

}
