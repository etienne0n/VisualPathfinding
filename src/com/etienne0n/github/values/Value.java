package com.etienne0n.github.values;
/**
 * 
 * @author Etienne Onasch
 *
 * @param <T>
 */
public class Value<T> {

	private T value;
	
	public Value(T value) {
		this.setValue(value);
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	
	}

}
