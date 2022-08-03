package br.com.mnb.theme.recalbox.xml.converter;

import java.io.File;

import com.thoughtworks.xstream.XStream;

import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.feature.AbstractFeature;
import br.com.mnb.theme.core.xml.feature.FeatureElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;
import br.com.mnb.theme.core.xml.view.View;
import br.com.mnb.theme.core.xml.xstream.XStreamBuilder;
import br.com.mnb.theme.recalbox.xml.element.Datetime;
import br.com.mnb.theme.recalbox.xml.element.HelpSystem;
import br.com.mnb.theme.recalbox.xml.element.Image;
import br.com.mnb.theme.recalbox.xml.element.Ninepatch;
import br.com.mnb.theme.recalbox.xml.element.Rating;
import br.com.mnb.theme.recalbox.xml.element.RecalboxCarousel;
import br.com.mnb.theme.recalbox.xml.element.Sound;
import br.com.mnb.theme.recalbox.xml.element.Text;
import br.com.mnb.theme.recalbox.xml.element.TextList;
import br.com.mnb.theme.recalbox.xml.element.Video;
import br.com.mnb.theme.recalbox.xml.feature.CarouselFeature;
import br.com.mnb.theme.recalbox.xml.theme.RecalboxTheme;

public class RecalboxConverter {
	
	private XStream xstream;
	
	public RecalboxConverter() {
		// @formatter:off
		xstream = XStreamBuilder
				.create()
					.configTheme(RecalboxTheme.class)
					.configContent()
					.configElement(new NamedTagConverter<AbstractElement>())
					.configView(new NamedTagConverter<AbstractViewElement>())
					.configFeature(new NamedTagConverter<AbstractFeature>())
					
					.addAlias("feature", FeatureElement.class)
					.addFeature(CarouselFeature.class)
					
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
					.addElement(RecalboxCarousel.class)
				.build();
		// @formatter:on
	}

	public String toXML(RecalboxTheme element) {
		return xstream.toXML(element);
	}

	public RecalboxTheme fromXML(String xml) {
		return (RecalboxTheme) xstream.fromXML(xml);
	}

	public RecalboxTheme fromXML(File file) {
		return (RecalboxTheme) xstream.fromXML(file);
	}

}
