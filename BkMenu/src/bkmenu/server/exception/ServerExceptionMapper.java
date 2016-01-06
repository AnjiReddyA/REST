package bkmenu.server.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ServerExceptionMapper implements ExceptionMapper<ServerException>
{
	@Override
	public Response toResponse(ServerException e) 
	{
		return Response.status(e.getStatus())
				.entity(e.getMessage())
				.type(MediaType.TEXT_PLAIN)
				.build();
	}
}
