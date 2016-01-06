package numbers.client;

import javax.xml.ws.soap.SOAPFaultException;

import numbers.Prime;
import numbers.PrimeImplService;

public class Client 
{
	public static void main(String[] args) 
	{
		try
		{
			PrimeImplService service = new PrimeImplService();
			Prime target = service.getPrimeImplPort();
			System.out.println("Client.main(): " + target.isPrime(98));
		}
		catch(SOAPFaultException fault)
		{
			System.out.println("Client.main(): " + fault.getMessage());
		}
	}
}
