package apsd.interfaces.containers.deqs;

import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;

// import apsd.interfaces.containers.base.ClearableContainer;
// import apsd.interfaces.containers.base.InsertableContainer;

public interface Queue<T extends Queue<T,Data>, Data> extends ClearableContainer<T>, InsertableContainer<T, Data> {
  // Must extend ClearableContainer and InsertableContainer.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of Queue                                       */
  /* ************************************************************************ */

  // Head
	Data Head();
	
  // Dequeue
	void Dequeue();
	
  // HeadNDequeue
	default Data HeadNDequeue() {
		Data dat=Head();
		Dequeue();
		return dat;
	}
	
  // Enqueue
	void Enqueue(Data newdat);

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // Clear
	default void Clear() {
		while(IsEmpty()) {
			Dequeue();
		}
	}

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  // Insert
	@Override
	default boolean Insert(Data dat) {
		if(dat==null)return false;
		Enqueue(dat);
		return true;
	}

}
