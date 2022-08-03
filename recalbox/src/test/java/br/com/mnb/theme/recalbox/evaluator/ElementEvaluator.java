package br.com.mnb.theme.recalbox.evaluator;

import static org.junit.jupiter.api.Assertions.assertFalse;

import br.com.mnb.theme.core.xml.element.CommonElement;

public class ElementEvaluator implements Evaluator<CommonElement> {

	private ClassEvaluator classEvalueate;
	private boolean evaluateContent;
	
	public ElementEvaluator(Class<? extends CommonElement> clazz) {
		this(clazz, false);
	}
	
	public ElementEvaluator(Class<? extends CommonElement> clazz, boolean evaluateContent) {
		this.classEvalueate = new ClassEvaluator(clazz);
		this.evaluateContent = evaluateContent;
	}

	@Override
	public void evaluate(CommonElement target) {
		classEvalueate.evaluate(target);

		if(evaluateContent) {
			assertFalse(target.getContent().getAttributes().isEmpty());	
		}
		
	}

	public void setEvaluateContent(boolean evaluateContent) {
		this.evaluateContent = evaluateContent;
	}

	public void setClassEvalueate(ClassEvaluator classEvalueate) {
		this.classEvalueate = classEvalueate;
	}

}
