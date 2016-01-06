
package server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "MathService", targetNamespace = "http://server/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface MathService {


    /**
     * 
     * @param arg0
     * @return
     *     returns byte[]
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "checkPrimes", targetNamespace = "http://server/", className = "server.CheckPrimes")
    @ResponseWrapper(localName = "checkPrimesResponse", targetNamespace = "http://server/", className = "server.CheckPrimesResponse")
    @Action(input = "http://server/MathService/checkPrimesRequest", output = "http://server/MathService/checkPrimesResponse")
    public byte[] checkPrimes(
        @WebParam(name = "arg0", targetNamespace = "")
        byte[] arg0);

}