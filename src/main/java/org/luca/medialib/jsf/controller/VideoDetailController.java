package org.luca.medialib.jsf.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.luca.medialib.domain.VideoContent;
import org.luca.medialib.jsf.model.rf.VideoListModel;
import org.luca.medialib.service.VideoContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Named
@ViewScoped
public class VideoDetailController implements Serializable
{
	private static final Logger log = LoggerFactory.getLogger( VideoDetailController.class );

	@Inject
	private VideoContentService videoContentService;

	@Inject
	private VideoListModel videoListModel;

	private VideoContent selected;

	private boolean editMode;


	@PostConstruct
	private void init()
	{
		log.debug( "Initialize via @PostConstruct ..." );
	}


	public void create( VideoContent toPersist )
	{
		videoContentService.persist( toPersist );
		videoListModel.refresh();
	}


	public void update()
	{
		selected = videoContentService.update( selected );
		videoListModel.refresh();

		editMode = false;
	}


	public VideoContent getSelected()
	{
		return selected;
	}


	public void setSelected( VideoContent selected )
	{
		this.selected = selected;
	}


	public boolean isEditMode()
	{
		return editMode;
	}


	public void setEditMode( boolean editMode )
	{
		this.editMode = editMode;
	}

}
