package br.com.mnb.theme.batocera.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.mnb.theme.batocera.xml.feature.AbstractFeature;
import br.com.mnb.theme.batocera.xml.view.View;
import br.com.mnb.theme.core.builder.ComponentBuilder;
import br.com.mnb.theme.core.factory.ExtensionFactory;
import br.com.mnb.theme.core.factory.SimpleFactory;
import br.com.mnb.theme.core.xml.element.CommonElement;

public class BatoceraBuilder extends ComponentBuilder implements FeatureBuilder {

	private ExtensionFactory<AbstractFeature> featureFactory;

	public BatoceraBuilder() {
		super();
		this.featureFactory = new SimpleFactory<AbstractFeature>();
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

	public ExtensionFactory<AbstractFeature> getFeatureFactory() {
		return featureFactory;
	}

	public void setFeatureFactory(ExtensionFactory<AbstractFeature> featureFactory) {
		this.featureFactory = featureFactory;
	}

}
