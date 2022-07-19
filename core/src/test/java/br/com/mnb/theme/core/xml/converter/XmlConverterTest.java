package br.com.mnb.theme.core.xml.converter;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.thoughtworks.xstream.XStream;

class XmlConverterTest {
	
	private XStream xstream;
	
	@BeforeEach
	public void setup() {
		xstream = mock(XStream.class);
	}

    @Test
    public void whenTransformCreateUniqueInstance() {
    	TransformerTester transformer = spy(new TransformerTester());
    	
		when(xstream.fromXML(anyString())).thenReturn(null);
    	when(xstream.toXML(any(Object.class))).thenReturn("");

		transformer.fromXML("");
		transformer.toXML(new Object());

    	verify(transformer, times(1).description("getXStream should be called 1 time")).getXStream();
    	
    }
	
	class TransformerTester extends XmlConverter<Object> {
		@Override
		public XStream getXStream() {
			return xstream;
		}		
	}

}
