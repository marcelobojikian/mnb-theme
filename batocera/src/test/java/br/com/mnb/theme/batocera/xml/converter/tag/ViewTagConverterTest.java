package br.com.mnb.theme.batocera.xml.converter.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.mnb.theme.batocera.xml.view.View;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;

class ViewTagConverterTest {

	private ViewTagConverter viewConverter = new ViewTagConverter();

	@Test
	void sucessWhenConvertTagNameToView() {
		
		AbstractViewElement view = viewConverter.toComponent("view");
		assertNotNull(view);
		assertInstanceOf(View.class, view);
		
	}

	@Test
	void sucessWhenConvertViewToTagName() {
		
		String result = viewConverter.toString(new View());
		assertNotNull(result);
		assertEquals("view", result);
		
	}

	@Test
	void failWhenConvertTagNameToViewWithTagUnmapped() {
		assertThrows(IllegalArgumentException.class, () -> {
			viewConverter.toComponent("Other");
		});
	}

	@Test
	void failWhenConvertViewToTagNameWithComponentUnmapped() {
		assertThrows(IllegalArgumentException.class, () -> {
			viewConverter.toString(new UnmappedView());
		});
	}
	
	class UnmappedView extends AbstractViewElement { 
		private static final long serialVersionUID = 1L;
	}

}
