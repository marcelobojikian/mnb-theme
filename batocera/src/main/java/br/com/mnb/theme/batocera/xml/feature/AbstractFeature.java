package br.com.mnb.theme.batocera.xml.feature;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.mnb.theme.batocera.xml.view.View;
import br.com.mnb.theme.core.xml.element.CommonElement;

public abstract class AbstractFeature implements BatoceraFeature {

	private static final long serialVersionUID = 1L;
	
	private View view;

	@Override
	public View getView() {
		return view;
	}

	@Override
	public void setView(View view) {
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
