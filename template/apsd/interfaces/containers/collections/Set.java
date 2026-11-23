package apsd.interfaces.containers.collections;

import apsd.interfaces.containers.base.TraversableContainer;

public interface Set<T extends Set<T, Data>, Data> extends Collection<T, Data> {
  // Must extend Collection.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of Set                                         */
  /* ************************************************************************ */

  // IsEqual
	default boolean IsEqual(Set<?, Data> con) {
		if(con==null) return Size().IsZero();
		return this==con || Size().equals(con.Size()) && !TraverseForward(dat -> !con.Exists(dat));
	}

  /* ************************************************************************ */
  /* Override specific member functions from TraversableContainer             */
  /* ************************************************************************ */

  // IsEqual
	@Override
	default boolean IsEqual(TraversableContainer<?, Data> con) {
		if (con == null) Size().IsZero();
	    if (this == con) return true;
	    if (!Size().equals(con.Size())) return false;
	    return !TraverseForward(dat -> con.Multiplicity(dat).IsNotOne());

	}

  /* ************************************************************************ */
  /* Override specific member functions from Collection                       */
  /* ************************************************************************ */

  // Intersection
	@Override
	default void Intersection(Collection<?, Data> col) {
		if (col!=null) {
			Filter(dat -> col.Exists(dat));
		}
	}

}
