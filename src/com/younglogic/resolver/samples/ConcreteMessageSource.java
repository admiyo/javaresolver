
package com.younglogic.resolver.samples;

import javax.jms.InvalidSelectorException;

public class ConcreteMessageSource implements MessageSource {

	public int sent = 0;

	public Message next() throws InvalidSelectorException {
		if (sent == 0) {
			sent++;
			return new Message("header", "message");
		} else {
			throw new InvalidSelectorException("no message");
		}
	}
}
