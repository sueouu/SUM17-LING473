Shu Ou Project 4 Read-Me

Purpose:
The purpose of this project is to develop a program that is capable of efficiently identifying all matches of DNA sequences in the hg19 GRCh37 human genome against a pool of target sequences. 

Program Elements Overview:
This program consists of a total of two classes and four methods:
1.Trie (class)
2. DNA (class)
  a. main method 
  b. load method
  c. search method
  d. print method 

Detailed Description of Program Elements:

Trie Class
The Trie class implements the Trie data structure to help organize large amount of target data in building a “tree-like” structure for efficient processing.   As there are only five possibilities of the GRCH37 human genome (“A”, “C”, “G”, “T”, “N”), 5 branches/children are created for every Trie object using.  For each Trie object, there is a boolean object isLeaf to categorize whether such Trie is the last character of a target String.  

DNA Class 
main method
The main method that first initiates a Trie object to prepare for target String processing, as well as several File & Scanner objects to read data from both the target file and the GRCH37 human genome files.  In addition, Hashmap extraCredit is established to accomplish alternatively output grouping by sequences instead of by GRCH37 file names. 

After creating all the necessary objects for genome data analysis, a WHILE Loop is generated to iterate all target Strings in the target file.  The load method is called in the WHILE loop to add target data to our “tree-like” dictionary.  

As the “tree-like” dictionary is produced, a FOR loop is implemented to iterate all the human genome files within the hg19 GRCh37 directory.   Within the FOR loop, a FileInputStream and a Scanner object are created to read each chromosome file into a String for processing.  After such String is created, the search method is called to analyze the String, and then returns a Hashmap with outputs grouped by sequences. 

Finally, the print method is called to print Hashmap extraCredit to the extra-credit file. 

load method
The load method takes in a Trie object and a String to build an information tree/dictionary. A FOR loop is used to process the input String by individual character elements.  An Array is created to generate pre-determined position for individual characters.   For example, if character c is ‘A’, then its position will be 1; if it’s ‘C’, its position will be 2.  An IF loop is then created for further analysis.  If the child/branch of the current Trie object is null at the mentioned position, such character c will be insert into the tree.   The current Trie will then points to its child Trie whose index is the same as position.  The isLeaf boolean will be set to true when the loop reaches the last character of the input String. 

Search method
The search method takes in a File object, two Trie objects, a String , a Hashmap, and returns a Hashmap.  A sample array is also used to generate pre-determined position for individual character.   In addition, a similar FOR loop to the load method is implemented to process the input String by individual characters.  However, there are two index positions i & j in such FOR loop.   Index i reads in one character at a time, if a child branch is found in that pre-determined position, such character will be appended to a Stringbuilder for creating a result String that matches one of the target Strings.   The current Trie will then switch to its child Trie whose index is the same as position.  If a child branch of the given character is not found and yet the given character is not the last character of any target Strings, index j will advance one position and index i  will be set equal to j to start the search process again.  In this case the current Trie object will be reset to the root of the Trie, and the Stringbuilder will be emptied.   When the last character of any target Strings is finally reached and the Stringbuilder is not empty,  a match is found!  Index j will be set to the start position of the match, and index j and the match will be printed.  After printing the match,  the match will be stored in the Hashmap with such match as the key, and the current input file name as well as the start position of the match are stored as its value.  After finishing storing the match into the map, the current Trie object will be reset to the root of the Trie, and the Stringbuilder will be emptied out to start the search process again.  The Hashmap is return after completing the input String analysis. 

Print method
The print method takes in a map object and a String to be used as the file name.   The method employs a PrintStream object to create a new file in the current directory and prints Keys as well as their corresponding values into the file. 

Post-Project Thoughts/Lesson Learned:
This is the first time I have tried implementing a Trie data structure in my program and it did take me quite some time to wrap my head around how a Trie can be generated without any mentions of its root.  The amount of data needed to be analyzed also created a lot of challenges for me.  I tried to open a chromosome file provided on my home computer using Wordpad and Notepad to get a quick glance, and such attempt only resulted in multiple file crashings.  In addition to reading the file on the Condor, I discovered an application named EmEditor that allows reading large text files.   Furthermore, I have also revised the type of methods used in my program several times to improve program run time.  In the beginning,  I structured  my Trie using Hashmap, and my program was taking a lot longer than I expected.  After doing some research, I have found that ArrayList is more efficient in implementing the Trie data structure.   I was able to cut my program run time down to 8 minutes after switching to using Array and eliminating other unnecessary codes. 

