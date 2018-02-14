Shu Ou Project 5 Read-Me


Project Objective
The main objective of this project is to create a naïve Bayesian classifier to help identify which of the 15 languages the given text fragments belong to. 


Program Element

This main program makes use of the Guava Libraries and is made out of one class, and two methods, while the extra program has an additional method. 

1.	Nb 

	a.	main method

	b.	classify method 

2.	Nbex

	a.	main method
	
b.	classify method
	
c.	CalCV method


Methodology and Approach


	I chose a multinomial naïve Bayesian classifier(NB) for this project as the given language models are all unigram models and can be considered discrete values.  In addition, the classifier created is of type Complement Naïve Bayes (CNB).  The decision to create a CNB classifier instead of a conventional NB is made after studying the given set of language models, and I have found the dataset size of such language models are extremely skewed.  Some languages have a lot of data that can be used for classification evaluation, while others only have a few.  If a conventional NB is chosen for this project, such NB would also prefer language models with more data (Rennie et.al 2003).   For CNBs, instead of calculating the probability of a word given the language it’s being evaluated with, it calculates the probability (the log probability in this case) of such word occurs in languages other than the one currently in use for evaluation, given the word occur in languages other than the one currently in use.  The language with the lowest logprob value will be classified as most suitable language for that given text utterance. 


	I first created a conventional NB and such NB has suffered from relatively low accuracy, and have failed to correctly classify a few of the test utterances.  After reading the paper “Tackling the Poor Assumptions of Naive Bayes Text Classifiers” (https://people.csail.mit.edu/jrennie/papers/icml03-nb.pdf), I decided to switch to create a CNB.
In addition, the Laplace smoothing (1-up smoothing) is used to account for cases when the data being evaluated has no presence in the language model that it’s evaluating against.  
Furthermore, I also employ a reward system that adds negative reward points to the logprob value of individual text utterance to help distance out the logprob value of the best suitable language for such text utterance.  The reward point is negative as we are trying to get argmin logprob value for CNBs.  For example, if there are three words of a given text utterance that belong to the English language model, such text utterance will be provided with  five (-1-2-3) rewards points to the its final logprob value against the English language.  This approach is also an extra effort to reduce the negative impact to the proposed classifier accuracy driven potential bias created by Laplace smoothing.

In the attempt of examining the extra text fragments for extra credit, the CalCV method is added to calculate the coefficient of variation (CV) of modified logprob values of all languages models against individual text utterance. I chose to use coefficient of variation instead of choosing a threshold of logprob values as the logprob values in this application is modified with the reward points system.   In addition,  CV is chosen over standard deviation as the length of the individual text utterance vary from each other, CV will be a better measurement.  A bigger CV means the spread of the data is wider, which indicates there is likely an outlier, and that outlier is likely to be the match.   

	After testing my program with the extra-train document, I decide to go with a CV threshold of -20.  If a CV is smaller than -20,  the text utterance being evaluated is likely belong of the 15 language.  The program is able to get up to a 93% accuracy with evaluating the test-extra document.
Algorithm
Language models provided are read into a guava table with its information on lang ID, word, and word counts stored in the table separately.  A Hashmap is created to store the total word count of individual languages.  In addition, an array is also crated to store lang IDs. 
A Scanner class is called to process the test file.  With the use a WHILE loop, individual text utterance is read once at a time and is removed of punctuations.  Then, such text utterance is separated into individual words, and stored in a String array ready for analyzed.  
The String array is then passed to the classify method along with the guave table created, the language ID array and the hashmap.  Two For loops are used to analyze the String array by language and by individual word elements.  Within in the classify method, a Hashamp is created to store the logprob value of individual text utterance.  At the end of the method, the all the logprob values are printed. 
For extra credit, the calCV method is called to analyze the spread of such logprob values.  If the calCV is smaller -20, the program will output the lanage ID with the smallest logprob for that text utterance; else, the result is determined to be unknown.  


