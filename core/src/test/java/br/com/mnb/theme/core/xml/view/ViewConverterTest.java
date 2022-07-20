package br.com.mnb.theme.core.xml.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mnb.theme.core.factory.ViewFactory;
import br.com.mnb.theme.core.model.View;

class ViewConverterTest {
	
	ViewConverter converter;
	
	@BeforeEach
	public void setup() {
		ViewFactory factory = mock(ViewFactory.class);
		when(factory.createView(View.class)).thenReturn(new View());
		
		converter = new ViewConverter(factory);
		converter.registerView("view", View.class);
	}

	@Test
	void sucessWhenConvertTagNameToView() {
		
		AbstractViewElement view = converter.toComponent("view");
		assertNotNull(view);
		assertInstanceOf(View.class, view);
		
	}

	@Test
	void sucessWhenConvertViewToTagName() {
		
		String result = converter.toString(new View());
		assertNotNull(result);
		assertEquals("view", result);
		
	}

	@Test
	void failWhenConvertTagNameToViewWithTagUnmapped() {
		assertThrows(IllegalArgumentException.class, () -> {
			converter.toComponent("Other");
		});
	}

	@Test
	void failWhenConvertViewToTagNameWithComponentUnmapped() {
		assertThrows(IllegalArgumentException.class, () -> {
			converter.toString(new UnmappedView());
		});
	}
	
	class UnmappedView extends AbstractViewElement { 
		private static final long serialVersionUID = 1L;
	}

}
