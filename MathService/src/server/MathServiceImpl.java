package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;

@WebService (endpointInterface = "server.MathService")
@MTOM (enabled = true, threshold = 10240)
public class MathServiceImpl implements MathService 
{
	@Override
	public byte[] checkPrimes(byte[] contents) 
	{
		try (
				ByteArrayInputStream inStream = new ByteArrayInputStream(contents);
				BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream))
			)
		{
			String line = null, space = " ";
			while((line = reader.readLine()) != null)
			{
				boolean retVal = isPrime(Integer.parseInt(line));
				writer.write(line + space + retVal);
				writer.newLine();
			}
			
			// flush the buffer before returning.
			writer.flush();
			return outStream.toByteArray();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}

	private boolean isPrime(int number) 
	{
		for(int i= 2; i <= number/2; i++)
	        if(number % i == 0) return false;
		
		return true;
	}
}
