package br.com.mnb.theme.batocera.factory;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.mnb.theme.batocera.xml.element.Text;
import br.com.mnb.theme.batocera.xml.theme.BatoceraTheme;
import br.com.mnb.theme.batocera.xml.view.View;
import br.com.mnb.theme.core.factory.ElementFactory;
import br.com.mnb.theme.core.factory.ThemeFactory;
import br.com.mnb.theme.core.factory.ViewFactory;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.theme.AbstractTheme;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;

class BatoceraInstanceFactoryTest {

	ThemeFactory themeFactory = new BatoceraInstanceFactory();
	ViewFactory viewFactory = new BatoceraInstanceFactory();
	ElementFactory elementFactory = new BatoceraInstanceFactory();

	@Test
	void sucessWhenCreateTheme() {
		BatoceraTheme theme = themeFactory.createTheme(BatoceraTheme.class);
		assertNotNull(theme);
	}

	@Test
	void failWhenCreateThemeWithNullParameter() {
		assertThrows(NullPointerException.class, () -> {
			themeFactory.createTheme(null);
		});
	}

	@Test
	void failWhenCreateThemeWithInvalidParameter() {
		assertThrows(RuntimeException.class, () -> {
			themeFactory.createTheme(AbstractTheme.class);
		});
	}

	@Test
	void sucessWhenCreateView() {
		View view = viewFactory.createView(View.class);
		assertNotNull(view);
	}

	@Test
	void failWhenCreateViewWithNullParameter() {
		assertThrows(NullPointerException.class, () -> {
			viewFactory.createView(null);
		});
	}

	@Test
	void failWhenCreateViewWithInvalidParameter() {
		assertThrows(RuntimeException.class, () -> {
			viewFactory.createView(AbstractViewElement.class);
		});
	}

	@Test
	void sucessWhenCreateElement() {
		Text element = elementFactory.createElement(Text.class);
		assertNotNull(element);
	}

	@Test
	void failWhenCreateElementWithNullParameter() {
		assertThrows(NullPointerException.class, () -> {
			elementFactory.createElement(null);
		});
	}

	@Test
	void failWhenCreateElementWithInvalidParameter() {
		assertThrows(RuntimeException.class, () -> {
			elementFactory.createElement(AbstractElement.class);
		});
	}

}
