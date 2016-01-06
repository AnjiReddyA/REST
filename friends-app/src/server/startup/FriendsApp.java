package server.startup;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import server.exception.ServerExceptionMapper;
import server.resource.FriendsResource;

@ApplicationPath("/*") // alternative to web.xml <servlet-mapping> tag
public class FriendsApp extends Application 
{
	@Override
	public Set<Class<?>> getClasses()
	{
		Set<Class<?>> s = new HashSet<Class<?>>();
		
		s.add(FriendsResource.class); // REST resource class
		s.add(ServerExceptionMapper.class);
		
		// Not using this as JacksonFeature as the the necessary binding classes
		//s.add(JSONMessageBodyReader.class);
		s.add(org.glassfish.jersey.jackson.JacksonFeature.class);
				
		return s;
	}
}

