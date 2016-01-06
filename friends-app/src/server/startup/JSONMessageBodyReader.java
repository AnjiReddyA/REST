package server.startup;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import server.exception.ServerException;
import server.model.Friend;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class JSONMessageBodyReader implements MessageBodyReader<Friend> 
{
	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations,
			MediaType mediaType) 
	{ return type == Friend.class; }

	@Override
	public Friend readFrom(Class<Friend> type, Type genericType, Annotation[] annotations, 
			MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
			InputStream entityStream) 
	{
		ObjectMapper mapper = new ObjectMapper();
		
		try 
		{
			mapper.configure(Feature.AUTO_CLOSE_TARGET, true);
			return mapper.readValue(entityStream, Friend.class); 
		} 
		catch (IOException | WebApplicationException e) 
		{
			e.printStackTrace();
			throw new ServerException(Status.BAD_REQUEST, 
					"Could not create Friend instance.");
		} 
	}
}
