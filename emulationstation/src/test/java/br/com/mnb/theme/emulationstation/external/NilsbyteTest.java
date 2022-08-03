package br.com.mnb.theme.emulationstation.external;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.util.ResourceUtils;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.DefaultNodeMatcher;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.ElementSelectors;

import br.com.mnb.theme.emulationstation.builder.EvaluatorBuilder;
import br.com.mnb.theme.emulationstation.evaluator.ThemeEvaluator;
import br.com.mnb.theme.emulationstation.external.template.ExternalFileTestCase;
import br.com.mnb.theme.emulationstation.external.template.ExternalFileTestCase.EvaluatorTestCase;
import br.com.mnb.theme.emulationstation.external.template.NilsbyteInvocationContextProvider;
import br.com.mnb.theme.emulationstation.xml.converter.ESConverter;
import br.com.mnb.theme.emulationstation.xml.theme.EmulationStationTheme;

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
	
	static final String targetFormatter = "classpath:external/%1$s/%2$s/theme.xml";

	@TestTemplate
    @ExtendWith(NilsbyteInvocationContextProvider.class)
	public void successWhenTestExternalFile(ExternalFileTestCase testCase) throws FileNotFoundException {

		final String themeFolder = "nilsbyte";
		
		applyTestCase(themeFolder, testCase);
		
	}
	
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
			checkTheme(themeFolder, system, themeEvaluator, false);
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
		
		ESConverter converter = new ESConverter();
		EmulationStationTheme theme = converter.fromXML(content);

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
