package br.com.mnb.theme.batocera.xml.feature;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class CarouselFeature extends AbstractFeature {

	private static final long serialVersionUID = 1L;

	@XStreamAlias("supported")
	@XStreamAsAttribute
	private String supported = "carousel";

	@Override
	public String getSupported() {
		return supported;
	}

}
