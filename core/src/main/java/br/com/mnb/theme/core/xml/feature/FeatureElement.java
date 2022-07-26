package br.com.mnb.theme.core.xml.feature;

import java.io.Serializable;

import br.com.mnb.theme.core.xml.view.ViewElement;

public interface FeatureElement extends Serializable {

	public ViewElement getView();

	public void setView(ViewElement view);

	public abstract String getSupported();

}
