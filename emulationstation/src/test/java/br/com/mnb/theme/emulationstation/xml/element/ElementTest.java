package br.com.mnb.theme.emulationstation.xml.element;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import br.com.mnb.theme.core.xml.Content;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.element.CommonElement;
import br.com.mnb.theme.emulationstation.xml.converter.ElementXmlConverter;

public class ElementTest {

	ElementXmlConverter xstream = new ElementXmlConverter();

	@Test
	public void whenCreateElement_DefaultValues() {
		
		Text text = new Text();
		
		assertNull(text.getName());
		assertEquals(text.isExtra(), false);
		assertNotNull(text.getContent());
		
	}

	@Test
	public void whenConvertElement_WithoutName() {
		
		Text text = new Text();

		assertThrows(NullPointerException.class, () -> {
			xstream.toXML(text);
		});
		
	}

	@Test
	public void whenConvertElement_OnlyWithName() {
		
		Text text = new Text();
		text.setName("TextElement");
		
		String contentXml = xstream.toXML(text);
		String result = "<text name=\"TextElement\"/>";

		assertEquals(contentXml, result);
		
	}

	@Test
	public void sucessWhenCreateXml_Element_Text() {
		sucessWhenCreateXml(new Text(), "text", true, "TextElement");
	}

	@Test
	void sucessWhenConvertToJava_Element_Text() {
		successWhenConvertToJava(Text.class, "text", true, "TextElement");
	}

	@Test
	public void sucessWhenCreateXml_Element_Image() {
		sucessWhenCreateXml(new Image(), "image", true, "ImageElement");
	}

	@Test
	public void sucessWhenConvertToJava_Element_Image() {
		successWhenConvertToJava(Image.class, "image", true, "ImageElement");
	}

	@Test
	public void sucessWhenCreateXml_Element_Sound() {
		sucessWhenCreateXml(new Sound(), "sound", true, "SoundElement");
	}

	@Test
	public void sucessWhenConvertToJava_Element_Sound() {
		successWhenConvertToJava(Sound.class, "sound", true, "SoundElement");
	}

	@Test
	public void sucessWhenCreateXml_Element_Rating() {
		sucessWhenCreateXml(new Rating(), "rating", true, "RatingElement");
	}

	@Test
	public void sucessWhenConvertToJava_Element_Rating() {
		successWhenConvertToJava(Rating.class, "rating", true, "RatingElement");
	}

	@Test
	public void sucessWhenCreateXml_Element_Video() {
		sucessWhenCreateXml(new Video(), "video", true, "VideoElement");
	}

	@Test
	public void sucessWhenConvertToJava_Element_Video() {
		successWhenConvertToJava(Video.class, "video", true, "VideoElement");
	}

	@Test
	public void sucessWhenCreateXml_Element_Textlist() {
		sucessWhenCreateXml(new TextList(), "textlist", true, "TextlistElement");
	}

	@Test
	public void sucessWhenConvertToJava_Element_Textlist() {
		successWhenConvertToJava(TextList.class, "textlist", true, "TextlistElement");
	}

	@Test
	public void sucessWhenCreateXml_Element_Ninepatch() {
		sucessWhenCreateXml(new Ninepatch(), "ninepatch", true, "NinepatchElement");
	}

	@Test
	public void sucessWhenConvertToJava_Element_Ninepatch() {
		successWhenConvertToJava(Ninepatch.class, "ninepatch", true, "NinepatchElement");
	}

	@Test
	public void sucessWhenCreateXml_Element_HelpSystem() {
		sucessWhenCreateXml(new HelpSystem(), "helpsystem", true, "HelpSystemElement");
	}

	@Test
	public void sucessWhenConvertToJava_Element_HelpSystem() {
		successWhenConvertToJava(HelpSystem.class, "helpsystem", true, "HelpSystemElement");
	}

	@Test
	public void sucessWhenCreateXml_Element_Datetime() {
		sucessWhenCreateXml(new Datetime(), "datetime", true, "DatetimeElement");
	}

	@Test
	public void sucessWhenConvertToJava_Element_Datetime() {
		successWhenConvertToJava(Datetime.class, "datetime", true, "DatetimeElement");
	}

	void sucessWhenCreateXml(AbstractElement element, String tagName, boolean attributeExtra,  String attributeName) {

		element.setName(attributeName);
		element.setExtra(attributeExtra);
		element.setContent(new Content());

		String elementXml = xstream.toXML(element);
		
		StringBuilder result = new StringBuilder("<"+tagName);
		if(attributeExtra) {
			result.append(" extra=\"true\"");
		}
		result.append(" name=\""+attributeName+"\"/>");
		
		assertEquals(elementXml, result.toString());
		
	}
	
	void successWhenConvertToJava(Class<? extends CommonElement> clazz, String tagName, boolean attributeExtra, String attributeName) {

		StringBuilder result = new StringBuilder("<"+tagName);
		if(attributeExtra) {
			result.append(" extra=\"true\"");
		}
		result.append(" name=\""+attributeName+"\"/>");

		CommonElement elementObj = (CommonElement) xstream.fromXML(result.toString());

		assertNotNull(elementObj);
		assertInstanceOf(clazz, elementObj);
		assertEquals(elementObj.getName(), attributeName);
		assertTrue(elementObj.isExtra());
		
	}

}
