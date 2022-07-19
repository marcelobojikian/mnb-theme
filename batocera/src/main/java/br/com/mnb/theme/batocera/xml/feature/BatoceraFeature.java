package br.com.mnb.theme.batocera.xml.feature;

import java.io.Serializable;

import br.com.mnb.theme.batocera.xml.view.View;

public interface BatoceraFeature extends Serializable {

	public View getView();

	public void setView(View view);

	public abstract String getSupported();

}
