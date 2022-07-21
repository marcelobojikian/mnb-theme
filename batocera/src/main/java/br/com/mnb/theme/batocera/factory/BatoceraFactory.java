package br.com.mnb.theme.batocera.factory;

import java.util.Map;

import br.com.mnb.theme.batocera.builder.BatoceraBuilder;
import br.com.mnb.theme.batocera.xml.element.BatoceraCarousel;
import br.com.mnb.theme.batocera.xml.element.Text;
import br.com.mnb.theme.batocera.xml.element.Video;
import br.com.mnb.theme.batocera.xml.feature.CarouselFeature;
import br.com.mnb.theme.batocera.xml.feature.VideoFeature;
import br.com.mnb.theme.batocera.xml.theme.BatoceraTheme;

public class BatoceraFactory extends ComponentFactory {

	private BatoceraBuilder builder;

	public BatoceraFactory() {
		this(new BatoceraBuilder());
	}

	public BatoceraFactory(BatoceraBuilder builder) {
		super(builder);
		this.builder = builder;
	}
	
	/**
	 * 
	 * @param version
	 * @return BatoceraTheme
	 */
	public BatoceraTheme createTheme(Integer version) {
		return builder.createTheme(BatoceraTheme.class, version);
	}
	
	public BatoceraTheme createTheme(Integer version, String... includes) {
		return builder.createTheme(BatoceraTheme.class, version, includes);
	}
	
	/**
	 * 
	 * @return logoText
	 */
	public Text createLogoText() {
		return createText("logoText");
	}
	
	public Text createLogoText(Map<String, String> attributes) {
		return createText("logoText", attributes);
	}
	
	/**
	 * 
	 * @return systemInfo
	 */
	public Text createSystemInfo() {
		return createText("systemInfo");
	}
	
	public Text createSystemInfo(Map<String, String> attributes) {
		return createText("systemInfo", attributes);
	}

	/**
	 * 
	 * @return CarouselSupport
	 */
	public CarouselFeature createFeatureCarousel() {
		BatoceraCarousel carousel = builder.createElement(BatoceraCarousel.class, "systemcarousel");
		return builder.createFeature(CarouselFeature.class, "system", carousel);
	}

	public CarouselFeature createFeatureCarousel(Map<String, String> attributes) {
		BatoceraCarousel carousel = builder.createElement(BatoceraCarousel.class, "systemcarousel", attributes);
		return builder.createFeature(CarouselFeature.class, "system", carousel);
	}
	
	/**
	 * 
	 * @return VideoSupport
	 */
	public VideoFeature createFeatureVideo() {
		Video video = createVideo("md_video");
		return builder.createFeature(VideoFeature.class, "video", video);
	}
	
	public VideoFeature createFeatureVideo(Map<String, String> attributes) {
		Video video = createVideo("md_video", attributes);
		return builder.createFeature(VideoFeature.class, "video", video);
	}
	
}
