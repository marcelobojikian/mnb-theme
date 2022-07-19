package br.com.mnb.theme.core.factory;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.model.Theme;
import br.com.mnb.theme.core.model.View;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.theme.AbstractTheme;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;

class InstanceFactoryTest {

	InstanceFactory instaceFactory = new InstanceFactory();

	@Test
	void sucessWhenCreateTheme() {
		Theme theme = instaceFactory.createTheme(Theme.class);
		assertNotNull(theme);
	}

	@Test
	void failWhenCreateThemeWithNullParameter() {
		assertThrows(NullPointerException.class, () -> {
			instaceFactory.createTheme(null);
		});
	}

	@Test
	void failWhenCreateThemeWithInvalidParameter() {
		assertThrows(RuntimeException.class, () -> {
			instaceFactory.createTheme(AbstractTheme.class);
		});
	}

	@Test
	void sucessWhenCreateView() {
		View view = instaceFactory.createView(View.class);
		assertNotNull(view);
	}

	@Test
	void failWhenCreateViewWithNullParameter() {
		assertThrows(NullPointerException.class, () -> {
			instaceFactory.createView(null);
		});
	}

	@Test
	void failWhenCreateViewWithInvalidParameter() {
		assertThrows(RuntimeException.class, () -> {
			instaceFactory.createView(AbstractViewElement.class);
		});
	}

	@Test
	void sucessWhenCreateElement() {
		Element element = instaceFactory.createElement(Element.class);
		assertNotNull(element);
	}

	@Test
	void failWhenCreateElementWithNullParameter() {
		assertThrows(NullPointerException.class, () -> {
			instaceFactory.createElement(null);
		});
	}

	@Test
	void failWhenCreateElementWithInvalidParameter() {
		assertThrows(RuntimeException.class, () -> {
			instaceFactory.createElement(AbstractElement.class);
		});
	}

}
