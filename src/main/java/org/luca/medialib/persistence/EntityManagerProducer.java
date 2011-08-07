package org.luca.medialib.persistence;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * Produces the default application {@link EntityManager}.
 * 
 * @author luc4
 */
@RequestScoped
public class EntityManagerProducer
{
	@PersistenceContext( unitName = "mediaLibPU" )
	private EntityManager entityManager;


	@Produces
	@RequestScoped
	@Default
	public EntityManager createDefaultEntityManager()
	{
		return entityManager;
	}


	public void dispose( @Disposes @Default EntityManager entityManager )
	{
		if ( entityManager.isOpen() )
		{
			entityManager.close();
		}
	}
}
