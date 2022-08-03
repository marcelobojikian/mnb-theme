package br.com.mnb.theme.recalbox.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mnb.theme.core.builder.ComponentBuilder;
import br.com.mnb.theme.core.xml.element.CommonElement;
import br.com.mnb.theme.core.xml.feature.AbstractFeature;
import br.com.mnb.theme.core.xml.feature.FeatureElement;
import br.com.mnb.theme.core.xml.view.View;
import br.com.mnb.theme.core.xml.view.ViewElement;
import br.com.mnb.theme.recalbox.xml.element.RecalboxCarousel;
import br.com.mnb.theme.recalbox.xml.element.Text;
import br.com.mnb.theme.recalbox.xml.feature.CarouselFeature;
import br.com.mnb.theme.recalbox.xml.theme.RecalboxTheme;

class RecalboxFactoryTest {

	private RecalboxFactory factory;

	@BeforeEach
	public void init() {
		factory = new RecalboxFactory();
	}

	@Test
	void sucessWhenCreateBatoceraThemeWithBatoceraBuilder() {

		ComponentBuilder builder = new ComponentBuilder();
		RecalboxFactory factory = new RecalboxFactory(builder);

		RecalboxTheme theme = factory.createTheme(4);
		assertInstanceOf(RecalboxTheme.class, theme);

		theme = factory.createTheme(4, "includeOne");
		assertInstanceOf(RecalboxTheme.class, theme);

	}

	@Test
	void sucessWhenCreateBatoceraThemeWithVersion() {

		RecalboxTheme theme = factory.createTheme(4);
		assertInstanceOf(RecalboxTheme.class, theme);

	}

	@Test
	void sucessWhenCreateBatoceraThemeWithVersionAndIncludes() {

		RecalboxTheme theme = factory.createTheme(4);
		assertInstanceOf(RecalboxTheme.class, theme);

		theme = factory.createTheme(4, "includeOne");
		assertInstanceOf(RecalboxTheme.class, theme);

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
		
		FeatureElement feature = factory.createFeatureCarousel();
		
		testFeatureLvl(feature, CarouselFeature.class, "carousel");
		testFeatureViewLvl(feature, "system", RecalboxCarousel.class, "systemcarousel");
		
		feature = factory.createFeatureCarousel(new HashMap<>());
		
		testFeatureLvl(feature, CarouselFeature.class, "carousel");
		testFeatureViewLvl(feature, "system", RecalboxCarousel.class, "systemcarousel");
		
	}
	
	void testElement(CommonElement element, Class<? extends CommonElement> type, String name) {
		assertInstanceOf(type, element);
		assertEquals(element.getName(), name);
		assertEquals(element.isExtra(), false);
		assertNotNull(element.getContent());
	}
	
	void testFeatureLvl(FeatureElement feature, Class<? extends AbstractFeature> type, String supperted) {
		assertInstanceOf(type, feature);
		assertInstanceOf(View.class, feature.getView());
		assertEquals(feature.getSupported(), supperted);
	}
	
	void testFeatureViewLvl(FeatureElement feature, String nameView, Class<? extends CommonElement> typeElement, String nameElement) {
		ViewElement viewFeature = feature.getView(); 
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
