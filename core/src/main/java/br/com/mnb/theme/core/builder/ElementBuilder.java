package br.com.mnb.theme.core.builder;

import java.util.Map;

import br.com.mnb.theme.core.xml.element.AbstractElement;

public interface ElementBuilder {
	
	public <T extends AbstractElement> T createElement(Class<T> clazz, String name);
	
	public <T extends AbstractElement> T createElement(Class<T> clazz, String name, boolean extra);
	
	public <T extends AbstractElement> T createElement(Class<T> clazz, String name, Map<String, String> content);
	
	public <T extends AbstractElement> T createElement(Class<T> clazz, String name, boolean extra, Map<String, String> content);

}
