package br.com.mnb.theme.batocera.xml.view;

import java.util.ArrayList;
import java.util.Arrays;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import br.com.mnb.theme.core.xml.element.CommonElement;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;

@XStreamAlias("view")
public class View extends AbstractViewElement {

	private static final long serialVersionUID = 1L;
	
	public View addElements(CommonElement... elements) {
		getElements().addAll(new ArrayList<>(Arrays.asList(elements)));
		return this;
	}
	
	public void addElement(CommonElement element) {
		getElements().add(element);
	}

}
