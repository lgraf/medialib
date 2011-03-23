package org.luca.medialib.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.luca.medialib.domain.VideoContent;


/**
 * @author luc4
 */
@Stateless
public class VideoContentService
{

	@PersistenceContext
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
		em.persist( toPersist );
	}

}