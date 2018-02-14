import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.InputMismatchException;

public class FSM {

	///opt/jdk8/bin/javac FSM.java

	public static void main (String [] args) throws IOException
	{
		FST fst = new FST();
		String line;
		
		System.out.println("<html><meta http-equiv='Content-Type' content='text/html; charset=UTF-8' /><body>");
				
		InputStream fileIn = new FileInputStream (new File("/opt/dropbox/17-18/473/project3/fsm-input.utf8.txt"));
		//BufferedReader reader = new BufferedReader(new InputStreamReader(fileIn, "UTF8"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(fileIn, "UTF8"));
		PrintStream ps = new PrintStream(System.out,true,"UTF-8");
		
		while ((line = reader.readLine())!=null)
		{
			ps.print(fst.process(line));
	
			System.out.println("<br/>");
		}
		
		System.out.println("</body></html>");
		
		
	}
	
	
	
	private static class FST
	{
		
		final static String V1 = "เแโใไ";
        final static String C1 = "กขฃคฅฆงจฉชซฌญฎฏฐฑฒณดตถทธนบปผฝพฟภมยรฤลฦวศษสหฬอฮ";
        final static String C2 = "รลวนม";
        final static String V2 = "ิีึืุูั็";
        final static String T  =  "\u0E48\u0E49\u0E4A\u0E4B";
        final static String V3 = "าอยว";
        final static String C3 = "งนมดบกยว";
		
		public static enum State {ZERO, ONE,TWO,THREE,FOUR,FIVE,SIX};
		public static enum Break {BREAK0,BREAK78,BREAK9};
		
		public static String process(String s_in)
		{
			State state = State.ZERO;
			StringBuilder sb = new StringBuilder();
			
						
			for (int i = 0; i < s_in.length(); i++)
			{
				String s = s_in.substring(i,i+1);
				Break bk = Break.BREAK0;
				//System.out.println(s);
			
					
				switch (state)	
				{	
					case ZERO:
						if (V1.contains(s))
						{	
							state = State.ONE;
						}	
						else if (C1.contains(s))
						{
							state = State.TWO;
						}	
						
						/*else
						{
							throw new InputMismatchException("Input is invalid.");
						}*/
					
						
						break;
					
					case ONE:
						if(C1.contains(s))
						{
							state = State.TWO;
						}
						/*else
						{
							throw new InputMismatchException("Input is invalid.");
						}*/
						
						break;
					
					case TWO:
						if (C2.contains(s))
						{
							state = State.THREE;
						}
						else if(V2.contains(s))
						{
							state = State.FOUR;
						}
						else if(T.contains(s))
						{
							state = State.FIVE;
						}
						else if(V3.contains(s))
						{
							state = State.SIX;
						}
						else if(C3.contains(s))
						{
							bk = Break.BREAK9;
							state = State.ZERO;
						}
						else if(V1.contains(s))
						{
							bk = Break.BREAK78;
							state = State.ONE;
						}
						else if(C1.contains(s))
						{
							bk = Break.BREAK78;
							state = State.TWO;
						}
						/*else
						{
							throw new InputMismatchException("Input is invalid.");
						}*/
						
						break;
						
					case THREE:
						if(V2.contains(s))
						{
							state = State.FOUR;
						}
						else if(T.contains(s))
						{
							state = State.FIVE;
						}
						else if(V3.contains(s))
						{
							state = State.SIX;
						}
						else if(C3.contains(s))
						{
							bk = Break.BREAK9;
							state = State.ZERO;
						}
						/*else
						{
							throw new InputMismatchException("Input is invalid.");
						}*/
						
						break;
						
					case FOUR:
						if (T.contains(s))
						{
							state = State.FIVE;
						}
						else if(V3.contains(s))
						{
							state = State.SIX;
						}
						else if(C3.contains(s))
						{
							bk = Break.BREAK9;
							state = State.ZERO;
						}
						else if(V1.contains(s))
						{
							bk = Break.BREAK78;
							state = State.ONE;
						}
						else if(C1.contains(s))
						{
							bk = Break.BREAK78;
							state = State.TWO;
						}
						/*else
						{
							throw new InputMismatchException("Input is invalid.");
						}*/
						
						break;
					
					case FIVE:
						if (V3.contains(s))
						{
							state = State.SIX;
						}
						else if (C3.contains(s))
						{
							bk = Break.BREAK9;
							state = State.ZERO;
						}
						else if (V1.contains(s))
						{
							bk = Break.BREAK78;
							state = State.ONE;
						}
						else if (C1.contains(s))
						{
							bk = Break.BREAK78;
							state = State.TWO;
						}
						else
						{
							throw new InputMismatchException("Input is invalid.");
						}
						
						break;
						
					case SIX:
						if (C3.contains(s))
						{
							bk = Break.BREAK9;
							state = State.ZERO;
						}
						else if(V1.contains(s))
						{
							bk = Break.BREAK78;
							state = State.ONE;
						}
						else if(C1.contains(s))
						{
							bk = Break.BREAK78;
							state = State.TWO;
						}
						/*else
						{
							throw new InputMismatchException("Input is invalid.");
						}*/
						
						break;
					
					default:
						throw new InputMismatchException("Input is invalid.");
						
						
						
				}
				
				switch(bk)
				{
				
					case BREAK0:
						sb.append(s);
						
						break;
					
					case BREAK78:
						sb.append(" ");
						
						sb.append(s);
						
						break;
					
					case BREAK9:
						sb.append(s);

						sb.append(" ");
						
						break;
					
					default:
						
						break;
				}
			
			}
			
			return sb.toString();
		
		}
	}
	
}
