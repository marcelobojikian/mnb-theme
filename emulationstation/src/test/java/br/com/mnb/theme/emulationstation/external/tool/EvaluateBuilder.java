package br.com.mnb.theme.emulationstation.external.tool;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.Map;

import br.com.mnb.theme.core.xml.element.CommonElement;
import br.com.mnb.theme.core.xml.view.ViewElement;

public class EvaluateBuilder {
	
	private Map<String, EvaluateItem> targetMap;
	
	private EvaluateBuilder() {
		targetMap = new HashMap<String, EvaluateItem>();
	}
	
	public static EvaluateBuilder create() {
		return new EvaluateBuilder();
	}
	
	@SuppressWarnings("unchecked")
	public EvaluateBuilder evaluateView(String viewName, int count, Class<? extends CommonElement>... classes) {
		targetMap.put(viewName, new EvaluateItem(count, classes));
		return this;
	}
	
	public ViewEvaluator build() {
		return new ViewEvaluator() {
			@Override
			public void evaluateView(ViewElement view) {				
				if(!targetMap.containsKey(view.getName())) {
					fail("Element Unmapped");
				}				
				EvaluateItem evaluateItem = targetMap.get(view.getName());	
				evaluateView(view, evaluateItem.getCount(), evaluateItem.getClasses());
			}
		};
	}
	
	class EvaluateItem {
		int count;
		Class<? extends CommonElement>[] classes;
		public EvaluateItem(int count, Class<? extends CommonElement>[] classes) {
			super();
			this.count = count;
			this.classes = classes;
		}
		public int getCount() {
			return count;
		}
		public Class<? extends CommonElement>[] getClasses() {
			return classes;
		}
	}

}
