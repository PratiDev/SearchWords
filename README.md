# SearchWords
A console application with command line driven text search directory, building an in memory representation of the files and their contents, and a command prompt at which interactive searches can be performed.
The search takes the words given as input via command prompt and returns a list of max 10 matching filenames in rank order.
Rand order calculation 100% if file contains all words. Ranking formula depends on the number of words given as input.
For example, if the seach words are 'to be' and word 'be' is not present in 203.txt but word 
'to' is present. And both words are present in files 204.txt, 205.txt. Below is the result :

3 files read in the directory
search> to be
205.txt : 100%
204.txt : 100%
203.txt : 50%
search> 

Have not added tests due to time constraints.

How to run ?
Since it is console stand alone, run it like any simple java program having main method.

