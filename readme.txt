Shu Ou Project 2 Read-Me

Purpose:
The Main purpose of this project is to develop a program that has the capability of analyzing all files within the /corpora/LDC/LDC02T31/nyt/2000 directory and counting the usage frequency of each word that appear in those files, excluding non-word variables such as HTML makeups, digits, etc. 

Program Elements Overview:
The program consists of a total of one class and four methods.
1.	Class -- WordFrequency
2.	Method -- main
3.	Method -- extractText
4.	Method --  sort
5.	Method -- print

Detailed Description of Program Elements:

Main
The main method first initiates an array list that holds all files located in the /corpora/LDC/LDC02T31/nyt/2000 directory.  Then a FOR Loop is called to analyze contents of those files with the help of the FileReader class and the method extractText.  Contents/words that fit our counting criteria will be passed to a StringBuilder variable, which then will be used to construct a String.  After such String is established, a WHILE loop, that makes use of the Scanner class, is called to iterate through the individual words of the String.   The count information is then stored in a TreeMap variable, with unique words found on the String as the keys of the Map, and the count of such words as the values.  The basic logic of this process is the following: if an unique word is found in the String, add the word to the Map and increase its count by 1; if a word is already part of the Map keys, increase the count of word key by 1 for each time it appears in the String.   After existing the WHILE Loop, a new Map, with the word counts sorted according to the descending order frequency, is created using the sort method.  Lastly, the print method is called to display the result of this program.

extractText: 
The extractText method takes a FileReader variable to accomplish the goal of extracting qualified file contents for analysis, and returns a String.   The method first makes use of the BufferedReader class to extract information from the file, such information is then passed down to the StringBuilder to help build a String in which the content is the same as the file.  After that, the String.replaceAll() and String.toLowerCase() methods are called to firstly remove HTML makeups as well as other non-word contents from the String, and to secondly convert all the letters to lower case.  After all the modification is completed, apolished String is returneds. 


sort: 
The sort method takes a Map variable and returns a new Map variable with the Map keys sorted by the individual key value in the descending order. The sort variable first convert the original map into a list and sort the list with the help the Collections.sort () method.  The approach is simple – compare two values of two different Map keys at a time, when value b is bigger than value a, the key corresponding to value b should be placed in front of value a.  After iterating through the list of Maps, a new LinkedHashMap variable is established to maintains the insertion order. 

print:
The print method take a Map variable and makes use of a FOR loop to display the details of the Map in the format of “Key” and “Value” with a tab space between them. 

Improvement for Future Projects
One key requirement for this project is the program developed needs to be capable of removing HTML makeups with the assumption that HTML makes are only texts that start with and end with “<>” tags.  However, there are other HTML makeups that do not follow the such pattern.  If a more discrete approach is needed for similar programs, one can make use of the HTML parser from the JSOUP library to capture those HTML makeups.



