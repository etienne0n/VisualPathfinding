package com.etienne0n.github.mathutils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
 * Reference:
 * http://interactivepython.org/runestone/static/pythonds/Trees/BinaryHeapImplementation.html
 */
/**
 * - A simple BinaryHeap where the heap structure is represented as a single
 * list.<br>
 * - The children of a node at index i are at the indices 2i and 2i+1.<br>
 * - The current root is always at index = 1. <br>
 * - Index 0 is not used (equals null), so simple integer division can be used.
 * <br>
 * - the index of the parent is always the result of the integer division of
 * n/2, where n is the index of a children.<br>
 * 
 * @author Etienne Onasch
 *
 */
public class BinaryMinHeap<T extends Comparable<? super T>> {
	private List<T> heapList = new ArrayList<>();
	private int currentSize;

	/**
	 * The empty binary heap has a single {@code null}
	 * as the first element of heapList and this position is not used. 
	 * It ensures that the children of an element at position i are always at position 2i and 2i+1.<br>
	 * So the index of the parent of an element can always be computed with a single integer division by 2.
	 * 
	 */
	public BinaryMinHeap() {
		heapList.add(null);
		currentSize = 0;
	}

	private void percUp(int i) {
		while (i / 2 > 0) {
			if (heapList.get(i).compareTo(heapList.get(i / 2)) < 0) {
				T tmp = heapList.get(i / 2);
				heapList.set(i / 2, heapList.get(i));
				heapList.set(i, tmp);
			}
			i /= 2;
		}
	}

	private void percDown(int i) {
		while (i * 2 <= currentSize) {
			int mc_index = minChild(i);
			if (heapList.get(i).compareTo(heapList.get(mc_index)) > 0) {
				T tmp = heapList.get(i);
				heapList.set(i, heapList.get(mc_index));
				heapList.set(mc_index, tmp);
			}
			i = mc_index;
		}
	}

	private int minChild(int i) {
		if ((i * 2 + 1) > currentSize) {
			return i * 2;
		} else if (heapList.get(i * 2).compareTo(heapList.get(i * 2 + 1)) < 0) {
			return i * 2;
		} else {
			return i * 2 + 1;
		}
	}

	public void add(T elem) {
		heapList.add(elem);
		currentSize++;
		percUp(currentSize);
	}

	public T removeMin() {
		T retVal = heapList.get(1);
		heapList.set(1, heapList.get(currentSize));
		heapList.remove(currentSize);
		currentSize--;
		percDown(1);
		return retVal;
	}
	
	public void buildHeap(Collection<? extends T> keys) {
		if (keys == null || keys.size() == 0) {
			throw new IllegalArgumentException("Argument must not be empty or null.");
		}
		currentSize = keys.size();
		int i = currentSize / 2;
		
		heapList.addAll(1, keys);
		while (i > 0) {
			percDown(i);
			i--;
		}
	}
	public int size() {
		return currentSize;
	}
	
	public boolean contains(Object o) {
		return heapList.contains(o);
	}
	public boolean isEmpty() {
		return currentSize == 0;
	}

	public void decreaseKey(T elem) {
		int index = heapList.indexOf(elem);
		percUp(index);
	}

}
