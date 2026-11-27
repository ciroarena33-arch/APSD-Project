package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ResizableContainer;

public interface DynVector<T extends DynVector<T, Data>, Data> extends ResizableContainer<T>, RemovableAtSequence<T, Data>, 
																			InsertableAtSequence<T, Data>, Vector<T, Data>{
  // Must extend ResizableContainer, InsertableAtSequence, RemovableAtSequence, and Vector.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */

  // InsertAt
	default void InsertAt(Data dat, Natural pos) {
		if(pos==null) throw new NullPointerException("Natural cannot be null");
		if(pos.ToLong()>Size().ToLong()) throw new IndexOutOfBoundsException("Index cannot be null");
		Expand();
		ShiftRight(pos);
		SetAt(dat,pos);
	}

  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  // AtNRemove
	@Override
	default Data AtNRemove(Natural pos) {
		Data dat= GetAt(pos);
		ShiftLeft(pos.Increment());
		Reduce();
		return dat;
	}

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // Size
	@Override
	Natural Size();


  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // SubSequence
	@Override
	default T SubSequence(Natural from, Natural to) {
		return InsertableAtSequence.super.SubSequence(from, to);
	}

  
}
