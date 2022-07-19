package br.com.mnb.theme.batocera.xml.converter.tag;

import br.com.mnb.theme.batocera.factory.BatoceraInstanceFactory;
import br.com.mnb.theme.batocera.factory.FeatureFactory;
import br.com.mnb.theme.batocera.xml.feature.AbstractFeature;
import br.com.mnb.theme.batocera.xml.feature.CarouselFeature;
import br.com.mnb.theme.batocera.xml.feature.VideoFeature;
import br.com.mnb.theme.core.xml.converter.TagConverter;

public class FeatureTagConverter implements TagConverter<AbstractFeature, String>{
	
	private FeatureFactory instaceFactory;
	
	public FeatureTagConverter() {
		this(new BatoceraInstanceFactory());
	}
	
	public FeatureTagConverter(FeatureFactory instaceFactory) {
		this.instaceFactory = instaceFactory;
	}

	@Override
	public String toString(AbstractFeature element) {
		if(element instanceof CarouselFeature) {
			return "carousel";
		} else if(element instanceof VideoFeature) {
			return "video";			
		} else {
			throw new IllegalArgumentException("Unmapped element " + element);
		}
	}

	@Override
	public AbstractFeature toComponent(String tagName) {
		switch (tagName) {
		case "carousel":
			return instaceFactory.createFeature(CarouselFeature.class);
		case "video":
			return instaceFactory.createFeature(VideoFeature.class);
		default:
			throw new IllegalArgumentException("Unmapped tag " + tagName);
		}
	}

}
