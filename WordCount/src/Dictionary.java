import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Dictionary {
	
	private Map<String, Integer> count;
	private Map<String, String> word;
	
	public Dictionary() {
		// TODO Auto-generated constructor stub
		this.count = new HashMap<>();
		this.word = new HashMap<>();
	}
	
	public Map<String, Integer> getCount() {
		return this.count;
	}
	
	public Map<String, String> getWord() {
		return word;
	}
	
	
	public void build(String directory) {
		File dir = new File(directory);
		for( File f: dir.listFiles()){
			if(f.isDirectory()){
				build(f.getAbsolutePath());
			}else{
				System.err.println("build words from  file "+f.getName());
				//getBufferedHashMap(f.getName());
			}
		}
	}
	
	
	public void getBufferedHashMap(String filename)  {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename)); 
			String line="";
		    while((line=br.readLine())!=null){
				 StringTokenizer st = new StringTokenizer(line, "!&(){}+-= '¡¯:;<> /\",*£º¡°¡±?@#$%^/\\¡®¡¤~`¡ª");
				 while(st.hasMoreTokens()){
					 String key = st.nextToken();
					 String key2 = key.toLowerCase();
					 if(!(key2.charAt(0)<='Z'&&key2.charAt(0)>='A'||key2.charAt(0)<='z'&&key2.charAt(0)>='a'))
						 continue;
					 if(this.word.containsKey(key2)){
						 this.count.put(key2, count.get(key2)+1);
						 if(key.compareTo(this.word.get(key2))==-1){
							 this.word.put(key2, key);
						 }
					 }else{
						 this.count.put(key2, 1);
						 this.word.put(key2, key);
					 }
				 }
			 }
			 br.close();
		}catch(FileNotFoundException e1){
			
			System.err.println("cannot find the file");
			e1.printStackTrace();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
