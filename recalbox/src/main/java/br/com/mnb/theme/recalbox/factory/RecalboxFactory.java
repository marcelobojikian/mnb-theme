package br.com.mnb.theme.recalbox.factory;

import java.util.Map;

import br.com.mnb.theme.core.builder.ComponentBuilder;
import br.com.mnb.theme.recalbox.xml.element.RecalboxCarousel;
import br.com.mnb.theme.recalbox.xml.element.Text;
import br.com.mnb.theme.recalbox.xml.feature.CarouselFeature;
import br.com.mnb.theme.recalbox.xml.theme.RecalboxTheme;

public class RecalboxFactory extends ComponentFactory {

	private ComponentBuilder builder;

	public RecalboxFactory() {
		this(new ComponentBuilder());
	}

	public RecalboxFactory(ComponentBuilder builder) {
		super(builder);
		this.builder = builder;
	}
	
	/**
	 * 
	 * @param version
	 * @return BatoceraTheme
	 */
	public RecalboxTheme createTheme(Integer version) {
		return builder.createTheme(RecalboxTheme.class, version);
	}
	
	public RecalboxTheme createTheme(Integer version, String... includes) {
		return builder.createTheme(RecalboxTheme.class, version, includes);
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
		RecalboxCarousel carousel = builder.createElement(RecalboxCarousel.class, "systemcarousel");
		return builder.createFeature(CarouselFeature.class, "system", carousel);
	}

	public CarouselFeature createFeatureCarousel(Map<String, String> attributes) {
		RecalboxCarousel carousel = builder.createElement(RecalboxCarousel.class, "systemcarousel", attributes);
		return builder.createFeature(CarouselFeature.class, "system", carousel);
	}
	
}
