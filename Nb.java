import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;


public class Nb {
	
	public static void main (String [] args) throws FileNotFoundException {
		
		File path = new File ("/opt/dropbox/17-18/473/project5/language-models");
		File [] files = path.listFiles();
		//File test = new File ("/opt/dropbox/17-18/473/project5/test.txt");
		File test = new File ("/opt/dropbox/17-18/473/project5/test.txt");
		Scanner sTest = new Scanner(test);
		Table<String, String, Integer> model = HashBasedTable.create();
		
		int sum = 0;

		Map<String, Integer> langCount = new HashMap<String, Integer>();
		
		
		for (int i =0; i < files.length; i++)
		{
			if (files[i].isFile())
			{
				Scanner s = new Scanner(files[i]);
				String name = files[i].getName().substring(0, 3);
				int total = 0;
				
				
				while (s.hasNextLine())
				{
					String str1 = s.nextLine();
					
					String[] str2 = str1.split("\t");
					
					String word = str2[0].toString();
			
					int count = Integer.parseInt(str2[1]);
				
					model.put(name, word, count);
					
					total = total+count;
				}
				
				langCount.put(name, total);
		
			}
		}
		
		for (Integer i :langCount.values()) {
		    sum += i;
		}
	
		Set<String> langName = langCount.keySet();
		String [] langType = langName.toArray(new String[langCount.keySet().size()]);
	
		
		while(sTest.hasNextLine())
		{
			String line = sTest.nextLine();

			
			String testStr = line.substring(line.indexOf("\t")+1, line.length());
			testStr = testStr.replaceAll("[.,!¡¥$£¿;:()\\\"—–/¹²³«»]","");
			testStr = testStr.replace("[", "").replace("]", "");
			testStr = testStr.replaceAll("(?<= )'", " ");
			testStr = testStr.replaceAll("'(?= )", " ");
			testStr =testStr.replace("(?<= )-", " ");
			testStr = testStr.replaceAll("-(?= )", "");
			
			String [] testArr = testStr.split(" ");

			
			System.out.println(line);
	
			classify(sum,model, langCount,langType,testArr);
		}
		
					
	}

	public static void classify (Integer sum, Table<String, String, Integer> model, Map <String, Integer> langCount, String[] langType, String[] testStrg)
	{
		
		int appear = 0;
		
		int exclude = 0;
		
		Set<String> unique = model.columnKeySet();
			
		int uniCount = unique.size();
		
		
		double prop = 0.000000;
		
		double rProp = 0.00000;
		
		double minProp = 10000000;
		
		double Cv = 0.0;
		

		Map<Double, String> output = new HashMap<Double, String>();
		
		for (String b: langType)
		
		{
			String language = b.toString();
			
			int bCount = langCount.get(b);
			int totCount = sum - bCount;
			
			int reward = 0;
			
			for (String a: testStrg)
			{	
				String testWord = a.toString();
				Map<String, Integer> e = model.column(a);
				
				for (Map.Entry<String, Integer> entry: e.entrySet())
				{
					
					
					exclude = exclude + entry.getValue();

				}
				
				boolean entryPresent = model.contains (language, testWord);
				//count(w,c)+1
				if (entryPresent)
				{	
					appear = exclude- model.get(language, testWord)+1; 
					reward = reward -1;
				}
				else
				{	
					
					appear =exclude + 1;
				
				
				}

				prop = ((Math.log10(appear)-Math.log10(totCount+uniCount))); 
				rProp = rProp + prop+reward;
				exclude = 0;
			
			}

			minProp = Math.min(minProp, rProp); 
			
			output.put(rProp, b);	
			rProp = 0;

		}
		
		output.forEach((k,v)->System.out.println(v+ "\t" + k));
		
		
		System.out.println("result" + "\t" + output.get(minProp));
		
		
	}
	

}

