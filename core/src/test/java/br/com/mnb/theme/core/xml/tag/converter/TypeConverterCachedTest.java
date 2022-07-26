package br.com.mnb.theme.core.xml.tag.converter;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;

import br.com.mnb.theme.core.xml.tag.converter.TypeConverterCached;
import br.com.mnb.theme.core.xml.xstream.XStreamConfigure;
import br.com.mnb.theme.core.xml.xstream.configure.EmptyConfigure;

class TypeConverterCachedTest {
	
	TypeConverterCached<EmptyConfigure> converter;
	EmptyConfigure mockConfigure;
	
	@BeforeEach
	public void setup() {
		mockConfigure = mock(EmptyConfigure.class);
		converter = new TypeConverterCached<EmptyConfigure>(mockConfigure);
	}
	
	@Test
	public void successWhenCheckInstanceCached() {
		XStreamConfigure configure = converter.getConfigure();
		assertInstanceOf(EmptyConfigure.class, configure);
	}
	
	@Test
	public void whenCallGetInstanceReturnSameObject() {
		
		when(mockConfigure.getXStream()).thenReturn(new XStream());
		
		XStream xstream = converter.getXStream();
		verify(mockConfigure, times(1).description("getXStream() should be called 1 time")).getXStream();

		XStream secondXstream = converter.getXStream();
		verify(mockConfigure, times(1).description("getXStream() should be called 1 time")).getXStream();

		XStream thirdXstream = converter.getXStream();
		verify(mockConfigure, times(1).description("getXStream() should be called 1 time")).getXStream();
		
		assertSame(xstream, secondXstream);
		assertSame(xstream, thirdXstream);
		assertSame(secondXstream, thirdXstream);
	}
	
	@Test
	public void whenCallGetInstanceReturnOtherObjectAfterNotifyChange() {
		
		when(mockConfigure.getXStream()).thenReturn(new XStream(), new XStream());
		
		XStream xstream = converter.getXStream();
		verify(mockConfigure, times(1).description("getXStream() should be called 1 time")).getXStream();

		XStream secondXstream = converter.getXStream();
		verify(mockConfigure, times(1).description("getXStream() should be called 1 time")).getXStream();
		
		converter.notifyChange();

		XStream thirdXstream = converter.getXStream();
		verify(mockConfigure, times(2).description("getXStream() should be called 2 time")).getXStream();
		
		assertSame(xstream, secondXstream);
		assertNotSame(xstream, thirdXstream);
		assertNotSame(secondXstream, thirdXstream);
	}

}
