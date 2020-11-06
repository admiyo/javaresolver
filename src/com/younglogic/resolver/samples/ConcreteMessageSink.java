package com.younglogic.resolver.samples;

public class ConcreteMessageSink implements MessageSink {
	public int received = 0;
	
	public void process(Message message) {
		this.received ++;
	}
}
