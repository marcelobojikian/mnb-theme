package br.com.mnb.theme.recalbox.evaluator;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ClassEvaluator implements Evaluator<Object> {
	
	public Class<?> clazz;
	
	public ClassEvaluator(Class<?> clazz) {
		this.clazz = clazz;
	}

	@Override
	public void evaluate(Object target) {
		assertInstanceOf(clazz.getClass(), target.getClass());
	}

}
