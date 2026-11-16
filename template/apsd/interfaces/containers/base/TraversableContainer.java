package apsd.interfaces.containers.base;

import apsd.classes.utilities.Box;
// import apsd.classes.utilities.MutableNatural;
// import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Accumulator;
import apsd.interfaces.traits.Predicate;

/** Interface: MembershipContainer con supporto all'attraversamento. */
public interface TraversableContainer<T extends TraversableContainer<T, Data>, Data> extends MembershipContainer<T, Data> {
  // Must extend MembershipContainer.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of TraversableContainer                        */
  /* ************************************************************************ */

  // TraverseForward
	boolean TraverseForward(Predicate<Data> fun);
	
  // TraverseBackward
	boolean TraverseBackward(Predicate<Data> fun);

  // FoldForward
	default  <Acc> Acc FoldForward(Accumulator<Data, Acc> fun, Acc ini) {
		final Box<> acc = new Box<ini>;
	}
  // FoldBackward
	default  <Acc> Acc FoldBackward(Accumulator<Data, Acc> fun, Acc ini) {
		
	}
  // Multiplicity
  // IsEqual

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // Size

  /* ************************************************************************ */
  /* Override specific member functions from MembershipContainer              */
  /* ************************************************************************ */

  // Exists

}
