package com.younglogic.resolver;

import java.util.HashMap;
import java.util.Map;
import java.lang.InstantiationError;

public class Registry {

	@SuppressWarnings("rawtypes")
	static Map<Class, Factory> factories = new HashMap<Class, Factory>();
	@SuppressWarnings("rawtypes")
	static Map<Class, Object> instances = new HashMap<Class, Object>();

	static <T> void Register(Class<T> c, Factory<T> f) {
		factories.put(c, f);
	};

	@SuppressWarnings("unchecked")
	public <T extends Object> T fetch(@SuppressWarnings("rawtypes") Class c) throws InstantiationError {
		T o = (T) instances.get(c);
		if (o == null) {
			// Don't synchronize for the fast path, only the
			// slow path where we need to create a new object.

			Factory<T> factory = factories.get(c);
			if (factory == null) {
				throw new InstantiationError();
			}
			synchronized (instances) {
				if (instances.get(c) == null) {
					try {
						o = (T) factory.create(this);
						instances.put(c, o);
					} catch (ClassCastException e) {
						throw new InstantiationError();
					}
				}
			}
		}
		return o;
	}
}
