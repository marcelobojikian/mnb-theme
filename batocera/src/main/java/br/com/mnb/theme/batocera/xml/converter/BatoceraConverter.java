package br.com.mnb.theme.batocera.xml.converter;

import java.io.File;

import com.thoughtworks.xstream.XStream;

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
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.feature.AbstractFeature;
import br.com.mnb.theme.core.xml.feature.FeatureElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;
import br.com.mnb.theme.core.xml.view.View;
import br.com.mnb.theme.core.xml.xstream.XStreamBuilder;

public class BatoceraConverter {
	
	private XStream xstream;
	
	public BatoceraConverter() {
		// @formatter:off
		xstream = XStreamBuilder
				.create()
					.configTheme(BatoceraTheme.class)
					.configContent()
					.configElement(new NamedTagConverter<AbstractElement>())
					.configView(new NamedTagConverter<AbstractViewElement>())
					.configFeature(new NamedTagConverter<AbstractFeature>())
					
					.addAlias("feature", FeatureElement.class)
					.addFeature(CarouselFeature.class)
					.addFeature(VideoFeature.class)
					
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
					.addElement(BatoceraCarousel.class)
				.build();
		// @formatter:on
	}

	public String toXML(BatoceraTheme element) {
		return xstream.toXML(element);
	}

	public BatoceraTheme fromXML(String xml) {
		return (BatoceraTheme) xstream.fromXML(xml);
	}

	public BatoceraTheme fromXML(File file) {
		return (BatoceraTheme) xstream.fromXML(file);
	}

}
