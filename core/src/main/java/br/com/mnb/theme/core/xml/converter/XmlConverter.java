package br.com.mnb.theme.core.xml.converter;

import com.thoughtworks.xstream.XStream;

public abstract class XmlConverter<T> {

	private XStream XSTREAM_INSTANCE = null;

	private XStream getInstance() {
		if (XSTREAM_INSTANCE == null) {
			XSTREAM_INSTANCE = getXStream();
		}
		return XSTREAM_INSTANCE;
	}
	
	protected abstract XStream getXStream();

	public T fromXML(String xml) {
		return (T) getInstance().fromXML(xml);
	}

	public String toXML(T object) {
		return getInstance().toXML(object);
	}

}
