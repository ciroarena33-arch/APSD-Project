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
		ShiftLeft(pos, Size(), Natural.ONE);
	}
	
	default void ShiftLeft(Natural pos, Natural num) {
		ShiftLeft(pos, Size(), num);
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
	default void ShiftFirstLeft() { ShiftLeft(Natural.ZERO); }
	
	
  // ShiftLastLeft
	default void ShiftLastLeft() { ShiftLeft(IsEmpty() ? Natural.ZERO : Size().Decrement()); }
	
  // ShiftRight
	default void ShiftRight(Natural pos) {
		ShiftRight(pos, Size(), Natural.ONE);
	}
	
	default void ShiftRight(Natural pos, Natural num) {
		ShiftRight(pos, Size(), num);
	}	
	
	default void ShiftRight(Natural srt, Natural end, Natural num) {
		if(srt==null||end==null||num==null) throw new NullPointerException("Natural cannot be null");
		long stidx = srt.ToLong();
		long enidx = end.ToLong();
		if(stidx <= enidx && enidx <= Size().ToLong()) {
			long len = num.ToLong();
			long wrt = enidx + len;
			if(wrt>Size().ToLong()) { wrt=Size().Decrement().ToLong(); }
			long ldr = wrt - len;
			for(; ldr > stidx; ldr--, wrt--) {
				Natural natldr=Natural.Of(ldr);
				SetAt(GetAt(natldr), Natural.Of(wrt));
			}
			for(; wrt > stidx; wrt--) {
				SetAt(null, Natural.Of(wrt));
			}
		}
	}
	
  // ShiftFirstRight
	default void ShiftFirstRight() { ShiftRight(Natural.ZERO); }
	
  // ShiftLastRight
	default void ShiftLastRight() { ShiftRight(IsEmpty() ? Natural.ZERO : Size().Decrement()); }


  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // Size
	@Override
	default Natural Size() {
		return Capacity();
	}

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // SubSequence
	@Override
	default T SubSequence(Natural from, Natural to) {
		if (from == null || to == null) throw new NullPointerException("Natural cannot be null!");
        T sub = Empty();
        if (IsInBound(from) && IsInBound(to) && from.ToLong() <= to.ToLong()) {
            long start = from.ToLong();
            long end = to.ToLong();
            sub.Realloc(Natural.Of(end - start + 1));
            for (long i = start; i <= end; i++) {
                sub.SetAt(GetAt(Natural.Of(i)), Natural.Of(i - start));
            }
        }
        return sub;
	}
		

}
