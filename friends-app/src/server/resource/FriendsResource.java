package server.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import server.controller.FriendsMgr;
import server.exception.ServerException;
import server.model.Friend;
import server.model.db.DBUtils;

@Path("")
public class FriendsResource 
{
	@Context
	private UriInfo uriInfo;
	/**
	 * Test method to check endpoint health
	 * @return
	 */
	@GET
	@Path("/testservice")
	@Produces(MediaType.TEXT_PLAIN)
	public Response testService()
	{ 
		DBUtils.testDBConn();
		return Response.status(Response.Status.OK)
				.entity("Endpoint active...")
				.build(); 
	}
	
	/**
	 * Get all friends, with hypermedia wrapped in JSON.
	 * @return
	 */
	@GET
	@Path("/getall")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll()
	{
		ArrayList<Friend> friends = FriendsMgr.getAll();
		ArrayList<String> uri = new ArrayList<String>();
		
		for (Friend f : friends)
		{
			try 
			{
				URI u = new URI(uriInfo.getBaseUri().toASCIIString() + 
						"get/" + 
						String.valueOf(f.getId()));
				uri.add(u.toString());
			} 
			catch (URISyntaxException e) 
			{ 
				throw new ServerException(Status.INTERNAL_SERVER_ERROR, ""
					+ "Could not generate resource links.");
			}
		}
		
		return Response.status(Response.Status.OK)
		.entity(uri)
		.build();
	}
	
	/**
	 * Get details of a friend, wrapped in JSON, from the hypermedia 
	 * sent by the {@link #getAll() getAll} method.
	 * @param id
	 * @return
	 */
	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFriend(@PathParam("id") String id)
	{
		return Response.status(Response.Status.OK)
				.entity(FriendsMgr.getFriend(Integer.parseInt(id)))
				.build();
	}
	
	/**
	 * Creates a friend and send the hypermedia for the newly created resource.
	 * Input and output are in JSON format.
	 * @param f
	 * @return
	 */
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addFriend(Friend f)
	{
		FriendsMgr.addFriend(f);
		
		try 
		{
			URI u = new URI(uriInfo.getBaseUri().toASCIIString() + 
					"get/" + 
					String.valueOf(f.getId()));
			
			return Response.status(Status.OK)
					.entity(u.toString())
					.build();
		}
		catch (URISyntaxException e) 
		{ 
			throw new ServerException(Status.INTERNAL_SERVER_ERROR, ""
				+ "Could not generate resource links.");
		}

	}
}
