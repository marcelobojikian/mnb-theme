package br.com.mnb.theme.batocera.builder;

import java.util.List;

import br.com.mnb.theme.batocera.xml.feature.AbstractFeature;
import br.com.mnb.theme.core.xml.element.CommonElement;

interface FeatureBuilder {
	
	public <T extends AbstractFeature> T createFeature(Class<T> clazz, String viewName);
	
	public <T extends AbstractFeature> T createFeature(Class<T> clazz, String viewName, CommonElement... elements);
	
	public <T extends AbstractFeature> T createFeature(Class<T> clazz, String viewName, List<CommonElement> elements);

}
