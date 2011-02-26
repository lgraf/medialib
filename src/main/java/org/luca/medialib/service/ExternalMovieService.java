package org.luca.medialib.service;

import java.util.List;

import org.luca.medialib.domain.Movie;


/**
 * Encapsulate third party APIs to retrieve external {@link Movie} information.
 * 
 * @author luc4
 */
public interface ExternalMovieService
{

	/**
	 * Find movies by their title.
	 * 
	 * @param title
	 * @return the search result or an empty list.
	 */
	public List<Movie> searchByTitle( String title );
}
