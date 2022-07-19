package br.com.mnb.theme.core.xml.theme;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import br.com.mnb.theme.core.xml.view.ViewElement;

public abstract class AbstractTheme implements ThemeElement {

	private static final long serialVersionUID = 1L;
	
	@XStreamAlias("formatVersion")
	private Integer formatVersion;
	
	@XStreamImplicit(itemFieldName="include")
	private List<String> includes = new ArrayList<>();

	@XStreamImplicit
	private List<ViewElement> viewElements = new ArrayList<>();

	@Override
	public Integer getFormatVersion() {
		return formatVersion;
	}

	public void setFormatVersion(Integer formatVersion) {
		this.formatVersion = formatVersion;
	}

	@Override
	public List<ViewElement> getViewElements() {
		return viewElements;
	}

	public void setViewElements(List<ViewElement> viewElements) {
		this.viewElements = viewElements;
	}

	@Override
	public List<String> getIncludes() {
		return includes;
	}

	public void setIncludes(List<String> includes) {
		this.includes = includes;
	}

}
