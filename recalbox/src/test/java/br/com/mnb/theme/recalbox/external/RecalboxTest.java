package br.com.mnb.theme.recalbox.external;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.util.ResourceUtils;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.DefaultNodeMatcher;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.ElementSelectors;

import com.thoughtworks.xstream.converters.ConversionException;

import br.com.mnb.theme.recalbox.builder.EvaluatorBuilder;
import br.com.mnb.theme.recalbox.evaluator.ThemeEvaluator;
import br.com.mnb.theme.recalbox.external.template.BlisslightInvocationContextProvider;
import br.com.mnb.theme.recalbox.external.template.EudoraInvocationContextProvider;
import br.com.mnb.theme.recalbox.external.template.ExternalFileTestCase;
import br.com.mnb.theme.recalbox.external.template.ExternalFileTestCase.EvaluatorTestCase;
import br.com.mnb.theme.recalbox.external.template.PixelInvocationContextProvider;
import br.com.mnb.theme.recalbox.external.template.RecalboxInvocationContextProvider;
import br.com.mnb.theme.recalbox.xml.converter.RecalboxConverter;
import br.com.mnb.theme.recalbox.xml.element.HelpSystem;
import br.com.mnb.theme.recalbox.xml.element.Image;
import br.com.mnb.theme.recalbox.xml.element.Text;
import br.com.mnb.theme.recalbox.xml.element.TextList;
import br.com.mnb.theme.recalbox.xml.theme.RecalboxTheme;

/**
 * 
 * Project recalbox-themes
 * 
 * Download to github from
 * emulationstation/src/test/resourcer/external/recalbox-themes
 * And enable the class test
 * 
 * @author marcelo
 *
 */
@Disabled("Disabled until Download project!")
class RecalboxTest {
	
	static final String targetFormatter = "classpath:external/recalbox-themes/%1$s/%2$s/theme.xml";
	static final String targetSingleFileFormatter = "classpath:external/recalbox-themes/%1$s/theme.xml";

	@TestTemplate
    @ExtendWith(BlisslightInvocationContextProvider.class)
	public void successWhenTestExternalFileBlisslight(ExternalFileTestCase testCase) throws FileNotFoundException {
		
		final String themeFolder = "Blisslight";
		
		applyTestCase(themeFolder, testCase);
		
	}

	@TestTemplate
    @ExtendWith(EudoraInvocationContextProvider.class)
	public void successWhenTestExternalFileEudora(ExternalFileTestCase testCase) throws FileNotFoundException {
		
		final String themeFolder = "eudora";
		
		applyTestCase(themeFolder, testCase);
		
	}

	@TestTemplate
    @ExtendWith(EudoraInvocationContextProvider.class)
	public void successWhenTestExternalFileEudoraBigshot(ExternalFileTestCase testCase) throws FileNotFoundException {
		
		final String themeFolder = "eudora-bigshot";
		
		applyTestCase(themeFolder, testCase);
		
	}

	@TestTemplate
    @ExtendWith(EudoraInvocationContextProvider.class)
	public void successWhenTestExternalFileEudoraConcise(ExternalFileTestCase testCase) throws FileNotFoundException {
		
		final String themeFolder = "eudora-concise";
		
		applyTestCase(themeFolder, testCase);
		
	}

	@TestTemplate
    @ExtendWith(PixelInvocationContextProvider.class)
	public void successWhenTestExternalFilePixel(ExternalFileTestCase testCase) throws FileNotFoundException {
		
		final String themeFolder = "pixel";
		
		applyTestCase(themeFolder, testCase);
		
	}

