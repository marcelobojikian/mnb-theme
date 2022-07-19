package br.com.mnb.theme.core.xml.view;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import br.com.mnb.theme.core.xml.element.CommonElement;

public abstract class AbstractViewElement implements ViewElement {

	private static final long serialVersionUID = 1L;

	@XStreamAlias("name")
	@XStreamAsAttribute
	private String name;

	@XStreamImplicit
	private List<CommonElement> elements = new ArrayList<>();

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public List<CommonElement> getElements() {
		return elements;
	}

	public void setElements(List<CommonElement> elements) {
		this.elements = elements;
	}

}
