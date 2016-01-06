package bkmenu.client;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import bkmenu.model.links.BkMenuItemLink;
import bkmenu.model.links.BkMenuLink;

public class JavaNetClient 
{
	private static final String TARGET_URI = "http://localhost:8080/BkMenu/menu/listing";
	
	public static void main(String[] args) throws Exception 
	{
		inXML("application/xml");
		
		inJSON("application/json");
	}
	
	private static void inXML(String mediaType) throws Exception
	{
		System.out.println("*** JavaNetClient.inXML() ***");
		
		URL url = new URL(TARGET_URI);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		
		connection.setRequestProperty("accept", mediaType);
		connection.connect();
		
		checkResponseStatus(connection.getResponseCode());
		
		try (InputStream stream = connection.getInputStream())
		{
			JAXBContext ctx = JAXBContext.newInstance(BkMenuLink.class);
			Unmarshaller reader = ctx.createUnmarshaller();
			BkMenuLink menu = (BkMenuLink) reader.unmarshal(stream);
			
			for (BkMenuItemLink item : menu.getMenu())
				System.out.println(item.getLink().getUri());
		}
		finally
		{ connection.disconnect(); }
	}

	private static void checkResponseStatus(int status) throws Exception
	{
		if (status != 200)
			throw new Exception("Could not connect to the URL. Code: " + status);
	}

	private static void inJSON(String mediaType) throws Exception 
	{
		System.out.println("*** JavaNetClient.inJSON() ***");
		
		URL url = new URL(TARGET_URI);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		
		connection.setRequestProperty("accept", mediaType);
		connection.connect();

		checkResponseStatus(connection.getResponseCode());
		
		try (InputStream stream = connection.getInputStream())
		{
			ObjectMapper mapper = new ObjectMapper();
			
			JsonNode tree = mapper.readTree(stream);
			
			// read the root
			JsonNode root = tree.get("food");
			// traverse the elements
			Iterator<JsonNode> children = root.elements();
			while (children.hasNext())
			{
				// get node "link"
				JsonNode link = children.next().get("link");
				// get the sub-node "href"
				JsonNode href = link.get("href");
				System.out.println(href.asText());					
			}
		}
		finally
		{ connection.disconnect(); }
	}
}
