package br.com.mnb.theme.core.factory;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.mnb.theme.core.model.Element;
import br.com.mnb.theme.core.model.Theme;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.theme.AbstractTheme;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;
import br.com.mnb.theme.core.xml.view.View;

class SimpleFactoryTest {

	SimpleFactory<AbstractTheme> themeFactory = new SimpleFactory<>();
	SimpleFactory<AbstractViewElement> viewFactory = new SimpleFactory<>();
	SimpleFactory<AbstractElement> elementFactory = new SimpleFactory<>();

	@Test
	void sucessWhenCreateTheme() {
		Theme theme = themeFactory.create(Theme.class);
		assertNotNull(theme);
	}

	@Test
	void failWhenCreateThemeWithNullParameter() {
		assertThrows(NullPointerException.class, () -> {
			themeFactory.create(null);
		});
	}

	@Test
	void failWhenCreateThemeWithInvalidParameter() {
		assertThrows(RuntimeException.class, () -> {
			themeFactory.create(AbstractTheme.class);
		});
	}

	@Test
	void sucessWhenCreateView() {
		View view = viewFactory.create(View.class);
		assertNotNull(view);
	}

	@Test
	void failWhenCreateViewWithNullParameter() {
		assertThrows(NullPointerException.class, () -> {
			viewFactory.create(null);
		});
	}

	@Test
	void failWhenCreateViewWithInvalidParameter() {
		assertThrows(RuntimeException.class, () -> {
			viewFactory.create(AbstractViewElement.class);
		});
	}

	@Test
	void sucessWhenCreateElement() {
		Element element = elementFactory.create(Element.class);
		assertNotNull(element);
	}

	@Test
	void failWhenCreateElementWithNullParameter() {
		assertThrows(NullPointerException.class, () -> {
			elementFactory.create(null);
		});
	}

	@Test
	void failWhenCreateElementWithInvalidParameter() {
		assertThrows(RuntimeException.class, () -> {
			elementFactory.create(AbstractElement.class);
		});
	}

}
