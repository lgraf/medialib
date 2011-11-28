package org.luca.medialib.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.myfaces.extensions.cdi.jpa.api.Transactional;
import org.luca.medialib.domain.VideoContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author luc4
 */
@Transactional
public class VideoContentService implements Serializable
{

	private static final Logger log = LoggerFactory.getLogger( VideoContentService.class );

	@Inject
	private EntityManager em;


	public Long count()
	{
		return em.createQuery( "SELECT count(c) FROM VideoContent c", Long.class )
				.getSingleResult();
	}


	public VideoContent findById( Long id )
	{
		return em.createQuery( "SELECT c FROM VideoContent c WHERE c.id = :id", VideoContent.class )
				.setParameter( "id", id ).getSingleResult();
	}


	public List<VideoContent> findAll()
	{
		return em.createQuery( "SELECT c FROM VideoContent c ORDER BY c.title", VideoContent.class )
				.getResultList();
	}


	public List<VideoContent> findRanged( int start, int size )
	{
		return em.createQuery( "SELECT c FROM VideoContent c ORDER BY c.title", VideoContent.class )
				.setFirstResult( start ).setMaxResults( size ).getResultList();
	}


	public void persist( VideoContent toPersist )
	{
		log.debug( "Persist new video {} ...", toPersist );
		em.persist( toPersist );
	}


	public VideoContent update( VideoContent toUpdate )
	{
		log.debug( "Update video {} ... ", toUpdate );
		return em.merge( toUpdate );
	}

}