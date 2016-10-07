import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.Set;

public class QuestionList extends Observable {
	private Queue<String> questions;
	private List<String> answers;
	private Set<String> questionTypes;
	private String currentQuestion;
	
	private static class QuestionListHolder {
		private static final QuestionList INSTANCE = new QuestionList();
	}
	
	public static final QuestionList getInstance() {
		return QuestionListHolder.INSTANCE;
	}
	
	public QuestionList() {
		questions = new LinkedList<String>();
		answers = new LinkedList<String>();
		questionTypes = new HashSet<String>();;
	}
	
	public void addQuestion(String question) {
		questions.add(question);
	}
	
	public void addAnswer(String answer) {
		answers.add(answer);
	}
	
	public void addQuestionType(String questionType) {
		questionTypes.add(questionType);
	}
	
	public Iterable<String> answerof() {
		return answers;
	}
	
	public void createSolutions() {
		SolutionFactory sf = new SolutionFactory();
		for(String s: questionTypes) {
			
			String str = s.substring(1).toLowerCase();
			String className = s.substring(0,1) + str + "Solution";
			//System.out.println(className);
			Solution solution = sf.createSolutions(className);
			addObserver((Observer) solution);
		}
		
		addObserver((Observer) sf.createSolutions("InvalidSolution"));
	}
	
	public void solvingQuestions() {
		while(questions.size() != 0) {
			solvingQuestion();
		}
	}
	
	public void solvingQuestion() {
		//System.out.println(questions.size());
		currentQuestion = questions.peek();
		//System.out.println(currentQuestion);
		setChanged();
		notifyObservers(currentQuestion);
	}
	
	public void solvedQestion() {
		questions.remove();
		clearChanged();
		currentQuestion = null;
	}
	
}
