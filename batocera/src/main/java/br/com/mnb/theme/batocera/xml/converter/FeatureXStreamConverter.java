package br.com.mnb.theme.batocera.xml.converter;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import br.com.mnb.theme.batocera.xml.feature.AbstractFeature;
import br.com.mnb.theme.batocera.xml.feature.BatoceraFeature;
import br.com.mnb.theme.batocera.xml.view.View;
import br.com.mnb.theme.core.xml.converter.TagConverter;

public class FeatureXStreamConverter implements Converter {

	private TagConverter<AbstractFeature, String> converter;

	public FeatureXStreamConverter(TagConverter<AbstractFeature, String> converter) {
		this.converter = converter;
	}
	
	@Override
	public boolean canConvert(Class clazz) {
		return BatoceraFeature.class.isAssignableFrom(clazz);
	}
	
	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		
		String supported = reader.getAttribute("supported");
		BatoceraFeature feature = converter.toComponent(supported);

		View view = (View) context.convertAnother(feature, View.class);
		feature.setView(view);

		return feature;
	}
	
	@Override
	public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
		BatoceraFeature feature = (BatoceraFeature) value;
		writer.addAttribute("supported", feature.getSupported());
		
		if(feature.getView() != null) {
			writer.startNode("view");
			context.convertAnother(feature.getView());
			writer.endNode();
		}
	}

}
