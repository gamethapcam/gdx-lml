package com.konfigurats.lml.util.tuple;

import java.io.Serializable;

/** Interface shared by all tuples. Ensures that they are serializable and iterable.
 *
 * @author MJ */
public interface Tuple extends Iterable<Object>, Serializable {
	int INVALID_INDEX = -1;
	String COMMA_WITH_SPACE_SEPARATOR = ", ";

	/** @param index index of the variable in pair.
	 * @return variable in pair with the passed index.
	 * @throws IndexOutOfBoundsException for invalid index. */
	public Object get(int index) throws IndexOutOfBoundsException;

	/** @param index index of the variable in pair.
	 * @return variable in pair with the passed index.
	 * @throws IndexOutOfBoundsException for invalid index.
	 * @throws ClassCastException if value's class doesn't match with the passed class parameter. */
	public <Type> Type get(int index, Class<Type> asInstanceOf) throws IndexOutOfBoundsException;

	/** @return current size of the tuple. */
	public int getSize();

	/** @return true if the value is stored in the tuple. Uses equals method. If the value is null, will return
	 *         true if any of the values are null. */
	public boolean contains(Object value);

	/** @return true if all values are stored in the tuple. */
	public boolean containsAll(Object... values);

	/** @return true if all values are stored in the tuple. */
	public boolean containsAll(Iterable<?> values);

	/** @return true if any of the values is stored in the tuple. */
	public boolean containsAny(Object... values);

	/** @return true if any of the values is stored in the tuple. */
	public boolean containsAny(Iterable<?> values);

	/** @return index of the passed value in the tuple or -1 if value is absent. If value is stored in the tuple
	 *         multiple times, only the first index will be returned. Accepts null; returns the first index of
	 *         null value in the tuple or -1 if all values are not null. */
	public int indexOf(Object value);

	/** @return tuple values stored in a new array. */
	public Object[] toArray();

	/** @return tuple values stored in the passed array. */
	public <Type> Type[] toArray(Type[] array);
}