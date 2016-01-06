package bkmenu.server.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import bkmenu.model.BkMenuItem;
import bkmenu.server.db.Utils;

/**
 * Resource class without error handling.
 * @author pbose
 *
 */
@Path ("/")
public class BkMenuResource 
{
	/**
	 * Checks if the server and database is up.
	 * @return 
	 */
	@GET
	@Path ("/ping")
	@Produces(MediaType.TEXT_PLAIN)
	public Response ping()
	{
		Utils.ping();
		return Response.status(Response.Status.OK)
				.entity("Service is up and running.")
				.build(); 
	}
	
	/**
	 * Gets an item based on id.
	 */
	@GET
	@Path ("/get/{id}")
	@Produces (MediaType.APPLICATION_XML)
	public Response getById(@PathParam("id") int id)
	{
		return Response.status(Response.Status.OK)
				.entity(Utils.getById(id))
				.build();
	}
	
	/**
	 * Searches menu items based on the query string.
	 * Searches on the "name" field of the data model.
	 */
	@GET
	@Path ("/get")
	public Response getByName(@QueryParam("search") String name)
	{
		return Response.status(Response.Status.OK)
				.entity(Utils.getByName(name))
				.build();
	}
	
	/**
	 * Getting data using @FormParam.
	 * Get by id and return the menu item in XML format.
	 */
	@POST
	@Path ("getById")
	@Consumes (MediaType.APPLICATION_FORM_URLENCODED)
	@Produces (MediaType.APPLICATION_XML)
	public Response formParam(@FormParam("id") int id)
	{
		return Response.status(Response.Status.OK)
				.entity(Utils.getById(id))
				.build();
	}
	
	@POST
	@Path ("/create")
	@Consumes (MediaType.APPLICATION_FORM_URLENCODED)
	@Produces (MediaType.TEXT_HTML)
	public Response createItem(
			@FormParam("id") int id,
			@FormParam("name") String name,
			@FormParam("price") float price,
			@FormParam("desc") String desc)
	{
		BkMenuItem item = new BkMenuItem(id, name, price, desc);
		
		Utils.createItem(item);
		
		return Response.status(Response.Status.OK)
				.entity("Item created: " + item.toString())
				.build();
	}
	
	@PUT
	@Path ("/update/{id}")
	@Consumes (MediaType.APPLICATION_JSON)
	@Produces (MediaType.TEXT_PLAIN)
	public Response updateItem(@PathParam("id") int id, float price)
	{
		Utils.updateItem(id, price);
		
		return Response.status(Response.Status.OK)
				.entity("Item updated")
				.build();
	}
	
	/**
	 * Demo method on how @Context can be used to get URI and HTTP header information.
	 * @param hdrs
	 * @param info
	 * @return
	 */
	@GET
	@Path ("/metadata")
	@Produces (MediaType.TEXT_PLAIN)
	public Response metadata(
			@Context HttpHeaders hdrs,
			@Context UriInfo info)
	{
		StringBuilder builder = new StringBuilder();
		
		// get the full URL
		builder.append("Request URL: ").append(info.getRequestUri().toASCIIString()).append("\n\n");
		
		// append the HTTP headers
		builder.append("*** HTTP Headers ***\n");
		MultivaluedMap<String, String> map = hdrs.getRequestHeaders();
		
		for (String s : hdrs.getRequestHeaders().keySet())
			builder.append(s).append(": ").append(map.get(s)).append('\n');
		
		// send the response as text/plain
		return Response.status(Response.Status.OK)
				.entity(builder.toString())
				.build();
	}
	
	/**
	 * Handling custom MIME types through custom content handlers
	 */
	@GET
	@Path ("/customget/{id}")
	@Produces ("application/prs.menuitem+json")
	public Response customGetById(@PathParam("id") int id)
	{
		// extracts the menu item from the menu and sets that as entity for Response
		return Response.status(Response.Status.OK)
				.entity(Utils.getById(id).getMenu().get(0))
				.build();
	}	
}
