package ws.server.rpc;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 * Service Endpoint Interface (SEI)
 * @author pbose
 *
 */
@WebService
@SOAPBinding (style = Style.RPC)
public interface MathUtil 
{
	@WebMethod public int sum(int [] list);
}
