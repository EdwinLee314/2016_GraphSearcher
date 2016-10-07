
import java.util.Observable;
import java.util.Observer;

public class PossibleSolution extends Solution implements Observer{
	String question;
	String solution;
	
	@Override
	public void solve() {
		String temp[ ] = question.split(" ");
		if (temp[0].equals("POSSIBLE") == true) {
			//System.out.println(temp[0]);
			String vw = temp[1];
			String v = vw.substring(0, 1);
			String w = vw.substring(1,2);
			String limit = vw.substring(2);
			
			int vpoint = WeightedGraph.getInstance().indexOf(v);
			int wpoint = WeightedGraph.getInstance().indexOf(w);
			int limitation = Integer.parseInt(limit);
			
			DlsPaths solver = new DlsPaths(vpoint,wpoint,limitation);
			solution = solver.toString();
			//System.out.println(solver.toString());
			QuestionList.getInstance().addAnswer(toString());
			QuestionList.getInstance().solvedQestion();
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
