package br.com.mnb.theme.core.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mnb.theme.core.factory.InstanceFactory;
import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.model.Theme;
import br.com.mnb.theme.core.model.View;
import br.com.mnb.theme.core.tools.ElementTester;
import br.com.mnb.theme.core.tools.ThemeTester;
import br.com.mnb.theme.core.tools.ViewTester;

class ComponentBuilderTest {
	
	ComponentBuilder builder;
	
	@BeforeEach
	public void setup() {
		
		InstanceFactory factory = mock(InstanceFactory.class);		
		when(factory.createTheme(Theme.class)).thenReturn(new Theme());
		when(factory.createElement(Element.class)).thenReturn(new Element());
		when(factory.createView(View.class)).thenReturn(new View());
		
		builder = new ComponentBuilder(factory);
		
	}

	@Test
	void sucessWhenCreateTheme() {		
		ThemeTester tester = new ThemeTester(builder); 		
		tester.testTheme(Theme.class);
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
