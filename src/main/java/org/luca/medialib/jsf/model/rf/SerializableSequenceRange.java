package org.luca.medialib.jsf.model.rf;

import java.io.IOException;
import java.io.Serializable;

import org.ajax4jsf.model.SequenceRange;


/**
 * This helper class can be used if an {@link Serializable} version of
 * org.ajax4jsf.model.SequenceRange is needed.
 * The org.ajax4jsf.model.SequenceRange class doesn't implement the {@link Serializable} interface.
 * Additionally this class implements a value based equals and hashCode method.
 * 
 * @author luc4
 */
public final class SerializableSequenceRange extends SequenceRange implements Serializable
{
	public SerializableSequenceRange()
	{
		super();
	}


	public SerializableSequenceRange( SequenceRange range )
	{
		super();
		if ( null != range )
		{
			this.setFirstRow( range.getFirstRow() );
			this.setRows( range.getRows() );
		}
	}


	private void writeObject( java.io.ObjectOutputStream out ) throws IOException
	{
		out.defaultWriteObject();
		out.writeInt( getFirstRow() );
		out.writeInt( getRows() );
	}


	private void readObject( java.io.ObjectInputStream in ) throws IOException,
			ClassNotFoundException
	{
		in.defaultReadObject();
		int firstRow = in.readInt();
		int rows = in.readInt();
		setFirstRow( firstRow );
		setRows( rows );
	}


	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		SerializableSequenceRange other = (SerializableSequenceRange) obj;
		if ( getFirstRow() != other.getFirstRow() )
			return false;
		if ( getRows() != other.getRows() )
			return false;
		return true;
	}


	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + getFirstRow();
		result = prime * result + getRows();
		return result;
	}


	@Override
	public String toString()
	{
		return "SerializableSequenceRange [firstRow=" + getFirstRow() + ", rows=" + getRows() + "]";
	}

}