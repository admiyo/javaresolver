package com.younglogic.resolver.samples;

import javax.jms.InvalidSelectorException;

public class ConcreteMessagePump implements MessagePump {
	public MessageSource source;
	public MessageSink sink;

	public ConcreteMessagePump(MessageSource source, MessageSink sink) {
		super();
		this.source = source;
		this.sink = sink;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Message message = this.source.next();
				this.sink.process(message);
			} catch (InvalidSelectorException e) {
				return;
			}
		}
	}
}
