
public class Main {
	public static void main(String[] args) {
		String inputFile = args[0];
		String outputFile = args[1];
		
		MyReader reader= new MyReader();
		reader.readGraphInfo(inputFile);
		reader.readQuestions(inputFile);
		System.out.println("Read file task completed");
		
		/*
		DistanceSolution dSolution = new DistanceSolution();
		System.out.println(dSolution.getClass().getName());
		QuestionList.getInstance().addObserver(dSolution);
		
		ShortestSolution sSolution = new ShortestSolution();
		QuestionList.getInstance().addObserver(sSolution);
		
		PossibleSolution pSolution = new PossibleSolution();
		QuestionList.getInstance().addObserver(pSolution);
		
		InvalidSolution iSolution = new InvalidSolution();
		QuestionList.getInstance().addObserver(iSolution);
		*/
		QuestionList.getInstance().createSolutions();
		QuestionList.getInstance().solvingQuestions();
		
		MyWriter writer = new MyWriter();
		writer.writeAnswers(outputFile);
		System.out.println("Write file task completed");
	}
}
