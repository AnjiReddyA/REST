package numbers.handlers;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class PrimeClientSOAPHandler implements SOAPHandler<SOAPMessageContext> 
{
	@Override
	public Set<QName> getHeaders() 
	{ return null; }

	/**
	 * Used for cleanup just before the outbound/inbond message leaves
	 */
	@Override
	public void close(MessageContext context) 
	{}

	@Override
	/**
	 * Return true to continue processing else return false to block processing
	 */
	public boolean handleFault(SOAPMessageContext context) 
	{
		/*
		 * If any exception is thrown from the endpoints or handleMessage(...), 
		 * the processing comes here.
		 */
		return true; 
	}

	@Override
	/**
	 * Return true to continue processing else return false to block processing
	 */
	public boolean handleMessage(SOAPMessageContext context) 
	{
		Boolean bOutbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		boolean bProceed = true;
		
		if (bOutbound.booleanValue())
		{
			System.out.println(">>>>> PrimeClientSOAPHandler.handleMessage(): Outbound");
			// get the SOAP message
			SOAPMessage message = context.getMessage();
			try 
			{
				// get the envelope
				SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
				// extract header from envelope
				SOAPHeader hdr = envelope.getHeader();
				
				// if the header is absent then add one
				if (hdr == null) 
					hdr = envelope.addHeader();
				
				// create an element
				QName namespace = new QName("http://numbers/", "COURSE");
				// add this to the header
				SOAPHeaderElement hdrElement = hdr.addHeaderElement(namespace);
				// set the actor who will handle this header
				hdrElement.setActor(SOAPConstants.URI_SOAP_ACTOR_NEXT);
				// add text to the header property
				hdrElement.addTextNode("Web Services In Java");
				// save the changes
				message.saveChanges();
			} 
			catch (SOAPException e) 
			{
				e.printStackTrace();
				bProceed = false;
			}
		}
		else
		{
			System.out.println(">>>>> PrimeClientSOAPHandler.handleMessage(): Inbound");
		}
		return bProceed;
	}
}
