package br.com.mnb.theme.core.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import br.com.mnb.theme.core.factory.InstanceFactory;
import br.com.mnb.theme.core.xml.Content;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.element.CommonElement;
import br.com.mnb.theme.core.xml.theme.AbstractTheme;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;

public class ComponentBuilder implements ThemeBuilder, ViewBuilder, ElementBuilder {

	private InstanceFactory instaceFactory;
	
	public ComponentBuilder(InstanceFactory instaceFactory) {
		this.instaceFactory = instaceFactory;
	}

	@Override
	public <T extends AbstractTheme> T createTheme(Class<T> clazz, Integer version) {
		T theme = instaceFactory.createTheme(clazz);
		theme.setFormatVersion(version);
		theme.setViewElements(new ArrayList<>());
		theme.setIncludes(new ArrayList<>());
		return theme;
	}

	@Override
	public <T extends AbstractTheme> T createTheme(Class<T> clazz, Integer version, String... includes) {
		T theme = instaceFactory.createTheme(clazz);
		theme.setFormatVersion(version);
		theme.setViewElements(new ArrayList<>());
		theme.setIncludes(new ArrayList<>(Arrays.asList(includes)));
		return theme;
	}

	@Override
	public <T extends AbstractViewElement> T createView(Class<T> clazz, String name) {
		T view = instaceFactory.createView(clazz);
		view.setName(name);
		view.setElements(new ArrayList<>());
		return view;
	}

	@Override
	public <T extends AbstractViewElement> T createView(Class<T> clazz, String name, CommonElement... elements) {
		T view = instaceFactory.createView(clazz);
		view.setName(name);
		view.setElements(new ArrayList<>(Arrays.asList(elements)));
		return view;
	}

	@Override
	public <T extends AbstractViewElement> T createView(Class<T> clazz, String name, List<CommonElement> elements) {
		T view = instaceFactory.createView(clazz);
		view.setName(name);
		view.setElements(elements);
		return view;
	}

	@Override
	public <T extends AbstractElement> T createElement(Class<T> clazz, String name) {
		T element = instaceFactory.createElement(clazz);
		element.setName(name);
		element.setExtra(false);
		element.setContent(new Content());
		return element;
	}

	@Override
	public <T extends AbstractElement> T createElement(Class<T> clazz, String name, boolean extra) {
		T element = instaceFactory.createElement(clazz);
		element.setName(name);
		element.setExtra(extra);
		element.setContent(new Content());
		return element;
	}

	@Override
	public <T extends AbstractElement> T createElement(Class<T> clazz, String name, Map<String, String> content) {
		T element = instaceFactory.createElement(clazz);
		element.setName(name);
		element.setExtra(false);
		element.setContent(new Content(content));
		return element;
	}

	@Override
	public <T extends AbstractElement> T createElement(Class<T> clazz, String name, boolean extra,
			Map<String, String> content) {
		T element = createElement(clazz, name, content);
		element.setName(name);
		element.setExtra(extra);
		element.setContent(new Content(content));
		return (T) element;
	}

}
