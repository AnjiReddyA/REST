package server.startup;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import server.resources.Customers;

@ApplicationPath ("/")
public class CustomersApp extends Application 
{
	@Override
	public Set<Class<?>> getClasses()
	{
		Set<Class<?>> classes = new HashSet<Class<?>>();
		
		// registering resources
		classes.add(Customers.class);

		// registering providers
		classes.add(RolesAllowedDynamicFeature.class);
	
		return classes;
	}
}
