package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.InsertableContainer;

/** Interface: Sequence con supporto all'inserimento di un dato tramite posizione. */
public interface InsertableAtSequence<T extends InsertableAtSequence<T, Data>, Data> extends Sequence<T,Data>, InsertableContainer<T, Data>{
  // Must extend Sequence and InsertableContainer.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of InsertableAtSequence                        */
  /* ************************************************************************ */

  // InsertAt
	void InsertAt(Data dat, Natural pos);
	
  // InsertFirst
	default void InsertFirst(Data dat) {
		InsertAt(dat,Natural.ONE);
	};
	
  // InsertLast
	default void InsertLast(Data dat) {
		InsertAt(dat, Size());
	};

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  // Insert
	default boolean Insert(Data dat) {
		if(dat==null)return false;
		InsertLast(dat);
		return true;
	}

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // SubSequence
	default T SubSequence(Natural from, Natural to) {
		long start= ExcIfOutOfBound(from);
		long end= ExcIfOutOfBound(to);
		T sub=Empty();
		for(;start<=end;start++) {
			sub.InsertLast(GetAt(Natural.Of(start)));
		}
		return sub;		
	}
}
