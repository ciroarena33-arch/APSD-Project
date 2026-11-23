package apsd.interfaces.containers.base.ordered;

import apsd.interfaces.containers.base.TraversableContainer;

/** Interface: OrderedContainer & TraversableContainer. */
public interface OrderedTraversableContainer<T extends OrderedTraversableContainer<T, Data>, Data extends Comparable<Data>> extends OrderedContainer<T, Data>, TraversableContainer<T, Data> {
  // Must extend OrderedContainer and TraversableContainer.
  // T must have a recursive bound.
  // Data must extend Comparable.

  /* ************************************************************************ */
  /* Specific member functions of OrderedTraversableContainer                 */
  /* ************************************************************************ */

  // Min
	@Override
	default Data Min() {
	    return FoldForward((dat, acc) -> {
	        if (acc == null || (dat != null && dat.compareTo(acc) < 0)) {
	            return dat;
	        }
	        return acc;
	    }, null);
	}
	
  // Max
	@Override
	default Data Max() {
	    return FoldForward((dat, acc) -> {
	        if (acc == null || (dat != null && dat.compareTo(acc) > 0)) {
	            return dat;
	        }
	        return acc;
	    }, null);
	}
	
  // Predecessor
	@Override
	default Data Predecessor(Data val) {
	    if (val == null) return null;
	    return FoldForward((dat, acc) -> {
	        if (dat != null && dat.compareTo(val) < 0) {
	            if (acc == null || dat.compareTo(acc) > 0) {
	                return dat;
	            }
	        }
	        return acc;
	    }, null);
	}
	
  // Successor
	@Override
	default Data Successor(Data val) {
	    if (val == null) return null;
	    return FoldForward((dat, acc) -> {
	        if (dat != null && dat.compareTo(val) > 0) {
	            if (acc == null || dat.compareTo(acc) < 0) {
	                return dat;
	            }
	        }
	        return acc;
	    }, null);
	}	

}
