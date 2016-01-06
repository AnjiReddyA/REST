package ws.server.doc;

import javax.xml.ws.Endpoint;

public class MathUtilPublisher 
{
	public static void main(String[] args) 
	{
		System.out.println("Necessary infrastructure created to run JAX-WS server...");
		Endpoint.publish("http://localhost:1974/ws/mathutil", new MathUtilImpl());
	}
}