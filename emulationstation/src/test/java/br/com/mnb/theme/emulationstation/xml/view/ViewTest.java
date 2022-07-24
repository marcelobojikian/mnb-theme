package br.com.mnb.theme.emulationstation.xml.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mnb.theme.core.xml.converter.TagViewConverter;
import br.com.mnb.theme.core.xml.element.CommonElement;
import br.com.mnb.theme.core.xml.view.ViewElement;
import br.com.mnb.theme.emulationstation.xml.element.Datetime;
import br.com.mnb.theme.emulationstation.xml.element.HelpSystem;
import br.com.mnb.theme.emulationstation.xml.element.Image;
import br.com.mnb.theme.emulationstation.xml.element.Ninepatch;
import br.com.mnb.theme.emulationstation.xml.element.Rating;
import br.com.mnb.theme.emulationstation.xml.element.Sound;
import br.com.mnb.theme.emulationstation.xml.element.Text;
import br.com.mnb.theme.emulationstation.xml.element.TextList;
import br.com.mnb.theme.emulationstation.xml.element.Video;

class ViewTest {

	TagViewConverter converter;
	
	@BeforeEach
	public void setup() {
		converter = new TagViewConverter();
		converter.addView("view", View.class);
		converter.addElement("text", Text.class);
		converter.addElement("image", Image.class);
		converter.addElement("datetime", Datetime.class);
		converter.addElement("helpsystem", HelpSystem.class);
		converter.addElement("ninepatch", Ninepatch.class);
		converter.addElement("rating", Rating.class);
		converter.addElement("sound", Sound.class);
		converter.addElement("textlist", TextList.class);
		converter.addElement("video", Video.class);
	}

	@Test
	public void whenCreateView_DefaultValues() {

		View view = new View();

		assertNull(view.getName());
		assertTrue(view.getElements().isEmpty());

	}

	@Test
	public void whenConvertElement_OnlyWithName() {

		View view = new View();
		view.setName("View");

		String contentXml = converter.toXML(view);
		String result = "<view name=\"View\"/>";

		assertEquals(contentXml, result);

	}

	@Test
	public void sucessWhenCreateXml_ViewWithOneElement() {

		Text text = new Text();
		text.setName("TextElement");

		View view = new View();
		view.setName("ViewElement");

		view.addElement(text);

		String viewXml = converter.toXML(view);
		// @formatter:off
		String result = "<view name=\"ViewElement\">\n"
					  + "  <text name=\"TextElement\"/>\n"
					  + "</view>";
		// @formatter:on

		assertEquals(viewXml, result);

	}

	@Test
	public void sucessWhenCreateXml_ViewWithManyElements() {

		Text text = new Text();
		text.setName("TextElement");

		Image image = new Image();
		image.setName("ImageElement");

		View view = new View();
		view.setName("ViewElement");

		view.addElements(text, image);

		String viewXml = converter.toXML(view);
		// @formatter:off
		String result = "<view name=\"ViewElement\">\n"
					  + "  <text name=\"TextElement\"/>\n"
					  + "  <image name=\"ImageElement\"/>\n"
					  + "</view>";
		// @formatter:on

		assertEquals(viewXml, result);

	}

	@Test
	public void sucessWhenCreateXml_ViewWithExtraElements() {

		Text text = new Text();
		text.setName("TextElement");
		text.setExtra(true);

		View view = new View();
		view.setName("ViewElement");

		view.addElement(text);

		String viewXml = converter.toXML(view);
		// @formatter:off
		String result = "<view name=\"ViewElement\">\n"
					  + "  <text extra=\"true\" name=\"TextElement\"/>\n"
					  + "</view>";
		// @formatter:on

		assertEquals(viewXml, result);

	}

	@Test
	void sucessWhenConvertToJava_View() {

		// @formatter:off
		String result = "<view name=\"ViewElement\">\n"
				  	  + "  <text name=\"TextElement\"/>\n"
				  	  + "  <image name=\"ImageElement\"/>\n"
				  	  + "</view>";
		// @formatter:on

		ViewElement viewObj = converter.fromXML(result);

		assertNotNull(viewObj);
		assertEquals(viewObj.getName(), "ViewElement");

		CommonElement text = viewObj.getElements().get(0);
		CommonElement image = viewObj.getElements().get(1);

		assertInstanceOf(Text.class, text);
		assertInstanceOf(Image.class, image);

	}

//	@Test
//	void failWhenConvertViewToTagNameWithComponentUnmapped() {
//		assertThrows(IllegalArgumentException.class, () -> {
//			converter.toXML(new UnmappedView());
//		});
//	}

}

//@XStreamAlias("unmapped")
//class UnmappedView extends AbstractViewElement { 
//	private static final long serialVersionUID = 1L;
//}
