package numbers.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import numbers.Prime;

public class PrimeClient 
{
	public static void main(String[] args) throws MalformedURLException 
	{
		URL url = new URL("http://localhost:8080/Prime/prime");
		QName namespace = new QName("http://numbers/", "PrimeImplService");
		Service service = Service.create(url, namespace);
		Prime endpoint = service.getPort(Prime.class);
		System.out.println("PrimeClient.main(): " + endpoint.isPrime(19));
	}
}
