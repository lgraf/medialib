package org.luca.medialib.domain;

import javax.persistence.Entity;


/**
 * Represents an Movie.
 * 
 * @author luc4
 */
@Entity
public class Movie extends VideoContent
{

	public Movie()
	{
		super();
	}

	public Movie( String title )
	{
		super( title );
	}

}
