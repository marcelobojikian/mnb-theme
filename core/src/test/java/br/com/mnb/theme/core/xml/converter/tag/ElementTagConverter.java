package br.com.mnb.theme.core.xml.converter.tag;

import br.com.mnb.theme.core.factory.ElementFactory;
import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.xml.converter.TagConverter;
import br.com.mnb.theme.core.xml.element.AbstractElement;

public class ElementTagConverter implements TagConverter<AbstractElement, String>{
	
	private ElementFactory instaceFactory;
	
	public ElementTagConverter(ElementFactory instaceFactory) {
		this.instaceFactory = instaceFactory;
	}

	@Override
	public String toString(AbstractElement element) {
		if(element instanceof Element) {
			return "element";
		} else {
			throw new IllegalArgumentException("Unmapped element " + element);
		}
	}

	@Override
	public AbstractElement toComponent(String tagName) {
		switch (tagName) {
		case "element":
			return instaceFactory.createElement(Element.class);
		default:
			throw new IllegalArgumentException("Unmapped tag " + tagName);
		}
	}

}
