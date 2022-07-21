package br.com.mnb.theme.batocera.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mnb.theme.batocera.builder.BatoceraBuilder;
import br.com.mnb.theme.batocera.xml.element.BatoceraCarousel;
import br.com.mnb.theme.batocera.xml.element.Text;
import br.com.mnb.theme.batocera.xml.element.Video;
import br.com.mnb.theme.batocera.xml.feature.AbstractFeature;
import br.com.mnb.theme.batocera.xml.feature.BatoceraFeature;
import br.com.mnb.theme.batocera.xml.feature.CarouselFeature;
import br.com.mnb.theme.batocera.xml.feature.VideoFeature;
import br.com.mnb.theme.batocera.xml.theme.BatoceraTheme;
import br.com.mnb.theme.batocera.xml.view.View;
import br.com.mnb.theme.core.xml.element.CommonElement;

class BatoceraFactoryTest {

	private BatoceraFactory factory;

	@BeforeEach
	public void init() {
		factory = new BatoceraFactory();
	}

	@Test
	void sucessWhenCreateBatoceraThemeWithBatoceraBuilder() {

		BatoceraBuilder builder = new BatoceraBuilder();
		BatoceraFactory factory = new BatoceraFactory(builder);

		BatoceraTheme theme = factory.createTheme(4);
		assertInstanceOf(BatoceraTheme.class, theme);

		theme = factory.createTheme(4, "includeOne");
		assertInstanceOf(BatoceraTheme.class, theme);

	}

	@Test
	void sucessWhenCreateBatoceraThemeWithVersion() {

		BatoceraTheme theme = factory.createTheme(4);
		assertInstanceOf(BatoceraTheme.class, theme);

	}

	@Test
	void sucessWhenCreateBatoceraThemeWithVersionAndIncludes() {

		BatoceraTheme theme = factory.createTheme(4);
		assertInstanceOf(BatoceraTheme.class, theme);

		theme = factory.createTheme(4, "includeOne");
		assertInstanceOf(BatoceraTheme.class, theme);

	}

	@Test
	void sucessWhenCreateElement_LogoText() {
		
		CommonElement text = factory.createLogoText();
		testElement(text, Text.class, "logoText");
		
		text = factory.createLogoText(new HashMap<>());
		testElement(text, Text.class, "logoText");
		
	}

	@Test
	void sucessWhenCreateElement_SystemInfo() {
		
		CommonElement text = factory.createSystemInfo();
		testElement(text, Text.class, "systemInfo");
		
		text = factory.createSystemInfo(new HashMap<>());
		testElement(text, Text.class, "systemInfo");
		
	}

	@Test
	void sucessWhenCreateFeature_Carousel() {
		
		BatoceraFeature feature = factory.createFeatureCarousel();
		
		testFeatureLvl(feature, CarouselFeature.class, "carousel");
		testFeatureViewLvl(feature, "system", BatoceraCarousel.class, "systemcarousel");
		
		feature = factory.createFeatureCarousel(new HashMap<>());
		
		testFeatureLvl(feature, CarouselFeature.class, "carousel");
		testFeatureViewLvl(feature, "system", BatoceraCarousel.class, "systemcarousel");
		
	}

	@Test
	void sucessWhenCreateFeature_Video() {
		
		BatoceraFeature feature = factory.createFeatureVideo();
		
		testFeatureLvl(feature, VideoFeature.class, "video");
		testFeatureViewLvl(feature, "video", Video.class, "md_video");
		
		feature = factory.createFeatureVideo(new HashMap<>());
		
		testFeatureLvl(feature, VideoFeature.class, "video");
		testFeatureViewLvl(feature, "video", Video.class, "md_video");
		
	}
	
	void testElement(CommonElement element, Class<? extends CommonElement> type, String name) {
		assertInstanceOf(type, element);
		assertEquals(element.getName(), name);
		assertEquals(element.isExtra(), false);
		assertNotNull(element.getContent());
	}
	
	void testFeatureLvl(BatoceraFeature feature, Class<? extends AbstractFeature> type, String supperted) {
		assertInstanceOf(type, feature);
		assertInstanceOf(View.class, feature.getView());
		assertEquals(feature.getSupported(), supperted);
	}
	
	void testFeatureViewLvl(BatoceraFeature feature, String nameView, Class<? extends CommonElement> typeElement, String nameElement) {
		View viewFeature = feature.getView(); 
		assertEquals(viewFeature.getName(), nameView);
		
		List<CommonElement> elements = viewFeature.getElements();
		assertEquals(elements.size(), 1);
		
		CommonElement element = elements.iterator().next();
		assertInstanceOf(typeElement, element);		
		assertEquals(element.getName(), nameElement);
		assertEquals(element.isExtra(), false);
		assertNotNull(element.getContent());
	}

}
