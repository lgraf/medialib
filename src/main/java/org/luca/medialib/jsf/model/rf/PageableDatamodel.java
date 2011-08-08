package org.luca.medialib.jsf.model.rf;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.ExtendedDataModel;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.luca.medialib.domain.Identifiable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Abstract pageable and data provider unaware datamodel.
 * Based on: http://gochev.blogspot.com/2009/08/richfaces-server-side-paging-with.html.
 * 
 * @author luc4
 */
abstract class PageableDatamodel<T extends Identifiable> extends ExtendedDataModel<T> implements
		Serializable
{
	private static final Logger log = LoggerFactory.getLogger( PageableDatamodel.class );

	private Long currentId;

	private Integer currentRowCount;

	private SerializableSequenceRange currentRange = new SerializableSequenceRange();

	// TODO luc4: try to avoid two caches!
	// needed because we need the cached data in guaranteed order.
	private List<T> cache;

	// needed because we want have a random accessible cache.
	private Map<Long, T> mapCache = new HashMap<Long, T>();


	/**
	 * Returns a data page.
	 * 
	 * @param start
	 * @param size
	 * @return
	 */
	protected abstract List<T> getItemsRanged( int start, int size );


	/**
	 * Returns the total count for items.
	 * 
	 * @return
	 */
	protected abstract int getItemCount();


	/**
	 * Refresh the datamodel for the current range.
	 */
	public void refresh()
	{
		log.debug( "Refreshing model {} ...", this );
		currentRowCount = getItemCount();
		cache = getItemsRanged( currentRange );
		mapCache.clear();
		for ( T item : cache )
		{
			mapCache.put( item.getId(), item );
		}
	}


	/**
	 * This method never called from framework. (non-Javadoc)
	 * 
	 * @see org.ajax4jsf.model.ExtendedDataModel#getRowKey()
	 */
	@Override
	public Object getRowKey()
	{
		return currentId;
	}


	/**
	 * This method normally called by Visitor before request Data Row.
	 */
	@Override
	public void setRowKey( Object key )
	{
		this.currentId = (Long) key;
	}


	/**
	 * This is main part of Visitor pattern. Method called by framework many times during request
	 * processing.
	 */
	@Override
	public void walk( FacesContext context, DataVisitor visitor, Range range, Object argument )
	{
		final SerializableSequenceRange nextRange = new SerializableSequenceRange(
				(SequenceRange) range );
		if ( hasRangeChanged( currentRange, nextRange ) )
		{
			currentRange = new SerializableSequenceRange( nextRange );
			cache = getItemsRanged( nextRange );
			mapCache.clear();
		}

		for ( T item : cache )
		{
			mapCache.put( item.getId(), item );
			visitor.process( context, item.getId(), argument );
		}
	}


	@Override
	public int getRowCount()
	{
		if ( null == currentRowCount )
		{
			currentRowCount = getItemCount();
		}
		return currentRowCount;
	}


	/**
	 * This is main way to obtain data row. It is intensively used by framework.
	 * We strongly recommend use of local cache in that method.
	 */
	@Override
	public T getRowData()
	{
		return mapCache.get( currentId );
	}


	/**
	 * Never called by framework.
	 */
	@Override
	public boolean isRowAvailable()
	{
		if ( null == currentId )
		{
			return false;
		}
		else
		{
			return (null != mapCache.get( currentId ));
		}
	}


	/**
	 * Unused rudiment from old JSF staff.
	 */
	@Override
	public int getRowIndex()
	{
		return -1;
	}


	/**
	 * Unused rudiment from old JSF staff.
	 */
	@Override
	public void setRowIndex( int rowIndex )
	{
		throw new UnsupportedOperationException();
	}


	/**
	 * Unused rudiment from old JSF staff.
	 */
	@Override
	public Object getWrappedData()
	{
		throw new UnsupportedOperationException();
	}


	/**
	 * Unused rudiment from old JSF staff.
	 */
	@Override
	public void setWrappedData( Object data )
	{
		throw new UnsupportedOperationException();
	}


	private boolean hasRangeChanged( SerializableSequenceRange current,
			SerializableSequenceRange next )
	{
		boolean changed = false;
		if ( !current.equals( next ) )
		{
			log.debug( "Current range has changed! Current was: {} | Next is: {}", currentRange,
					next );
			changed = true;
		}
		return changed;
	}


	private List<T> getItemsRanged( SerializableSequenceRange currentRange )
	{
		final int start = currentRange.getFirstRow();
		final int size = currentRange.getRows();
		return getItemsRanged( start, size );
	}

}