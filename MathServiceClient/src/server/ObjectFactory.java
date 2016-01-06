
package server;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the server package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CheckPrimesResponse_QNAME = new QName("http://server/", "checkPrimesResponse");
    private final static QName _CheckPrimes_QNAME = new QName("http://server/", "checkPrimes");
    private final static QName _CheckPrimesResponseReturn_QNAME = new QName("", "return");
    private final static QName _CheckPrimesArg0_QNAME = new QName("", "arg0");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: server
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CheckPrimesResponse }
     * 
     */
    public CheckPrimesResponse createCheckPrimesResponse() {
        return new CheckPrimesResponse();
    }

    /**
     * Create an instance of {@link CheckPrimes }
     * 
     */
    public CheckPrimes createCheckPrimes() {
        return new CheckPrimes();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckPrimesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server/", name = "checkPrimesResponse")
    public JAXBElement<CheckPrimesResponse> createCheckPrimesResponse(CheckPrimesResponse value) {
        return new JAXBElement<CheckPrimesResponse>(_CheckPrimesResponse_QNAME, CheckPrimesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckPrimes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server/", name = "checkPrimes")
    public JAXBElement<CheckPrimes> createCheckPrimes(CheckPrimes value) {
        return new JAXBElement<CheckPrimes>(_CheckPrimes_QNAME, CheckPrimes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = CheckPrimesResponse.class)
    public JAXBElement<byte[]> createCheckPrimesResponseReturn(byte[] value) {
        return new JAXBElement<byte[]>(_CheckPrimesResponseReturn_QNAME, byte[].class, CheckPrimesResponse.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "arg0", scope = CheckPrimes.class)
    public JAXBElement<byte[]> createCheckPrimesArg0(byte[] value) {
        return new JAXBElement<byte[]>(_CheckPrimesArg0_QNAME, byte[].class, CheckPrimes.class, ((byte[]) value));
    }

}
