package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ReallocableContainer;

public interface Vector<T extends Vector<T, Data>, Data> extends ReallocableContainer<T>, MutableSequence<T, Data> {
  // Must extend ReallocableContainer and MutableSequence.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of Vector                                      */
  /* ************************************************************************ */

  // ShiftLeft
	default void ShiftLeft(Natural pos) {
		
	}
	
	default void ShiftLeft(Natural pos,Natural num) {
		
	}

	default void ShiftLeft(Natural srt, Natural end, Natural num) {
		if(srt==null||end==null||num==null) throw new NullPointerException("Natural cannot be null");
		long stidx = srt.ToLong();
		long enidx = end.ToLong();
		if(stidx <= enidx && enidx <= Size().ToLong()) {
			long len = num.ToLong();
			long wrt = stidx - len;
			if(wrt<0) { wrt=0; }
			long rdr = wrt + len;
			for(; rdr < enidx; rdr++, wrt++) {
				Natural natrdr=Natural.Of(rdr);
				SetAt(GetAt(natrdr), Natural.Of(wrt));
			}
			for(; wrt < enidx; wrt++) {
				SetAt(null, Natural.Of(wrt));
			}
		}
	}
	
  // ShiftFirstLeft
	
	
  // ShiftLastLeft
	
	
  // ShiftRight
	
	
  // ShiftFirstRight
	
	
  // ShiftLastRight
	

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // Size
	@Override
	default Natural Size() {
		
	}

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // SubSequence

}
