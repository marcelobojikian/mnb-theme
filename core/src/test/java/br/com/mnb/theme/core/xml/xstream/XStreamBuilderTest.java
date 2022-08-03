package br.com.mnb.theme.core.xml.xstream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.model.Feature;
import br.com.mnb.theme.core.model.SecondFeature;
import br.com.mnb.theme.core.model.Theme;
import br.com.mnb.theme.core.xml.Content;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.feature.AbstractFeature;
import br.com.mnb.theme.core.xml.feature.FeatureElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.theme.AbstractTheme;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;
import br.com.mnb.theme.core.xml.view.View;

@ExtendWith(MockitoExtension.class)
class XStreamBuilderTest {

	NamedTagConverter<AbstractFeature> featureConverter = new NamedTagConverter<AbstractFeature>();
	NamedTagConverter<AbstractViewElement> viewConverter = new NamedTagConverter<AbstractViewElement>();
	NamedTagConverter<AbstractElement> elementConverter = new NamedTagConverter<AbstractElement>();

	@Test
	void successCreateEmptyConfigure() {
		XStreamBuilder builder = XStreamBuilder.create();
		assertNotNull(builder);

		XStream xstream = builder.build();
		assertNotNull(xstream);
	}

	@Nested
	@DisplayName("Builder Content Configure")
	class BuilderContentConfigureTest {

		final String TAG = "element";
		// @formatter:off	
		XStream xstream = XStreamBuilder
				.create()
					.configContent(TAG)
				.build();
		// @formatter:on

		@Test
		@DisplayName("Should convert to XML")
		public void sucessWhenCreateContentConfigureAndConvertToXml() {

			String resultExpected = "<" + TAG + "/>";
			String result = xstream.toXML(new Content());
			assertEquals(resultExpected, result);
		}

		@Test
		@DisplayName("Should convert to Java")
		public void sucessWhenCreateContentConfigureAndConvertToJava() {

			Content content = (Content) xstream.fromXML("<" + TAG + "/>");
			assertNotNull(content);
			assertTrue(content.getAttributes().isEmpty());
		}

	}

	@Nested
	@DisplayName("Builder Element Configure")
	class BuilderElementConfigureTest {

		// @formatter:off
		XStream xstream = XStreamBuilder
				.create()
					.configContent()
					.configElement(elementConverter)
					.addElement(Element.class)
				.build();
		// @formatter:on

		@Test
		@DisplayName("Should throw error when not configure de element")
		public void sucessWhenNotConfigureFeature() {

			assertThrows(NullPointerException.class, () -> {
				XStreamBuilder.create().addElement(Element.class);
			});

		}

		@Test
		@DisplayName("Should convert to XML")
		void sucessWhenConvertToXml() {

			String resultExpected = "<element name=\"tagname\"/>";

			Element element = new Element();
			element.setName("tagname");

			String result = xstream.toXML(element);
			assertEquals(resultExpected, result);
		}

		@Test
		@DisplayName("Should convert to Java")
		void sucessWhenConvertToJava() {

			Element element = (Element) xstream.fromXML("<element name=\"tagname\"/>");
			assertNotNull(element);
			assertEquals("tagname", element.getName());
			assertFalse(element.isExtra());
		}
	}

	@Nested
	@DisplayName("Builder View Configure")
	class BuilderViewConfigureTest {

		// @formatter:off
		XStream xstream = XStreamBuilder
				.create()
					.configElement(elementConverter)
					.configView(viewConverter)
					.addView(View.class)
				.build();
		// @formatter:on

		@Test
		@DisplayName("Should throw error when not configure de view")
		public void sucessWhenNotConfigureFeature() {

			assertThrows(NullPointerException.class, () -> {
				XStreamBuilder.create().addView(View.class);
			});

		}

		@Test
		@DisplayName("Should convert to XML")
		public void sucessWhenConvertToXml() {
			String resultExpected = "<view/>";
			String result = xstream.toXML(new View());
			assertEquals(resultExpected, result);
		}

		@Test
		@DisplayName("Should convert to Java")
		public void sucessWhenConvertToJava() {
			View view = (View) xstream.fromXML("<view/>");
			assertNotNull(view);
			assertEquals(null, view.getName());
		}

	}

	@Nested
	@DisplayName("Builder Feature Configure")
	class BuilderFeatureConfigureTest {

		// @formatter:off
		XStream xstream = XStreamBuilder
				.create()
					.configFeature(featureConverter)
					.configView(viewConverter)
					.configElement(elementConverter)
					.addAlias("feature", FeatureElement.class)
					.addFeature(Feature.class)
					.addFeature(SecondFeature.class)
					.addView(View.class)
					.addElement(Element.class)
				.build();
		// @formatter:on

		@Test
		@DisplayName("Should throw error when not configure de feature")
		public void sucessWhenNotConfigureFeature() {

			assertThrows(NullPointerException.class, () -> {
				XStreamBuilder.create().addFeature(Feature.class);
			});

		}

		@Test
		@DisplayName("Should convert to XML Carousel")
		public void sucessWhenConvertToXmlCarousel() {
			String resultExpected = "<feature supported=\"first\"/>";
			String result = xstream.toXML(new Feature());
			assertEquals(resultExpected, result);
		}

		@Test
		@DisplayName("Should convert to Java Carousel")
		public void sucessWhenConvertToJavaCarousel() {
			Feature feature = (Feature) xstream.fromXML("<feature supported=\"first\"/>");
			assertNotNull(feature);
			assertEquals("first", feature.getSupported());
		}

