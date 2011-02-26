package org.luca.medialib.jsf.listener;

import static javax.faces.event.PhaseId.ANY_PHASE;
import static javax.faces.event.PhaseId.RENDER_RESPONSE;
import static javax.faces.event.PhaseId.RESTORE_VIEW;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * PhaseListener for debugging the JSF-Lifecycle.
 * Note: It seems that listeners are shared between requests!
 * So use this listener only in a controlled dev environment, never in production!
 * 
 * @author luc4
 */
public class TracePhaseListener implements PhaseListener
{

	private static final Logger log = LoggerFactory.getLogger( TracePhaseListener.class );

	private long phaseTimer;
	private long requestTimer;

	@Override
	public void beforePhase( PhaseEvent pe )
	{
		phaseTimer = System.currentTimeMillis();

		if ( RESTORE_VIEW.equals( pe.getPhaseId() ) )
		{
			requestTimer = 0;
			log.debug( "+++ New Request started! +++" );
		}

		log.debug( "Begin JSF-Phase({})!", pe.getPhaseId() );
	}

	@Override
	public void afterPhase( PhaseEvent pe )
	{
		phaseTimer = System.currentTimeMillis() - phaseTimer;
		requestTimer += phaseTimer;

		log.debug( " End of JSF-Phase({})! [{}ms]", pe.getPhaseId(), phaseTimer );
		if ( RENDER_RESPONSE.equals( pe.getPhaseId() ) )
		{
			log.debug( "+++ End of Request reached! [{}]ms +++", requestTimer );
		}
	}

	@Override
	public PhaseId getPhaseId()
	{
		return ANY_PHASE;
	}

}