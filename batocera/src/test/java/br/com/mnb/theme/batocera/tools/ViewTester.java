package br.com.mnb.theme.batocera.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import br.com.mnb.theme.batocera.xml.element.Image;
import br.com.mnb.theme.batocera.xml.element.Text;
import br.com.mnb.theme.core.builder.ViewBuilder;
import br.com.mnb.theme.core.xml.element.CommonElement;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;
import br.com.mnb.theme.core.xml.view.ViewElement;

public class ViewTester {
	
	final ViewBuilder builder;
	
	public ViewTester(ViewBuilder builder){
		this.builder = builder;
	}
	
	public <T extends AbstractViewElement> void testView(Class<T> clazz) {

		testView(clazz, "NameView");
		
		Text first = new Text();
		first.setName("FirstElement");
		
		testView(clazz, "NameElement", first);
		
		Image second = new Image();
		second.setName("SecondElement");
		
		List<CommonElement> asList = Arrays.asList(first	, second);
		
		testView(clazz, "NameElement", asList);
		
	}
	
	<T extends AbstractViewElement> void testView(Class<T> clazz, String nameExpected) {
		AbstractViewElement view = builder.createView(clazz, nameExpected);
		assertInstanceOf(clazz, view);
		testViewContent(view, nameExpected);
	}
	
	<T extends AbstractViewElement> void testView(Class<T> clazz, String nameExpected, CommonElement... elementsExpected) {
		AbstractViewElement view = builder.createView(clazz, nameExpected, elementsExpected);
		assertInstanceOf(clazz, view);
		testViewContent(view, nameExpected, elementsExpected);
	}
	
	<T extends AbstractViewElement> void testView(Class<T> clazz, String nameExpected, List<CommonElement> elementsExpected) {
		AbstractViewElement view = builder.createView(clazz, nameExpected, elementsExpected);
		assertInstanceOf(clazz, view);
		testViewContent(view, nameExpected, elementsExpected);
	}
	
	public static <T extends ViewElement> void testViewContent(T view, String nameExpected, CommonElement... elementsExpected) {
		assertNotNull(view);
		assertEquals(nameExpected, view.getName());
		testViewElements(view, elementsExpected);
	}
	
	public static <T extends ViewElement> void testViewContent(T view, String nameExpected, List<CommonElement> elementsExpected) {
		assertNotNull(view);
		assertEquals(nameExpected, view.getName());
		testViewElements(view, elementsExpected);
	}
	
	private static <T extends ViewElement> void testViewElements(T view, CommonElement... elementsExpected) {
		assertNotNull(elementsExpected);
		List<CommonElement> viewElements = view.getElements();
		if(elementsExpected.length == 0) {
			assertTrue(viewElements.isEmpty());
		} else {
			for (int i = 0; i < elementsExpected.length; i++) {
				assertTrue(viewElements.contains(elementsExpected[i]));
			}
		}
	}
	
	private static <T extends ViewElement> void testViewElements(T view, List<CommonElement> elementsExpected) {
		assertNotNull(elementsExpected);
		List<CommonElement> viewElements = view.getElements();
		if(elementsExpected.isEmpty()) {
			assertTrue(viewElements.isEmpty());
		} else {
			Iterator<CommonElement> iterator = elementsExpected.iterator();
			while (iterator.hasNext()) {
				assertTrue(viewElements.contains(iterator.next()));	
			}
		}
	}

}
