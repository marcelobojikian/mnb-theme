package br.com.mnb.theme.core.xml.xstream;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;

import br.com.mnb.theme.core.xml.Content;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.feature.AbstractFeature;
import br.com.mnb.theme.core.xml.tag.NamedTagConverter;
import br.com.mnb.theme.core.xml.theme.AbstractTheme;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;
import br.com.mnb.theme.core.xml.xstream.converter.ContentXstreamConverter;
import br.com.mnb.theme.core.xml.xstream.converter.ElementXstreamConverter;
import br.com.mnb.theme.core.xml.xstream.converter.FeatureXStreamConverter;

public class XStreamBuilder {
	
	private static Logger log = LoggerFactory.getLogger(XStreamBuilder.class);
	
	private Class<? extends AbstractTheme> themeClass;
	
	private XStream xstream;
	private Set<Class<?>> allowTypes;
	
	private Map<String, Class<?>> aliasMap;
	private NamedTagConverter<AbstractElement> elementConverter;
	private NamedTagConverter<AbstractViewElement> viewConverter;
	private NamedTagConverter<AbstractFeature> featureConverter;
	
	private XStreamBuilder() {
		xstream = new XStream();
		allowTypes = new HashSet<>();
		aliasMap = new HashMap<>();
	}

	public static XStreamBuilder create() {
		return new XStreamBuilder();
	}
	
	public XStreamBuilder configTheme(Class<? extends AbstractTheme> clazz) {	
		if(themeClass != null) {
			allowTypes.remove(themeClass);
		}
		allowTypes.add(clazz);
		themeClass = clazz;
		return this;
	}
	
	public XStreamBuilder configContent(String alias) {
		allowTypes.add(Content.class);
		xstream.alias(alias, Content.class);
		xstream.registerConverter(new ContentXstreamConverter());
		return this;
	}
	
	public XStreamBuilder configContent() {
		this.allowTypes.add(Content.class);
		this.xstream.registerConverter(new ContentXstreamConverter());
		return this;
	}
	
	public XStreamBuilder configFeature(NamedTagConverter<AbstractFeature> converter) {
		this.featureConverter = converter;
		return this;
	}
	
	public XStreamBuilder configView(NamedTagConverter<AbstractViewElement> converter) {
		this.viewConverter = converter;
		return this;
	}
	
	public XStreamBuilder configElement(NamedTagConverter<AbstractElement> converter) {
		this.elementConverter = converter;
		return this;
	}
	
	public XStream build() {

		xstream.autodetectAnnotations(true);
		xstream.ignoreUnknownElements();
		
		xstream.addPermission(NoTypePermission.NONE);
		
		for (Class<?> clazz : allowTypes) {
			xstream.processAnnotations(clazz);
		}
		log.debug("Define ProcessAnnotations {}", allowTypes);		

		for (Entry<String, Class<?>> entry : aliasMap.entrySet()) {
			xstream.alias(entry.getKey(), entry.getValue());
		}
		log.debug("Define Alias {}", aliasMap);

		if(featureConverter != null) {
			xstream.registerConverter(new FeatureXStreamConverter(featureConverter));
		}	

		if(elementConverter != null) {
			xstream.registerConverter(new ElementXstreamConverter(elementConverter));
		}		

		Class<?>[] classes = new Class[allowTypes.size()];
		xstream.allowTypes(allowTypes.toArray(classes));
		log.debug("Define AllowTypes {}", Arrays.toString(classes));
		
		return xstream;
	}
	
	// Add elements
	
	public XStreamBuilder addAlias(String name, Class<?> clazz) {
		this.allowTypes.add(clazz);
		this.aliasMap.put(name, clazz);
		return this;
	}
	
	public XStreamBuilder addFeature(Class<? extends AbstractFeature> clazz) {
		if(featureConverter == null) {
			throw new NullPointerException("NamedTag Feature Converter is not configured.");
		}	
		allowTypes.add(clazz);
		featureConverter.put(clazz);
		return this;
	}

	public XStreamBuilder addView(Class<? extends AbstractViewElement> clazz) {
		if(viewConverter == null) {
			throw new NullPointerException("NamedTag View Converter is not configured.");
		}	
		allowTypes.add(clazz);
		viewConverter.put(clazz);
		return this;
	}

	public XStreamBuilder addElement(Class<? extends AbstractElement> clazz) {
		if(elementConverter == null) {
			throw new NullPointerException("NamedTag Element Converter is not configured.");
		}		
		allowTypes.add(clazz);
		elementConverter.put(clazz);
		return this;
	}

}
