package com.younglogic.resolver.samples;

public class ConcreteMessageSink implements MessageSink {

	public void process(Message message) {
		System.out.print("header: ");
		System.out.println(message.header);
		System.out.print("body: ");
		System.out.println(message.body);
	}
}
