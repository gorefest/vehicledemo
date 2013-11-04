package com.lsy.training.convert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class DtoConverter<T,U> {
	
	public List<U> from(Collection<T> input) {
		List<U> result = new ArrayList<U>(input.size());
		for (T t : input) {
			result.add(from(t));
		}
		return result;
	}
	
	public abstract U from(T item);
}
