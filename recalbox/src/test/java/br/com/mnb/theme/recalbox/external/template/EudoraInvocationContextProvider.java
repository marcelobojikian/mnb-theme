package br.com.mnb.theme.recalbox.external.template;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;

import br.com.mnb.theme.recalbox.external.template.ExternalFileTestCase.EvaluatorTestCase;
import br.com.mnb.theme.recalbox.xml.element.Image;

public class EudoraInvocationContextProvider implements TestTemplateInvocationContextProvider {

	@Override
	public boolean supportsTestTemplate(ExtensionContext context) {
		System.out.println(context.getDisplayName());
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
		return Stream.of(featureMainContext(new ExternalFileTestCase("First group ",
				new String[] { "3do", "amiga", "amiga1200", "amiga600", "amstradcpc", "apple2", "atari2600",
						"atari5200", "atari7800", "atari800", "atarijaguar", "atarist", "c64", "cavestory", "coco",
						"doom", "dragon32", "dreamcast", "favorites", "fba", "fbneo", "fds", "gamegear", "gb", "gba",
						"gbc", "gw", "imageviewer", "intellivision", "kodi", "lutro", "lynx", "macintosh", "mame",
						"mastersystem", "megadrive", "moonlight", "msx", "msx1", "msx2", "n64", "neogeo", "nes", "ngp",
						"ngpc", "odyssey2", "pc", "pcengine", "pcenginecd", "ports", "psx", "saturn", "scummvm",
						"sega32x", "segacd", "sg1000", "snes", "supergrafx", "vectrex", "videopac", "virtualboy",
						"wonderswan", "wonderswancolor", "zmachine", "zx81", "zxspectrum" },
				new EvaluatorTestCase("system", 2, Image.class, Image.class),
				new EvaluatorTestCase("basic, detailed", 2, Image.class, Image.class))),
				featureMainContext(new ExternalFileTestCase("Second group ",
						new String[] { "colecovision", "gc", "ps2", "psp", "wii" },
						new EvaluatorTestCase("system", 2, false, Image.class, Image.class),
						new EvaluatorTestCase("basic, detailed", 2, false, Image.class, Image.class)

				)));
	}

	private TestTemplateInvocationContext featureMainContext(ExternalFileTestCase themeTestCase) {
		return new TestTemplateInvocationContext() {

			@Override
			public String getDisplayName(int invocationIndex) {
				return themeTestCase.getDisplayName() + "[" + themeTestCase.getSystems().length + "] systems";
			}

			@Override
			public List<Extension> getAdditionalExtensions() {
				return Arrays.asList(new GenericTypedParameterResolver<ExternalFileTestCase>(themeTestCase));
			}

		};
	}

}
