
import java.util.Observable;
import java.util.Observer;

public class InvalidSolution extends Solution implements Observer {
	String question;
	String solution;
	
	String unsolvedQuestion;
	int catchtimes = 0;
	
	@Override
	public void solve() {
		if (catchtimes <= 4) {
			catchtimes++;
			unsolvedQuestion = question;
		}
		else {
			if(unsolvedQuestion.equals(question)){
				QuestionList.getInstance().addAnswer(toString());
				QuestionList.getInstance().solvedQestion();
				
			}
			catchtimes = 0;
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
		return question + " = " + "Invalid Question"; 
	}

	
}
