package bkmenu.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.JacksonFeature;

import bkmenu.model.links.BkMenuItemLink;
import bkmenu.model.links.BkMenuLink;

public class OrderClient 
{
	private static final String TARGET_URI = "http://localhost:8080/BkMenu/menu/listing";

	public static void main(String[] args) 
	{
		try 
		{
			BkMenuLink menu = getMenuListing();
		
			for (BkMenuItemLink item : menu.getMenu())
				System.out.println(item.getLink().getRel() + ": " + item.getLink().getUri());
			
			placeOrder(menu);
		} 
		catch (Exception e) 
		{ e.printStackTrace(); }
	}
	
	public static BkMenuLink getMenuListing() throws Exception
	{
		Client client = ClientBuilder.newClient().register(JacksonFeature.class);
		WebTarget target = client.target(TARGET_URI);
		Builder builder = target.request(MediaType.APPLICATION_JSON);
		Response response = builder.get();
		
		if (response.getStatus() != 200)
			throw new Exception(response.readEntity(String.class));
		
		BkMenuLink menu = response.readEntity(BkMenuLink.class); 
		response.close();

		return menu;	
	}

	private static void placeOrder(BkMenuLink menu) 
	{
		Client client = ClientBuilder.newClient().register(JacksonFeature.class);
		
		WebTarget target = null;
		Builder builder = null;
		Response response = null;
		
		for (BkMenuItemLink item : menu.getMenu())
		{
			target = client.target(item.getLink().getUri());
			builder = target.request(MediaType.APPLICATION_JSON);
			response = builder.get();
			
			System.out.println(response.readEntity(String.class)); 
			response.close();
		}
	}
}
