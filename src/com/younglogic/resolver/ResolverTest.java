package com.younglogic.resolver;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
		
		Registry registry = new Registry();
		Resolver resolver = registry.createResolver();
		
		registry.register(MessageSource.class, new Factory<MessageSource>() {
			@Override
			public ConcreteMessageSource create(Resolver registry) {
				return new ConcreteMessageSource();
			}
		});
		
		registry.register(MessageSink.class, new Factory<MessageSink>() {
			@Override
			public ConcreteMessageSink create(Resolver registry) {
				return new ConcreteMessageSink();
			}
		});

		registry.register(MessagePump.class, new Factory<MessagePump>() {
			@Override
			public MessagePump create(Resolver registry) {
				return new ConcreteMessagePump(registry.fetch(MessageSource.class), registry.fetch(MessageSink.class) );
			}
		});

		MessagePump messagePump = resolver.fetch(MessagePump.class);
		
		ConcreteMessagePump pump = (ConcreteMessagePump)messagePump;
		ConcreteMessageSource source = (ConcreteMessageSource)pump.source;
		ConcreteMessageSink sink = (ConcreteMessageSink)pump.sink;

		
		assertEquals(source.sent, 0);
		assertEquals(sink.received, 0);

		messagePump.run();
		assertEquals(source.sent, 1);
		assertEquals(sink.received, 1);

	}

}
