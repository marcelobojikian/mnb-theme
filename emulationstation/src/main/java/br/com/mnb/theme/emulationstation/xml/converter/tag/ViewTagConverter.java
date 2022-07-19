package br.com.mnb.theme.emulationstation.xml.converter.tag;

import br.com.mnb.theme.core.factory.InstanceFactory;
import br.com.mnb.theme.core.factory.ViewFactory;
import br.com.mnb.theme.core.xml.converter.TagConverter;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;
import br.com.mnb.theme.emulationstation.xml.view.View;

public class ViewTagConverter implements TagConverter<AbstractViewElement, String> {
	
	private ViewFactory instaceFactory;
	
	public ViewTagConverter() {
		this(new InstanceFactory());
	}
	
	public ViewTagConverter(ViewFactory instaceFactory) {
		this.instaceFactory = instaceFactory;
	}

	@Override
	public String toString(AbstractViewElement element) {
		if(element instanceof View) {
			return "view";
		} else {
			throw new IllegalArgumentException("Unmapped element " + element);
		}
	}

	@Override
	public AbstractViewElement toComponent(String tagName) {
		if(tagName.equals("view")) {
			return instaceFactory.createView(View.class);	
		} else {
			throw new IllegalArgumentException("Unmapped tag " + tagName);
		}
	}

}
