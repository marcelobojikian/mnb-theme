package br.com.mnb.theme.recalbox.external.template;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;

import br.com.mnb.theme.recalbox.external.template.ExternalFileTestCase.EvaluatorTestCase;
import br.com.mnb.theme.recalbox.xml.element.HelpSystem;
import br.com.mnb.theme.recalbox.xml.element.Image;
import br.com.mnb.theme.recalbox.xml.element.Text;
import br.com.mnb.theme.recalbox.xml.element.TextList;

public class PixelInvocationContextProvider implements TestTemplateInvocationContextProvider {

	@Override
	public boolean supportsTestTemplate(ExtensionContext context) {
		System.out.println(context.getDisplayName());
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
		return Stream.of(featureMainContext(new ExternalFileTestCase("First group ",
				new String[] { "3do", "ags", "amiga", "amiga1200", "amiga600", "amstradcpc", "apple2", "arcade",
						"atari2600", "atari5200", "atari7800", "atari800", "atarijaguar", "atarilynx", "atarist",
						"cavestory", "coco", "colecovision", "daphne", "doom", "dragon32", "dreamcast", "famicom",
						"favorites", "fba", "fbneo", "fds", "gamegear", "gb", "gba", "gbc", "gc", "genesis",
						"intellivision", "kodi", "love", "lutro", "lynx", "macintosh", "mame", "megadrive", "mess",
						"moonlight", "msx", "msx1", "msx2", "n64", "nds", "neogeo", "nes", "ngp", "ngpc", "odyssey2",
						"pc", "ports", "ps2", "psp", "psx", "residualvm", "samcoupe", "saturn", "scummvm", "sega32x",
						"segacd", "sfc", "sg-1000", "sg1000", "snes", "steam", "supergrafx", "vectrex", "videopac",
						"virtualboy", "wii", "wonderswan", "wonderswancolor", "zmachine", "zx81", "zxspectrum" },
				false,
				new EvaluatorTestCase("system", 4, Image.class, Image.class, Image.class, HelpSystem.class),
				new EvaluatorTestCase("basic, detailed", 0),
				new EvaluatorTestCase("basic", 4, Image.class, Image.class, Image.class, TextList.class),
				new EvaluatorTestCase("detailed", 6, Image.class, Image.class, Image.class, TextList.class, Image.class,
						Text.class))
		), featureMainContext(new ExternalFileTestCase("Second group ",
				new String[] { "c64", "mastersystem", "pcengine", "pcenginecd", "desktop", "tg16", "tgcd" },
				false,
				new EvaluatorTestCase("system", 4, Image.class, Image.class, Image.class, HelpSystem.class),
				new EvaluatorTestCase("basic, detailed", 0),
				new EvaluatorTestCase("basic", 4, Image.class, Image.class, Image.class, TextList.class),
				new EvaluatorTestCase("detailed", 6, Image.class, Image.class, Image.class, Image.class, TextList.class,
						Text.class)
		)), featureMainContext(new ExternalFileTestCase("Third group ",
				new String[] { "pipplware","system" },
				false,
				new EvaluatorTestCase("system", 4, Image.class, Image.class, Image.class, HelpSystem.class),
				new EvaluatorTestCase("basic, detailed", 0),
				new EvaluatorTestCase("basic", 4, Image.class, Image.class, Image.class, TextList.class),
				new EvaluatorTestCase("detailed", 6, Image.class, Image.class, Image.class, TextList.class, Text.class, Text.class)
		)), featureMainContext(new ExternalFileTestCase("Fourth group ",
				new String[] { "gameandwatch", "gw" },
				true,
				new EvaluatorTestCase("system", 4, Image.class, Image.class, Image.class, HelpSystem.class),
				new EvaluatorTestCase("basic, detailed", 0),
				new EvaluatorTestCase("basic", 4, Image.class, Image.class, Image.class, TextList.class),
				new EvaluatorTestCase("detailed", 6, Image.class, Image.class, Image.class, Image.class, TextList.class, Text.class)
		))
		);
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
