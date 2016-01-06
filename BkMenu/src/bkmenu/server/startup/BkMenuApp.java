package bkmenu.server.startup;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.jackson.JacksonFeature;

import bkmenu.server.exception.ServerExceptionMapper;
import bkmenu.server.mime.handlers.MenuItemWriter;
import bkmenu.server.resources.BkMenuOrderResource;
import bkmenu.server.resources.BkMenuResource;
import bkmenu.server.resources.ErrorSimulationResource;

@ApplicationPath ("/menu")
public class BkMenuApp extends Application
{
	/**
	 * Registration of resources, providers and application listeners.
	 */
	@Override
	public Set<Class<?>> getClasses()
	{
		Set<Class<?>> classes = new HashSet<Class<?>>();
		
		// resources
		classes.add(BkMenuResource.class);
		classes.add(BkMenuOrderResource.class);
		classes.add(ErrorSimulationResource.class);
			
		// providers
		classes.add(MenuItemWriter.class);
		classes.add(ServerExceptionMapper.class);
		classes.add(JacksonFeature.class);
		
		return classes;
	}
	
	/**
	 * Returns the singleton classes to be used in the application.
	 */
	@Override
	public Set<Object> getSingletons()
	{ return null; }
}
