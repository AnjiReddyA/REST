package bkmenu.server.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import bkmenu.model.links.BkMenuItemLink;
import bkmenu.model.links.BkMenuLink;
import bkmenu.server.db.Utils;

/**
 * Handling HATEOAS
 * @author pbose
 *
 */
@Path ("/")
public class BkMenuOrderResource 
{
	/**
	 * Menu listing 
	 * @param uri
	 * @return
	 */
	@GET
	@Path ("/listing")
	@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getMenu(@Context UriInfo uri)
	{
		BkMenuLink menu = Utils.getAll();
		
		// create the ATOM links for the menu items
		for (BkMenuItemLink item : menu.getMenu())
		{
			Link link = Link.fromUri(uri.getBaseUri() + "order/{id}")
							.rel("order")
							.type(MediaType.APPLICATION_JSON)
							.build(item.getId());
			
			item.setLink(link);
		}
		
		return Response.status(Response.Status.OK)
				.entity(menu)
				.build();
	}
	
	/**
	 * Menu ordering
	 * @param id
	 * @return
	 */
	@GET
	@Path ("/order/{id}")
	@Consumes (MediaType.APPLICATION_JSON)
	@Produces (MediaType.APPLICATION_JSON)
	public Response order(@PathParam("id") int id)
	{
		return Response.status(Response.Status.OK)
				.entity("Order added for " + id)
				.build();
	}
}
