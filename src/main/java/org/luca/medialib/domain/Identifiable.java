package org.luca.medialib.domain;

/**
 * Specify that implementors are identifiable over an unique id.
 * The id should be unique in the domain of the implemented type.
 * 
 * @author luc4
 */
public interface Identifiable
{
	/**
	 * Returns the unique id.
	 * 
	 * @return
	 */
	Long getId();
}
