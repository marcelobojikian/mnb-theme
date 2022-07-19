package br.com.mnb.theme.batocera.xml.converter.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

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
import br.com.mnb.theme.core.xml.element.AbstractElement;

class ElementTagConverterTest {

	private ElementTagConverter elementConverter = new ElementTagConverter();

	@Test
	void sucessWhenConvertTagNameToElement() {
		
		AbstractElement element = elementConverter.toComponent("text");
		assertNotNull(element);
		assertInstanceOf(Text.class, element);
		
		element = elementConverter.toComponent("image");
		assertNotNull(element);
		assertInstanceOf(Image.class, element);
		
		element = elementConverter.toComponent("datetime");
		assertNotNull(element);
		assertInstanceOf(Datetime.class, element);
		
		element = elementConverter.toComponent("helpsystem");
		assertNotNull(element);
		assertInstanceOf(HelpSystem.class, element);
		
		element = elementConverter.toComponent("ninepatch");
		assertNotNull(element);
		assertInstanceOf(Ninepatch.class, element);

		element = elementConverter.toComponent("rating");
		assertNotNull(element);
		assertInstanceOf(Rating.class, element);
		
		element = elementConverter.toComponent("sound");
		assertNotNull(element);
		assertInstanceOf(Sound.class, element);
		
		element = elementConverter.toComponent("textlist");
		assertNotNull(element);
		assertInstanceOf(TextList.class, element);
		
		element = elementConverter.toComponent("video");
		assertNotNull(element);
		assertInstanceOf(Video.class, element);
		
		element = elementConverter.toComponent("carousel");
		assertNotNull(element);
		assertInstanceOf(BatoceraCarousel.class, element);
		
	}

	@Test
	void sucessWhenConvertElementToTagName() {
		
		String result = elementConverter.toString(new Text());
		assertNotNull(result);
		assertEquals("text", result);
		
		result = elementConverter.toString(new Image());
		assertNotNull(result);
		assertEquals("image", result);
		
		result = elementConverter.toString(new Datetime());
		assertNotNull(result);
		assertEquals("datetime", result);
		
		result = elementConverter.toString(new HelpSystem());
		assertNotNull(result);
		assertEquals("helpsystem", result);
		
		result = elementConverter.toString(new Ninepatch());
		assertNotNull(result);
		assertEquals("ninepatch", result);
		
		result = elementConverter.toString(new Rating());
		assertNotNull(result);
		assertEquals("rating", result);
		
		result = elementConverter.toString(new Sound());
		assertNotNull(result);
		assertEquals("sound", result);
		
		result = elementConverter.toString(new TextList());
		assertNotNull(result);
		assertEquals("textlist", result);
		
		result = elementConverter.toString(new Video());
		assertNotNull(result);
		assertEquals("video", result);
		
		result = elementConverter.toString(new BatoceraCarousel());
		assertNotNull(result);
		assertEquals("carousel", result);
		
	}

	@Test
	void failWhenConvertTagNameToElementWithTagUnmapped() {
		assertThrows(IllegalArgumentException.class, () -> {
			elementConverter.toComponent("Other");
		});
	}

	@Test
	void failWhenConvertElementToTagNameWithComponentUnmapped() {
		assertThrows(IllegalArgumentException.class, () -> {
			elementConverter.toString(new UnmappedElement());
		});
	}
	
	class UnmappedElement extends AbstractElement { 
		private static final long serialVersionUID = 1L;
	}

}
