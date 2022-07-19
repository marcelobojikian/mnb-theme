package br.com.mnb.theme.core.xml.view;

import java.io.Serializable;
import java.util.List;

import br.com.mnb.theme.core.xml.element.CommonElement;

public interface ViewElement extends Serializable {
	
	public String getName();
	
	public List<CommonElement> getElements();

}
