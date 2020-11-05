package com.younglogic.resolver.samples;

import javax.jms.InvalidSelectorException;

public interface MessageSource {
	Message next() throws InvalidSelectorException;

}