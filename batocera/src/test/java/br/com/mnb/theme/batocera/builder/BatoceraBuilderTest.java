package br.com.mnb.theme.batocera.builder;

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
import br.com.mnb.theme.batocera.xml.feature.CarouselFeature;
import br.com.mnb.theme.batocera.xml.feature.VideoFeature;
import br.com.mnb.theme.batocera.xml.theme.BatoceraTheme;
import br.com.mnb.theme.batocera.xml.view.View;

class BatoceraBuilderTest {
	
	private BatoceraBuilder builder = new BatoceraBuilder();

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
