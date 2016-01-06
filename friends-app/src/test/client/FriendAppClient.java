package test.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.JacksonFeature;

import server.model.Friend;

public class FriendAppClient 
{
	public static void main(String[] args) 
	{
		Friend f = new Friend("Java Client", "Roxy Blacker", "freaked");
		
		Client client = ClientBuilder.newClient().register(JacksonFeature.class);
		WebTarget target = client.target("http://localhost:8080/friends-app/add");
		Builder builder = target.request();
		
		Response response = builder.post(Entity.json(f));
		
		if (response.getStatus() == 200)
			System.out.println("FriendAppClient.main() " + response.readEntity(String.class));
		else
			System.out.println("HTTP Error Code: " + response.getStatus());
	}
}
