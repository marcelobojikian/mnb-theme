package br.com.mnb.theme.core.xml.element;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import br.com.mnb.theme.core.xml.Content;

public abstract class AbstractElement implements CommonElement {

	private static final long serialVersionUID = 1L;

	@XStreamAlias("name")
	@XStreamAsAttribute
	private String name;

	@XStreamAlias("extra")
	@XStreamAsAttribute
	private boolean extra;

	private Content content = new Content();

	@Override
	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean isExtra() {
		return extra;
	}

	public void setExtra(boolean extra) {
		this.extra = extra;
	}

}
