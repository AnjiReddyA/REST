package numbers.handlers;

import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;

public class PrimeLogicalHandler implements LogicalHandler<LogicalMessageContext> 
{
	@Override
	public void close(MessageContext context) 
	{}

	@Override
	public boolean handleFault(LogicalMessageContext context) 
	{ return false; }

	@Override
	public boolean handleMessage(LogicalMessageContext context) 
	{
		Boolean bOutbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		
		if (bOutbound.booleanValue())
			System.out.println(">>>>>> PrimeLogicalHandler.handleMessage()::Outbound");
		else
		{
			System.out.println(">>>>>> PrimeLogicalHandler.handleMessage()::Inbound");
		}
		return true;
	}

}
