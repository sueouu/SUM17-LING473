Shu Ou Project 1 Read-Me

Purpose: 
The purpose of this project is design a program that’s capable of counting the number of syntactic constituent types including Sentence, Noun Phrase, Verb Phrase, Ditransitive Verb Phrase, and Intransitive Verb Phrase, which occur in the Treebank-3 corpus from the Linguistics Data Consortium.


Results
Constituent			PTB Symbol			                      Count
Sentence			  (S ...)				                        4670
Noun Phrase			(NP ...)			                        13221
Verb Phrase			(VP ...)			                        7920
Ditransitive Verb Phrase	(VP verb (NP ...) (NP ...)) 	5
Intransitive Verb Phrase	(VP verb)			               119


Program Elements Overview


The program consists of a total of one class, and three methods as listed below: 
1.	Class -- CountCType
2.	Method -- Main
3.	Method -- Count
4.	Method -- Count1


Detailed Descriptions of Program Elements

Main
The Main method first initiates String variable corporaName to be later used for looping through all files in the corpus, arrays typeCount and t to later be used for collecting the count of various syntactic constituent types with the count method, as well as File (corporaFile) and Scanner (scanner & scanner2) variables to help scan through information of individual files in the corpus.   The scanner variable is used on counting constituent types excluding the Ditransitive Verb Phrase (DVP), whereas the scanner2 variable is employed to assist on counting DVP. 

The Main method establishes a FOR loop to iterate over all the *.prd files in the directory (“/corpora/LDC/LDC99T42/RAW/parsed/prd/wsj/14”) with help of the Scanner variables mentioned above.  The FOR loop also calls methods Count and Count1 to count the number of syntactic constituent types (as mentioned above) from individual *.prd files in the directory.
After the counting process is finished, the System.out.println() method is employed to present the results to the user. 


Count
The Count method first initiates all local variables needed to execute this method.  Such local variables include String representations for PTB symbols of individual syntactic constituent types.   
Then the Count method establishes a WHILE loop to execute line-by-line processing to search whether a selected input line has one or more of the syntactic constituent types, while there are more input lines in the selected Scanner file.  The IF statements nested in the WHILE loop have the action to provide counting instructions when selected input line meets the IF-condition.  

The IF statements execute the String.contains (string presentation of constituent type) method to identify whether a selected input line contains the String representation of a given syntactic constituent types.  If such input line contains the string representation, it will be passed down to the WHILE loop nested in that particular IF statement to count the number of occurrences of that string representation in that line.   The number of occurrence will be added to the count of such constituent type (the typeCount array).  
To count Intransitive Verb Phrase (IVP), an additional IF-statement (child statement) is nested in the IF statement (parent statement) responsible for counting IVP, to determine whether the verb phrase passes down from the parent statement matches the IVP form - (VP verb).  If matches, the count of Intransitive Verb Phrase will be updated in the typeCount array.  


Count1
The Count1 method has a similar structure as the Count method in that Count1 also first initiates all local variables needed to execute this method, and contains a WHILE loop to execute line-by-line processing to search whether a selected input line has one of the wanted syntactic constituent types, while there are additional input lines in the selected file.  
The Count1 method then establishes nested IF Statements to provide counting instructions.  As DVP  takes the form of (VP verb (NP ...) (NP ...)).  The first IF statement analyzes whether the selected input line (a) contains an open VP or not; if so, scan for next input line (b).  The second IF statement nested in the first IF statement checks if line b contains a daughter NP node; if so, scan for next input line (c).   The third IF statement nested in the second IF statement evaluates line c, and if line c has a closed daughter NP node (NP ...)) , the count of DVP will be updated in the array t. 


