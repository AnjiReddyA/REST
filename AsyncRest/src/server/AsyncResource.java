package server;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

@Path ("/")
public class AsyncResource 
{
	@GET
	@Path ("/result")
	@Consumes (MediaType.TEXT_PLAIN)
	@Produces (MediaType.TEXT_PLAIN)
	public void getResult(@Suspended final AsyncResponse asyncResponse)
	{
		Thread t = new Thread(new RequestHandlerTask(asyncResponse));
		System.out.println("AsyncResource.getResult(): Starting RequestHandlerTask.");
		t.start();
	}
}
