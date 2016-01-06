import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.ws.soap.MTOMFeature;

import server.MathService;
import server.MathServiceImplService;

public class Client 
{
	public static void main(String[] args) 
	{
		MathServiceImplService service = new MathServiceImplService();
		MathService port = service.getMathServiceImplPort(
				new MTOMFeature(true, 10240));
		
		File ifile = new File("./data/numbers.txt"),
				ofile = new File("./data/numbers_result.txt");
		
		try (
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(ifile));
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(ofile))
			)
		{
			byte[] contents = new byte[(int)ifile.length()];
			in.read(contents);		
			out.write(port.checkPrimes(contents));
			System.out.println("Service executed...");
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
