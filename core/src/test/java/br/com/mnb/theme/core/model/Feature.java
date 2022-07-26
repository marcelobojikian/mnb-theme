package br.com.mnb.theme.core.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import br.com.mnb.theme.core.xml.feature.AbstractFeature;

@XStreamAlias("feature")
public class Feature extends AbstractFeature {

	private static final long serialVersionUID = 1L;

	@XStreamAlias("supported")
	@XStreamAsAttribute
	private String supported = "first";

	@Override
	public String getSupported() {
		return supported;
	}

}
