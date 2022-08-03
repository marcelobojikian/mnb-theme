package br.com.mnb.theme.recalbox.external.template;

import br.com.mnb.theme.core.xml.element.CommonElement;

public class ExternalFileTestCase {

	String displayName;
	String[] systems;
	EvaluatorTestCase[] evaluator;
	boolean requeredSanitize = false;

	public ExternalFileTestCase(String displayName, String[] systems, EvaluatorTestCase... evaluator) {
		this(displayName, systems, false, evaluator);
	}

	public ExternalFileTestCase(String displayName, String[] systems, boolean requeredSanitize, EvaluatorTestCase... evaluator) {
		this.displayName = displayName;
		this.systems = systems;
		this.evaluator = evaluator;
		this.requeredSanitize = requeredSanitize;
	}
	
	public static class EvaluatorTestCase {
		
		String name;
		int count;
		boolean evaluateContent;
		Class<? extends CommonElement>[] classes;

		@SuppressWarnings("unchecked")
		public EvaluatorTestCase(String name, int count, Class<? extends CommonElement>... classes) {
			this(name, count, true, classes);
		}

		@SuppressWarnings("unchecked")
		public EvaluatorTestCase(String name, int count, boolean evaluateContent, Class<? extends CommonElement>... classes) {
			this.name = name;
			this.count = count;
			this.classes = classes;
			this.evaluateContent = evaluateContent;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public Class<? extends CommonElement>[] getClasses() {
			return classes;
		}

		public void setClasses(Class<? extends CommonElement>[] classes) {
			this.classes = classes;
		}

		public boolean isEvaluateContent() {
			return evaluateContent;
		}

		public void setEvaluateContent(boolean evaluateContent) {
			this.evaluateContent = evaluateContent;
		}

	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String[] getSystems() {
		return systems;
	}

	public void setSystems(String[] systems) {
		this.systems = systems;
	}

	public EvaluatorTestCase[] getEvaluator() {
		return evaluator;
	}

	public void setEvaluator(EvaluatorTestCase[] evaluator) {
		this.evaluator = evaluator;
	}

	public boolean isRequeredSanitize() {
		return requeredSanitize;
	}

	public void setRequeredSanitize(boolean requeredSanitize) {
		this.requeredSanitize = requeredSanitize;
	}
	
}
