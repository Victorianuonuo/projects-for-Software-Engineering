
public class Test {

	public static void main(String[] args) {
		Dictionary dictionary = new Dictionary();
		if(args.length==1){
			String s1 = args[0];
			dictionary.ReadDir(s1,1);
			dictionary.OutputToFile(s1+"\\output.txt");
		}else if(args.length==2){
			String s1 = args[0];
			String s2 = args[1];
			if(!s1.equals("-e")){
				System.err.println("Wrong input!");
			}else{
				dictionary.ReadDir(s2,2);
				dictionary.OutputToFile(s2+"\\output.txt");
			}
		}else{
			System.err.println("Wrong input!");
		}
	}
	
}
