package com.younglogic.resolver;

public interface  Factory<T> {
	T create (Registry registry); 
};
