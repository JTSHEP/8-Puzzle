# 8-Puzzle
Sovles an 8 tile sliding puzzle using various algorithms

The 8-Puzzle problem can be explained here: https://en.wikipedia.org/wiki/15_puzzle.

This program represents the state of the puzzle with a list of ints in the following format:
NOTE: 0 represents the empty space.
new int[]{1,3,4,8,6,2,7,0,5}

This corresponds to:

--------------
| 1 | 3 | 4 | 
| 8 | 6 | 2 | 
| 7 |   | 5 | 
--------------

To run the program, compile all java files, and run the Tester Class.

The system will output to the command line, asking you to respond with the keyboard which puzzle you would
like to run, and which algorithm you would like to use. The three preloaded puzzles are as follows. You cam also
create your own puzzle.

EASY: {1,3,4,8,6,2,7,0,5}
MEDIUM: {2,8,1,0,4,3,7,6,5}
HARD: {5,6,7,4,0,8,3,2,1}

The system will solve the puzzle with the selected algorith, provide the a solution, and print some performance benchmarks.
