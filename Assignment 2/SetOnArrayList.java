package assignment02;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import components.set.AbstractSet;
import components.set.Set;

/**
 * {@code Set<E>} represented as a {@code java.util.ArrayList<E>} with
 * implementation of primary methods.
 * 
 * @author Harrison Smith
 * @date September 4, 2019
 *
 * @param <E> type of element
 */
public class SetOnArrayList<E> extends AbstractSet<E> {
	/*
	 * Private members
	 */
	/**
	 * Underlying ArrayList representation
	 */
	private List<E> rep;
	
	/**
	 * No-argument constructor; builds an empty set.
	 * 
	 */
	public SetOnArrayList() {
		this.rep = new ArrayList<E>();
	}

	@Override
	public void add(E x) {
		assert x != null : "Violation of x is not null";
		assert !this.rep.contains(x) : "Violation of x already contained";
		this.rep.add(x);
	}

	@Override
	public boolean contains(E x) {
		assert x != null : "Violation of x is not null";
		for(int i = 0; i < this.rep.size(); i++) {
			if(rep.get(i) == x)
				return true;
		}
		return false;
	}

	@Override
	public void remove(E x) {
		assert x != null : "Violation of x is not null";
		assert this.rep.contains(x) : "Violation of x is in this";
		
		rep.remove(x);
	}

	@Override
	public void clear() {
		while(this.rep.size() > 0) {
			this.rep.remove(0);
		}
	}
	
	@Override
	public int size() {
		return this.rep.size();
	}

	// ----------------------------------------------------------------

	/*
	 * Already implemented for you
	 */
	@Override
	public Iterator<E> iterator() {
		return this.rep.iterator();
	}


}
