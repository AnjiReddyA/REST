package bkmenu.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.JacksonFeature;

public class PutClient 
{
	public static void main(String[] args) 
	{
		int id = 3;
		float price = (float)10.95;

		Client client = ClientBuilder.newClient().register(JacksonFeature.class);
		
		WebTarget target = client.target("http://localhost:8080/BkMenu/update/" + id);
		
		Builder builder = target.request();
		
		Response response = builder.put(Entity.json(price));
		
		if (response.getStatus() == 200)
			System.out.println("FriendAppClient.main() " + response.readEntity(String.class));
		else
		{
			System.out.println("HTTP Error Code: " + response.getStatus());
			System.out.println("FriendAppClient.main() " + response.readEntity(String.class));
		}
		
		response.close();
	}
}
