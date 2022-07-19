package br.com.mnb.theme.core.xml;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.SerializationUtils;

public class Content implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Map<String, String> attributes = new HashMap<>();
	
	public Content() {
	}
	
	public Content(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	
	public Content clone() {
		return (Content) SerializationUtils.clone(this);
	}

}
