package br.com.mnb.theme.batocera.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.mnb.theme.batocera.factory.BatoceraInstanceFactory;
import br.com.mnb.theme.batocera.xml.feature.AbstractFeature;
import br.com.mnb.theme.batocera.xml.view.View;
import br.com.mnb.theme.core.builder.ComponentBuilder;
import br.com.mnb.theme.core.xml.element.CommonElement;

public class BatoceraBuilder extends ComponentBuilder implements FeatureBuilder {

	private BatoceraInstanceFactory instaceFactory;

	public BatoceraBuilder() {
		this(new BatoceraInstanceFactory());
	}

	public BatoceraBuilder(BatoceraInstanceFactory instaceFactory) {
		super(instaceFactory);
		this.instaceFactory = instaceFactory;
	}

	@Override
	public <T extends AbstractFeature> T createFeature(Class<T> clazz, String viewName) {
		T feature = instaceFactory.createFeature(clazz);
		View view = instaceFactory.createView(View.class);
		view.setName(viewName);
		feature.setView(view);
		return feature;
	}

	@Override
	public <T extends AbstractFeature> T createFeature(Class<T> clazz, String viewName, CommonElement... elements) {
		T feature = instaceFactory.createFeature(clazz);
		View view = instaceFactory.createView(View.class);
		view.setName(viewName);
		view.setElements(new ArrayList<>(Arrays.asList(elements)));
		feature.setView(view);
		return feature;
	}

	@Override
	public <T extends AbstractFeature> T createFeature(Class<T> clazz, String viewName, List<CommonElement> elements) {
		T feature = instaceFactory.createFeature(clazz);
		View view = instaceFactory.createView(View.class);
		view.setName(viewName);
		view.setElements(elements);
		feature.setView(view);
		return feature;
	}

}
