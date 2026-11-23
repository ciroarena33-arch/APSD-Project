package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;
import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.base.RemovableContainer;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.traits.Predicate;

public interface Collection<T extends Collection<T, Data>, Data> extends ClearableContainer<T>, InsertableContainer<T, Data>, RemovableContainer<T, Data>, IterableContainer<T, Data>{
  // Must extend ClearableContainer, InsertableContainer, RemovableContainer, and IterableContainer.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of Collection                                  */
  /* ************************************************************************ */

  // InsertIfAbsent
	default boolean InsertIfAbsent(Data dat) {
		return(!Exists(dat)? Insert(dat): false);
	}
	
  // RemoveOccurrences
	default void RemoveOccurrences(Data dat) {
		Filter(elm -> !elm.equals(dat));
	}
	
  // Filter
	default boolean Filter(Predicate<Data> fun) {
		if(fun!=null) {
			Natural oldsize=Size();
			while(TraverseForward( 
					dat -> {
						if(!fun.Apply(dat)) {
							Remove(dat);
							return true;
						}
						return false;
					}))
			{}
			return !Size().equals(oldsize);
		}
		return false;
	}
  // Union
	default void Union(Collection<?, Data> col) { AllInserted(col); }
	
  // Difference
	default void Difference(Collection<?, Data> col) { AllRemoved(col); }
	
  // Intersection
	default void Intersection(Collection<?, Data> col) {
		if (col!=null) {
			Filter(dat -> Multiplicity(dat).compareTo(col.Multiplicity(dat))<=0);
		}
	}
	

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // Clear
	default void Clear() { Filter(dat->false); }

}
