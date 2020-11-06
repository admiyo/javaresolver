package com.younglogic.resolver;

import java.util.HashMap;
import java.util.Map;

public class Registry {
	
	@SuppressWarnings("rawtypes")
	Map<Class, Factory> factories = new HashMap<Class, Factory>();

	public Resolver createResolver() {
		return new Resolver(this);
	}

	<T> void register(Class<T> c, Factory<T> f) {
		factories.put(c, f);
	};

}
