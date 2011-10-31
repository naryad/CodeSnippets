package yn.experiments;

public class SplitExperiments {
	public static void main(String[] args) {
		if (args.length != 0) {
			String [] arg0split = args[0].split(",");
			System.out.println(arg0split[0]);
		}
		
		String keywordQuery = "test, test1,test2";
		for(String keyword : keywordQuery.split("\\s*,\\s*")){
			System.out.print(keyword);	
		}
		System.out.println("\n");
		System.out.println("fasdf lang:en".replaceAll("\\s*lang:en\\s*", ""));
		
		
		String a = "";
		String b = a.split(" ")[0];
		System.out.println("a.split(\\\" \\\")[0] --< " + b);
	}
}
