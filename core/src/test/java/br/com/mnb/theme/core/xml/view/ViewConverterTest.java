package br.com.mnb.theme.core.xml.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mnb.theme.core.factory.SimpleFactory;
import br.com.mnb.theme.core.model.View;
import br.com.mnb.theme.core.xml.converter.NamedTagConverter;

class ViewConverterTest {
	
	NamedTagConverter<AbstractViewElement> converter;
	
	@BeforeEach
	public void setup() {

		@SuppressWarnings("unchecked")
		SimpleFactory<AbstractViewElement> factory = mock(SimpleFactory.class);
		when(factory.create(View.class)).thenReturn(new View());
		
		converter = new NamedTagConverter<AbstractViewElement>(factory);
		converter.put("view", View.class);
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
