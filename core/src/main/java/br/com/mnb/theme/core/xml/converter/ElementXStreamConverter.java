package br.com.mnb.theme.core.xml.converter;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import br.com.mnb.theme.core.xml.Content;
import br.com.mnb.theme.core.xml.element.AbstractElement;

public class ElementXStreamConverter implements Converter {
	
	private TagConverter<AbstractElement, String> converter;

	public ElementXStreamConverter(TagConverter<AbstractElement, String> converter) {
		this.converter = converter;
	}

	public boolean canConvert(Class clazz) {
		return AbstractElement.class.isAssignableFrom(clazz);
	}

	public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {

		AbstractElement element = (AbstractElement) value;

		if (element.isExtra()) {
			writer.addAttribute("extra", "true");
		}

		writer.addAttribute("name", element.getName());

		context.convertAnother(element.getContent());

	}

	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {

		AbstractElement element = converter.toComponent(reader.getNodeName());

		String name = reader.getAttribute("name");
		element.setName(name);

		String extra = reader.getAttribute("extra");
		element.setExtra(Boolean.valueOf(extra));

		Content content = (Content) context.convertAnother(element, Content.class);
		element.setContent(content);

		return element;
	}

}
