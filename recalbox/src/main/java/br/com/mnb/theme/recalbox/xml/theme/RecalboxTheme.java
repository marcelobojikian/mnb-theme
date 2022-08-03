package br.com.mnb.theme.recalbox.xml.theme;

import java.util.ArrayList;
import java.util.Arrays;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import br.com.mnb.theme.core.xml.feature.FeatureElement;
import br.com.mnb.theme.core.xml.theme.AbstractTheme;
import br.com.mnb.theme.core.xml.view.View;

@XStreamAlias("theme")
public class RecalboxTheme extends AbstractTheme {
	
	private static final long serialVersionUID = 1L;

	public RecalboxTheme addFeatures(FeatureElement features) {
		getFeatures().addAll(new ArrayList<>(Arrays.asList(features)));
		return this;
	}

	public RecalboxTheme addViews(View... elements) {
		getViewElements().addAll(new ArrayList<>(Arrays.asList(elements)));
		return this;
	}
	
}
