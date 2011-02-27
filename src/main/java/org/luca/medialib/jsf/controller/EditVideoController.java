package org.luca.medialib.jsf.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.luca.medialib.domain.VideoContent;
import org.luca.medialib.service.VideoContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author luc4
 */
@Named
@RequestScoped
public class EditVideoController
{

	private static final Logger log = LoggerFactory.getLogger( EditVideoController.class );

	@Inject
	private VideoContentService videoContentService;

	@PostConstruct
	public void init()
	{
		log.debug( "Initialize via @PostConstruct ..." );
	}

	public String persist( VideoContent toPersist )
	{
		videoContentService.persist( toPersist );
		log.debug( "new item {} persistet and added ...", toPersist );
		return null;
	}

}