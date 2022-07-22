package br.com.mnb.theme.batocera.xml.converter;

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
import br.com.mnb.theme.batocera.xml.feature.AbstractFeature;
import br.com.mnb.theme.batocera.xml.feature.BatoceraFeature;
import br.com.mnb.theme.batocera.xml.feature.CarouselFeature;
import br.com.mnb.theme.batocera.xml.feature.VideoFeature;
import br.com.mnb.theme.batocera.xml.theme.BatoceraTheme;
import br.com.mnb.theme.batocera.xml.view.View;
import br.com.mnb.theme.core.factory.ExtensionFactory;
import br.com.mnb.theme.core.factory.SimpleFactory;
import br.com.mnb.theme.core.xml.Content;
import br.com.mnb.theme.core.xml.converter.NamedTagConverter;
import br.com.mnb.theme.core.xml.converter.XStreamConfigure;
import br.com.mnb.theme.core.xml.converter.XmlConverter;
import br.com.mnb.theme.core.xml.element.AbstractElement;

public class BatoceraXmlConverter implements XStreamConfigure {
	
	private XmlConverter converter;	
	private NamedTagConverter<AbstractFeature> featureConverter;
	
	public BatoceraXmlConverter() {
		this(new SimpleFactory<AbstractElement>(), new SimpleFactory<AbstractFeature>()); 
	}
	
	public BatoceraXmlConverter(ExtensionFactory<AbstractElement> elementFactory, ExtensionFactory<AbstractFeature> featureFactory) {
		converter = new XmlConverter(this);
		converter.setElementConverter(new NamedTagConverter<AbstractElement>(elementFactory));		
		converter.putTag("text", Text.class);
		converter.putTag("image", Image.class);
		converter.putTag("datetime", Datetime.class);
		converter.putTag("helpsystem", HelpSystem.class);
		converter.putTag("ninepatch", Ninepatch.class);
		converter.putTag("rating", Rating.class);
		converter.putTag("sound", Sound.class);
		converter.putTag("textlist", TextList.class);
		converter.putTag("video", Video.class);
		converter.putTag("carousel", BatoceraCarousel.class);

		setFeatureConverter(new NamedTagConverter<AbstractFeature>(featureFactory));
		getFeatureConverter().put("carousel", CarouselFeature.class);
		getFeatureConverter().put("video", VideoFeature.class);
		
	}

	public String toXML(BatoceraTheme theme) {
		return converter.toXML(theme);
	}

	public NamedTagConverter<AbstractFeature> getFeatureConverter() {
		return featureConverter;
	}

	public void setFeatureConverter(NamedTagConverter<AbstractFeature> featureConverter) {
		this.featureConverter = featureConverter;
	}

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
				BatoceraFeature.class, // nao esta no processAnnotations
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
		// Batocera
		xstream.processAnnotations(BatoceraTheme.class);
		xstream.processAnnotations(BatoceraCarousel.class);
		xstream.processAnnotations(CarouselFeature.class);
		xstream.processAnnotations(VideoFeature.class);
	}

	@Override
	public void addConverter(XStream xstream) {
		FeatureXStreamConverter featureXmlConverter = new FeatureXStreamConverter(featureConverter);
		xstream.registerConverter(featureXmlConverter);
	}

	@Override
	public void defineAlias(XStream xstream) {
		xstream.alias("feature", CarouselFeature.class);
		xstream.alias("feature", VideoFeature.class);		
	}

}
