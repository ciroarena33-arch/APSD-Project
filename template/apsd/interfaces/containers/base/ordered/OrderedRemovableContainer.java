package apsd.interfaces.containers.base.ordered;

import apsd.interfaces.containers.base.RemovableContainer;

/** Interface: OrderedContainer & RemovableContainer. */
public interface OrderedRemovableContainer<T extends OrderedRemovableContainer<T, Data>, Data extends Comparable<Data>> extends RemovableContainer<T, Data>, OrderedContainer<T, Data> {
  // Must extend OrderedContainer and RemovableContainer.
  // T must have a recursive bound.
  // Data must extend Comparable.

  /* ************************************************************************ */
  /* Specific member functions of OrderedRemovableContainer                   */
  /* ************************************************************************ */

  // RemoveMin
	default void RemoveMin() {
		Remove(Min());
	}
	
  // MinNRemove
	default Data MinNRemove() {
		Data dat=Min();
		Remove(dat);
		return dat;
	}
	
  // RemoveMax
	default void RemoveMax() {
		Remove(Max());
	}
	
  // MaxNRemove
	default Data MaxNRemove() {
		Data dat=Max();
		Remove(dat);
		return dat;
	}
	
  // RemovePredecessor
	default void RemovePredecessor(Data dat) {
		Remove(Predecessor(dat));
	}
	
  // PredecessorNRemove
	default Data PredecessorNRemove(Data prev) {
		Data dat=Predecessor(prev);
		Remove(dat);
		return dat;
	}
	
  // RemoveSuccessor
	default void RemoveSuccessor(Data dat) {
		Remove(Successor(dat));
	}
	
  // SuccessorNRemove
	default Data SuccessorNRemove(Data prev) {
		Data dat=Successor(prev);
		Remove(dat);
		return dat;
	}
}
