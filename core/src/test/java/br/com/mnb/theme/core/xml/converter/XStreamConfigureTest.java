package br.com.mnb.theme.core.xml.converter;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;

class XStreamConfigureTest {
	
	private DefaultConfigure configure;
	
	@BeforeEach
	public void setup() {
		configure = spy(DefaultConfigure.class);
	}

    @Test
    void whenCallGetXStreamCallAllMethod() {
    	
    	XStream xstream = configure.getXStream();
    	
    	assertNotNull(xstream);
    	verify(configure, times(1).description("getXStream should be called 1 time")).defineProcessAnnotations(xstream);
    	verify(configure, times(1).description("getXStream should be called 1 time")).defineAlias(xstream);
    	verify(configure, times(1).description("getXStream should be called 1 time")).addConverter(xstream);
    	verify(configure, times(1).description("getXStream should be called 1 time")).defineAllowTypes(xstream);
    	
    }

}
