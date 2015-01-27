/*
 * Joe Sheppard
 * CSC 380
 * 1/16/2015
 * 
 * The State class is an Object type that holds information for a configuration of the 8 puzzle. 
 * The arrangement is represented through an array of 9 integers. Each index in the Array represents
 * a location in the puzzle as follows:
 * _____________
 * |_1_|_2_|_3_|
 * |_4_|_5_|_6_|
 * |_7_|_8_|_9_|
 * 
 * Each index in the Array contains an integer in the corresponding space that represents a piece of the
 * gameboard, with 0 representing the empty space.
 */
import java.util.Arrays;
import java.util.LinkedList;
public class State {
	
	private int[] arr = new int[9];
	
	public State(int[] in)
	{
		if(in.length!=9)
			throw new Error("Must have array of 9 integers to create state");
		else
		{
			arr=Arrays.copyOf(in, 9);
		}
	}
	
	public int[] getState()
	{
		return arr;
	}
	
	//the following finds at what position the empty space(0) is.
	public int findBlank()
	{
		for(int x=0;x<9;x++)
		{
			if(arr[x]==0)
				return x;
		}
		
		return -1;
	}
	
	//overrides the Object equals method to test if two states are equal
	public boolean equals(Object o)
	{	State x = (State)o;
		return isThisState(x.getState());
	}
	
	//The Successor Method returns a list of all the possible states that can be derived from the current
	//state in one move.
	public LinkedList<State> successor()
	{
		int empty=findBlank();
		
		empty++; //converts empty array index to logical slot in puzzle for use in switch
		LinkedList<State> toReturn = new LinkedList<State>();
		
		//creates successor states depending on where the empty slot is located
		switch (empty) {
        case 1:  toReturn.add(new State(swap(arr,1,2)));
        		 toReturn.add(new State(swap(arr,1,4)));
        break;
        case 2:  toReturn.add(new State(swap(arr,2,1)));
        		 toReturn.add(new State(swap(arr,2,3)));
        		 toReturn.add(new State(swap(arr,2,5)));
                 break;
        case 3:  toReturn.add(new State(swap(arr,3,2)));
        		 toReturn.add(new State(swap(arr,3,6)));
                 break;
        case 4:  toReturn.add(new State(swap(arr,4,1)));
		 		 toReturn.add(new State(swap(arr,4,5)));
		 		 toReturn.add(new State(swap(arr,4,7)));
                 break;
        case 5:  toReturn.add(new State(swap(arr,5,2)));
		 		 toReturn.add(new State(swap(arr,5,4)));
		 		 toReturn.add(new State(swap(arr,5,6)));
		 		 toReturn.add(new State(swap(arr,5,8)));
                 break;
        case 6:  toReturn.add(new State(swap(arr,6,3)));
		 		 toReturn.add(new State(swap(arr,6,5)));
		 		 toReturn.add(new State(swap(arr,6,9)));
                 break;
        case 7:  toReturn.add(new State(swap(arr,7,4)));
		 		 toReturn.add(new State(swap(arr,7,8)));
                 break;
        case 8:  toReturn.add(new State(swap(arr,8,5)));
        		 toReturn.add(new State(swap(arr,8,7)));
        		 toReturn.add(new State(swap(arr,8,9)));
                 break;
        case 9:  toReturn.add(new State(swap(arr,9,6)));
        		 toReturn.add(new State(swap(arr,9,8)));
                 break;
        default: throw new Error("Arrangement Error");
                 
		}
		
		return toReturn;
	}
	
	public int hashCode()
	{
		int hc=arr.length;
		for(int i=0;i<arr.length;++i)
		{
		     hc=hc*17 +arr[i];
		}
		return hc;
	}
	
	//returns the hueristic defined by h = number of tiles that are not in the
	//correct place (not counting the blank). 
	public int h()
	{
		int h=8;
		if(arr[0]==1)
			h--;
		if(arr[1]==2)
			h--;
		if(arr[2]==3)
			h--;
		if(arr[3]==8)
			h--;
		if(arr[5]==4)
			h--;
		if(arr[6]==7)
			h--;
		if(arr[7]==6)
			h--;
		if(arr[8]==5)
			h--;
		return h;
	}
	
	
	//returns the hueristic defined by h = h = sum of Manhattan distances between all tiles and their correct positions. 
		public int h2()
		{
			int h=0;
			for(int i=0;i<9;i++)
			{
				int temp = arr[i];
				if(temp==1)
					h+=distanceBetween(i,0);
				if(temp==2)
					h+=distanceBetween(i,1);
				if(temp==3)
					h+=distanceBetween(i,2);
				if(temp==4)
					h+=distanceBetween(i,5);
				if(temp==5)
					h+=distanceBetween(i,8);
				if(temp==6)
					h+=distanceBetween(i,7);
				if(temp==7)
					h+=distanceBetween(i,6);
				if(temp==8)
					h+=distanceBetween(i,3);
				if(temp==0)
					h+=distanceBetween(i,4);
			}
			return h;
		}
		
		
		public static int distanceBetween(int x,int y)
		{
			int xRow = x/3;
			int xCol = x%3;
			int yRow = y/3;
			int yCol = y%3;
			return(Math.abs(xRow-yRow)+Math.abs(xCol-yCol));
		}

	
	//A helper method to return a new array with indexes i and j swapped
	public static int[] swap (int[] a, int i, int j) {
		  i--; j--; //converts from visual orientation to array orientation
		  int t = a[i];
		  int [] toReturn = Arrays.copyOf(a, 9);
		  toReturn[i] = toReturn[j];
		  toReturn[j] = t;
		  return toReturn;
		}
	
	public boolean isThisState(int[] toCheck)
	{
		if(Arrays.equals(arr,toCheck))
			return true;
		
		return false;
	}
}
