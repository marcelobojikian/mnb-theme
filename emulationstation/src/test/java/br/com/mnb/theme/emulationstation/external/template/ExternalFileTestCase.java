package br.com.mnb.theme.emulationstation.external.template;

import br.com.mnb.theme.core.xml.element.CommonElement;

public class ExternalFileTestCase {

	String displayName;
	String[] systems;
	EvaluatorTestCase[] evaluator;

	public ExternalFileTestCase(String displayName, String[] systems, EvaluatorTestCase... evaluator) {
		this.displayName = displayName;
		this.systems = systems;
		this.evaluator = evaluator;
	}
	
	public static class EvaluatorTestCase {
		
		String name;
		int count;
		Class<? extends CommonElement>[] classes;

		@SuppressWarnings("unchecked")
		public EvaluatorTestCase(String name, int count, Class<? extends CommonElement>... classes) {
			this.name = name;
			this.count = count;
			this.classes = classes;
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
	
}
