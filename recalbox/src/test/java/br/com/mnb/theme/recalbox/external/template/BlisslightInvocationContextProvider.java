package br.com.mnb.theme.recalbox.external.template;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;

import br.com.mnb.theme.recalbox.external.template.ExternalFileTestCase.EvaluatorTestCase;
import br.com.mnb.theme.recalbox.xml.element.Datetime;
import br.com.mnb.theme.recalbox.xml.element.HelpSystem;
import br.com.mnb.theme.recalbox.xml.element.Image;
import br.com.mnb.theme.recalbox.xml.element.Rating;
import br.com.mnb.theme.recalbox.xml.element.Sound;
import br.com.mnb.theme.recalbox.xml.element.Text;
import br.com.mnb.theme.recalbox.xml.element.TextList;

public class BlisslightInvocationContextProvider implements TestTemplateInvocationContextProvider {

	@Override
	public boolean supportsTestTemplate(ExtensionContext context) {
		System.out.println(context.getDisplayName());
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
		return Stream.of(featureMainContext(new ExternalFileTestCase("First group ",
				new String[] { "amiga1200", "amiga600", "apple2", "atari2600", "atari7800", "atarist", "c64",
						"cavestory", "colecovision", "doom", "dreamcast", "fbneo", "fds", "gamegear", "gb", "gba",
						"gbc", "gc", "gw", "lutro", "lynx", "mastersystem", "megadrive", "moonlight", "msx", "neogeo",
						"nes", "ngp", "ngpc", "odyssey2", "pc", "pcengine", "pcenginecd", "psp", "psx", "sega32x",
						"segacd", "sg1000", "snes", "supergrafx", "vectrex", "virtualboy", "wii", "wonderswan",
						"wonderswancolor", "zelda", "zx81", "zxspectrum" },
				new EvaluatorTestCase("system", 4, Sound.class, Image.class, Image.class, HelpSystem.class),
				new EvaluatorTestCase("basic, detailed", 4, Image.class, Text.class, Text.class, Image.class),
				new EvaluatorTestCase("basic", 1, TextList.class),
				new EvaluatorTestCase("detailed", 21, Image.class, Text.class, Text.class, Text.class, Text.class,
						Text.class, Text.class, Text.class, Text.class, Text.class, Text.class, Text.class,
						Datetime.class, Text.class, Text.class, Text.class, Text.class, Datetime.class, Rating.class,
						Text.class, TextList.class))

		), featureMainContext(new ExternalFileTestCase("Second group ",
				new String[] { "favorites", "fba", "imageviewer", "mame", "msx1", "msx2", "n64", "scummvm" },
				new EvaluatorTestCase("system", 4, Sound.class, Image.class, Image.class, HelpSystem.class),
				new EvaluatorTestCase("basic, detailed", 3, Image.class, Text.class, Image.class),
				new EvaluatorTestCase("basic", 1, TextList.class),
				new EvaluatorTestCase("detailed", 21, Image.class, Text.class, Text.class, Text.class, Text.class,
						Text.class, Text.class, Text.class, Text.class, Text.class, Text.class, Text.class,
						Datetime.class, Text.class, Text.class, Text.class, Text.class, Datetime.class, Rating.class,
						Text.class, TextList.class)

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
