package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.RemovableAtSequence;

public interface Chain<T extends Chain<T, Data>, Data> extends Collection<T, Data>, RemovableAtSequence<T, Data> {
  // Must extend Collection and RemovableAtSequence.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  // Remove
	@Override
	default boolean Remove(Data dat) {
		if(dat==null) return false;
		return RemovableAtSequence.super.Remove(dat);
	}

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // Search
	@Override
	default Natural Search(Data dat) {
		if(dat==null) return null;
		return RemovableAtSequence.super.Search(dat);
	}

}
