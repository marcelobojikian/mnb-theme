package br.com.mnb.theme.core.factory;

public interface ExtensionFactory<X> {
	
	public <T extends X> T create(Class<T> clazz);
}
