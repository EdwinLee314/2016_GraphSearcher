import java.util.ArrayList;
import java.util.Stack;

public class DlsPaths {
	// Depth limited search for all possible paths
	private Stack<Edge> theStack;
	private ArrayList<String> routes;
	private int source;
	private int destination;
	
	public DlsPaths(int s,int t, int limit) {
		theStack = new Stack<Edge>();
		routes = new ArrayList<String>();
		this.source = s;
		this.destination = t;
		dls(s, limit);	
	}
	
	private void dls(int s, int limit) {
		
		for (Edge e : WeightedGraph.getInstance().adjacencyOf(s)) {
			
			int w = e.other(s);
			
			if(w != source) {
				theStack.push(e);
				//System.out.println("After Add Stack: "+ printStack());
			
				if(!theStack.empty()) {
					if(routeLength() <= limit) {
						if(w != destination){
							
							//System.out.println("Not find Start: " + WeightedGraph.getInstance().nameOf(s));
							//System.out.println("Not find End: " + WeightedGraph.getInstance().nameOf(w));
							dls(w,limit);
						}
						else{
							// find the destination
							addRoute();
							//System.out.println(toString());

							//System.out.println("find Start: " + WeightedGraph.getInstance().nameOf(s));
							//System.out.println("find End: " + WeightedGraph.getInstance().nameOf(w));
							dls(w,limit);
						}
					}
					else{
						
						theStack.pop();
						//System.out.println("Delete Start: " + WeightedGraph.getInstance().nameOf(dEdge.either()));
						//System.out.println("Delete End: " + WeightedGraph.getInstance().nameOf(dEdge.other(dEdge.either())));
						//System.out.println("After Delete Stack: "+ printStack());
					}
				}
			}
			
		}
		if(!theStack.empty()) {
			theStack.pop();
		}
		
	}
	
	private int routeLength() {
		int distance = 0;
		for (Edge e: theStack){
			distance = distance + e.weight();
		}
		return distance;
	}
	
	private String printStack() {
		String oneRoute = "";
		int w = this.source;
		oneRoute = oneRoute + WeightedGraph.getInstance().nameOf(w);
		
		for (Edge e : theStack){
			//System.out.println("end: " + w);
			//System.out.println("Start: " + source);
			//System.out.println(WeightedGraph.getInstance());
			//System.out.println(e);
			int temp = e.other(w);
			w = temp;
			oneRoute = oneRoute + WeightedGraph.getInstance().nameOf(w);
		}
		oneRoute = oneRoute + routeLength();
		return oneRoute;
	}
	
	private void addRoute() {
		routes.add(printStack());
	}
	
	public String toString() {
		String temp = "";
		if(!routes.isEmpty()) {
			for(String i: routes){
				temp = temp + i + " ";
			}
		}
		else{
			throw new IndexOutOfBoundsException("Do not have routes in required distance");
		}
		return temp;
	}
}
