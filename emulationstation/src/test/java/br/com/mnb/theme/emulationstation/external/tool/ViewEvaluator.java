package br.com.mnb.theme.emulationstation.external.tool;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import br.com.mnb.theme.core.xml.element.CommonElement;
import br.com.mnb.theme.core.xml.view.ViewElement;

public interface ViewEvaluator {
	
	public void evaluateView(ViewElement view);
	
	default void evaluate(ViewElement view) {
		assertNotNull(view);
		evaluateView(view);
	}
	
	@SuppressWarnings("unchecked")
	default void evaluateView(ViewElement view, int count, Class<? extends CommonElement>... classes) {
		List<CommonElement> elementsList = view.getElements();
		assertEquals(elementsList.size(), count);
		CommonElement[] array = elementsList.toArray(new CommonElement[classes.length]);
		for (int i = 0; i < classes.length; i++) {
			assertInstanceOf(classes[i], array[i]);
			assertFalse(array[i].getContent().getAttributes().isEmpty());
		}
	}

}
