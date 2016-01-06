package net.webservicex.client;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import net.webservicex.StockQuoteSoap;

/**
 * Getting stock quotes using the WSDL at http://www.webservicex.net/stockquote.asmx?WSDL
 * @author pbose
 *
 */
public class StockQuoteClient 
{
	public static void main(String[] args) throws MalformedURLException 
	{
		if (args.length != 1)
		{
			System.out.println("Usage: java StockQuoteClient stock_symbol");
			System.exit(-1);
		}
		
		// provider URL
		URL url = new URL("http://www.webservicex.net/stockquote.asmx");
		// service location and name
		QName qName = new QName("http://www.webserviceX.NET/", "StockQuote");
		
		Service service = Service.create(url, qName);
		// get the SEI
		StockQuoteSoap quote = service.getPort(StockQuoteSoap.class);
		//invoke the operation
		System.out.println(quote.getQuote(args[0]));
	}
}
