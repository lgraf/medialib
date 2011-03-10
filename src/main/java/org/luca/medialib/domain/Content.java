package org.luca.medialib.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Abstract base for all audio/video related content.
 * 
 * @author luc4
 */
@Entity
@Inheritance( strategy = InheritanceType.JOINED )
abstract class Content extends Persistable
{

	// TODO luc4: this is not the natural relation between content and medium (normally a medium has content ...)
	@Enumerated( EnumType.STRING )
	private Medium medium;

	@Enumerated( EnumType.STRING )
	private Format format;

	@Size( min = 10, max = 13 )
	private String isbn;

	@NotNull
	@Size( min = 2, max = 255 )
	private String title;

	@Temporal( TemporalType.DATE )
	private Date releaseDate;

	private double runtime;


	public Medium getMedium()
	{
		return medium;
	}


	public void setMedium( Medium medium )
	{
		this.medium = medium;
	}


	public Format getFormat()
	{
		return format;
	}


	public void setFormat( Format format )
	{
		this.format = format;
	}


	public String getIsbn()
	{
		return isbn;
	}


	public void setIsbn( String isbn )
	{
		this.isbn = isbn;
	}


	public String getTitle()
	{
		return title;
	}


	public void setTitle( String title )
	{
		this.title = title;
	}


	public Date getReleaseDate()
	{
		return releaseDate;
	}


	public void setReleaseDate( Date releaseDate )
	{
		this.releaseDate = releaseDate;
	}


	public double getRuntime()
	{
		return runtime;
	}


	public void setRuntime( double runtime )
	{
		this.runtime = runtime;
	}


	@Override
	public boolean equals( Object obj )
	{
		if ( obj == null )
		{
			return false;
		}
		if ( getClass() != obj.getClass() )
		{
			return false;
		}
		final Content other = (Content) obj;
		if ( (this.title == null) ? (other.title != null) : !this.title.equals( other.title ) )
		{
			return false;
		}
		return true;
	}


	@Override
	public int hashCode()
	{
		int hash = 7;
		hash = 11 * hash + (this.title != null ? this.title.hashCode() : 0);
		return hash;
	}

}
