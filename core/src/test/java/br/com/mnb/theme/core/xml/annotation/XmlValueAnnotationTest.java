package br.com.mnb.theme.core.xml.annotation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Format;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.model.Feature;
import br.com.mnb.theme.core.model.SecondElement;
import br.com.mnb.theme.core.model.SecondFeature;
import br.com.mnb.theme.core.model.Theme;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.exception.TagAliasNotFoundException;
import br.com.mnb.theme.core.xml.feature.AbstractFeature;
import br.com.mnb.theme.core.xml.theme.AbstractTheme;

class XmlValueAnnotationTest {
	
	@Test
	void successWhenGetXmlValueByTheme() {
		
		XmlValueAnnotation<AbstractTheme> xmlValue = new XmlValueAnnotation<>();

		String expected = "theme";
		String theme = xmlValue.getElementName(Theme.class);
		assertEquals(expected, theme);		
		
	}

	@Test
	void successWhenGetXmlValueByFeature() {
		
		XmlValueAnnotation<AbstractFeature> xmlValue = new XmlValueAnnotation<>();
		
		String expectedFirst = "first";
		String elementFirst = xmlValue.getElementName(Feature.class);
		assertEquals(expectedFirst, elementFirst);
		
		String expectedSecond = "second";
		String elementSecond = xmlValue.getElementName(SecondFeature.class);
		assertEquals(expectedSecond, elementSecond);
		
	}

	@Test
	void successWhenGetXmlValueByElement() {
		
		XmlValueAnnotation<AbstractElement> xmlValue = new XmlValueAnnotation<>();
		
		String expectedFirst = "element";
		String elementFirst = xmlValue.getElementName(Element.class);
		assertEquals(expectedFirst, elementFirst);
		
		String expectedSecond = "second";
		String elementSecond = xmlValue.getElementName(SecondElement.class);
		assertEquals(expectedSecond, elementSecond);
		
	}

	@Test
	void failWhenEvaluateGetXmlValue() {
		
		XmlValueAnnotation<AbstractElement> xmlValue = new XmlValueAnnotation<>();
		
		assertThrows(TagAliasNotFoundException.class, () -> {
			xmlValue.getElementName(AbstractElement.class);
		});
		
	}

	@Test
	void successWhenGetXmlValueHaveManyAnnotations() {
		
		XmlValueAnnotation<AbstractElement> xmlValue = new XmlValueAnnotation<>();
		
		String expected = "ManyAnnotations";
		String element = xmlValue.getElementName(ManyAnnotations.class);
		assertEquals(expected, element);
		
	}

	@Test
	void successWhenGetXmlValueHaveManyAnnotationsInFeatures() {
		
		XmlValueAnnotation<AbstractFeature> xmlValue = new XmlValueAnnotation<>();
		
		String expected = "yes";
		String element = xmlValue.getElementName(ManyAnnotationsFeature.class);
		assertEquals(expected, element);
		
	}

	@Test
	void failWhenGetXmlValueCantInstaceClass() {
		
		XmlValueAnnotation<AbstractFeature> xmlValue = new XmlValueAnnotation<>();
		
		assertThrows(TagAliasNotFoundException.class, () -> {
			xmlValue.getElementName(InvalidFeature.class);
		});
		
	}

	@XStreamAlias("InvalidFeature")
	class InvalidFeature extends AbstractFeature {
		private static final long serialVersionUID = 1L;

		@Format(formats = { "" })
		@XStreamAlias("supported")
		@XStreamAsAttribute
		private String supported = "yes";

		@Override
		public String getSupported() {
			return supported;
		}
	}

}

@Disabled
@XStreamAlias("ManyAnnotations")
class ManyAnnotations extends AbstractElement {
	private static final long serialVersionUID = 1L;
}

@Disabled
@XStreamAlias("ManyAnnotationsFeature")
class ManyAnnotationsFeature extends AbstractFeature {
	private static final long serialVersionUID = 1L;

	@Format(formats = { "" })
	@XStreamAlias("other")
	@XStreamAsAttribute
	private String other = "other";

	@Format(formats = { "" })
	@XStreamAlias("supported")
	@XStreamAsAttribute
	private String supported = "yes";

	@Override
	public String getSupported() {
		return supported;
	}
}
