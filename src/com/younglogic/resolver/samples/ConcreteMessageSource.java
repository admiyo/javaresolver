
package com.younglogic.resolver.samples;

import javax.jms.InvalidSelectorException;

public class ConcreteMessageSource implements MessageSource {

	int count = 0;

	public Message next() throws InvalidSelectorException {
		if (count == 0) {
			count++;
			return new Message("header", "message");
		} else {
			throw new InvalidSelectorException("no message");
		}
	}
}
