
public class SolutionFactory {

	public Solution createSolutions(String className) {
		Solution s = null;
		try{
			s = (Solution)Class.forName(className).newInstance();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
}
