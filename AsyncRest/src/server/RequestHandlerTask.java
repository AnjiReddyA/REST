package server;

import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class RequestHandlerTask implements Runnable
{
	private AsyncResponse asyncResponse;
	
	public RequestHandlerTask(AsyncResponse asyncResponse)
	{
		this.asyncResponse = asyncResponse;
	}
	
	@Override
	public void run() 
	{
		Response r = Response.status(Status.OK)
				.entity("Results processed.")
				.build();
		
		System.out.println("RequestHandlerTask.run(): Resuming suspended response.");
		asyncResponse.resume(r);
	}
}
