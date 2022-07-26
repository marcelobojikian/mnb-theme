package br.com.mnb.theme.core.xml.tag.converter;

import com.thoughtworks.xstream.XStream;

import br.com.mnb.theme.core.xml.xstream.XStreamConfigure;

public class TypeConverterCached<T extends XStreamConfigure> {

	private XStream XSTREAM_INSTANCE = null;
	private boolean reloadXstream = true;
	private T configure;

	public TypeConverterCached(T configure) {
		this.configure = configure;
	}

	public XStream getXStream() {
		if (XSTREAM_INSTANCE == null || reloadXstream) {
			XSTREAM_INSTANCE = configure.getXStream();
			reloadXstream = false;
		}
		return XSTREAM_INSTANCE;
	}
	
	public void notifyChange() {
		reloadXstream = true;
	}
	
	public T getConfigure() {
		return configure;
	}

}
