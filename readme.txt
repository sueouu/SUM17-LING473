Shu Ou Project 3 Read-Me

Purpose:
	The purpose of this project is to develop a program that implements a finite state machine (FSM) which allows syllable identification for the Thai written language.  

Program Elements Overview:
	This program consists of a total of two classes, two methods and two enumerations.
1.FSM (Class)
  a.main (method)
2.FST (Class)
  a.process (method)
  b.State (enum)
  c.Break (enum)

Detailed Description of Program Elements:

FSM Class
	The FSM class first initiates a main method that declares a FST object, as well as makes use of the InputStream, InputStreamReader and BufferedReader classes to process the targeted Thai text for analysis.  A PrintStream object is also constructed to make sure that the results of this program are displayed properly with the UTF-8 format.  After the BufferedReader pulls in the input, a WHILE loop is established to iterate through the input line by line to simplify the analyzing process.  Within the WHILE Loop, the FST object is called to implement the process method to separate the Thai inputs by words/syllables.  After the separation is completed, the Prinstream object is used to display the final results.  

FST Class
	The FST Class is a private static class that acts as a helper to the FSM class on Thai syllable identification.  The FST class first creates seven final static Strings to store various Thai vowels, constants, and tone marks by categories.   After that, two enums (State & Break) are declared to represent the nine different states of the finite machine needs to go through in order to successfully separate a given Thai text by syllables.   The State enum are representations of the first six states of the FSM, whereas the Break enum includes the three final/accept states of the FSM.   Finally, the process method comes into play.  The process method takes in a String variable, and returns a String variable.  Such method executes the mentioned nine states with the help of two switch statements.  The String variable/input first enters the State Switch Statement, is evaluated based on the number and the order of constants and or vowels that it consists of.  For example, if an Initial consonant is found (State 0) in the input, such input will skip State 1, and will be directed to State 2 of the machine, etc.  In addition, the State Switch Statement also references the Break switch statement in States 3 to 6, where different directions on syllable/character break are given.  After such examination, a String with Thai syllables separated by empty spaces is return. 

Post-Project Thoughts/Lesson Learned
	This project is the first time where I am requested to produce a program that can analyze UTF-8 encoding inputs.  During the process of constructing this program, I am able to gain knowledge on which methods are more suitable for UTF-8 environment and how to compile & run UTF-8 encoded Java program.  For example, I learn that the PrintStream method works much better in the UTF-8 environment than the System.out.print methods when displaying to the console is needed.  I believe this project is a good starting point for me to become familiar with coding in a UTF-8 environment, and I aim to do more practices to learn more about it. 
