package org.luca.medialib.domain;

import javax.persistence.Entity;


/**
 * Abstract base for video content.
 * 
 * @author luc4
 */
@Entity
public abstract class VideoContent extends Content
{

	public VideoContent()
	{
		super();
	}

	public VideoContent( String title )
	{
		super( title );
	}

}
