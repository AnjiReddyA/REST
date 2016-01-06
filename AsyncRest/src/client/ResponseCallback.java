package client;

import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.Response;

public class ResponseCallback implements InvocationCallback<Response> 
{

	@Override
	public void completed(Response r) 
	{ System.out.println("MyInvocationCallback.completed: " + r.readEntity(String.class)); }

	@Override
	public void failed(Throwable t) 
	{ System.out.println("MyInvocationCallback.failed: " + t.getMessage()); }
}
