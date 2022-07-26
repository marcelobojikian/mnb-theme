package br.com.mnb.theme.core.xml.xstream.configure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import br.com.mnb.theme.core.model.Feature;
import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.model.Theme;
import br.com.mnb.theme.core.model.SecondFeature;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.feature.AbstractFeature;
import br.com.mnb.theme.core.xml.feature.FeatureElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.theme.AbstractTheme;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;
import br.com.mnb.theme.core.xml.view.View;

class TagThemeConfigureTest {
	
	TagThemeConfigure configure;
	
	@BeforeEach
	public void setup() {
		NamedTagConverter<AbstractViewElement> viewConverter = new NamedTagConverter<AbstractViewElement>();
		NamedTagConverter<AbstractElement> elementConverter = new NamedTagConverter<AbstractElement>();
		NamedTagConverter<AbstractFeature> featureConverter = new NamedTagConverter<AbstractFeature>();
		configure = new TagThemeConfigure(featureConverter, viewConverter, elementConverter);
		configure.setTheme(Theme.class);
		configure.addAlias("feature", FeatureElement.class);
		configure.addFeature("first", Feature.class);
		configure.addFeature("second", SecondFeature.class);
		configure.addView("view", View.class);
		configure.addElement("element", Element.class);
	}

	@Test
	@DisplayName("Should change theme")
	public void sucessWhenChangeTheme() {
		configure.setTheme(OtherTheme.class);
		XStream xstream = configure.getXStream();
		String resultExpected = "<"+TagThemeConfigure.THEME_ELEMENT_NAME+"/>";
		String result = xstream.toXML(new OtherTheme());
		assertEquals(resultExpected, result);
	}

	@Test
	@DisplayName("Should convert to XML")
	public void sucessWhenConvertToXml() {	
		XStream xstream = configure.getXStream();	
		String resultExpected = "<"+TagThemeConfigure.THEME_ELEMENT_NAME+"/>";
		String result = xstream.toXML(new Theme());
		assertEquals(resultExpected, result);
	}

	@Test
	@DisplayName("Should convert to Java")
	public void sucessWhenConvertToJava() {
		XStream xstream = configure.getXStream();
		Theme theme = (Theme) xstream.fromXML("<"+TagThemeConfigure.THEME_ELEMENT_NAME+"/>");
		assertNotNull(theme);
	}

	@Test
	@DisplayName("Should convert to XML With SecondFeature")
	public void sucessWhenConvertToXmlWithSecondFeature() {	
		XStream xstream = configure.getXStream();	
		// @formatter:off
		String resultExpected = "<"+TagThemeConfigure.THEME_ELEMENT_NAME+">\n"
							  + "  <feature supported=\"second\"/>\n"
							  + "</"+TagThemeConfigure.THEME_ELEMENT_NAME+">";
		Theme theme = new Theme();
		// @formatter:on
		theme.addFeatures(new SecondFeature());
		String result = xstream.toXML(theme);
		assertEquals(resultExpected, result);
	}

	@Test
	@DisplayName("Should convert to Java With SecondFeature")
	public void sucessWhenConvertToJavaWithSecondFeature() {
		XStream xstream = configure.getXStream();
		// @formatter:off
		Object obj = xstream.fromXML("<"+TagThemeConfigure.THEME_ELEMENT_NAME+">\n"
								   + "  <feature supported=\"second\"/>\n"
								   + "</"+TagThemeConfigure.THEME_ELEMENT_NAME+">");
		// @formatter:on
		assertNotNull(obj);
		assertInstanceOf(Theme.class, obj);
	}
	
	@Test
	@DisplayName("Should convert to XML With Feature")
	public void sucessWhenConvertToXmlWithFeature() {	
		XStream xstream = configure.getXStream();	
		// @formatter:off
		String resultExpected = "<"+TagThemeConfigure.THEME_ELEMENT_NAME+">\n"
							  + "  <feature supported=\"first\"/>\n"
							  + "</"+TagThemeConfigure.THEME_ELEMENT_NAME+">";
		Theme theme = new Theme();
		// @formatter:on
		theme.addFeatures(new Feature());
		String result = xstream.toXML(theme);
		assertEquals(resultExpected, result);
	}

	@Test
	@DisplayName("Should convert to Java With Feature")
	public void sucessWhenConvertToJavaWithFeature() {
		XStream xstream = configure.getXStream();
		// @formatter:off
		Object obj = xstream.fromXML("<"+TagThemeConfigure.THEME_ELEMENT_NAME+">\n"
								   + "  <feature supported=\"first\">\n"
								   + "    <view/>\n"
								   + "  </feature>\n"
								   + "</"+TagThemeConfigure.THEME_ELEMENT_NAME+">");
		// @formatter:on
		assertNotNull(obj);
		assertInstanceOf(Theme.class, obj);
	}
	
	@Test
	@DisplayName("Should convert to XML With 2 Feature")
	public void sucessWhenConvertToXmlWithTwoFeature() {	
		XStream xstream = configure.getXStream();	
		// @formatter:off
		String resultExpected = "<"+TagThemeConfigure.THEME_ELEMENT_NAME+">\n"
							  + "  <feature supported=\"first\"/>\n"
							  + "  <feature supported=\"second\"/>\n"
							  + "</"+TagThemeConfigure.THEME_ELEMENT_NAME+">";
		// @formatter:on
		Theme theme = new Theme();
		theme.addFeatures(new Feature(), new SecondFeature());
		String result = xstream.toXML(theme);
		assertEquals(resultExpected, result);
	}

	@Test
	@DisplayName("Should convert to Java With 2 Feature")
	public void sucessWhenConvertToJavaWithTwoFeature() {
		XStream xstream = configure.getXStream();
		// @formatter:off
		Object obj = xstream.fromXML("<"+TagThemeConfigure.THEME_ELEMENT_NAME+">\n"
								   + "  <feature supported=\"first\"/>\n"
								   + "  <feature supported=\"second\"/>\n"
								   + "</"+TagThemeConfigure.THEME_ELEMENT_NAME+">");
		// @formatter:on
		assertNotNull(obj);
		assertInstanceOf(Theme.class, obj);
	}

}

@XStreamAlias("theme")
class OtherTheme extends AbstractTheme {
	private static final long serialVersionUID = 1L;
}
