import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class CountCType {
	
	public static void main(String[] args) throws FileNotFoundException
	{
		String corporaName = null;
		int [] typeCount = new int [4];
		int [] t = new int[1];
		
		for (int i = 1400; i<= 1499; i++)
		{
			corporaName = i+".prd";
			
			File corporaFile = new File("/corpora/LDC/LDC99T42/RAW/parsed/prd/wsj/14/wsj_"+corporaName);
			Scanner scanner = new Scanner (corporaFile);
			Scanner scanner2 = new Scanner (corporaFile);
			
			count (scanner, typeCount, corporaName);
			count1 (scanner2, t, corporaName);
		}
		  
		System.out.println("/corpora/LDC/LDC99T42/RAW/parsed/prd/wsj/14/"+ "\t" + "S: "+ typeCount[0]+ " "
		+ "NP: " + typeCount[1] + " " + "VP: " + typeCount[2]+ " " + "DVP: " + t[0] + " " 
		+ "IVP: " + typeCount[3]);
		
	}
	
 
	public static void count(Scanner scanner, int[]typeCount, String corporaName)
	{
	
		String s = "(S ";
		String np1 = "(NP ";
		String vp = "(VP ";
		String vp1= "(VP (";
		String close = ")";	
  
		
		while (scanner.hasNextLine())
		{
			String input = scanner.nextLine();
			
			if (input.contains(s))
			{
				int index = 0;
				
				while (index != -1)
				{
					index = input.indexOf(s, index);
					if(index != -1)
					{
						typeCount[0]++;
						index += s.length();
					}
				}
			}
			
			if (input.contains(np1)) 
			{
				int index = 0;
				
				while (index != -1)
				{
					index = input.indexOf(np1, index);
					if(index != -1)
					{
						typeCount[1]++;
						index += np1.length();
					}
				}
				
			}
			
			
			if(input.contains(vp))
			{
				int index = 0;
				
				while (index != -1)
				{
					index = input.indexOf(vp, index);
					if(index != -1)
					{
						typeCount[2]++;
						index += vp.length();
					}
				}
			
				
				if ((!input.contains(vp1)) && (input.contains(close)))
				{
					typeCount[3]++;
				}
			}	
		
			
		}
	}

	public static void count1(Scanner scanner2, int[] t, String corporaName)
	{

		String np1 = "(NP ";
		String vp = "(VP ";
		String close = ")";
    String close2 = "))";		
		
		
		while (scanner2.hasNextLine())
		{
			String input1 = scanner2.nextLine();	
			if ( input1.contains(vp)&& (!input1.contains(close))) 
			{
				
				String input2 = scanner2.nextLine();
				
				if (input2.contains(np1) && (input2.contains(close)))
				{
					String input3 = scanner2.nextLine();
					
					if (input3.contains(np1) && (input3.contains(close2)))
					{
						t[0]++;
					}
				}
				
			}
			
		}
	}
}
	

			
	


	

