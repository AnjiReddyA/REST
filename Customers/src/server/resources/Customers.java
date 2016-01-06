package server.resources;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.Status;

import server.model.Customer;

@Path ("/")
public class Customers 
{
	/**
	 * Allow only "admin" role to create customers.
	 * @param customer
	 * @return
	 */
	@RolesAllowed ("admin")
	@POST
	@Path ("/create")
	@Consumes (MediaType.APPLICATION_XML)
	public Response create(@Context SecurityContext ctx, Customer customer)
	{		
		System.out.println("Customers.createItem(): " + customer.toString());
		System.out.println("Customers.createItem(): User name: " + ctx.getUserPrincipal().getName());
		System.out.println("Customers.createItem(): Is user in admin role: " + ctx.isUserInRole("admin"));
		System.out.println("Customers.createItem(): Authentication Scheme: " + ctx.getAuthenticationScheme());
		System.out.println("Customers.createItem(): Is secured: " + ctx.isSecure());

		return Response.status(Response.Status.OK)
				.build();
	}
	
	/**
	 * Allow "admin" and "operator" roles to view customers.
	 * @param ctx
	 * @return
	 */
	@RolesAllowed ({"admin", "operator"})
	@GET
	@Path ("/listing")
	@Produces (MediaType.APPLICATION_XML)
	public Response listing(@Context SecurityContext ctx)
	{
		System.out.println("Customers.listing(): User name: " + ctx.getUserPrincipal().getName());
		System.out.println("Customers.listing(): Is user in admin role: " + ctx.isUserInRole("admin"));
		System.out.println("Customers.listing(): Is user in operator role: " + ctx.isUserInRole("operator"));

        return Response.status(Status.OK)
        		.entity(new server.model.Customer("Preferred Customer"))
				.build();
	}
}
