package br.com.mnb.theme.core.xml.theme;

import java.io.Serializable;
import java.util.List;

import br.com.mnb.theme.core.xml.view.ViewElement;

public interface ThemeElement extends Serializable {

	public Integer getFormatVersion();

	public List<ViewElement> getViewElements();

	public List<String> getIncludes();

}
