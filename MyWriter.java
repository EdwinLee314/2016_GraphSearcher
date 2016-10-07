import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MyWriter {	
	public void writeAnswers(String outputFile) {
		for(String answer: QuestionList.getInstance().answerof()) {
			try{
				FileWriter fw=new FileWriter(outputFile,true);  
		        PrintWriter pw=new PrintWriter(fw); 
		        pw.println(answer);
				fw.close();
			} catch (IOException e) {  
	            e.printStackTrace();  
	        } 
		}
	}
}
