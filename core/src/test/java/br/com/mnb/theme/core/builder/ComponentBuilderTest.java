package br.com.mnb.theme.core.builder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mnb.theme.core.factory.SimpleFactory;
import br.com.mnb.theme.core.model.Feature;
import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.model.Theme;
import br.com.mnb.theme.core.tools.ElementTester;
import br.com.mnb.theme.core.tools.FeatureTester;
import br.com.mnb.theme.core.tools.ThemeTester;
import br.com.mnb.theme.core.tools.ViewTester;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.feature.AbstractFeature;
import br.com.mnb.theme.core.xml.theme.AbstractTheme;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;
import br.com.mnb.theme.core.xml.view.View;

class ComponentBuilderTest {
	
	ComponentBuilder builder;
	
	@BeforeEach
	@SuppressWarnings("unchecked")
	public void setup() {
		
		SimpleFactory<AbstractTheme> themeFactory = mock(SimpleFactory.class);
		when(themeFactory.create(Theme.class)).thenReturn(new Theme());

		SimpleFactory<AbstractFeature> featureFactory = mock(SimpleFactory.class);
		when(featureFactory.create(Feature.class)).thenReturn(new Feature());

		SimpleFactory<AbstractViewElement> viewFactory = mock(SimpleFactory.class);
		when(viewFactory.create(View.class)).thenReturn(new View());

		SimpleFactory<AbstractElement> elementFactory = mock(SimpleFactory.class);
		when(elementFactory.create(Element.class)).thenReturn(new Element());
		
		builder = new ComponentBuilder();
		
		builder.setThemeFactory(themeFactory);
		builder.setViewFactory(viewFactory);
		builder.setElementFactory(elementFactory);
		builder.setFeatureFactory(featureFactory);
		
	}
	
	@Test
	void sucessWhenCreateBuilderHaveAllConverters() {
		ComponentBuilder builder = new ComponentBuilder();
		assertNotNull(builder.getThemeFactory());
		assertNotNull(builder.getViewFactory());
		assertNotNull(builder.getElementFactory());
		assertNotNull(builder.getFeatureFactory());
	}

	@Test
	void sucessWhenCreateTheme() {		
		ThemeTester tester = new ThemeTester(builder); 		
		tester.testTheme(Theme.class);
	}

	@Test
	void sucessWhenCreateFeature() {		
		FeatureTester tester = new FeatureTester(builder);		
		tester.testFeature(Feature.class, "first");
	}

	@Test
	void sucessWhenCreateView() {		
		ViewTester tester = new ViewTester(builder);		
		tester.testView(View.class);
	}

	@Test
	void sucessWhenCreateElement() {		
		ElementTester tester = new ElementTester(builder);
		tester.testElement(Element.class);
	}

}
