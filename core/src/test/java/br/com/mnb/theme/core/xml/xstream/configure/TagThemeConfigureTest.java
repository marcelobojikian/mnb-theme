package br.com.mnb.theme.core.xml.xstream.configure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.model.Theme;
import br.com.mnb.theme.core.model.View;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.theme.AbstractTheme;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;

class TagThemeConfigureTest {
	
	TagThemeConfigure configure;
	
	@BeforeEach
	public void setup() {
		NamedTagConverter<AbstractViewElement> viewConverter = new NamedTagConverter<AbstractViewElement>();
		NamedTagConverter<AbstractElement> elementConverter = new NamedTagConverter<AbstractElement>();
		configure = new TagThemeConfigure(viewConverter, elementConverter);
		configure.setTheme(Theme.class);
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

}

@XStreamAlias("theme")
class OtherTheme extends AbstractTheme {
	private static final long serialVersionUID = 1L;
}
