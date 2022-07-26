package br.com.mnb.theme.core.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import br.com.mnb.theme.core.factory.ExtensionFactory;
import br.com.mnb.theme.core.factory.SimpleFactory;
import br.com.mnb.theme.core.xml.Content;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.element.CommonElement;
import br.com.mnb.theme.core.xml.feature.AbstractFeature;
import br.com.mnb.theme.core.xml.theme.AbstractTheme;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;
import br.com.mnb.theme.core.xml.view.View;

public class ComponentBuilder implements ThemeBuilder, FeatureBuilder, ViewBuilder, ElementBuilder {

	private ExtensionFactory<AbstractTheme> themeFactory;
	private ExtensionFactory<AbstractFeature> featureFactory;
	private ExtensionFactory<AbstractElement> elementFactory;
	private ExtensionFactory<AbstractViewElement> viewFactory;
	
	public ComponentBuilder() {
		this.themeFactory = new SimpleFactory<AbstractTheme>();
		this.featureFactory = new SimpleFactory<AbstractFeature>();
		this.elementFactory = new SimpleFactory<AbstractElement>();
		this.viewFactory = new SimpleFactory<AbstractViewElement>();
	}

	@Override
	public <T extends AbstractTheme> T createTheme(Class<T> clazz, Integer version) {
		T theme = themeFactory.create(clazz);
		theme.setFormatVersion(version);
		theme.setViewElements(new ArrayList<>());
		theme.setIncludes(new ArrayList<>());
		return theme;
	}

	@Override
	public <T extends AbstractTheme> T createTheme(Class<T> clazz, Integer version, String... includes) {
		T theme = themeFactory.create(clazz);
		theme.setFormatVersion(version);
		theme.setViewElements(new ArrayList<>());
		theme.setIncludes(new ArrayList<>(Arrays.asList(includes)));
		return theme;
	}

	@Override
	public <T extends AbstractFeature> T createFeature(Class<T> clazz, String viewName) {
		T feature = featureFactory.create(clazz);
		View view = createView(View.class, viewName);
		feature.setView(view);
		return feature;
	}

	@Override
	public <T extends AbstractFeature> T createFeature(Class<T> clazz, String viewName, CommonElement... elements) {
		T feature = featureFactory.create(clazz);
		View view = createView(View.class, viewName, new ArrayList<>(Arrays.asList(elements)));
		feature.setView(view);
		return feature;
	}

	@Override
	public <T extends AbstractFeature> T createFeature(Class<T> clazz, String viewName, List<CommonElement> elements) {
		T feature = featureFactory.create(clazz);
		View view = createView(View.class, viewName, elements);
		feature.setView(view);
		return feature;
	}

	@Override
	public <T extends AbstractViewElement> T createView(Class<T> clazz, String name) {
		T view = viewFactory.create(clazz);
		view.setName(name);
		view.setElements(new ArrayList<>());
		return view;
	}

	@Override
	public <T extends AbstractViewElement> T createView(Class<T> clazz, String name, CommonElement... elements) {
		T view = viewFactory.create(clazz);
		view.setName(name);
		view.setElements(new ArrayList<>(Arrays.asList(elements)));
		return view;
	}

	@Override
	public <T extends AbstractViewElement> T createView(Class<T> clazz, String name, List<CommonElement> elements) {
		T view = viewFactory.create(clazz);
		view.setName(name);
		view.setElements(elements);
		return view;
	}

	@Override
	public <T extends AbstractElement> T createElement(Class<T> clazz, String name) {
		T element = elementFactory.create(clazz);
		element.setName(name);
		element.setExtra(false);
		element.setContent(new Content());
		return element;
	}

	@Override
	public <T extends AbstractElement> T createElement(Class<T> clazz, String name, boolean extra) {
		T element = elementFactory.create(clazz);
		element.setName(name);
		element.setExtra(extra);
		element.setContent(new Content());
		return element;
	}

	@Override
	public <T extends AbstractElement> T createElement(Class<T> clazz, String name, Map<String, String> content) {
		T element = elementFactory.create(clazz);
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

	public ExtensionFactory<AbstractTheme> getThemeFactory() {
		return themeFactory;
	}

	public void setThemeFactory(ExtensionFactory<AbstractTheme> themeFactory) {
		this.themeFactory = themeFactory;
	}

	public ExtensionFactory<AbstractElement> getElementFactory() {
		return elementFactory;
	}

	public void setElementFactory(ExtensionFactory<AbstractElement> elementFactory) {
		this.elementFactory = elementFactory;
	}

	public ExtensionFactory<AbstractFeature> getFeatureFactory() {
		return featureFactory;
	}

	public void setFeatureFactory(ExtensionFactory<AbstractFeature> featureFactory) {
		this.featureFactory = featureFactory;
	}

	public ExtensionFactory<AbstractViewElement> getViewFactory() {
		return viewFactory;
	}

	public void setViewFactory(ExtensionFactory<AbstractViewElement> viewFactory) {
		this.viewFactory = viewFactory;
	}

}
