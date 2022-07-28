package br.com.mnb.theme.emulationstation.external.tool;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.util.ResourceUtils;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.DefaultNodeMatcher;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.ElementSelectors;

import br.com.mnb.theme.core.xml.view.ViewElement;
import br.com.mnb.theme.emulationstation.xml.converter.ESConverter;
import br.com.mnb.theme.emulationstation.xml.theme.EmulationStationTheme;

public class SystemEvaluater {

	private ESConverter converter = new ESConverter();
	private String target;

	public SystemEvaluater(String target) {
		this.target = target;
	}

	public void evaluate(ViewEvaluator viewEvaluator) throws FileNotFoundException {

		EmulationStationTheme theme = getTheme(target);
		System.out.println("Start test with "+ target);
		assertNotNull(theme);
		
		List<ViewElement> views = theme.getViewElements();
		for (ViewElement view : views) {
			viewEvaluator.evaluate(view);
		}
		
		evaluate(theme);

	}

	public void evaluate(EmulationStationTheme theme) {
		
		try {
			Path filePath = ResourceUtils.getFile(target).toPath();
			String content = Files.readString(filePath);

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
			
		} catch (IOException e) {
			fail(e);
		}
		
	}

	private EmulationStationTheme getTheme(String target) throws FileNotFoundException {
		File file = ResourceUtils.getFile(target);
		return converter.fromXML(file);
	}

}
