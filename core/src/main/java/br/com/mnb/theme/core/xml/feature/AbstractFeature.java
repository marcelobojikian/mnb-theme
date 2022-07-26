package br.com.mnb.theme.core.xml.feature;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.mnb.theme.core.xml.element.CommonElement;
import br.com.mnb.theme.core.xml.view.ViewElement;

public abstract class AbstractFeature implements FeatureElement {

	private static final long serialVersionUID = 1L;
	
	private ViewElement view;

	@Override
	public ViewElement getView() {
		return view;
	}

	@Override
	public void setView(ViewElement view) {
		this.view = view;
	}

	public AbstractFeature addElements(CommonElement... elements) {
		view.getElements().addAll(new ArrayList<>(Arrays.asList(elements)));
		return this;
	}

	public void addElement(CommonElement element) {
		view.getElements().add(element);
	}

}
