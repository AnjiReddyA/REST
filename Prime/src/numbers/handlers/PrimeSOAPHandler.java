package numbers.handlers;

import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.soap.SOAPFaultException;

public class PrimeSOAPHandler implements SOAPHandler<SOAPMessageContext>
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
		System.out.println(">>>>> PrimeSOAPHandler.handleFault()");
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
			System.out.println(">>>>> PrimeSOAPHandler.handleMessage(): Outbound");
		}
		else
		{
			System.out.println(">>>>> PrimeSOAPHandler.handleMessage(): Inbound");
			// get the SOAP message
			SOAPMessage message = context.getMessage();
			try 
			{
				// get the envelope
				SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
				// extract header from envelope
				SOAPHeader hdr = envelope.getHeader();
				
				// get the iterator to the headers
				Iterator<?> itHdr = hdr.extractHeaderElements(SOAPConstants.URI_SOAP_ACTOR_NEXT);
				
				while (itHdr.hasNext())
				{
					Node element = (Node)itHdr.next();
					
					// header missing, restrict client access...
					if (element.getValue().compareTo("Web Services In Java") != 0)
						raiseFault(envelope);
				}
			} 
			catch (SOAPException e) 
			{
				e.printStackTrace();
				bProceed = false;
			}
		}
		return bProceed;
	}
	
	private void raiseFault(SOAPEnvelope envelope)
	{
		try 
		{
			SOAPBody msgBody = envelope.getBody();
			SOAPFault fault = msgBody.addFault();
			fault.setFaultString("You are not welcome...");
			
			throw new SOAPFaultException(fault);		
		} 
		catch (SOAPException e) 
		{
			e.printStackTrace();
		}
	}
}
