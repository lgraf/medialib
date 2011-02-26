package org.luca.medialib.jsf.controller;

import java.util.List;

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
public class VideoListModel
{

	private static final Logger log = LoggerFactory.getLogger( VideoListModel.class );

	@Inject
	private VideoContentService videoContentService;

	private List<VideoContent> videoList;

	@PostConstruct
	public void init()
	{
		log.debug( "Initialize via @PostConstruct ..." );
		videoList = videoContentService.getAll();
	}

	public List<VideoContent> getVideoList()
	{
		return videoList;
	}

	public void setVideoList( List<VideoContent> videoList )
	{
		this.videoList = videoList;
	}

}