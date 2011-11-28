package org.luca.medialib.persistence;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Produces the default application {@link EntityManager}.
 * 
 * @author luc4
 */
@ApplicationScoped
public class EntityManagerProducer
{

	private static final Logger log = LoggerFactory.getLogger( EntityManagerProducer.class );

	private EntityManagerFactory emf;


	@PostConstruct
	void initialize()
	{
		log.debug( "Create EntityManagerFactory ..." );
		emf = Persistence.createEntityManagerFactory( "mediaLibPU" );
		log.debug( "EntityManagerFactory succesfully created [{}][opened: {}]", emf, emf.isOpen() );
	}


	@PreDestroy
	void release()
	{
		log.debug( "Releasing EntityManagerFactory ..." );
		if ( null != emf && emf.isOpen() )
		{
			emf.close();
			log.debug( "EntityManagerFactory succesfully closed [{}][opened: {}]", emf,
					emf.isOpen() );
		}
	}


	@Produces
	@RequestScoped
	@Default
	public EntityManager createDefaultEntityManager()
	{
		log.debug( "Create new @RequestScoped EntityManger ..." );
		final EntityManager em = emf.createEntityManager();
		log.debug( "EntityManger successfully created [{}][opened: {}]!", em, em.isOpen() );
		return em;
	}


	public void dispose( @Disposes @Default EntityManager em )
	{
		log.debug( "Closing @RequestScoped EntityManger ..." );
		if ( em.isOpen() )
		{
			em.close();
			log.debug( "EntityManger successfully closed [{}][opened: {}]!", em, em.isOpen() );
		}
	}
}
