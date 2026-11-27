package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.MutableNatural; 
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.Vector;

/** Object: Abstract vector base implementation. */
abstract public class VectorBase<T extends VectorBase<T, Data>, Data> implements Vector<T, Data> {
  // Must implement Vector.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

  // protected Data[] arr = null;
	protected Data[] arr = null;
	
  // VectorBase()
	public VectorBase() {
		ArrayAlloc(Natural.ZERO);
	}
	
  // VectorBase(Natural inisize)
	public VectorBase(Natural inisize) {
		if(inisize==null) { throw new NullPointerException("Natural cannot be null");}
		ArrayAlloc(inisize);
	}
  // VectorBase(TraversableContainer<?, Data> con)
	public VectorBase(TraversableContainer<?, Data> con) {
		if(con==null) { throw new NullPointerException("Traversable container cannot be null");}
		ArrayAlloc(con.Size());
		final MutableNatural idx = new MutableNatural();
		con.TraverseForward(dat -> {arr[(int) idx.GetNIncrement().ToLong()]=dat; return false;});
	}

  /* ************************************************************************ */
  /* Specific member functions of VectorBase                                  */
  /* ************************************************************************ */

  // ArrayAlloc
	@SuppressWarnings("unchecked")
	protected void ArrayAlloc(Natural newsize) {
		long size=newsize.ToLong();
		if(size >= Integer.MAX_VALUE) {
			throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE");
		}
		arr=(Data[]) new Object[(int) size];
	}

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // Clear
	@Override
	public void Clear() {
		ArrayAlloc(Natural.ZERO);
	}

  /* ************************************************************************ */
  /* Override specific member functions from ResizableContainer               */
  /* ************************************************************************ */

  // Capacity
	@Override
	public Natural Capacity() {
		return Natural.Of(arr.length);
	}

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  // VectorFIterator
	protected class VectorFIterator implements MutableForwardIterator<Data>{
		protected long cur = 0L;
		
		@Override
		public boolean IsValid() {
			return (cur>=0) && (cur<Size().ToLong());
		}
		
		@Override
		public void Next() {
			if (!IsValid()) {throw new IllegalStateException("Iterator terminated");}
			cur++;
		}

		@Override
		public Data DataNNext() {
			if (!IsValid()) {throw new IllegalStateException("Iterator terminated");}
			return GetAt(Natural.Of(cur++));
		}

		@Override
		public void Reset() {
			cur=0L;
			
		}

		@Override
		public Data GetCurrent() {
			if (!IsValid()) {throw new IllegalStateException("Iterator terminated");}
			return GetAt(Natural.Of(cur));
		}

		@Override
		public void SetCurrent(Data dat) {
			if (!IsValid()) {throw new IllegalStateException("Iterator terminated");}
			SetAt(dat,Natural.Of(cur));
		}
	}
	
  // FIterator
	public MutableForwardIterator<Data> FIterator(){ return new  VectorFIterator();}
	
  // VectorBIterator
	protected class VectorBIterator implements MutableBackwardIterator<Data>{
		protected long cur = Size().ToLong()-1;
		
		@Override
		public boolean IsValid() {
			return (cur>=0) && (cur<Size().ToLong());
		}
		
		@Override
		public void Prev() {
			if (!IsValid()) {throw new IllegalStateException("Iterator terminated");}
			cur--;
		}

		@Override
		public Data DataNPrev() {
			if (!IsValid()) {throw new IllegalStateException("Iterator terminated");}
			return GetAt(Natural.Of(cur--));
		}

		@Override
		public void Reset() {
			cur=Size().ToLong()-1;
			
		}

		@Override
		public Data GetCurrent() {
			if (!IsValid()) {throw new IllegalStateException("Iterator terminated");}
			return GetAt(Natural.Of(cur));
		}

		@Override
		public void SetCurrent(Data dat) {
			if (!IsValid()) {throw new IllegalStateException("Iterator terminated");}
			SetAt(dat,Natural.Of(cur));
		}
	}
  // BIterator
	public MutableBackwardIterator<Data> BIterator(){ return new  VectorBIterator();}

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // GetAt
	@Override
	abstract public Data GetAt(Natural pos);

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  // SetAt
	@Override
	abstract public void SetAt(Data dat, Natural pos);

}
