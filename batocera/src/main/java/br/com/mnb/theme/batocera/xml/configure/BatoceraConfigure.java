package br.com.mnb.theme.batocera.xml.configure;

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
import br.com.mnb.theme.core.xml.Content;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.feature.AbstractFeature;
import br.com.mnb.theme.core.xml.feature.FeatureElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.view.View;
import br.com.mnb.theme.core.xml.xstream.XStreamConfigure;
import br.com.mnb.theme.core.xml.xstream.converter.ContentXstreamConverter;
import br.com.mnb.theme.core.xml.xstream.converter.ElementXstreamConverter;
import br.com.mnb.theme.core.xml.xstream.converter.FeatureXStreamConverter;

public class BatoceraConfigure implements XStreamConfigure {

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
				Content.class,
				// Batocera
				BatoceraTheme.class,
				BatoceraCarousel.class,
				FeatureElement.class, // nao esta no processAnnotations
				CarouselFeature.class,
				VideoFeature.class});		
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

		xstream.processAnnotations(FeatureElement.class); //TESTE
		// Batocera
		xstream.processAnnotations(BatoceraTheme.class);
		xstream.processAnnotations(BatoceraCarousel.class);
		xstream.processAnnotations(CarouselFeature.class);
		xstream.processAnnotations(VideoFeature.class);
	}
	
	@Override
	public void addConverter(XStream xstream) {
		xstream.registerConverter(new ContentXstreamConverter());
		
		NamedTagConverter<AbstractElement> elementConverter = new NamedTagConverter<AbstractElement>();
		elementConverter.put(Text.class);
		elementConverter.put(Image.class);
		elementConverter.put(Datetime.class);
		elementConverter.put(HelpSystem.class);
		elementConverter.put(Ninepatch.class);
		elementConverter.put(Rating.class);
		elementConverter.put(Sound.class);
		elementConverter.put(TextList.class);
		elementConverter.put(Video.class);
		elementConverter.put(BatoceraCarousel.class);		
		xstream.registerConverter(new ElementXstreamConverter(elementConverter));
		
		NamedTagConverter<AbstractFeature> featureConverter = new NamedTagConverter<AbstractFeature>();
		featureConverter.put(CarouselFeature.class);
		featureConverter.put(VideoFeature.class);
		xstream.registerConverter(new FeatureXStreamConverter(featureConverter));
		
	}

	@Override
	public void defineAlias(XStream xstream) {
		xstream.alias("feature", FeatureElement.class);		
	}

}
