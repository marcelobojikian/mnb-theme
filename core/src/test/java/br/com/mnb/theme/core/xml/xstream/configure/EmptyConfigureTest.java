package br.com.mnb.theme.core.xml.xstream.configure;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;

class EmptyConfigureTest {
	
	EmptyConfigure configure;
	
	@BeforeEach
	public void setup() {
		configure = spy(EmptyConfigure.class);
	}

    @Test
    void whenCallGetXStreamCallAllMethod() {
    	
    	XStream xstream = configure.getXStream();
    	
    	assertNotNull(xstream);
    	verify(configure, times(1).description("defineProcessAnnotations should be called 1 time")).defineProcessAnnotations(xstream);
    	verify(configure, times(1).description("defineAlias should be called 1 time")).defineAlias(xstream);
    	verify(configure, times(1).description("addConverter should be called 1 time")).addConverter(xstream);
    	verify(configure, times(1).description("defineAllowTypes should be called 1 time")).defineAllowTypes(xstream);
    	
    }

}