	@Test
	@SuppressWarnings("unchecked")
	public void failWithInvalidPixelTheme() throws FileNotFoundException {
		
		final String themeFolder = "pixel";
		
		// @formatter:off	
		ThemeEvaluator themeEvaluator = EvaluatorBuilder
		.create()
			.evaluateView("system", 4, Image.class, Image.class, Image.class, HelpSystem.class)
			.evaluateView("basic, detailed", 0)
			.evaluateView("basic", 4, Image.class, Image.class, Image.class, TextList.class)
			.evaluateView("detailed", 6, Image.class, Image.class, Image.class, Image.class, TextList.class, Text.class)
		.build();
		// @formatter:on

		System.out.println("Test system retropie [invalid]");

		assertThrows(ConversionException.class, () -> {
			checkTheme(themeFolder, "retropie", themeEvaluator, true);
		});
		
	}

	@TestTemplate
    @ExtendWith(RecalboxInvocationContextProvider.class)
	public void successWhenTestExternalFileRecalbox(ExternalFileTestCase testCase) throws FileNotFoundException {
		
		final String themeFolder = "recalbox";
		
		applyTestCase(themeFolder, testCase);
		
	}

//	@TestTemplate
//    @ExtendWith(RecalboxInvocationContextProvider.class)
	public void successWhenTestExternalFileRecalboxGoa2(ExternalFileTestCase testCase) throws FileNotFoundException {
		
		final String themeFolder = "recalbox-goa2";
		String target = String.format(targetFormatter, themeFolder);
		
		// @formatter:off	
		ThemeEvaluator themeEvaluator = EvaluatorBuilder
		.create()
			.evaluateView("system", 4, Image.class, Image.class, Image.class, HelpSystem.class)
			.evaluateView("basic, detailed", 0)
			.evaluateView("basic", 4, Image.class, Image.class, Image.class, TextList.class)
			.evaluateView("detailed", 6, Image.class, Image.class, Image.class, Image.class, TextList.class, Text.class)
		.build();
		// @formatter:on
		
		applyTestCase(themeFolder, testCase);
		
	}
	
//	File file = ResourceUtils.getFile("classpath:external/recalbox-themes/recalbox");
//	String[] list = file.list();
//	Arrays.sort(list);
//	for (String folder : list) {
//		System.out.print("\""+folder+"\",");
//	}
	
	void applyTestCase(String themeFolder, ExternalFileTestCase testCase) throws FileNotFoundException {

		String[] systems = testCase.getSystems();
		for (String system : systems) {
			EvaluatorTestCase[] evaluators = testCase.getEvaluator();
			EvaluatorBuilder builder = EvaluatorBuilder.create();
			for (EvaluatorTestCase evaluator : evaluators) {
				builder.evaluateView(evaluator.getName(), evaluator.getCount(), evaluator.getClasses());
			}
			ThemeEvaluator themeEvaluator = builder.build();
			System.out.println("Test system " + system);
			checkTheme(themeFolder, system, themeEvaluator, testCase.isRequeredSanitize());
		}
		
	}

	void checkTheme(String themeFolder, String system, ThemeEvaluator themeEvaluator, boolean sanitizeTarget) throws FileNotFoundException {
		
		String target = String.format(targetFormatter, themeFolder, system);
		
		File file = ResourceUtils.getFile(target);
		String content = null;
		try {
			content = Files.readString(file.toPath());
			if(sanitizeTarget) {
				content = content.replaceAll("&", "&amp;");
			}
		} catch (IOException e) {
			throw new FileNotFoundException(target);
		}
		
		RecalboxConverter converter = new RecalboxConverter();
		RecalboxTheme theme = converter.fromXML(content);

		// Evaluator apply
		themeEvaluator.evaluate(theme);
		
		String xml = converter.toXML(theme);
		
		Diff diff = DiffBuilder.compare(xml).withTest(content)
	    		.checkForSimilar()
	    		.ignoreComments()
	    		.ignoreWhitespace()
	    		.normalizeWhitespace()
	    		.ignoreElementContentWhitespace() 
	    		.withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byNameAndText))
	    		.build();
		
	    assertFalse(diff.hasDifferences(), "XML similar " + diff.toString());
	    
	}

}
