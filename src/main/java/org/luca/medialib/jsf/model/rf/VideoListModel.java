package org.luca.medialib.jsf.model.rf;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.luca.medialib.domain.VideoContent;
import org.luca.medialib.service.VideoContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.verborgh.enterprise.context.ViewScoped;


/**
 * Pagable model for {@link VideoContent}.
 * 
 * Note:
 * JSF restore/save the state for datatables on Phase 1/6!
 * This implies to initialize the model on each request.
 * Also when we use execute/render for ajax requests to "exclude" the table!
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