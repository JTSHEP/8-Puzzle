/* Joe Sheppard
 * CSC 380
 * 1/16/2015
 * 
 * Main Client to take user input and test the 8-puzzle solver.
 * Also provides benchmarks to compare algorithms, such as time.
*/


import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Tester  {

	
	public static void main (String[] args) throws Exception
	{
		
		//System.out.println(new State(new int[]{1,3,4,8,6,2,7,0,5}).h2());
		System.out.println("Hello!");
		System.out.println("--------------------");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Which puzzle would you like to Run?");
		System.out.println("Easy: 1");
		System.out.println("Medium: 2");
		System.out.println("Hard: 3");
		System.out.println("New Puzzle: 4");
		System.out.println("Please Enter Below:");
		int difficulty = Integer.parseInt(in.readLine());
		
		State root;
		if(difficulty==1){
			root= new State(new int[]{1,3,4,8,6,2,7,0,5});
		}
		else if(difficulty==2){
			root= new State(new int[]{2,8,1,0,4,3,7,6,5});
		}
		else if(difficulty==3){
			root= new State(new int[]{5,6,7,4,0,8,3,2,1});
		}
		else if(difficulty==4){
			
			System.out.println("Enter a Problem in the following format, separated by commas:");
			System.out.println("2,8,1,0,4,3,7,6,5");
			String ing = in.readLine();
			String[] userInputStr=ing.split(",");
			int[] userInputInt = new int[9];
			for(int i=0;i<9;i++)
				userInputInt[i]=Integer.parseInt(userInputStr[i]);
		
			root= new State(userInputInt);
		}
		else 
			throw new Error("Unrecognized Difficulty. PLease Enter 1,2,3 or 4. ");
		
		
		System.out.println("--------------------------------");
		System.out.println("Which Algorithm would you like to use?");
		System.out.println("DFS: 1");
		System.out.println("BFS: 2");
		System.out.println("Greedy Hueristic: 3");
		System.out.println("A* w/h1:  4");
		System.out.println("A* w/h2:  5");
		System.out.println("Please Enter Below:");
		int type = Integer.parseInt(in.readLine());
		State goal= new State(new int[]{1,2,3,8,0,4,7,6,5});
		long startTime = System.nanoTime();
		Node result = Search.start(root, goal, type);
		System.out.println(result.trace());
		System.out.println("Estimated Elapsed Time: "+(System.nanoTime()-startTime)/1000000+ " miliseconds");
	}
}
