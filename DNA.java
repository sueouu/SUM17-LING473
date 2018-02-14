import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;


class Trie {

	Trie [] children = new Trie[5];
	boolean isLeaf = false;
	
	public Trie() 
	{

		  for(int i=0;i<5;i++){
		      children[i]=null;

		  }

	}
	
}

public class DNA {

	public static void main (String [] args) throws IOException {
		
		//final long startTime = System.nanoTime();
		
		Trie root = new Trie();
		File tFile = new File ("/opt/dropbox/17-18/473/project4/targets");
		Scanner s1 = new Scanner(tFile);
		
		File path = new File ("/opt/dropbox/17-18/473/project4/hg19-GRCh37/");
		File [] files = path.listFiles();
	
		
		Map<String, Set<String>> extraCredit = new HashMap<String, Set<String>>();
	
		while (s1.hasNextLine())
		{
		
			String line = s1.nextLine();

			load(root,line); 

		}

	
		for (int i =0; i < files.length; i++)
		{
			if (files[i].isFile())
			{
				FileInputStream testStream = new FileInputStream(files[i]);
				Scanner s2 = new Scanner(testStream);
				String testString = s2.useDelimiter("\\A").next();
        System.out.println("/opt/dropbox/15-16/473/project4/hg19-GRCh37/" +files[i].getName());
        extraCredit = search(files[i], root, root, testString, extraCredit);					
					
			}
		} 
	

		print(extraCredit, "extra-credit");
		
		//final long duration = System.nanoTime() - startTime;
		//System.out.println(TimeUnit.MINUTES.convert(duration, TimeUnit.NANOSECONDS));
		
		
	}
	
	public static void load (Trie root, String pattern){
		Trie cRoot = root;
		char [] sample = {'A', 'C', 'G', 'T','N'};
		int index = -1;
		
		for (int i = 0; i<pattern.length(); i++)
		{
		
			char c = pattern.charAt(i);
	
			if (c == sample[0]){index = 0;}
			else if (c == sample[1]){index = 1;}
			else if (c == sample[2]){index = 2;}
			else if (c == sample[3]){index = 3;}
			else if (c == sample[4]){index = 4;}
			
			int j = index;
			if (cRoot.children[j] == null)
			{
				Trie tRoot = new Trie();
				cRoot.children[j] = tRoot;
				
				cRoot = tRoot;
			} 
			else
			{
				cRoot = cRoot.children[j];
			}
		}
		cRoot.isLeaf = true;	
		
	}

	
	
	public static Map<String, Set<String>> search (File file, Trie root1, Trie root2, String testString, Map<String, Set<String>> map) throws IOException     
	{
		//String fileName0 = "/opt/dropbox/15-16/473/project4/hg19-GRCh37/" + file.getName().toString(); 
		String fileName1 = file.getName().toString(); 

		
		Trie p = root1;
		Trie q = root2;
		String result = "";
	    StringBuilder sb = new StringBuilder();
	    
	        
	    char [] sample = {'A', 'C', 'G', 'T','N'};
	    int index = -1;
	    int j = 0;
	        
	   
	    		
	    for(int i=0; i<testString.length(); i++)
	    {
	    	
	    	char c= testString.charAt(i);
	    	c = Character.toUpperCase(c);
 	
	    	
	    	if (c == sample[0]){index = 0;}
	    	else if (c == sample[1]){index = 1;}
			else if (c == sample[2]){index = 2;}
			else if (c == sample[3]){index = 3;}
			else if (c == sample[4]) {index = 4;}
	    	
	    
	    	
	    	if(index !=4 && p.children[index]!=null)
	    	{

	    		sb.append(c);
	    		p = p.children[index];

	            }
	            
	    	else if (index ==4 || p.isLeaf!=true)
	    	{
	    		j=j+1;
	    		i=j;
	            p = q;
	            sb.setLength(0);
	            result = "";
	    	}
	            
	    	
	    	
	    	if (p.isLeaf)
	    	{
	    		
	    		result = sb.toString();
	    		if(result != null || result.length()>0)
	    		{
   			    j = i-result.length()+1;
	    			System.out.println("\t" + String.format("%08X", j) + "\t" + result);
	    			
	    	
	           		if (!(map.containsKey(result)))
	           		{
	           			map.put(result, new HashSet<String>());
	           			map.get(result).add("\t" + String.format("%08X", j) + "\t" + fileName1);
	           		}
	           		else
	           		{
	           			map.get(result).add("\t" + String.format("%08X", j) + "\t" + fileName1);
	           		}
					
				}

	    		//j = j+ result.length();
	    		//i=j;
	    		p = q;
	            sb.setLength(0);
	            result = "";
	            	
	            
	           
	    	}
	        	
	    }
			
	    //input.close();
	    //s2.close();
	    return map;
	
		
	}
	public static void print (Map<String,Set<String>> map, String outputFile) throws FileNotFoundException
	{
		
		
		File opFile = new File(outputFile);
		PrintStream output = new PrintStream(opFile);
		
		for (String key: map.keySet())
		{
			output.printf("%s%n",key);
			for (String value: map.get(key))
			output.printf ("\t%s%n",value);
		}
		
	}
	
	
	
}