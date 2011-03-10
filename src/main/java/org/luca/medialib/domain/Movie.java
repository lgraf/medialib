package org.luca.medialib.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.validation.constraints.Size;


/**
 * Represents an Movie.
 * 
 * @author luc4
 */
@Entity
public class Movie extends VideoContent
{

	@Lob
	@Size( max = 65536 )
	private String overview;

	@Embedded
	private Image poster;


	public String getOverview()
	{
		return overview;
	}


	public void setOverview( String overview )
	{
		this.overview = overview;
	}


	public Image getPoster()
	{
		return poster;
	}


	public void setPoster( Image poster )
	{
		this.poster = poster;
	}

}
