package br.com.mnb.theme.emulationstation.external;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import br.com.mnb.theme.emulationstation.external.tool.EvaluateBuilder;
import br.com.mnb.theme.emulationstation.external.tool.SystemEvaluater;
import br.com.mnb.theme.emulationstation.external.tool.ViewEvaluator;
import br.com.mnb.theme.emulationstation.xml.element.Datetime;
import br.com.mnb.theme.emulationstation.xml.element.HelpSystem;
import br.com.mnb.theme.emulationstation.xml.element.Image;
import br.com.mnb.theme.emulationstation.xml.element.Rating;
import br.com.mnb.theme.emulationstation.xml.element.Text;
import br.com.mnb.theme.emulationstation.xml.element.TextList;

/**
 * 
 * Project nilsbyte Releases Tag 1.4
 * 
 * Download to github from emulationstation/src/test/resourcer/external/nilsbyte
 * And enable the class test
 * 
 * @author marcelo
 *
 */
@Disabled("Disabled until Download project!")
public class NilsbyteTest {

	@Test
	@SuppressWarnings("unchecked")
	public void testWithDefaultEvaluator() throws FileNotFoundException {
		// @formatter:off
		String[] systemArray = new String[] {
				"3do", "amiga", "amstradcpc", "atari2600",
				"atari5200", "atari7800", "atari800", "atarijaguar", 
				"atarijaguarcd", "atarilynx", "atarixe", "c64", 
				"colecovision", "dreamcast", "famicom", "gamegear", 
				"gb", "gba", "gbc", "gc", "genesis", "intellivision", 
				"macintosh", "mastersystem", "megadrive", "neogeo", 
				"neogeocd", "nes", "ngp", "ngpc", "odyssey2", "pc", 
				"pcengine", "ps2", "psp", "psx", "saturn", "sega32x", 
				"segacd", "sfc", "snes", "vectrex", "virtualboy", 
				"wii", "wonderswan", "wonderswancolor", "xbox", 
				"zmachine", "zxspectrum" };
		
		ViewEvaluator defaultEvaluator = EvaluateBuilder
		.create()
			.evaluateView("system", 3, Image.class, Image.class, HelpSystem.class)
			.evaluateView("basic, detailed", 3, Text.class, Text.class, Image.class)
			.evaluateView("basic", 1, TextList.class)
			.evaluateView("detailed", 19, Image.class, Text.class, Text.class, Text.class, Text.class, Text.class, Text.class,
					Text.class, Text.class, Text.class, Datetime.class, Text.class, Text.class, Text.class, Text.class,
					Datetime.class, Rating.class, Text.class, TextList.class)
		.build();
		// @formatter:on

		for (String systemName : systemArray) {
			System.out.println("Test system " + systemName);
			testSystem(systemName, defaultEvaluator);
		}
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testWithDiffBasicDtailedEvaluator() throws FileNotFoundException {
		// @formatter:off
		String[] systemArray = new String[] {
				"apple2", "atarifalcon", "atarist", "fba", 
				"mame", "msx", "n64", "nds", "ports", "scummvm" };
		
		ViewEvaluator diffBasicDtailedEvaluator = EvaluateBuilder
		.create()
			.evaluateView("system", 3, Image.class, Image.class, HelpSystem.class)
			.evaluateView("basic, detailed", 2, Text.class, Image.class)
			.evaluateView("basic", 1, TextList.class)
			.evaluateView("detailed", 19, Image.class, Text.class, Text.class, Text.class, Text.class, Text.class, Text.class,
					Text.class, Text.class, Text.class, Datetime.class, Text.class, Text.class, Text.class, Text.class,
					Datetime.class, Rating.class, Text.class, TextList.class)
		.build();
		// @formatter:on

		for (String systemName : systemArray) {
			System.out.println("Test system " + systemName);
			testSystem(systemName, diffBasicDtailedEvaluator);
		}
	}

	public void testSystem(String target, ViewEvaluator viewEvaluator) throws FileNotFoundException {
		String targetValue = getTarget(target);
		SystemEvaluater evaluator = new SystemEvaluater(targetValue);
		evaluator.evaluate(viewEvaluator);
	}

	public String getTarget(String target) {
		return "classpath:external/nilsbyte/" + target + "/theme.xml";
	}

}
