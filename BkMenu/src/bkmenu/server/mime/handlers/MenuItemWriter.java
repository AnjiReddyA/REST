package bkmenu.server.mime.handlers;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import bkmenu.model.BkMenuItem;
import bkmenu.server.exception.ServerException;

@Provider
@Produces ("application/prs.menuitem+json")
public class MenuItemWriter implements MessageBodyWriter<BkMenuItem> 
{

	@Override
	/**
	 * Not used in JAX-RS 2.0.
	 * JAX-RS automatically calculates and sets the content-length header element
	 */
	public long getSize(BkMenuItem menuItem, Class<?> classType, Type type, Annotation[] annotations,
			MediaType media) 
	{
		return -1;
	}

	@Override
	public boolean isWriteable(Class<?> classType, Type type, Annotation[] annotations,
			MediaType media) 
	{ 
		return type == BkMenuItem.class; 
	}

	@Override
	/**
	 * Writes the custom content to the response.
	 */
	public void writeTo(BkMenuItem menuItem, Class<?> classType, Type type, Annotation[] annotations,
			MediaType media, MultivaluedMap<String, Object> httpHeaders,
			OutputStream outputStream)
	{
		ObjectMapper mapper = new ObjectMapper();
		// ensures the streams are closed after use.
		mapper.configure(Feature.AUTO_CLOSE_TARGET, true);
		
		try 
		{ mapper.writeValue(outputStream, menuItem); } 
		catch (IOException | WebApplicationException e) 
		{ 
			throw new ServerException(Response.Status.INTERNAL_SERVER_ERROR, 
				"Cannot write for MIME type application/x.menuitem+json");
		}
	}
}
