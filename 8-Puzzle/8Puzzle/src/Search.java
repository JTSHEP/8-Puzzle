/*
 * Joe Sheppard
 * CSC 380
 * 1/18/2015
 * 
 * Main Search class for the 8-Puzzle problem
 */

import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;


public class Search {
	
	private static int numOnQueue=1;
	
	 //Will hold nodes to check next
	private static LinkedList<Node> queue;
	
	//holds checked states to prevent loops
	private static Hashtable<State,Boolean> checked = new Hashtable<State,Boolean>(); 
	
	//holds pointers to Nodes on the queue for each state to compare depths. value is offNode if state is already fully processed.
	private static Hashtable<State,Node> onQueue = new Hashtable<State,Node>(); 
	private static Node offNode = new Node(null,-1,null,null); //node that will signify that a state has been taken off the queue
	private static AStarComparator AStar = new AStarComparator();
	private static AStarComparator2 AStar2 = new AStarComparator2();
	
	//type is the method of searching: types DFS:1 BFS:2 BEST-FIRST:3  A*h1:4   A* h2:5
	public static Node start(State root,State goal, int type)
	{
		
		int max = 0; //variable to hold the maximum number of Nodes seen in the Queue at one time;
		queue =new LinkedList<Node>();
		Node r = new Node(root,0,null,null);
		queue.add(r);
		checked.put(root, true);
		onQueue.put(root, r);
		
		while(!(queue.isEmpty()))
		{
			if(numOnQueue>max)
				max=numOnQueue;
			numOnQueue--;
			Node current = queue.removeFirst();
			onQueue.put(current.getState(),(offNode)); //signifies the following state has been fully processed
			if(current.getState().equals(goal))
			{
				System.out.println("Max Number on Queue: "+max);
				return current;
			}
			
			
			toQueue(current.successor(),type);
			
			
		}
		
		return null; // no solution found
	}
	
	
	//is called above by start to put Nodes on the queue in the proper way, according to the algorithm(type)
	//the switch statement runs the different set of instructions of each algorithm.
	private static void toQueue(LinkedList<Node> nodes, int type)
	{
		switch(type)
		{
			case 1: for(Node x:nodes) //DFS
						{
							if(checked.get(x.getState())==null)
							{
								checked.put(x.getState(), true);
								onQueue.put(x.getState(), x);
								queue.addFirst(x);
								numOnQueue++;
							}
							else
							{
								Node on = onQueue.get(x.getState());
								if(on.getState()!=null) //checks if the state has already been taken off the queue
								{
									if(x.getDepth()<on.getDepth())
									{
										queue.remove(on);
										queue.addFirst(x);
										onQueue.put(x.getState(), x);
									}
								}
							}
						}
						break;
			case 2: for(Node x:nodes) //BFS
						{
							if(checked.get(x.getState())==null)
							{
								checked.put(x.getState(), true);
								onQueue.put(x.getState(), x);
								queue.addLast(x);
								numOnQueue++;
							}
							else
							{
								Node on = onQueue.get(x.getState());
								if(on.getState()!=null) //checks if the state has already been taken off the queue
								{
									if(x.getDepth()<on.getDepth())
									{
										queue.remove(on);
										queue.addLast(x);
										onQueue.put(x.getState(), x);
									}
								}
							}
						}
						break;
			case 3: for(Node x:nodes) //BEST FIRST
			{
				if(checked.get(x.getState())==null)
				{
					checked.put(x.getState(), true);
					onQueue.put(x.getState(), x);
					queue.addLast(x);
					numOnQueue++;
					Collections.sort(queue);
				}
				else
				{
					Node on = onQueue.get(x.getState());
					if(on.getState()!=null) //checks if the state has already been taken off the queue
					{
						if(x.getDepth()<on.getDepth())
						{
							queue.remove(on);
							queue.addLast(x);
							onQueue.put(x.getState(), x);
							Collections.sort(queue);
						}
					}
				}
			}
			break;
			
			case 4: for(Node x:nodes) //A* h1
			{
				if(checked.get(x.getState())==null)
				{
					checked.put(x.getState(), true);
					onQueue.put(x.getState(), x);
					queue.addLast(x);
					numOnQueue++;
					Collections.sort(queue,AStar);
				}
				else
				{
					Node on = onQueue.get(x.getState());
					if(on.getState()!=null) //checks if the state has already been taken off the queue
					{
						if(x.getDepth()<on.getDepth())
						{
							queue.remove(on);
							queue.addLast(x);
							onQueue.put(x.getState(), x);
							Collections.sort(queue,AStar);
						}
					}
				}
			}
			break;
			
			case 5: for(Node x:nodes) //A* h2
			{
				if(checked.get(x.getState())==null)
				{
					checked.put(x.getState(), true);
					onQueue.put(x.getState(), x);
					queue.addLast(x);
					numOnQueue++;
					Collections.sort(queue,AStar2);
				}
				else
				{
					Node on = onQueue.get(x.getState());
					if(on.getState()!=null) //checks if the state has already been taken off the queue
					{
						if(x.getDepth()<on.getDepth())
						{
							queue.remove(on);
							queue.addLast(x);
							onQueue.put(x.getState(), x);
							Collections.sort(queue,AStar2);
						}
					}
				}
			}
			break;
			default: throw new Error("Unrecognized Search Type");
		}
	}

}
