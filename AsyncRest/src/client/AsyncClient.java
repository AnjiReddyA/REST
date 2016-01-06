package client;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class AsyncClient 
{
	private static final String TARGET_URI = "http://localhost:8080/AsyncRest/result";
	public static void main(String[] args) 
	{
		usingFuture();
		
		usingCallbacks();
	}

	private static void usingCallbacks() 
	{
		Client cl = ClientBuilder.newClient();
		WebTarget target = cl.target(TARGET_URI);
		Invocation.Builder builder = target.request();
		AsyncInvoker async = builder.async();
		
		Response r = null;
		try
		{
			Future<Response>f = async.get(new ResponseCallback()); // HTTP method call

			Thread.sleep(5000);
			System.out.println("AsyncClient.usingCallbacks: Cancelled: " + f.isCancelled() + 
					" Completed: " + f.isDone()); 
		}
		catch (Exception e)
		{
			System.out.println("AsyncClient.usingCallbacks: " + e.getMessage());
		}
		finally
		{
			if (r != null) r.close();
		}	
	}

	private static void usingFuture() 
	{
		Client cl = ClientBuilder.newClient();
		WebTarget target = cl.target(TARGET_URI);
		Invocation.Builder builder = target.request();
		AsyncInvoker async = builder.async();
		
		Response r = null;
		try
		{
			Future<Response>f = async.get(); // HTTP method call
			r = f.get(); // get the results
			System.out.println("AsyncClient.usingFuture:" + r.readEntity(String.class)); // read response
		}
		catch (InterruptedException | ExecutionException e)
		{
			System.out.println("AsyncClient.usingFuture: " + e.getMessage());
		}
		finally
		{
			if (r != null) r.close();
		}
	}
}
