package org.luca.medialib.jsf.model.rf;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.luca.medialib.domain.VideoContent;
import org.luca.medialib.service.VideoContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Pageable model for {@link VideoContent}.
 * 
 * @author luc4
 */
@Named
@ViewScoped
public class VideoListModel extends PageableDatamodel<VideoContent>
{

	private static final Logger log = LoggerFactory.getLogger( VideoListModel.class );

	@Inject
	private VideoContentService videoContentService;


	@PostConstruct
	private void init()
	{
		log.debug( "VideoListModel get initialized!" );
	}


	@Override
	protected int getItemCount()
	{
		log.debug( "getItemCount() get called!" );
		return videoContentService.count().intValue();
	}


	@Override
	protected List<VideoContent> getItemsRanged( int start, int size )
	{
		log.debug( "getItemsRanged() get called!" );
		return videoContentService.findRanged( start, size );
	}

}