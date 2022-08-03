package br.com.mnb.theme.recalbox.evaluator;

import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.element.CommonElement;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.tag.TagConverter;

public class ElementTagNameEvaluator extends ElementEvaluator {

	private TagConverter<AbstractElement, String> converter;
	private String tagName;
	
	public ElementTagNameEvaluator(String tagName) {
		this(new NamedTagConverter<AbstractElement>(), tagName);
	}
	
	public ElementTagNameEvaluator(TagConverter<AbstractElement, String> converter, String tagName) {
		this(converter, tagName, false);
	}
	
	public ElementTagNameEvaluator(TagConverter<AbstractElement, String> converter, String tagName, boolean evaluateContent) {
		super(null, evaluateContent);
		this.converter = converter;
		this.tagName = tagName;
	}

	@Override
	public void evaluate(CommonElement target) {
		CommonElement element = converter.toComponent(tagName);
		
		if(element == null) {
			throw new NullPointerException("TagConverter could not find " + tagName);
		}
		
		setClassEvalueate(new ClassEvaluator(element.getClass()));
		super.evaluate(target);
		
	}

}
