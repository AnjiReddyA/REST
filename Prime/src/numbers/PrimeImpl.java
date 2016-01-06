package numbers;

import javax.jws.HandlerChain;
import javax.jws.WebService;

@WebService (endpointInterface = "numbers.Prime")
@HandlerChain(file = "handlers/handler-chain.xml")
public class PrimeImpl implements Prime
{
	@Override
	public boolean isPrime(int number) 
	{
		for(int i= 2; i <= number/2; i++)
	        if(number % i == 0) return false;
		
		return true;
	}
}
