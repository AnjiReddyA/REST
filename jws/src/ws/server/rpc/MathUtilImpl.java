package ws.server.rpc;

import javax.jws.WebService;

@WebService (endpointInterface = "ws.server.rpc.MathUtil")
public class MathUtilImpl implements MathUtil 
{
	@Override
	public int sum(int[] list) 
	{
		int sum = 0;
		for (int i : list)
			sum += i;
		
		return sum;
	}
}