		@Test
		@DisplayName("Should convert to XML Video")
		public void sucessWhenConvertToXmlVideo() {
			String resultExpected = "<feature supported=\"second\"/>";
			String result = xstream.toXML(new SecondFeature());
			assertEquals(resultExpected, result);
		}

		@Test
		@DisplayName("Should convert to Java Video")
		public void sucessWhenConvertToJavaVideo() {
			SecondFeature feature = (SecondFeature) xstream.fromXML("<feature supported=\"second\"/>");
			assertNotNull(feature);
			assertEquals("second", feature.getSupported());
		}

	}

	@Nested
	@DisplayName("Builder Theme Configure")
	class BuilderThemeConfigureTest {

		final static String THEME_ELEMENT_NAME = "theme";
		XStreamBuilder configure;

		@BeforeEach
		public void setup() {

			// @formatter:off
			configure = XStreamBuilder
					.create()
						.configTheme(Theme.class)
						.configFeature(featureConverter)
						.configView(viewConverter)
						.configElement(elementConverter)
						.addAlias("feature", FeatureElement.class)
						.addFeature(Feature.class)
						.addFeature(SecondFeature.class)
						.addView(View.class)
						.addElement(Element.class);
			// @formatter:on

		}

		@Test
		@DisplayName("Should change theme")
		public void sucessWhenChangeTheme() {
			configure.configTheme(OtherTheme.class);
			XStream xstream = configure.build();
			String resultExpected = "<" + THEME_ELEMENT_NAME + "/>";
			String result = xstream.toXML(new OtherTheme());
			assertEquals(resultExpected, result);
		}

		@Test
		@DisplayName("Should convert to XML")
		public void sucessWhenConvertToXml() {
			XStream xstream = configure.build();
			String resultExpected = "<" + THEME_ELEMENT_NAME + "/>";
			String result = xstream.toXML(new Theme());
			assertEquals(resultExpected, result);
		}

		@Test
		@DisplayName("Should convert to Java")
		public void sucessWhenConvertToJava() {
			XStream xstream = configure.build();
			Theme theme = (Theme) xstream.fromXML("<" + THEME_ELEMENT_NAME + "/>");
			assertNotNull(theme);
		}

		@Test
		@DisplayName("Should convert to XML With SecondFeature")
		public void sucessWhenConvertToXmlWithSecondFeature() {
			XStream xstream = configure.build();
			// @formatter:off
			String resultExpected = "<"+THEME_ELEMENT_NAME+">\n"
								  + "  <feature supported=\"second\"/>\n"
								  + "</"+THEME_ELEMENT_NAME+">";
			Theme theme = new Theme();
			// @formatter:on
			theme.addFeatures(new SecondFeature());
			String result = xstream.toXML(theme);
			assertEquals(resultExpected, result);
		}

		@Test
		@DisplayName("Should convert to Java With SecondFeature")
		public void sucessWhenConvertToJavaWithSecondFeature() {
			XStream xstream = configure.build();
			// @formatter:off
			Object obj = xstream.fromXML("<"+THEME_ELEMENT_NAME+">\n"
									   + "  <feature supported=\"second\"/>\n"
									   + "</"+THEME_ELEMENT_NAME+">");
			// @formatter:on
			assertNotNull(obj);
			assertInstanceOf(Theme.class, obj);
		}

		@Test
		@DisplayName("Should convert to XML With Feature")
		public void sucessWhenConvertToXmlWithFeature() {
			XStream xstream = configure.build();
			// @formatter:off
			String resultExpected = "<"+THEME_ELEMENT_NAME+">\n"
								  + "  <feature supported=\"first\"/>\n"
								  + "</"+THEME_ELEMENT_NAME+">";
			Theme theme = new Theme();
			// @formatter:on
			theme.addFeatures(new Feature());
			String result = xstream.toXML(theme);
			assertEquals(resultExpected, result);
		}

		@Test
		@DisplayName("Should convert to Java With Feature")
		public void sucessWhenConvertToJavaWithFeature() {
			XStream xstream = configure.build();
			// @formatter:off
			Object obj = xstream.fromXML("<"+THEME_ELEMENT_NAME+">\n"
									   + "  <feature supported=\"first\">\n"
									   + "    <view/>\n"
									   + "  </feature>\n"
									   + "</"+THEME_ELEMENT_NAME+">");
			// @formatter:on
			assertNotNull(obj);
			assertInstanceOf(Theme.class, obj);
		}

		@Test
		@DisplayName("Should convert to XML With 2 Feature")
		public void sucessWhenConvertToXmlWithTwoFeature() {
			XStream xstream = configure.build();
			// @formatter:off
			String resultExpected = "<"+THEME_ELEMENT_NAME+">\n"
								  + "  <feature supported=\"first\"/>\n"
								  + "  <feature supported=\"second\"/>\n"
								  + "</"+THEME_ELEMENT_NAME+">";
			// @formatter:on
			Theme theme = new Theme();
			theme.addFeatures(new Feature(), new SecondFeature());
			String result = xstream.toXML(theme);
			assertEquals(resultExpected, result);
		}

		@Test
		@DisplayName("Should convert to Java With 2 Feature")
		public void sucessWhenConvertToJavaWithTwoFeature() {
			XStream xstream = configure.build();
			// @formatter:off
			Object obj = xstream.fromXML("<"+THEME_ELEMENT_NAME+">\n"
									   + "  <feature supported=\"first\"/>\n"
									   + "  <feature supported=\"second\"/>\n"
									   + "</"+THEME_ELEMENT_NAME+">");
			// @formatter:on
			assertNotNull(obj);
			assertInstanceOf(Theme.class, obj);
		}

	}
}

@XStreamAlias("theme")
class OtherTheme extends AbstractTheme {
	private static final long serialVersionUID = 1L;
}
