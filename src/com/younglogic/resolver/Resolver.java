package com.younglogic.resolver;

import java.util.HashMap;
import java.util.Map;
import java.lang.InstantiationError;

public class Resolver {

	@SuppressWarnings("rawtypes")
	Map<Class, Object> instances = new HashMap<Class, Object>();

	private Registry registry;

	Resolver(Registry registry) {
		super();
		this.registry = registry;
	}

	@SuppressWarnings("unchecked")
	public <T extends Object> T fetch(@SuppressWarnings("rawtypes") Class c) throws InstantiationError {
		T o = (T) instances.get(c);
		if (o == null) {
			// Don't synchronize for the fast path, only the
			// slow path where we need to create a new object.

			Factory<T> factory = registry.factories.get(c);
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
