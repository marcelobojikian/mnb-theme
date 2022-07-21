package br.com.mnb.theme.emulationstation.factory;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import br.com.mnb.theme.core.builder.ComponentBuilder;
import br.com.mnb.theme.emulationstation.xml.theme.EmulationStationTheme;

class EmulationStationFactoryTest {

	@Test
	void sucessWhenCreateEmulationStationThemeWithBuilder() {

		ComponentBuilder builder = new ComponentBuilder();
		EmulationStationFactory factory = new EmulationStationFactory(builder);

		EmulationStationTheme theme = factory.createTheme(4);
		assertInstanceOf(EmulationStationTheme.class, theme);

		theme = factory.createTheme(4, "includeOne");
		assertInstanceOf(EmulationStationTheme.class, theme);

	}

	@Test
	void sucessWhenCreateEmulationStationTheme() {
		
		EmulationStationFactory factory = new EmulationStationFactory();

		EmulationStationTheme theme = factory.createTheme(4);
		assertInstanceOf(EmulationStationTheme.class, theme);

		theme = factory.createTheme(4, "includeOne");
		assertInstanceOf(EmulationStationTheme.class, theme);

	}

}
