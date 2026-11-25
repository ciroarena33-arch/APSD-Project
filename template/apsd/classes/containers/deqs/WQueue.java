package apsd.classes.containers.deqs;

import apsd.classes.utilities.Natural;
// import apsd.classes.containers.collections.concretecollections.VList;
// import apsd.classes.utilities.Natural;
// import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.deqs.Queue;

/** Object: Wrapper lstue implementation. */
public class WQueue<Data>  implements Queue<WQueue<Data>, Data>{
  // Must implement Queue.

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

  // protected final List<?, Data> lst;
	protected final List<?, Data> lst;
  // WQueue()
  // WQueue(List<?, Data> lst)
  // WQueue(TraversableContainer<?, Data> con)
  // WQueue(List<?, Data> lst, TraversableContainer<?, Data> con)

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // Size
	@Override
	public Natural Size() {
		// TODO Auto-generated method stub
		return null;
	}
	
  // Empty
	@Override
	public WQueue<Data> Empty() {
		// TODO Auto-generated method stub
		return null;
	}

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // Clear
	public void Clear() {
		
	}

  /* ************************************************************************ */
  /* Override specific member functions from Queue                            */
  /* ************************************************************************ */

  // Head
	@Override
	public Data Head() {
		return null;
	}
	
  // Dequeue
	@Override
	public void Dequeue() {
		
	}
	
  // HeadNDequeue
	@Override
	public Data HeadNDequeue() {
		return null;
	}
	
  // Enqueue
	@Override
	public void Enqueue(Data dat) {
		
	}

	

	

}
