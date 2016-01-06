import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class NumberGenerator 
{
	public static void main(String [] args)
	{
		File ofile = new File("./data/numbers.txt"); 
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(ofile)))
		{
			Random r = new Random();
			int i = 2000, c = i;
			do 
			{
				writer.write(String.valueOf(r.nextInt(100000)));
				writer.newLine();
				i--;
			}while (i > 0);
			
			System.out.println(c + " numbers generated...");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
