package bkmenu.server.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path ("/")
public class ErrorSimulationResource 
{	
	@GET
	@Path ("/error")
	public Response error()
	{ 
		throw new WebApplicationException(); // default error code: 500
	}
	
	@GET
	@Path ("/badrequest")
	@Produces(MediaType.TEXT_PLAIN)
	public Response badRequest()
	{
		Response res = Response.status(Response.Status.BAD_REQUEST)
				.entity(">>> BAD REQUEST Simulation <<< " + Response.Status.BAD_REQUEST)
				.build();
		
		throw new WebApplicationException(res);
	}
}
