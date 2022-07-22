package br.com.mnb.theme.core.xml.converter;

import com.thoughtworks.xstream.XStream;

import br.com.mnb.theme.core.factory.SimpleFactory;
import br.com.mnb.theme.core.xml.element.AbstractElement;
import br.com.mnb.theme.core.xml.theme.AbstractTheme;
import br.com.mnb.theme.core.xml.view.AbstractViewElement;

public class XmlConverter {

	private final XStreamConfigure configure;

	private NamedTagConverter<AbstractElement> elementConverter;
	private NamedTagConverter<AbstractViewElement> viewConverter;
	private NamedTagConverter<AbstractTheme> themeConverter;
	
	private XStream XSTREAM_INSTANCE = null;
	private boolean reloadXstream = true;
	
	public XmlConverter() {
		this(new DefaultConfigure());
	}
	
	public XmlConverter(XStreamConfigure configure) {
		this.configure = configure;
		elementConverter = new NamedTagConverter<AbstractElement>(new SimpleFactory<AbstractElement>());
		viewConverter = new NamedTagConverter<AbstractViewElement>(new SimpleFactory<AbstractViewElement>());
		themeConverter = new NamedTagConverter<AbstractTheme>(new SimpleFactory<AbstractTheme>());
	}

	private XStream getInstance() {
		if (XSTREAM_INSTANCE == null || reloadXstream) {
			XSTREAM_INSTANCE = configure.getXStream();

			ContentXStreamConverter contentXmlConverter = new ContentXStreamConverter();
			XSTREAM_INSTANCE.registerConverter(contentXmlConverter);

			ElementXStreamConverter elementXmlConverter = new ElementXStreamConverter(elementConverter);
			XSTREAM_INSTANCE.registerConverter(elementXmlConverter);
			
			reloadXstream = false;
		}
		return XSTREAM_INSTANCE;
	}
	
	public void putTag(String tagName, Class<?> clazz) {
		reloadXstream = true;
		if(AbstractElement.class.isAssignableFrom(clazz)) {
			elementConverter.put(tagName, (Class<? extends AbstractElement>) clazz);
		} else if(AbstractViewElement.class.isAssignableFrom(clazz)) {
			viewConverter.put(tagName, (Class<? extends AbstractViewElement>) clazz);
		} else if(AbstractTheme.class.isAssignableFrom(clazz)) {
			themeConverter.put(tagName, (Class<? extends AbstractTheme>) clazz);
		} else {
			throw new IllegalArgumentException("Invalid class " + clazz);
		}
	}

	public Object fromXML(String xml) {
		return getInstance().fromXML(xml);
	}

	public String toXML(Object object) {
		return getInstance().toXML(object);
	}

	public NamedTagConverter<AbstractElement> getElementConverter() {
		return elementConverter;
	}

	public void setElementConverter(NamedTagConverter<AbstractElement> elementConverter) {
		this.elementConverter = elementConverter;
	}

	public NamedTagConverter<AbstractViewElement> getViewConverter() {
		return viewConverter;
	}

	public void setViewConverter(NamedTagConverter<AbstractViewElement> viewConverter) {
		this.viewConverter = viewConverter;
	}

	public NamedTagConverter<AbstractTheme> getThemeConverter() {
		return themeConverter;
	}

	public void setThemeConverter(NamedTagConverter<AbstractTheme> themeConverter) {
		this.themeConverter = themeConverter;
	}

}
