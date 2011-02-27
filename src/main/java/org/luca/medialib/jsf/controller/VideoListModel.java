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
 * TODO luc4: think about scope.
 * JSF restore/save the state for datatables on Phase 1/6!
 * This implies to initialize the model on each request also when we use execute/render for ajax
 * requests to "exclude" the table. For now we will use ViewScope to avoid the reinitialization on
 * each request.
 * 
 * How we can avoid this? This will not scale well.
 * 
 * @author luc4
 */
@Named
@RequestScoped
//@ViewScoped
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