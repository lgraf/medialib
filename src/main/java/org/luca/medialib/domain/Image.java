package org.luca.medialib.domain;

import java.awt.Dimension;

import javax.persistence.Embeddable;


/**
 * Represents an Image.
 * TODO luc4: support local stored images too
 * 
 * @author luc4
 */
@Embeddable
public class Image
{

	private String name;

	private String url;

	private int width;

	private int height;


	public String getName()
	{
		return name;
	}


	public void setName( String name )
	{
		this.name = name;
	}


	public String getUrl()
	{
		return url;
	}


	public void setUrl( String url )
	{
		this.url = url;
	}


	public int getWidth()
	{
		return width;
	}


	public void setWidth( int width )
	{
		this.width = width;
	}


	public int getHeight()
	{
		return height;
	}


	public void setHeight( int height )
	{
		this.height = height;
	}


	public void setDimension( Dimension dimension )
	{
		if ( null == dimension )
			throw new IllegalArgumentException( "imension must be not null!" );

		width = dimension.width;
		height = dimension.height;
	}

}
