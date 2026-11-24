package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.sequences.DynVector;

/** Object: Abstract dynamic linear vector base implementation. */
abstract public class DynLinearVectorBase<T extends DynLinearVectorBase<T, Data>, Data> extends LinearVectorBase<T, Data> implements DynVector<T, Data>{
  // Must extend LinearVectorBase.
  // Must implement DynVector.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

  // protected long size = 0L;
	protected long size = 0L;
	
  // DynLinearVectorBase()
	public DynLinearVectorBase() {
		super();
	}
	
  // DynLinearVectorBase(Natural inicapacity)
	public DynLinearVectorBase(Natural inisize) {
		super(inisize);
	}
	
  // DynLinearVectorBase(TraversableContainer<?, Data> con)
	public DynLinearVectorBase(TraversableContainer<?, Data> con) {
		super(con);
		size=con.Size().ToLong();
	}

  /* ************************************************************************ */
  /* Specific member functions of DynLinearVectorBase                         */
  /* ************************************************************************ */

  // ArrayAlloc
	@Override
	  protected void ArrayAlloc(Natural newcapacity) {
	    if (newcapacity == null) { throw new NullPointerException("Natural cannot be null!"); }
	    final long MIN_CAPACITY = 1L;
	    super.ArrayAlloc(newcapacity.ToLong() < MIN_CAPACITY ? Natural.Of(MIN_CAPACITY) : newcapacity);
	  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // Size
	@Override
	public Natural Size() {
		return Natural.Of(size);
	}

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // Clear
	@Override
	public void Clear() {
	    super.Clear();
	    size = 0L;
	  }

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  // Realloc
	@Override
	  public void Realloc(Natural newcapacity) {
	    super.Realloc(newcapacity);
	    if (size > newcapacity.ToLong()) {
	      size = newcapacity.ToLong();
	    }
	  }

  /* ************************************************************************ */
  /* Override specific member functions from ResizableContainer               */
  /* ************************************************************************ */

  // Expand
	@Override
	  public void Expand(Natural num) {
	    Grow(num);
	    size += num.ToLong();
	  }
	
  // Reduce
	@Override
	  public void Reduce(Natural num) {
	    if (num == null) { throw new NullPointerException("Natural cannot be null!"); }
	    long len = num.ToLong();
	    if (len > size) { throw new ArithmeticException("Underflow: size cannot be negative!"); }
	    for (long idx = size - len; idx < size; idx++) {
	      arr[(int) idx] = null;
	    }
	    size -= len;
	    Shrink();
	  }

}
