package apsd.interfaces.containers.iterators;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Predicate;

/** Interface: Iteratore in avanti. */
public interface ForwardIterator<Data> extends Iterator<Data>{
  // Must extend Iterator.

  /* ************************************************************************ */
  /* Specific member functions of ForwardIterator                             */
  /* ************************************************************************ */

  // Next
	default void Next() {
		DataNNext();
	}
	
	default void Next(Natural num) {
		if(num==null)throw new NullPointerException("Natural cannot be null");
		Next(num.ToLong());
	}
	
	default void Next(long num) {
		if(num<0)throw new IllegalArgumentException("The Natural cannot be negative");
		for(long i=0; i<num && IsValid(); i++) {
			Next();
		}
	}
	
  // DataNNext
	Data DataNNext();
	
  // ForEachForward
	default boolean ForEachForward(Predicate<Data> pred) {
		if(pred!=null) {
			while(IsValid()) {
				if(pred.Apply(DataNNext())) {
					return true;
				}
			}
		}
		return false;
	}

}
