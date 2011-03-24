package org.luca.medialib.jsf.controller;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.luca.medialib.domain.Movie;
import org.luca.medialib.service.ExternalMovieService;
import org.luca.medialib.service.ExternalServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.verborgh.enterprise.context.ViewScoped;


/**
 * TODO luc4: think about scope.
 * ViewScope is needed because we use commandlinks in the table. We need the state for following
 * requests to know the clicked row.
 * 
 * How we can avoid this? This will not scale well.
 * 
 * @author luc4
 */
@Named
@ViewScoped
public class ExternalMovieSearchController implements Serializable
{

	private static final Logger log = LoggerFactory.getLogger( ExternalMovieSearchController.class );

	@Inject
	private ExternalMovieService movieService;

	@NotNull
	@Size( min = 2, max = 255 )
	private String searchString;

	private List<Movie> searchResult = Collections.emptyList();


	@PostConstruct
	private void init()
	{
		log.debug( "Initialize via @PostConstruct ..." );
	}


	public String search()
	{
		try
		{
			searchResult = movieService.searchByTitle( searchString );
		}
		catch ( ExternalServiceException e )
		{
			log.warn( "error while movie search! ", e );
			// TODO luc4: handle exception
		}
		return null;
	}


	public String getSearchString()
	{
		return searchString;
	}


	public void setSearchString( String searchString )
	{
		this.searchString = searchString;
	}


	public List<Movie> getSearchResult()
	{
		return searchResult;
	}


	public void setSearchResult( List<Movie> searchResult )
	{
		this.searchResult = searchResult;
	}

}
