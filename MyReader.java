import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MyReader {
	public void readGraphInfo(String inputFile) {
		File file = new File(inputFile);
		BufferedReader inputStream = null;
		try{
			if(file.exists()){
				inputStream = new BufferedReader(new FileReader(file), 40*1024*1024);
				//read graph definition
				String graphInfo = inputStream.readLine();
				String routes[ ] = graphInfo.split(" ");
				for (String i: routes){
					// build symbols table
					String ivertex = String.valueOf(i.charAt(0));
					String jvertex = String.valueOf(i.charAt(1));
					WeightedGraph.getInstance().addVertex(ivertex);
					WeightedGraph.getInstance().addVertex(jvertex);
					
					// build adjacencyList by add edges
					String weightS = String.valueOf(i.charAt(2));
					int weight = Integer.parseInt(weightS);
					WeightedGraph.getInstance().addEdge(ivertex, jvertex, weight);

				}
				// inverted index to get string keys in an array
				WeightedGraph.getInstance().buildInvertedIndex();
				//System.out.println(WeightedGraph.getInstance().toString());
				
				inputStream.close();
			}
			else{
				System.out.println("No input files");
			}		
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void readQuestions(String inputFile) {
		File file = new File(inputFile);
		BufferedReader inputStream = null;
		try{
			if(file.exists()){
				inputStream = new BufferedReader(new FileReader(file), 40*1024*1024);
				//skip graph definition
				inputStream.readLine();
				
				String question = "";
				while((question = inputStream.readLine()) != null) {
					QuestionList.getInstance().addQuestion(question);
					//System.out.println(question);
					String temp[ ] = question.split(" ");
					String type = temp[0];
					QuestionList.getInstance().addQuestionType(type);
				}
				inputStream.close();
			}
			else{
				System.out.println("No input files");
			}		
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
