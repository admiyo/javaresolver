package com.younglogic.resolver.samples;

import com.younglogic.resolver.Factory;
import com.younglogic.resolver.Resolver;

public class ConcreteMessageSourceFactory implements Factory<MessageSource>{
	public MessageSource create (Resolver registry) {
		return new ConcreteMessageSource();
	}
	
}