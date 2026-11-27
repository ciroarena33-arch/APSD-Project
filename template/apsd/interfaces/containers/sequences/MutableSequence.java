package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural; 
import apsd.interfaces.containers.base.MutableIterableContainer;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;

/** Interface: Sequence & MutableIterableContainer con supporto alla scrittura tramite posizione. */
public interface MutableSequence<T extends MutableSequence<T, Data>, Data> extends Sequence<T, Data>, MutableIterableContainer<T, Data> {
  // Must extend Sequence and MutableIterableContainer.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of MutableSequence                             */
  /* ************************************************************************ */

  // SetAt
	default void SetAt(Data dat, Natural pos) {
		long id= ExcIfOutOfBound(pos);
		MutableForwardIterator<Data> FwIt=FIterator();
		FwIt.Next(id);
		FwIt.SetCurrent(dat);
	}
	
  // GetNSetAt
	default Data GetNSetAt(Data newdat, Natural pos) {
		Data dat=GetAt(pos);
		SetAt(newdat, pos);
		return dat;
	}
	
  // SetFirst
	default void SetFirst(Data dat) {
		SetAt(dat, Natural.ZERO);
	}
	
  // GetNSetFirst
	default Data GetNSetFirst(Data newdat) {
		Data dat=GetFirst();
		SetFirst(newdat);
		return dat;
	}
	
  // SetLast
	default void SetLast(Data dat) {
		SetAt(dat, Size().Decrement());
	}
	
  // GetNSetLast
	default Data GetNSetLast(Data newdat) {
		Data dat=GetLast();
		SetLast(newdat);
		return dat;
	}
	
  // Swap
	default void Swap(Natural fst, Natural snd) {
		long idx1=fst.ToLong();
		long idx2=snd.ToLong();
		if(idx1==idx2)return;
		SetAt(GetNSetAt(GetAt(snd),fst), snd);
	}

}
