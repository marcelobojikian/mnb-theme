package br.com.mnb.theme.emulationstation.external.template;

import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;

import br.com.mnb.theme.emulationstation.external.template.ExternalFileTestCase.EvaluatorTestCase;
import br.com.mnb.theme.emulationstation.xml.element.Datetime;
import br.com.mnb.theme.emulationstation.xml.element.HelpSystem;
import br.com.mnb.theme.emulationstation.xml.element.Image;
import br.com.mnb.theme.emulationstation.xml.element.Rating;
import br.com.mnb.theme.emulationstation.xml.element.Text;
import br.com.mnb.theme.emulationstation.xml.element.TextList;

public class NilsbyteInvocationContextProvider implements TestTemplateInvocationContextProvider {

	@Override
	public boolean supportsTestTemplate(ExtensionContext context) {
		System.out.println(context.getDisplayName());
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
		return Stream.of(featureMainContext(new ExternalFileTestCase("First group ",
				new String[] { "3do", "amiga", "amstradcpc", "atari2600", "atari5200", "atari7800", "atari800",
						"atarijaguar", "atarijaguarcd", "atarilynx", "atarixe", "c64", "colecovision", "dreamcast",
						"famicom", "gamegear", "gb", "gba", "gbc", "gc", "genesis", "intellivision", "macintosh",
						"mastersystem", "megadrive", "neogeo", "neogeocd", "nes", "ngp", "ngpc", "odyssey2", "pc",
						"pcengine", "ps2", "psp", "psx", "saturn", "sega32x", "segacd", "sfc", "snes", "vectrex",
						"virtualboy", "wii", "wonderswan", "wonderswancolor", "xbox", "zmachine", "zxspectrum" },
				new EvaluatorTestCase("system", 3, Image.class, Image.class, HelpSystem.class),
				new EvaluatorTestCase("basic, detailed", 3, Text.class, Text.class, Image.class),
				new EvaluatorTestCase("basic", 1, TextList.class),
				new EvaluatorTestCase("detailed", 19, Image.class, Text.class, Text.class, Text.class, Text.class,
						Text.class, Text.class, Text.class, Text.class, Text.class, Datetime.class, Text.class,
						Text.class, Text.class, Text.class, Datetime.class, Rating.class, Text.class, TextList.class)

		)), featureMainContext(new ExternalFileTestCase("Second group ",
				new String[] { "apple2", "atarifalcon", "atarist", "fba", "mame", "msx", "n64", "nds", "ports",
						"scummvm" },
				new EvaluatorTestCase("system", 3, Image.class, Image.class, HelpSystem.class),
				new EvaluatorTestCase("basic, detailed", 2, Text.class, Image.class),
				new EvaluatorTestCase("basic", 1, TextList.class),
				new EvaluatorTestCase("detailed", 19, Image.class, Text.class, Text.class, Text.class, Text.class,
						Text.class, Text.class, Text.class, Text.class, Text.class, Datetime.class, Text.class,
						Text.class, Text.class, Text.class, Datetime.class, Rating.class, Text.class, TextList.class)

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

class GenericTypedParameterResolver<T> implements ParameterResolver {
	T data;

	public GenericTypedParameterResolver(T data) {
		this.data = data;
	}

	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
		Parameter parameter = parameterContext.getParameter();
		System.out.println("Support: " + parameter);
		return parameter.getType().isInstance(data);
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
		return data;
	}

}
