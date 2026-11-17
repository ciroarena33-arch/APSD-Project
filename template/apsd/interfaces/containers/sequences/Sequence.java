package apsd.interfaces.containers.sequences;

// import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.iterators.ForwardIterator;

/** Interface: IterableContainer con supporto alla lettura e ricerca tramite posizione. */
public interface Sequence<T extends Sequence<T, Data>, Data> extends IterableContainer<T, Data>{
  // Must extend IterableContainer.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of Sequence                                    */
  /* ************************************************************************ */

  // GetAt
	default Data GetAt(Natural num) {
		long id= ExcIfOutOfBound(num);
		ForwardIterator<Data> FwIt=FIterator();
		FwIt.Next(id);
		return FwIt.GetCurrent();
	}
	
  // GetFirst
	default Data GetFirst() {
		return GetAt(Natural.ONE);
	}
  // GetLast
	default Data GetLast() {
		return GetAt(Size());
	}
  // Search
	default Natural Search(Data dat) {
		for(int i=1; i<=Size().ToLong(); i++) {
			if(GetAt(Natural.Of(i))==dat) return Natural.Of(i);
		}
		throw new IllegalArgumentException("Data is not valid");
	}
	
  // IsInBound
	default boolean IsInBound(Natural num) {
		return (num==null) && (num.ToLong()<Size().ToLong());
	};
	
  // ExcIfOutOfBound
	default long ExcIfOutOfBound(Natural num) {
		if(!IsInBound(num)||num==null)throw new IllegalArgumentException("Position not valid");
		return num.ToLong();
	};
	
  // IsEqual
	default boolean IsEqual(Sequence<?,Data> seq) {
		if(seq==null)return false;
		if(this==seq)return true;
		if(!Size().equals(seq.Size()))return false;
		ForwardIterator<Data> itr=seq.FIterator();
		return !TraverseForward(dat -> {Data other= itr.DataNNext(); 
					return (dat==null) ?(other!=null):!dat.equals(other);});
	}
	
  // SubSequence
	T SubSequence(Natural from, Natural to);

}
