package apsd.interfaces.containers.iterators;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Predicate;

/** Interface: Iteratore all'indietro. */
public interface BackwardIterator<Data> extends Iterator<Data>{
  // Must extend Iterator.

  /* ************************************************************************ */
  /* Specific member functions of BackwardIterator                            */
  /* ************************************************************************ */

  // Prev
	default void Prev() {
		DataNPrev();
	}
	
	default void Prev(Natural num) {
		if(num==null)throw new NullPointerException("Natural cannot be null");
		Prev(num.ToLong());
	}
	
	default void Prev(long num) {
		if(num<0)throw new IllegalArgumentException("The Natural cannot be negative");
		for(int i=0; i<num && IsValid(); i++) {
			Prev();
		}
	}
	
  // DataNPrev
	Data DataNPrev();
	
  // ForEachBackward
	default boolean ForEachBackward(Predicate<Data> pred) {
		if(pred!=null) {
			while(IsValid()) {
				if(pred.Apply(DataNPrev())) {
					return true;
				}
			}
		}
		return false;
	}
}
