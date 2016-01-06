package ws.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;

public class MathUtilClient 
{
	public static void main(String[] args)
	{
		URL url = null;
		QName qName = null;
		Service service = null;
				
		// create the url
		try
		{
			url = new URL("http://localhost:1974/ws/mathutil");
		}
		catch (MalformedURLException e) 
		{
			System.out.println("Error: Malformed web service url.");
			System.exit(-1);
		}

		// connect to the RPC based WS
		try 
		{
			qName = new QName("http://rpc.server.ws/", "MathUtilImplService");
			
			service = Service.create(url, qName);
			ws.server.rpc.MathUtil mu = service.getPort(ws.server.rpc.MathUtil.class);
			System.out.println("RPC style web service -> " +  mu.sum(new int [] {1, 2, 3, 4, 5}));
		} 
		catch(WebServiceException e)
		{
			System.out.println("Error: RPC style web service not available.");
		}

		// connect to the Document based WS
		try 
		{
			qName = new QName("http://doc.server.ws/", "MathUtilImplService");
			
			service = Service.create(url, qName);
			ws.server.doc.MathUtil mu = service.getPort(ws.server.doc.MathUtil.class);
			System.out.println("Document style web service -> " +  mu.sum(new int [] {1, 2, 3, 4, 5}));
		} 
		catch(WebServiceException e)
		{
			System.out.println("Error: Document style web service not available.");
		}
}
}
