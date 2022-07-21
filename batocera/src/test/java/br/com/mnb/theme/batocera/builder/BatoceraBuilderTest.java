package br.com.mnb.theme.batocera.builder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mnb.theme.batocera.tools.ElementTester;
import br.com.mnb.theme.batocera.tools.FeatureTester;
import br.com.mnb.theme.batocera.tools.ThemeTester;
import br.com.mnb.theme.batocera.tools.ViewTester;
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
import br.com.mnb.theme.batocera.xml.feature.CarouselFeature;
import br.com.mnb.theme.batocera.xml.feature.VideoFeature;
import br.com.mnb.theme.batocera.xml.theme.BatoceraTheme;
import br.com.mnb.theme.batocera.xml.view.View;
import br.com.mnb.theme.core.factory.SimpleFactory;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.theme.AbstractTheme;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;

class BatoceraBuilderTest {
	
	BatoceraBuilder builder = new BatoceraBuilder();
	
	@BeforeEach
	@SuppressWarnings("unchecked")
	public void setup() {
		
		SimpleFactory<AbstractTheme> themeFactory = mock(SimpleFactory.class);
		when(themeFactory.create(BatoceraTheme.class)).thenReturn(new BatoceraTheme());

		SimpleFactory<AbstractViewElement> viewFactory = mock(SimpleFactory.class);
		when(viewFactory.create(View.class)).thenReturn(new View());

		SimpleFactory<AbstractElement> elementFactory = mock(SimpleFactory.class);
		when(elementFactory.create(BatoceraCarousel.class)).thenReturn(new BatoceraCarousel());
		when(elementFactory.create(Text.class)).thenReturn(new Text());
		when(elementFactory.create(Image.class)).thenReturn(new Image());
		when(elementFactory.create(Sound.class)).thenReturn(new Sound());
		when(elementFactory.create(Rating.class)).thenReturn(new Rating());
		when(elementFactory.create(Video.class)).thenReturn(new Video());
		when(elementFactory.create(TextList.class)).thenReturn(new TextList());
		when(elementFactory.create(Ninepatch.class)).thenReturn(new Ninepatch());
		when(elementFactory.create(HelpSystem.class)).thenReturn(new HelpSystem());
		when(elementFactory.create(Datetime.class)).thenReturn(new Datetime());

		SimpleFactory<AbstractFeature> featureFactory = mock(SimpleFactory.class);
		when(featureFactory.create(CarouselFeature.class)).thenReturn(new CarouselFeature());
		when(featureFactory.create(VideoFeature.class)).thenReturn(new VideoFeature());
		
		builder = new BatoceraBuilder();
		
		builder.setThemeFactory(themeFactory);
		builder.setViewFactory(viewFactory);
		builder.setElementFactory(elementFactory);
		builder.setFeatureFactory(featureFactory);
	}
	
	@Test
	void sucessWhenCreateBuilderHaveAllConverters() {
		BatoceraBuilder builder = new BatoceraBuilder();
		assertNotNull(builder.getThemeFactory());
		assertNotNull(builder.getViewFactory());
		assertNotNull(builder.getElementFactory());
		assertNotNull(builder.getFeatureFactory());
	}

	@Test
	void sucessWhenCreateTheme() {
		
		ThemeTester tester = new ThemeTester(builder); 
		
		tester.testTheme(BatoceraTheme.class);
	}

	@Test
	void sucessWhenCreateFeature() {
		
		FeatureTester tester = new FeatureTester(builder);
		
		tester.testFeature(CarouselFeature.class, "carousel");
		tester.testFeature(VideoFeature.class, "video");
	}

	@Test
	void sucessWhenCreateView() {
		
		ViewTester tester = new ViewTester(builder);
		
		tester.testView(View.class);
	}

	@Test
	void sucessWhenCreateElement() {
		
		ElementTester tester = new ElementTester(builder);

		tester.testElement(BatoceraCarousel.class);
		tester.testElement(Text.class);
		tester.testElement(Image.class);
		tester.testElement(Sound.class);
		tester.testElement(Rating.class);
		tester.testElement(Video.class);
		tester.testElement(TextList.class);
		tester.testElement(Ninepatch.class);
		tester.testElement(HelpSystem.class);
		tester.testElement(Datetime.class);
		
	}

}
