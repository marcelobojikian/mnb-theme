package br.com.mnb.theme.core.xml.theme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import br.com.mnb.theme.core.xml.feature.FeatureElement;
import br.com.mnb.theme.core.xml.view.View;
import br.com.mnb.theme.core.xml.view.ViewElement;

public abstract class AbstractTheme implements ThemeElement {

	private static final long serialVersionUID = 1L;
	
	@XStreamAlias("formatVersion")
	private Integer formatVersion;
	
	@XStreamImplicit(itemFieldName="include")
	private List<String> includes = new ArrayList<>();

	@XStreamImplicit
	private List<ViewElement> viewElements = new ArrayList<>();

	@XStreamImplicit
	private List<FeatureElement> features = new ArrayList<>();
	
	public AbstractTheme addFeatures(FeatureElement... features) {
		this.features.addAll(new ArrayList<>(Arrays.asList(features)));
		return this;
	}
	
	public AbstractTheme addViews(View... elements) {
		getViewElements().addAll(new ArrayList<>(Arrays.asList(elements)));
		return this;
	}

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

	@Override
	public List<FeatureElement> getFeatures() {
		return features;
	}

	public void setFeatures(List<FeatureElement> features) {
		this.features = features;
	}

}
