package br.com.mnb.theme.core.xml.element;

import java.io.Serializable;

import br.com.mnb.theme.core.xml.Content;

public interface CommonElement extends Serializable {

	public Content getContent();

	public String getName();

	public boolean isExtra();

}
