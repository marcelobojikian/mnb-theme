package br.com.mnb.theme.batocera.xml.theme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import br.com.mnb.theme.batocera.xml.feature.BatoceraFeature;
import br.com.mnb.theme.batocera.xml.view.View;
import br.com.mnb.theme.core.xml.theme.AbstractTheme;

@XStreamAlias("theme")
public class BatoceraTheme extends AbstractTheme {

	private static final long serialVersionUID = 1L;

	@XStreamImplicit(itemFieldName = "feature")
	private List<BatoceraFeature> features = new ArrayList<>();
	
	public BatoceraTheme addFeatures(BatoceraFeature... features) {
		this.features.addAll(new ArrayList<>(Arrays.asList(features)));
		return this;
	}
	
	public BatoceraTheme addViews(View... elements) {
		getViewElements().addAll(new ArrayList<>(Arrays.asList(elements)));
		return this;
	}

}
