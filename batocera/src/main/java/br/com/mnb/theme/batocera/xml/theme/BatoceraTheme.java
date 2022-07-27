package br.com.mnb.theme.batocera.xml.theme;

import java.util.ArrayList;
import java.util.Arrays;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import br.com.mnb.theme.core.xml.feature.FeatureElement;
import br.com.mnb.theme.core.xml.theme.AbstractTheme;
import br.com.mnb.theme.core.xml.view.View;

@XStreamAlias("theme")
public class BatoceraTheme extends AbstractTheme {
	
	private static final long serialVersionUID = 1L;

	public BatoceraTheme addFeatures(FeatureElement features) {
		getFeatures().addAll(new ArrayList<>(Arrays.asList(features)));
		return this;
	}

	public BatoceraTheme addViews(View... elements) {
		getViewElements().addAll(new ArrayList<>(Arrays.asList(elements)));
		return this;
	}
	
}
