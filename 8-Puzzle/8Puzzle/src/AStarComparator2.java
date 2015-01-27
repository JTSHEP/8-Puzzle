/* Joe Sheppard
 * CSC 380
 * 1/16/2015
 * 
 * The following is an implementation of Comparator for type Node. 
 * This is used as a secondary comparison method, so we can sort by both the h, and h+depth 
 * for both A* and Greedy Best-First
*/

import java.util.Comparator;

public class AStarComparator2 implements Comparator<Node>{
	
	//A secondary comparison method being used to sort the nodes via A*
		public int compare(Node n1, Node n2)
		{
			int t = ((n1.getState().h2())+(n1.getDepth()));
			int o = ((n2.getState().h2())+(n2.getDepth()));
			if(t>o)
				return 1;
			if(t<o)
				return -1;
			return 0;
		}

}
