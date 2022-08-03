package br.com.mnb.theme.emulationstation.builder;

import java.util.HashMap;
import java.util.Map;

import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.element.CommonElement;
import br.com.mnb.theme.core.xml.tag.TagConverter;
import br.com.mnb.theme.emulationstation.evaluator.ElementEvaluator;
import br.com.mnb.theme.emulationstation.evaluator.ElementTagNameEvaluator;
import br.com.mnb.theme.emulationstation.evaluator.ThemeEvaluator;
import br.com.mnb.theme.emulationstation.evaluator.ViewEvaluator;

public class EvaluatorBuilder {
	
	private Map<String, ViewEvaluator> targetMap;
	private Map<String, Integer> mapEnableContent;
	
	private EvaluatorBuilder() {
		targetMap = new HashMap<>();
		mapEnableContent = new HashMap<>();
	}
	
	public static EvaluatorBuilder create() {
		return new EvaluatorBuilder();
	}
	
	public EvaluatorBuilder enableEvaluateContent(String viewName, int index){
		mapEnableContent.put(viewName, index);
		return this;
	}
	
	public EvaluatorBuilder evaluateView(String viewName) {
		targetMap.put(viewName, new ViewEvaluator(viewName));
		return this;
	}
	
	public EvaluatorBuilder evaluateView(String viewName, int count, TagConverter<AbstractElement, String> converter, String... tags) {
		ViewEvaluator viewEvaluator = new ViewEvaluator(viewName, count);
		for (String tag : tags) {
			ElementTagNameEvaluator elementEvaluator = new ElementTagNameEvaluator(converter, tag);
			viewEvaluator.addEvaluator(elementEvaluator);
		}		
		targetMap.put(viewName, viewEvaluator);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public EvaluatorBuilder evaluateView(String viewName, int count, Class<? extends CommonElement>... classes) {
		ViewEvaluator viewEvaluator = new ViewEvaluator(viewName, count);
		for (Class<? extends CommonElement> clazz : classes) {
			ElementEvaluator elementEvaluator = new ElementEvaluator(clazz);
			viewEvaluator.addEvaluator(elementEvaluator);
		}		
		targetMap.put(viewName, viewEvaluator);
		return this;
	}
	
	public ThemeEvaluator build() {
		mapEnableContent.forEach((key, value) -> targetMap.get(key).enableEvaluateContent(value, true) );
		return new ThemeEvaluator(targetMap.values());
	}

}
