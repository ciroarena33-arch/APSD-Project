package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Abstract (static) circular vector base implementation. */
abstract public class CircularVectorBase<T extends CircularVectorBase<T, Data>, Data> extends VectorBase<T, Data>{
  // Must extend VectorBase.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

  // protected long start = 0L;
	protected long start = 0L;
	
  // CircularVectorBase()
	public CircularVectorBase() {
		super();
	}
	
  // CircularVectorBase(Natural inisize)
	public CircularVectorBase(Natural inisize) {
		super(inisize);
	}
	
  // CircularVectorBase(TraversableContainer<?, Data> con)
	public CircularVectorBase(TraversableContainer<?, Data> con) {
		super(con);
		
	}

  /* ************************************************************************ */
  /* Specific member functions of CircularVectorBase                          */
  /* ************************************************************************ */

  // ArrayAlloc
	@Override
	  protected void ArrayAlloc(Natural newsize) {
	    super.ArrayAlloc(newsize);
	    start = 0L;
	  }


  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  // Realloc
	 public void Realloc(Natural newsize) {
		    if (newsize == null) { throw new NullPointerException("Natural cannot be null!"); }
		    Data[] oldarr = arr;
		    long oldstart = start;
		    long oldsize = Size().ToLong();
		    ArrayAlloc(newsize);
		    long minsize = Math.min(oldsize, newsize.ToLong());
		    for (int i = 0; i < minsize; i++) {
		      arr[i] = oldarr[(int) ((oldstart + i) % oldarr.length)];
		    }
		  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // GetAt
	 @Override
	  public Data GetAt(Natural pos) {
	    return arr[(int) ((start + ExcIfOutOfBound(pos)) % arr.length)];
	  }

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  // SetAt
	 @Override
	  public void SetAt(Data dat, Natural pos) {
	    arr[(int) ((start + ExcIfOutOfBound(pos)) % arr.length)] = dat;
	  }

  /* ************************************************************************ */
  /* Specific member functions of Vector                                      */
  /* ************************************************************************ */

  // ShiftLeft
	 @Override
	  public void ShiftLeft(Natural pos, Natural num) {
	    if (pos == null || num == null) { throw new NullPointerException("Natural cannot be null!"); }
	    long idx = pos.ToLong();
	    long len = num.ToLong();
	    long size = Size().ToLong();
	    if (len > size) {
	      len = size;
	    }
	    if (len > 0 && idx < size - idx) {
	      if (idx > len) {
	        super.ShiftRight(Natural.ZERO, Natural.Of(idx - len), Natural.Of(len));
	      } else {
	        for (long i = 0; i < idx; i++) {
	          SetAt(null, Natural.Of(i));
	        }
	      }
	      for (long i = idx; i < len; i++) {
	        SetAt(null, Natural.Of(i));
	      }
	      start = (start + len) % arr.length;
	      for (long i = size - len; i < size; i++) {
	        SetAt(null, Natural.Of(i));
	      }
	    } else {
	      super.ShiftLeft(pos, num);
	    }
	  }
	 
  // ShiftRight
	 @Override
	  public void ShiftRight(Natural pos, Natural num) {
	    if (pos == null || num == null) { throw new NullPointerException("Natural cannot be null!"); }
	    long idx = pos.ToLong();
	    long len = num.ToLong();
	    long size = Size().ToLong();
	    if (idx < size) {
	      if (len > size - idx) {
	        len = size - idx;
	      }
	      if (len > 0 && idx < size - idx) {
	        for (long i = size - len; i < size; i++) {
	          SetAt(null, Natural.Of(i));
	        }
	        start = (arr.length + start - len) % arr.length;
	        super.ShiftLeft(Natural.Of(len), Natural.Of(idx + len), Natural.Of(len));
	      } else {
	        super.ShiftRight(pos, num);
	      }
	    }
	  }

}
