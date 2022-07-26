package br.com.mnb.theme.emulationstation.factory;

import br.com.mnb.theme.core.builder.ComponentBuilder;
import br.com.mnb.theme.emulationstation.xml.theme.EmulationStationTheme;

public class EmulationStationFactory extends ComponentFactory {

	public EmulationStationFactory() {
		super();
	}

	public EmulationStationFactory(ComponentBuilder builder) {
		super(builder);
	}
	
	/**
	 * 
	 * @return EmulationStationTheme
	 */
	public EmulationStationTheme createTheme(Integer version) {
		return createTheme(EmulationStationTheme.class, version);
	}

	public EmulationStationTheme createTheme(Integer version, String... includes) {
		return createTheme(EmulationStationTheme.class, version, includes);
	}
	
}
