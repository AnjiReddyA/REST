package server.exception;

import javax.ws.rs.core.Response.Status;

@SuppressWarnings("serial")
public class ServerException extends RuntimeException 
{
	private Status status = Status.OK;
	
	public ServerException(Status status, String message)
	{ 
		super(message); 
		this.status = status;
	}
	
	public ServerException(Status status, String message, Throwable t)
	{
		super(message, t);
		this.status = status;
	}
	
	public ServerException(String message) 
	{ super(message); }

	public Status getStatus()
	{ return status; }
}
