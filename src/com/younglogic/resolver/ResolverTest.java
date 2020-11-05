package com.younglogic.resolver;

import org.junit.jupiter.api.Test;

import com.younglogic.resolver.samples.ConcreteMessageSink;
import com.younglogic.resolver.samples.ConcreteMessageSource;
import com.younglogic.resolver.samples.ConcreteMessagePump;
import com.younglogic.resolver.samples.MessageSink;
import com.younglogic.resolver.samples.MessageSource;
import com.younglogic.resolver.samples.MessagePump;



class ResolverTest {

	@Test
	void test() {
		Registry.Register(MessageSource.class, new Factory<MessageSource>() {
			@Override
			public ConcreteMessageSource create(Registry registry) {
				return new ConcreteMessageSource();
			}
		});
		
		Registry.Register(MessageSink.class, new Factory<MessageSink>() {
			@Override
			public ConcreteMessageSink create(Registry registry) {
				return new ConcreteMessageSink();
			}
		});

		Registry.Register(MessagePump.class, new Factory<MessagePump>() {
			@Override
			public MessagePump create(Registry registry) {
				return new ConcreteMessagePump(registry.fetch(MessageSource.class), registry.fetch(MessageSink.class) );
			}
		});

		Registry registry = new Registry();
		MessagePump messagePump = registry.fetch(MessagePump.class);
		messagePump.run();
	}

}
