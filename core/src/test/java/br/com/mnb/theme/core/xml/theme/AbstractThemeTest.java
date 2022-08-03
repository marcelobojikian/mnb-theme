package br.com.mnb.theme.core.xml.theme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;

import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.model.Feature;
import br.com.mnb.theme.core.model.SecondElement;
import br.com.mnb.theme.core.model.SecondFeature;
import br.com.mnb.theme.core.model.Theme;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.feature.AbstractFeature;
import br.com.mnb.theme.core.xml.feature.FeatureElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;
import br.com.mnb.theme.core.xml.view.View;
import br.com.mnb.theme.core.xml.view.ViewElement;
import br.com.mnb.theme.core.xml.xstream.XStreamBuilder;

class AbstractThemeTest {

	XStream xstream;

	@BeforeEach
	public void setup() {
		// @formatter:off
		xstream = XStreamBuilder
				.create()
					.configTheme(Theme.class)
					.configContent()
					.configElement(new NamedTagConverter<AbstractElement>())
					.configView(new NamedTagConverter<AbstractViewElement>())
					.configFeature(new NamedTagConverter<AbstractFeature>())
					.addAlias("feature", FeatureElement.class)
					.addFeature(Feature.class)
					.addFeature(SecondFeature.class)
					.addView(View.class)
					.addElement(Element.class)
					.addElement(SecondElement.class)
				.build();
		// @formatter:on
	}

	@Test
	public void whenCreateTheme_DefaultValues() {

		Theme theme = new Theme();

		assertNull(theme.getFormatVersion());
		assertTrue(theme.getViewElements().isEmpty());
		assertTrue(theme.getIncludes().isEmpty());

	}

	@Test
	public void sucessWhenCreateXml() {

		Theme theme = new Theme();

		String contentXml = xstream.toXML(theme);
		String result = "<theme/>";

		assertEquals(contentXml, result);

	}

	@Test
	public void sucessWhenCreateXml_ViewWithFormatVersion() {

		Theme theme = new Theme();
		theme.setFormatVersion(4);

		String contentXml = xstream.toXML(theme);
		// @formatter:off
		String result = "<theme>\n"
					  + "  <formatVersion>4</formatVersion>\n"
					  + "</theme>";
		// @formatter:on

		assertEquals(contentXml, result);

	}

	@Test
	public void sucessWhenCreateXml_ThemeWithOneView() {

		Theme theme = new Theme();

		View view = new View();
		view.setName("ViewElement");

		theme.addViews(view);

		String contentXml = xstream.toXML(theme);
		// @formatter:off
		String result = "<theme>\n"
					  + "  <view name=\"ViewElement\"/>\n"
					  + "</theme>";
		// @formatter:on

		assertEquals(contentXml, result);

	}

	@Test
	public void sucessWhenCreateXml_ThemeWithTwoView() {

		Theme theme = new Theme();

		View firstView = new View();
		firstView.setName("ViewElement");

		View secondView = new View();
		secondView.setName("SecondViewElement");

		theme.setViewElements(Arrays.asList(firstView, secondView));

		String contentXml = xstream.toXML(theme);
		// @formatter:off
		String result = "<theme>\n"
					  + "  <view name=\"ViewElement\"/>\n"
					  + "  <view name=\"SecondViewElement\"/>\n"
					  + "</theme>";
		// @formatter:on

		assertEquals(contentXml, result);

	}

	@Test
	public void sucessWhenCreateXml_ThemeWithOneInclude() {

		Theme theme = new Theme();

		theme.getIncludes().add("IncludeName");

		String contentXml = xstream.toXML(theme);
		// @formatter:off
		String result = "<theme>\n"
					  + "  <include>IncludeName</include>\n"
					  + "</theme>";
		// @formatter:on

		assertEquals(contentXml, result);

	}

	@Test
	public void sucessWhenCreateXml_ThemeWithTwoInclude() {

		Theme theme = new Theme();

		theme.setIncludes(Arrays.asList("IncludeName", "SecondIncludeName"));

		String contentXml = xstream.toXML(theme);
		// @formatter:off
		String result = "<theme>\n"
					  + "  <include>IncludeName</include>\n"
					  + "  <include>SecondIncludeName</include>\n"
					  + "</theme>";
		// @formatter:on

		assertEquals(contentXml, result);

	}

	@Test
	public void sucessWhenCreateXml_ThemeWithOneFeature() {

		Theme theme = new Theme();

		Feature feature = new Feature();

		theme.getFeatures().add(feature);

		String contentXml = xstream.toXML(theme);
		// @formatter:off
		String result = "<theme>\n"
				  	  + "  <feature supported=\"first\"/>\n"
					  + "</theme>";
		// @formatter:on

		assertEquals(contentXml, result);

	}

	@Test
	public void sucessWhenCreateXml_ThemeWithTwoFeature() {

		Theme theme = new Theme();

		Feature firstFeature = new Feature();
		SecondFeature secondFeature = new SecondFeature();

		theme.setFeatures(Arrays.asList(firstFeature, secondFeature));

		String contentXml = xstream.toXML(theme);
		// @formatter:off
		String result = "<theme>\n"
					  + "  <feature supported=\"first\"/>\n"
					  + "  <feature supported=\"second\"/>\n"
					  + "</theme>";
		// @formatter:on

		assertEquals(contentXml, result);

	}

	@Test
	void sucessWhenConvertToJava_Theme() {

		// @formatter:off
		String result = "<theme>\n"
					  + "  <formatVersion>4</formatVersion>\n"
					  + "  <include>Include One</include>\n"
					  + "  <include>Include Two</include>\n"
					  + "  <view name=\"ViewOne\"/>\n"
					  + "  <view name=\"ViewTwo\"/>\n"
					  + "  <feature supported=\"first\"/>\n"
					  + "  <feature supported=\"second\"/>\n"
					  + "</theme>";
		// @formatter:on

		ThemeElement themeObj = (ThemeElement) xstream.fromXML(result);

		assertNotNull(themeObj);
		assertEquals(themeObj.getFormatVersion(), 4);

		ViewElement viewOne = themeObj.getViewElements().get(0);
		assertInstanceOf(View.class, viewOne);
		assertEquals(viewOne.getName(), "ViewOne");

		ViewElement viewTwo = themeObj.getViewElements().get(1);
		assertInstanceOf(View.class, viewTwo);
		assertEquals(viewTwo.getName(), "ViewTwo");
		
		FeatureElement carousel = themeObj.getFeatures().get(0);
		assertInstanceOf(Feature.class, carousel);
		assertEquals(carousel.getSupported(), "first");
		
		FeatureElement video = themeObj.getFeatures().get(1);
		assertInstanceOf(SecondFeature.class, video);
		assertEquals(video.getSupported(), "second");

	}

}
