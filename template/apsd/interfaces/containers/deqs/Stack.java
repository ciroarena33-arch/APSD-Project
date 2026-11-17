package apsd.interfaces.containers.deqs;

import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;

public interface Stack<T extends Stack<T, Data>, Data> extends ClearableContainer<T>, InsertableContainer<T, Data>{
  // Must extend ClearableContainer and InsertableContainer.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of Stack                                       */
  /* ************************************************************************ */

  // Top
	Data Top();
	
  // Pop
	void Pop();
	
  // TopNPop
	default Data TopNPop() {
		Data dat=Top();
		Pop();
		return dat;
	}
	
  // SwapTop
	default void SwapTop(Data dat) {
		Pop();
		Push(dat);
	}
	
  // TopNSwap
	default Data TopNSwap(Data newData) {
		Data dat=Top();
		SwapTop(newData);
		return dat;
	}
	
  // Push
	void Push(Data dat);

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // Clear
	@Override
	default void Clear() {
		while(IsEmpty()) {
			Pop();
		}
	}

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  // Insert
	@Override
	default boolean Insert(Data dat) {
		if(dat==null) return false;
		Push(dat);
		return true;
	}

}
