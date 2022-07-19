package br.com.mnb.theme.emulationstation.factory;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import br.com.mnb.theme.core.factory.InstanceFactory;
import br.com.mnb.theme.emulationstation.xml.theme.EmulationStationTheme;

class EmulationStationFactoryTest {

	@Test
	void sucessWhenCreateEmulationStationThemeWithInstanceFactory() {

		InstanceFactory instanceFactory = new InstanceFactory();
		EmulationStationFactory factory = new EmulationStationFactory(instanceFactory);

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
