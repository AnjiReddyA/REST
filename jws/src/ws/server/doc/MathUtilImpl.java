package ws.server.doc;

import javax.jws.WebService;

/**
 * Service Endpoint Interface (SEI)
 * @author pbose
 *
 */
@WebService (endpointInterface = "ws.server.doc.MathUtil")
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
