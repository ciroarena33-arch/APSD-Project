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

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  // Realloc

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // GetAt

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  // SetAt

  /* ************************************************************************ */
  /* Specific member functions of Vector                                      */
  /* ************************************************************************ */

  // ShiftLeft
  // ShiftRight

}
