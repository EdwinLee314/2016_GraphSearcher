
import java.util.Observable;
import java.util.Observer;

public class DistanceSolution extends Solution implements Observer {
	String question;
	String solution;

	@Override
	public void solve() {
		String temp[ ] = question.split(" ");
		if (temp[0].equals("DISTANCE") == true) {
			//System.out.println(temp[0]);
			int distance = WeightedGraph.getInstance().calDistance(temp[1]);
			//System.out.println(toString());
			solution = temp[1] + distance; 
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
