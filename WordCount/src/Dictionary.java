import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		return this.word;
	}
	
	
	public void ReadDir(String directory, int flag) {
		File dir = new File(directory);
		for( File f: dir.listFiles()){
			if(f.isDirectory()){
				ReadDir(f.getAbsolutePath(), flag);
			}else if(flag==1){
				System.err.println("read words from  file "+dir.getAbsolutePath()+"\\"+f.getName());
				ReadWordFromFile(dir.getAbsolutePath()+"/"+f.getName());
			}else{
				System.err.println("read words from  file "+dir.getAbsolutePath()+"\\"+f.getName());
				ReadWordFromFile_Extended(dir.getAbsolutePath()+"/"+f.getName());
			}
		}
	}
	
	private ArrayList<String> SortByCount() {
		ArrayList<String> list = new ArrayList<String>(this.count.keySet());
		Collections.sort(list, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				if(count.get(o1).equals(count.get(o2))){
					return word.get(o1).compareTo(word.get(o2));
				}else{
					return count.get(o2).compareTo(count.get(o1));
				}
			}
			
		});
		return list;
	}
	
	public void OutputToFile(String filename) {
		ArrayList<String> list=SortByCount();
		try {
			FileWriter fw = new FileWriter(filename);
			BufferedWriter bf = new BufferedWriter(fw);
			for(int i=0;i<list.size();i++){
				String wd = list.get(i);
				String s1 = this.word.get(wd);
				bf.write("<"+s1+">: "+this.count.get(wd));
				bf.newLine();
			}
			bf.flush();
			fw.close();
			bf.close();
			System.err.println("write the list to file "+filename);
		} catch (FileNotFoundException e1) {
			// TODO: handle exception
			System.err.println("cannot find the file "+filename+"  to write");
			e1.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void ReadWordFromFile_Extended(String filename)  {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename)); 
			String line="";
			Pattern pattern = Pattern.compile("\\d+$");
			Matcher matcher = pattern.matcher("");
		    while((line=br.readLine())!=null){
		    	 line = line.replaceAll("[^a-zA-Z0-9]", " ");
				 StringTokenizer st = new StringTokenizer(line, " ");
				 while(st.hasMoreTokens()){
					 String key = st.nextToken();
					 String key2 = key.toLowerCase();
					 String key3 = null;
					 int len = 0;
					 if(key2.length() < 4)
						 continue;
					 int i;
					 for(i = 0; i < 4; i++){
						 if(!(key2.charAt(i)<='Z'&&key2.charAt(i)>='A'||key2.charAt(i)<='z'&&key2.charAt(i)>='a'))
						 	break;
					 }
					 if(i != 4)continue;
					 matcher.reset(key2);
					 if(matcher.find()){
						 len = matcher.group().length();
					 }
					 key2 = key2.substring(0, key2.length()-len);
					 key3 = key.substring(0, key.length()-len);
					 if(this.word.containsKey(key2)){
						 this.count.put(key2, count.get(key2)+1);
						 if(key3.compareTo(this.word.get(key2))==-1){
							 this.word.put(key2, key3);
						 }
					 }else{
						 this.count.put(key2, 1);
						 this.word.put(key2, key3);
					 }
				 }
			 }
			 br.close();
		}catch(FileNotFoundException e1){
			
			System.err.println("cannot find the file "+filename+" to read");
			e1.printStackTrace();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void ReadWordFromFile(String filename)  {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename)); 
			String line="";
		    while((line=br.readLine())!=null){
		    	 line = line.replaceAll("[^a-zA-Z0-9]", " ");
				 StringTokenizer st = new StringTokenizer(line, " ");
				 while(st.hasMoreTokens()){
					 String key = st.nextToken();
					 String key2 = key.toLowerCase();
					 if(key2.length() < 4)
						 continue;
					 int i;
					 for(i = 0; i < 4; i++){
						 if(!(key2.charAt(i)<='Z'&&key2.charAt(i)>='A'||key2.charAt(i)<='z'&&key2.charAt(i)>='a'))
						 	break;
					 }
					 if(i != 4)continue;
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
			
			System.err.println("cannot find the file "+filename+" to read");
			e1.printStackTrace();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
