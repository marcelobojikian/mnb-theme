package br.com.mnb.theme.core.xml.converter;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import br.com.mnb.theme.core.xml.Content;

public class ContentXStreamConverter implements Converter {

	@Override
	public boolean canConvert(Class type) {
		return Content.class.isAssignableFrom(type);
	}

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		Content content = (Content) source;
		Map<String, String> map = content.getAttributes();
		for (Entry<String, String> entry : map.entrySet()) {
			writer.startNode(entry.getKey());
			writer.setValue(entry.getValue());
			writer.endNode();
		}
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {

		Map<String, String> map = new HashMap<String, String>();

		while (reader.hasMoreChildren()) {
			reader.moveDown();
			map.put(reader.getNodeName(), reader.getValue());
			reader.moveUp();
		}

		Content content = new Content(map);
		return content;

	}

}
