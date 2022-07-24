package br.com.mnb.theme.batocera.xml.xstream.configure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;

import br.com.mnb.theme.batocera.xml.feature.CarouselFeature;
import br.com.mnb.theme.batocera.xml.feature.VideoFeature;
import br.com.mnb.theme.batocera.xml.theme.BatoceraTheme;

class BatoceraConfigureTest {
	
	BatoceraConfigure configure = new BatoceraConfigure();

	@Test
	@DisplayName("Should convert to XML")
	public void sucessWhenConvertToXml() {	
		XStream xstream = configure.getXStream();	
		String resultExpected = "<"+TagThemeConfigure.THEME_ELEMENT_NAME+"/>";
		String result = xstream.toXML(new BatoceraTheme());
		assertEquals(resultExpected, result);
	}

	@Test
	@DisplayName("Should convert to Java")
	public void sucessWhenConvertToJava() {
		XStream xstream = configure.getXStream();
		Object obj = xstream.fromXML("<"+TagThemeConfigure.THEME_ELEMENT_NAME+"/>");
		assertNotNull(obj);
		assertInstanceOf(BatoceraTheme.class, obj);
	}

	@Test
	@DisplayName("Should convert to XML With VideoFeature")
	public void sucessWhenConvertToXmlWithVideoFeature() {	
		XStream xstream = configure.getXStream();	
		// @formatter:off
		String resultExpected = "<"+TagThemeConfigure.THEME_ELEMENT_NAME+">\n"
							  + "  <feature supported=\"video\"/>\n"
							  + "</"+TagThemeConfigure.THEME_ELEMENT_NAME+">";
		BatoceraTheme theme = new BatoceraTheme();
		// @formatter:on
		theme.addFeatures(new VideoFeature());
		String result = xstream.toXML(theme);
		assertEquals(resultExpected, result);
	}

	@Test
	@DisplayName("Should convert to Java With VideoFeature")
	public void sucessWhenConvertToJavaWithVideoFeature() {
		XStream xstream = configure.getXStream();
		// @formatter:off
		Object obj = xstream.fromXML("<"+TagThemeConfigure.THEME_ELEMENT_NAME+">\n"
								   + "  <feature  supported=\"video\"/>\n"
								   + "</"+TagThemeConfigure.THEME_ELEMENT_NAME+">");
		// @formatter:on
		assertNotNull(obj);
		assertInstanceOf(BatoceraTheme.class, obj);
	}
	
	@Test
	@DisplayName("Should convert to XML With CarouselFeature")
	public void sucessWhenConvertToXmlWithCarouselFeature() {	
		XStream xstream = configure.getXStream();	
		// @formatter:off
		String resultExpected = "<"+TagThemeConfigure.THEME_ELEMENT_NAME+">\n"
							  + "  <feature supported=\"carousel\"/>\n"
							  + "</"+TagThemeConfigure.THEME_ELEMENT_NAME+">";
		BatoceraTheme theme = new BatoceraTheme();
		// @formatter:on
		theme.addFeatures(new CarouselFeature());
		String result = xstream.toXML(theme);
		assertEquals(resultExpected, result);
	}

	@Test
	@DisplayName("Should convert to Java With CarouselFeature")
	public void sucessWhenConvertToJavaWithCarouselFeature() {
		XStream xstream = configure.getXStream();
		// @formatter:off
		Object obj = xstream.fromXML("<"+TagThemeConfigure.THEME_ELEMENT_NAME+">\n"
								   + "  <feature class=\"feature\" supported=\"carousel\">\n"
								   + "    <view/>\n"
								   + "  </feature>\n"
								   + "</"+TagThemeConfigure.THEME_ELEMENT_NAME+">");
		// @formatter:on
		assertNotNull(obj);
		assertInstanceOf(BatoceraTheme.class, obj);
	}
	
	@Test
	@DisplayName("Should convert to XML With 2 Feature")
	public void sucessWhenConvertToXmlWithTwoFeature() {	
		XStream xstream = configure.getXStream();	
		// @formatter:off
		String resultExpected = "<"+TagThemeConfigure.THEME_ELEMENT_NAME+">\n"
							  + "  <feature supported=\"carousel\"/>\n"
							  + "  <feature supported=\"video\"/>\n"
							  + "</"+TagThemeConfigure.THEME_ELEMENT_NAME+">";
		// @formatter:on
		BatoceraTheme theme = new BatoceraTheme();
		theme.addFeatures(new CarouselFeature(), new VideoFeature());
		String result = xstream.toXML(theme);
		assertEquals(resultExpected, result);
	}

	@Test
	@DisplayName("Should convert to Java With 2 Feature")
	public void sucessWhenConvertToJavaWithTwoFeature() {
		XStream xstream = configure.getXStream();
		// @formatter:off
		Object obj = xstream.fromXML("<"+TagThemeConfigure.THEME_ELEMENT_NAME+">\n"
								   + "  <feature class=\"feature\" supported=\"carousel\"/>\n"
								   + "  <feature class=\"feature\" supported=\"video\"/>\n"
								   + "</"+TagThemeConfigure.THEME_ELEMENT_NAME+">");
		// @formatter:on
		assertNotNull(obj);
		assertInstanceOf(BatoceraTheme.class, obj);
	}

}
