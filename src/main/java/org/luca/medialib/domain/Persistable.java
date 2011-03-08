package org.luca.medialib.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;


/**
 * Abstract base type for all JPA entities.
 * 
 * @author luc4
 */
@MappedSuperclass
abstract class Persistable implements Identifiable, Serializable
{

	@Id
	@GeneratedValue( strategy = GenerationType.TABLE )
	private Long id;

	@Version
	private Long version;


	public Long getId()
	{
		return id;
	}


	public Long getVersion()
	{
		return version;
	}


	public boolean isPersistent()
	{
		return null != id;
	}

}
