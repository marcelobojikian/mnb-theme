package br.com.mnb.theme.batocera.xml.converter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;

import br.com.mnb.theme.batocera.factory.BatoceraInstanceFactory;
import br.com.mnb.theme.batocera.xml.converter.tag.ElementTagConverter;
import br.com.mnb.theme.batocera.xml.converter.tag.FeatureTagConverter;
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
import br.com.mnb.theme.batocera.xml.feature.BatoceraFeature;
import br.com.mnb.theme.batocera.xml.feature.CarouselFeature;
import br.com.mnb.theme.batocera.xml.feature.VideoFeature;
import br.com.mnb.theme.batocera.xml.theme.BatoceraTheme;
import br.com.mnb.theme.batocera.xml.view.View;
import br.com.mnb.theme.core.xml.Content;
import br.com.mnb.theme.core.xml.converter.ContentXStreamConverter;
import br.com.mnb.theme.core.xml.converter.ElementXStreamConverter;
import br.com.mnb.theme.core.xml.converter.XmlConverter;

public class FeatureXmlConverter extends XmlConverter<BatoceraFeature> {

	ElementXStreamConverter elementXmlConverter;
	FeatureXStreamConverter featureXmlConverter;
	
	public FeatureXmlConverter() {
		this(new BatoceraInstanceFactory());
	}
	
	public FeatureXmlConverter(BatoceraInstanceFactory instanceFactory) {
		
		ElementTagConverter converter = new ElementTagConverter(instanceFactory);
		elementXmlConverter = new ElementXStreamConverter(converter);
		
		FeatureTagConverter featureConverter = new FeatureTagConverter(instanceFactory);
		featureXmlConverter = new FeatureXStreamConverter(featureConverter);
	}


	@Override
	public XStream getXStream() {

		XStream  xstream = new XStream();

		xstream.autodetectAnnotations(true);
		xstream.ignoreUnknownElements();

		xstream.registerConverter(new ContentXStreamConverter());
		xstream.registerConverter(elementXmlConverter);

		xstream.processAnnotations(BatoceraTheme.class);
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

		xstream.processAnnotations(BatoceraTheme.class);
		xstream.processAnnotations(BatoceraCarousel.class);
		xstream.processAnnotations(CarouselFeature.class);
		xstream.processAnnotations(VideoFeature.class);

		xstream.alias("feature", CarouselFeature.class);
		xstream.alias("feature", VideoFeature.class);
		
		xstream.registerConverter(featureXmlConverter);
		
		xstream.allowTypes(new Class[] {
				BatoceraTheme.class,
				BatoceraCarousel.class,
				BatoceraFeature.class,
				CarouselFeature.class,
				VideoFeature.class });
		
		return xstream;
	}

}
