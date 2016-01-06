package client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import server.model.Customer;

public class CustomerClient 
{
	public static void main(String[] args) 
	{		
		forOperators("frontoffice", "abc123");
		
		forAdmins("backoffice", "test123");
		
		// using operator username results in HTTP Code: 403: Forbidden
		forAdmins("frontoffice", "abc123");
		// Sending wrong username or password, HTTP Code: 401, Authentication Failure
		forOperators("xyz", "12345");
	}
	
	private static void forAdmins(String username, String password) 
	{
		// Provides HTTP Basic and Digest client authentication (based on RFC 2617)
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(username, password);
		
		Client client = ClientBuilder.newClient();
		client.register(feature);

		WebTarget createTarget = client.target("http://localhost:8080/Customers/create");
		Builder createBuilder = createTarget.request();
		Response response = createBuilder.post(Entity.xml(new Customer("Test Customer")));

		System.out.println("CustomerClient.forAdmins(): " + response.getStatus());
		response.close();
	}

	private static void forOperators(String username, String password)
	{
		// Provides HTTP Basic and Digest client authentication (based on RFC 2617)
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(username, password);
		
		Client client = ClientBuilder.newClient();
		client.register(feature);

		WebTarget listingTarget = client.target("http://localhost:8080/Customers/listing");
		Builder listingBuilder = listingTarget.request();
				
		Response response = listingBuilder.get();
		System.out.println("CustomerClient.forOperators(): " + response.getStatus());
		response.close();
	}
}
