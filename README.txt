Problem Description
	You will be provided with an input file which defines a graph and a series of questions relating to the graph. Your program will need to read this input file and output the answers to an output file.
 
Input
 	1. The program must accept a single argument, a path to the input file. e.g. "~/input.txt”
	2. The input file will be structured as follows:
		a. The first line will be the graph definition, a single line of routes separated by spaces.
		b. A route will be defined as AC5 (i.e. The distance from A to C is 5).
 		c. Following the problem definition each line will represent one of the following questions (The routes included in these questions are examples only).

			i. DISTANCE CBAE: What is calculated distance of the route CBAE? The answer should be the route, including distance. i.e. CBAE12
			ii. SHORTEST CA: What is the shortest route from C to A? The answer should be the route, including distance. i.e. CBA12
			iii. POSSIBLE CA15: What are the possible routes from C to A with a maximum distance of 15? The answer should be all routes, including distances, separated by spaces. i.e. CBA8 CBADA12 CBDA11 CBDADA15 CEA12 CBEA15
 		d. The input file can contain any number of questions, in any order.

 	3. Example input.txt:
 
		AB5 AD2 AE4 BC3 BD6 BE8 CE8 DE6
		DISTANCE CBAE
		DISTANCE DEABD
		SHORTEST CA
		POSSIBLE CA15
 
Output
	1. Output the answers to a file named 'output.txt', one answer per line, in the following format: DISTANCE CBAE = CBAE12
	2. Example output.txt:

		DISTANCE CBAE = CBAE12
		DISTANCE DEABD = DEABD21
		SHORTEST CA = CBA8
		POSSIBLE CA = CBA8 CBDA11 CBADA12 CEA12 CBDADA15 CBEA15

Solution Description
	
	Design pattern: Singleton, Factory, Observer
	Data Structure: Weighted Graph, Index min heap priority queue
	Algorithm: Dijkstra Shortest Path, Depth limited Search

Execution of the Program

	javac Main
	java Main <input-file-path> <output-file-path>