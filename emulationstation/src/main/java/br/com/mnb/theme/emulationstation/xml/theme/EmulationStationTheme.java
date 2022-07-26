package br.com.mnb.theme.emulationstation.xml.theme;

import java.util.ArrayList;
import java.util.Arrays;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import br.com.mnb.theme.core.xml.theme.AbstractTheme;
import br.com.mnb.theme.core.xml.view.View;

@XStreamAlias("theme")
public class EmulationStationTheme extends AbstractTheme {

	private static final long serialVersionUID = 1L;
	
	public EmulationStationTheme addViews(View... elements) {
		getViewElements().addAll(new ArrayList<>(Arrays.asList(elements)));
		return this;
	}

}
