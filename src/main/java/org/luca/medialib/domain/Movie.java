package org.luca.medialib.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
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
	@AttributeOverrides( {
			@AttributeOverride( name = "name", column = @Column( name = "poster_name" ) ),
			@AttributeOverride( name = "url", column = @Column( name = "poster_url" ) ),
			@AttributeOverride( name = "width", column = @Column( name = "poster_width" ) ),
			@AttributeOverride( name = "height", column = @Column( name = "poster_height" ) ) } )
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
