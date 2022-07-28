package br.com.mnb.theme.core.xml.view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.model.SecondElement;
import br.com.mnb.theme.core.xml.element.CommonElement;
import br.com.mnb.theme.core.xml.tag.converter.TagViewConverter;

class ViewTest {

	TagViewConverter converter;
	
	@BeforeEach
	public void setup() {
		converter = new TagViewConverter();
		converter.addView(View.class);
		converter.addElement(Element.class);
		converter.addElement(SecondElement.class);
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

		Element element = new Element();
		element.setName("TextElement");

		View view = new View();
		view.setName("ViewElement");

		view.addElement(element);

		String viewXml = converter.toXML(view);
		// @formatter:off
		String result = "<view name=\"ViewElement\">\n"
					  + "  <element name=\"TextElement\"/>\n"
					  + "</view>";
		// @formatter:on

		assertEquals(viewXml, result);

	}

	@Test
	public void sucessWhenCreateXml_ViewWithManyElements() {

		Element element = new Element();
		element.setName("TextElement");

		SecondElement secondElement = new SecondElement();
		secondElement.setName("ImageElement");

		View view = new View();
		view.setName("ViewElement");

		view.addElements(element, secondElement);

		String viewXml = converter.toXML(view);
		// @formatter:off
		String result = "<view name=\"ViewElement\">\n"
					  + "  <element name=\"TextElement\"/>\n"
					  + "  <second name=\"ImageElement\"/>\n"
					  + "</view>";
		// @formatter:on

		assertEquals(viewXml, result);

	}

	@Test
	public void sucessWhenCreateXml_ViewWithExtraElements() {

		Element element = new Element();
		element.setName("TextElement");
		element.setExtra(true);

		View view = new View();
		view.setName("ViewElement");

		view.addElement(element);

		String viewXml = converter.toXML(view);
		// @formatter:off
		String result = "<view name=\"ViewElement\">\n"
					  + "  <element extra=\"true\" name=\"TextElement\"/>\n"
					  + "</view>";
		// @formatter:on

		assertEquals(viewXml, result);

	}

	@Test
	void sucessWhenConvertToJava_View() {

		// @formatter:off
		String result = "<view name=\"ViewElement\">\n"
				  	  + "  <element name=\"TextElement\"/>\n"
				  	  + "  <second name=\"ImageElement\"/>\n"
				  	  + "</view>";
		// @formatter:on

		ViewElement viewObj = converter.fromXML(result);

		assertNotNull(viewObj);
		assertEquals(viewObj.getName(), "ViewElement");

		CommonElement element = viewObj.getElements().get(0);
		CommonElement second = viewObj.getElements().get(1);

		assertInstanceOf(Element.class, element);
		assertInstanceOf(SecondElement.class, second);

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

