package com.younglogic.resolver.samples;

import com.younglogic.resolver.Factory;
import com.younglogic.resolver.Registry;

public class ConcreteMessageSourceFactory implements Factory<MessageSource>{
	public MessageSource create (Registry registry) {
		return new ConcreteMessageSource();
	}
	
}