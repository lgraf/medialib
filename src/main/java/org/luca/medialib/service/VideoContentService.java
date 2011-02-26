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

	public List<VideoContent> getAll()
	{
		return em.createQuery( "SELECT v FROM VideoContent v", VideoContent.class ).getResultList();
	}

	public void persist( VideoContent toPersist )
	{
		em.persist( toPersist );
	}

}