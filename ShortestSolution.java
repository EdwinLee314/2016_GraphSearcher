
import java.util.Observable;
import java.util.Observer;

public class ShortestSolution extends Solution implements Observer{
	String question;
	String solution;
	
	@Override
	public void solve() {
		String temp[ ] = question.split(" ");
		if (temp[0].equals("SHORTEST") == true) {
			String vw = temp[1];
			String v = vw.substring(0, 1);
			String w = vw.substring(1);
			
			int vpoint = WeightedGraph.getInstance().indexOf(v);
			int wpoint = WeightedGraph.getInstance().indexOf(w);
			
			DijkstraPath solver = new DijkstraPath(vpoint);
			if (solver.hasPathTo(wpoint)) {
				solution = solver.StringPathTo(wpoint);
				//System.out.println(toString());
				QuestionList.getInstance().addAnswer(toString());
				QuestionList.getInstance().solvedQestion();
			}			
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof QuestionList) {
			question = (String) arg;
			solve();
		}
	}
	
	public String toString() {
		return question + " = " + solution; 
	}

}
