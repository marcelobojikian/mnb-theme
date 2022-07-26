package br.com.mnb.theme.core.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import br.com.mnb.theme.core.builder.ComponentBuilder;
import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.model.SecondElement;
import br.com.mnb.theme.core.xml.element.CommonElement;
import br.com.mnb.theme.core.xml.feature.AbstractFeature;

public class FeatureTester {
	
	final ComponentBuilder builder;

	public FeatureTester(ComponentBuilder builder) {
		this.builder = builder;
	}
	
	public <T extends AbstractFeature> void testFeature(Class<T> clazz, String supported) {

		testFeature(clazz, supported, "ViewNameFeature");
		
		Element element = new Element();
		element.setName("FirstElement");
		
		testFeature(clazz, supported, "ViewNameFeature", element);
		
		SecondElement secondElement = new SecondElement();
		secondElement.setName("SecondElement");
		
		List<CommonElement> asList = Arrays.asList(element, secondElement);
		
		testFeature(clazz, supported, "ViewNameFeature", asList);
		
	}
	
	<T extends AbstractFeature> void testFeature(Class<T> clazz, String supported, String viewName) {
		AbstractFeature feature = builder.createFeature(clazz, viewName);
		assertInstanceOf(clazz, feature);
		testFeatureContent(feature, supported, viewName);
	}
	
	<T extends AbstractFeature> void testFeature(Class<T> clazz, String supported, String viewName, CommonElement... elements) {
		AbstractFeature feature = builder.createFeature(clazz, viewName, elements);
		assertInstanceOf(clazz, feature);
		testFeatureContent(feature, supported, viewName, elements);
	}
	
	<T extends AbstractFeature> void testFeature(Class<T> clazz, String supported, String viewName, List<CommonElement> elements) {
		AbstractFeature feature = builder.createFeature(clazz, viewName, elements);
		assertInstanceOf(clazz, feature);
		testFeatureContent(feature, supported, viewName, elements);
	}
	
	<T extends AbstractFeature> void testFeatureContent(T feature, String supported, String viewName, CommonElement... elements) {
		assertNotNull(feature);
		assertNotNull(feature.getView());
		assertEquals(feature.getSupported(), supported);
		assertEquals(feature.getView().getName(), viewName);
		testFeatureElements(feature, elements);
	}
	
	<T extends AbstractFeature> void testFeatureContent(T feature, String supported, String viewName, List<CommonElement> elements) {
		assertNotNull(feature);
		assertNotNull(feature.getView());
		assertEquals(feature.getSupported(), supported);
		assertEquals(feature.getView().getName(), viewName);
		testFeatureElements(feature, elements);
	}
	
	<T extends AbstractFeature> void testFeatureElements(T feature, CommonElement... elements) {
		List<CommonElement> viewElements = feature.getView().getElements();
		if(elements.length > 0) {
			for (int i = 0; i < elements.length; i++) {
				assertTrue(viewElements.contains(elements[i]));
			}
		}
	}
	
	<T extends AbstractFeature> void testFeatureElements(T feature, List<CommonElement> elements) {
		List<CommonElement> viewElements = feature.getView().getElements();
		if(elements.size() > 0) {
			Iterator<CommonElement> iterator = elements.iterator();
			while (iterator.hasNext()) {
				assertTrue(viewElements.contains(iterator.next()));	
			}
		}
	}

}
