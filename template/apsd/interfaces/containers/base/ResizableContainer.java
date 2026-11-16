package apsd.interfaces.containers.base;

import apsd.classes.utilities.Natural;

/** Interface: ReallocableContainer che è espandibile e riducibile. */
public interface ResizableContainer<T extends ResizableContainer<T>> extends ReallocableContainer<T> {
  // T must have a recursive bound.
  // Must extend ReallocableContainer.

  /* ************************************************************************ */
  /* Specific member functions of ResizableContainer                          */
  /* ************************************************************************ */

  double THRESHOLD_FACTOR = 2.0; // Must be strictly greater than 1.

  // Expand
  default void Expand() {
	  Expand(Natural.ONE);
  }
  
  void Expand(Natural dim);
  
  // Reduce
  default void Reduce() {
	  Reduce(Natural.ONE);
  }
  void Reduce(Natural dim);
  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // Size
  @Override
  abstract Natural Size();
  
  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  // Grow
  @Override
  default void Grow(Natural dim) {
	  if(dim==null) {throw new NullPointerException("Natural cannot be null");}
	  if(Capacity().ToLong()>=Long.MAX_VALUE) {throw new ArithmeticException("Overflow: Capacity cannot exceed Long");}
	  if(Size().ToLong()+dim.ToLong()>=Capacity().ToLong()) {ReallocableContainer.super.Grow(dim);}
  }
  // Shrink
  @Override
  default void Shrink() {
	  if((long)(THRESHOLD_FACTOR*SHRINK_FACTOR*Size().ToLong())<=Capacity().ToLong()) {ReallocableContainer.super.Shrink();}
  }
}
