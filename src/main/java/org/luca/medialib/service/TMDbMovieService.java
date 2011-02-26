package org.luca.medialib.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.jtmdb.GeneralSettings;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.luca.medialib.domain.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Implementation based on the TMDb API (http://api.themoviedb.org/2.1) and the jtmdb Java wrapper
 * API (http://sourceforge.net/projects/jtmdb/).
 * 
 * @author luc4
 */
public class TMDbMovieService implements ExternalMovieService
{

	private static final Logger log = LoggerFactory.getLogger( TMDbMovieService.class );

	public TMDbMovieService()
	{
		GeneralSettings.setApiKey( "f170f66dcbeb2f9b278b631ed58cc7b2" );
	}

	@Override
	public List<Movie> searchByTitle( String searchString )
	{
		if ( StringUtils.isBlank( searchString ) )
		{
			return Collections.emptyList();
		}

		List<Movie> searchResult;
		try
		{
			searchResult = convert( net.sf.jtmdb.Movie.search( searchString ) );
			log.debug( "Found {} movies for string '{}'", searchResult.size(), searchString );
		}
		catch ( IOException e )
		{
			throw new ExternalServiceException( "IO error while movie search on tmdb!", e );
		}
		catch ( JSONException e )
		{
			throw new ExternalServiceException( "JSON error while movie search on tmdb!", e );
		}
		return searchResult;
	}

	private Movie convert( net.sf.jtmdb.Movie base )
	{
		if ( null == base )
		{
			throw new IllegalArgumentException( "Base object must be not null!" );
		}

		Movie converted = new Movie();
		//TODO luc4: add all related fields
		converted.setTitle( base.getName() );
		converted.setDescription( base.getOverview() );
		return converted;
	}

	private List<Movie> convert( List<net.sf.jtmdb.Movie> baseList )
	{
		if ( null == baseList )
		{
			throw new IllegalArgumentException( "Base list must be not null!" );
		}

		List<Movie> convertedList = new ArrayList<Movie>();
		for ( net.sf.jtmdb.Movie base : baseList )
		{
			convertedList.add( convert( base ) );
		}
		return convertedList;
	}
}
