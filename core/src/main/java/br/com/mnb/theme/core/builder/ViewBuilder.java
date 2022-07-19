package br.com.mnb.theme.core.builder;

import java.util.List;

import br.com.mnb.theme.core.xml.element.CommonElement;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;

public interface ViewBuilder {
	
	public <T extends AbstractViewElement> T createView(Class<T> clazz, String name);
	
	public <T extends AbstractViewElement> T createView(Class<T> clazz, String name, CommonElement... elements);
	
	public <T extends AbstractViewElement> T createView(Class<T> clazz, String name, List<CommonElement> elements);

}
