import java.io.IOException;
import java.io.FileReader;
import java.io.Reader;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;


public class WordFrequency {
	
	public static void main(String[] args) throws Exception{
		 
		StringBuilder sb1 = new StringBuilder();
		 File path = new File ("/corpora/LDC/LDC02T31/nyt/2000");
		 File [] files = path.listFiles();

		    for (int i =0; i < files.length; i++)
		    {
		      if (files[i].isFile())
		      {
		    	  FileReader reader = new FileReader(files[i]);
		    	  String d = WordFrequency.extractText(reader);
			    
				  	sb1.append(d+" ");	
		      }
		    }
		
	
		String s = sb1.toString();
		
	  
		Scanner input = new Scanner (s);
		Map<String, Integer> wordCounts = new TreeMap<String, Integer>();
	  
	
		while (input.hasNext())
		{
			String next = input.next();
			if (!wordCounts.containsKey(next)) 
			{
				wordCounts.put(next, 1);
			} 
			else 
			{
              wordCounts.put(next, wordCounts.get(next) + 1);
			}
		}
	  
		Map<String, Integer> sortedMap = sort(wordCounts);
        print(sortedMap);
		
		

		
	}
	  
	  
	
	public static String extractText(Reader reader) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(reader);
		String line;
		while ( (line=br.readLine()) != null) 
		{
			sb.append(line + " ");
		}
		
		String txtOnly = sb.toString();
		txtOnly = txtOnly.replaceAll("<[^>]*>", " ");
		txtOnly = txtOnly.replaceAll("[^a-zA-Z']", " ");
    txtOnly = txtOnly.replaceAll("(?<= )'*", " ");
		txtOnly = txtOnly.replaceAll("'*(?= )", " ");
		txtOnly = txtOnly.toLowerCase();
		    		
		return txtOnly;
	  
	}
	 private static Map<String, Integer> sort(Map<String, Integer> unsortedMap) {

	
	        List<Map.Entry<String, Integer>> list =
	                new LinkedList<Map.Entry<String, Integer>>(unsortedMap.entrySet());


	        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
	            public int compare(Map.Entry<String, Integer> o1,
	                               Map.Entry<String, Integer> o2) {
	                return (o2.getValue()).compareTo(o1.getValue());
	            }
	        });

	        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
	        for (Map.Entry<String, Integer> entry : list) {
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }



	        return sortedMap;
	    }

	    public static <K, V> void print(Map<K, V> map) {
	        for (Map.Entry<K, V> entry : map.entrySet()) {
	            System.out.println(entry.getKey()
	                    + "\t" + entry.getValue());
	        }
	    

	}
	
	
	  
}