package br.com.mnb.theme.batocera.xml.feature;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class VideoFeature extends AbstractFeature {

	private static final long serialVersionUID = 1L;

	@XStreamAlias("supported")
	@XStreamAsAttribute
	private String supported = "video";

	@Override
	public String getSupported() {
		return supported;
	}

}
