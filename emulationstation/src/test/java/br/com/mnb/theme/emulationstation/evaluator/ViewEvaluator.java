package br.com.mnb.theme.emulationstation.evaluator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import br.com.mnb.theme.core.xml.element.CommonElement;
import br.com.mnb.theme.core.xml.view.ViewElement;

public class ViewEvaluator implements Evaluator<ViewElement> {

	private final String viewName;
	
	private int strictCountChildren = 0;
	private List<ElementEvaluator> elementEvalueate;
	
	public ViewEvaluator(String name) {
		this(name,0);
	}
	
	public ViewEvaluator(String name, int countChildren) {
		this.viewName = name;
		this.strictCountChildren = countChildren;
		this.elementEvalueate = new ArrayList<>();
	}
	
	@SuppressWarnings("unchecked")
	public void addEvaluator(boolean evaluateContent, Class<? extends CommonElement>... classes) {
		for (Class<? extends CommonElement> clazz : classes) {
			elementEvalueate.add(new ElementEvaluator(clazz, evaluateContent));
		}
	}

	public void enableEvaluateContent(int index, boolean evaluateContent) {
		elementEvalueate.get(index).setEvaluateContent(evaluateContent);
	}
	
	public void addEvaluator(Class<? extends CommonElement> clazz) {
		elementEvalueate.add(new ElementEvaluator(clazz, true));
	}
	
	public void addEvaluator(Class<? extends CommonElement> clazz, boolean evaluateContent) {
		elementEvalueate.add(new ElementEvaluator(clazz, evaluateContent));
	}
	
	public void addEvaluator(ElementEvaluator elementEvaluator) {
		elementEvalueate.add(elementEvaluator);
	}

	public String getViewName() {
		return viewName;
	}

	@Override
	public void evaluate(ViewElement target) {

		if(target.getName().equals(viewName)) {
		
			List<CommonElement> elementsList = target.getElements();
			
			if(strictCountChildren > 0) {				
				assertEquals(elementsList.size(), strictCountChildren);				
			}
			
			if(elementsList != null) {
				CommonElement[] elementTargets = elementsList.toArray(new CommonElement[elementsList.size()]);
				for (int i = 0; i < elementTargets.length; i++) {
					elementEvalueate.get(i).evaluate(elementTargets[i]);
				}
			}
			
		} else {
			fail("Invalid evaluator called ["+target.getName()+"] for " + viewName);
		}
		
	}

}
