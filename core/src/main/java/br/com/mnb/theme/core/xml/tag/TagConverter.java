package br.com.mnb.theme.core.xml.tag;

public interface TagConverter<X,Y> {
	
    public Y toString(X element);

    public X toComponent(Y tagName);
    
}
