package org.luca.medialib.service;

/**
 * Indicates an error with an third party information provider.
 * 
 * @author luc4
 */
public class ExternalServiceException extends RuntimeException
{

	public ExternalServiceException( String message )
	{
		super( message );
	}

	public ExternalServiceException( String message, Throwable cause )
	{
		super( message, cause );
	}

}
