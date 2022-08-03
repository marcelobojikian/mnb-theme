package br.com.mnb.theme.emulationstation.evaluator;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.mnb.theme.core.xml.theme.ThemeElement;
import br.com.mnb.theme.core.xml.view.ViewElement;

public class ThemeEvaluator implements Evaluator<ThemeElement> {

	private Collection<ViewEvaluator> viewEvaluators;

	public ThemeEvaluator() {
		this.viewEvaluators = new ArrayList<>();
	}

	public ThemeEvaluator(Collection<ViewEvaluator> viewEvaluators) {
		this.viewEvaluators = viewEvaluators;
	}

	@Override
	public void evaluate(ThemeElement theme) {
		assertNotNull(theme);
		List<ViewElement> views = theme.getViewElements();
		for (ViewElement view : views) {
			
			ViewEvaluator viewEvaluator = null;
			for (ViewEvaluator evaluator : viewEvaluators) {
				if(evaluator.getViewName().equals(view.getName())) {
					viewEvaluator = evaluator;
				}
			}
			
			if(viewEvaluator == null) {
				fail("ViewElement unmapped ["+view.getName()+"]");
			}

			viewEvaluator.evaluate(view);
			
		}
		
	}

}
