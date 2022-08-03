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
import br.com.mnb.theme.recalbox.xml.element.Image;
import br.com.mnb.theme.recalbox.xml.element.Rating;
import br.com.mnb.theme.recalbox.xml.element.Text;
import br.com.mnb.theme.recalbox.xml.element.TextList;

public class RecalboxInvocationContextProvider implements TestTemplateInvocationContextProvider {

	@Override
	public boolean supportsTestTemplate(ExtensionContext context) {
		System.out.println(context.getDisplayName());
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
		return Stream.of(
		featureMainContext(new ExternalFileTestCase("First group ",
				new String[] { "3do", "amiga1200", "amiga600", "apple2", "atari2600", "atari7800", "c64",
						"cavestory", "colecovision", "dreamcast", "fds", "gc", "mastersystem", "megadrive",
						"msx", "msx1", "msx2", "n64", "neogeo", "nes", "odyssey2", "pc", "pcengine", "ports",
						"pcenginecd", "psx", "scummvm", "sega32x", "segacd", "sg1000", "snes", "supergrafx",
						"vectrex", "virtualboy", "wii", "x68000", "zx81", "zxspectrum" },
				false,
				new EvaluatorTestCase("system", 4, Image.class, Image.class, Image.class, Image.class),
				new EvaluatorTestCase("basic, detailed", 3, Image.class, Image.class, Image.class),
				new EvaluatorTestCase("basic", 2, TextList.class, Image.class),
				new EvaluatorTestCase("detailed", 23, Image.class, Image.class, Image.class,
						Text.class, Text.class, Text.class, Text.class, Text.class, Text.class, Text.class, Text.class, Text.class,
						Datetime.class, Text.class, Text.class, Text.class, Text.class, Datetime.class, 
						Text.class, Text.class, Rating.class, Text.class, TextList.class))
		),
		featureMainContext(new ExternalFileTestCase("Second group ",
				new String[] { "amstradcpc","atarist","favorites","gw","imageviewer","moonlight"},
				false,
				new EvaluatorTestCase("system", 2, Image.class, Image.class),
				new EvaluatorTestCase("basic, detailed", 2, Image.class, Image.class),
				new EvaluatorTestCase("basic", 2, TextList.class, Image.class),
				new EvaluatorTestCase("detailed", 23, Image.class, Image.class, Image.class,
						Text.class, Text.class, Text.class, Text.class, Text.class, Text.class, Text.class, Text.class, Text.class,
						Datetime.class, Text.class, Text.class, Text.class, Text.class, Datetime.class, 
						Text.class, Text.class, Rating.class, Text.class, TextList.class))
		), 
		featureMainContext(new ExternalFileTestCase("Third group ",
				new String[] { "doom","fba","fbneo","mame" },
				false,
				new EvaluatorTestCase("system", 3, Image.class, Image.class, Image.class),
				new EvaluatorTestCase("basic, detailed", 2, Image.class, Image.class),
				new EvaluatorTestCase("basic", 2, TextList.class, Image.class),
				new EvaluatorTestCase("detailed", 23, Image.class, Image.class, Image.class,
						Text.class, Text.class, Text.class, Text.class, Text.class, Text.class, Text.class, Text.class, Text.class,
						Datetime.class, Text.class, Text.class, Text.class, Text.class, Datetime.class, 
						Text.class, Text.class, Rating.class, Text.class, TextList.class))
		),
		featureMainContext(new ExternalFileTestCase("Fourth group ",
				new String[] { "gamegear", "gb", "gba", "gbc", "lutro", "lynx", "nds", "ngp", "ngpc", "psp",
						"wonderswan", "wonderswancolor" },
				false,
				new EvaluatorTestCase("system", 3, Image.class, Image.class, Image.class),
				new EvaluatorTestCase("basic, detailed", 3, Image.class, Image.class, Image.class),
				new EvaluatorTestCase("basic", 2, TextList.class, Image.class),
				new EvaluatorTestCase("detailed", 23, Image.class, Image.class, Image.class,
						Text.class, Text.class, Text.class, Text.class, Text.class, Text.class, Text.class, Text.class, Text.class,
						Datetime.class, Text.class, Text.class, Text.class, Text.class, Datetime.class, 
						Text.class, Text.class, Rating.class, Text.class, TextList.class))
		));
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
