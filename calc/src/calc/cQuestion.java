package calc;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.Scanner;

public class cQuestion {

	public static String str = "";//题目
	public static int num = 4;//每题中数的个数
	public static int num_i = 0;//题目中已有数的个数	
	public static int numberRange = 20;//运算中数的最大取值	
	public static number sum = new number();//结果
	
	public static void main(String[] args) {
		
		System.out.println();
		System.out.println("Please finish the following 25 questions!");
		System.out.println("Note: don't seperate your answer with space, ");
		System.out.println("or you will be considered wrong!");
		System.out.println();
		
		Scanner input=new Scanner(System.in);
		
		String file="out.txt";
		
		int right=0;
		PrintStream out = null;  
		
		
		if(args.length>=1)
			file=args[0];
		
		try {
			out=new PrintStream(new FileOutputStream(file));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			input.close();
			return ;
		}
		
		out.println("\n\n"+new Date()+"\n\n");

		for (int i = 0; i < 25; i++) {
			
			GetQuestion();
			System.out.print(i+1);
			System.out.print(". " + str +"\nYour answer:      ");
			String answer="";
			try{
				answer=input.nextLine();
			}catch(Exception e){
			}
			System.out.print("true answer:  "+sum.toString()+"    ");
			number re=sum.add(new number(10));
			try{
				re=new number(answer);
				if(re.equals(sum))
				{
					System.out.println("You are right!");
					right=right+1;
				}else {
					System.out.println("You are wrong!");
				}
			}catch(Exception e){
				if(answer.indexOf('.')!=-1)
				{
					try{
						double ant=Double.parseDouble(answer);
						if(Math.abs(ant-sum.ParseDouble())<1e-5)
						{
							System.out.println("You are right!");
							right=right+1;
						}else {
							System.out.println("You are wrong!");
						}
					}catch(Exception ex){
						System.out.println("You are wrong!");
					}
				}else{
					System.out.println("You are wrong!");
				}
			}		
			out.print(i+1);
			out.println(". " + str );
			out.println("Your answer:      "+answer);
			out.println("True answer:     "+sum.toString());
		}
		System.out.println(right+" / 25, So your accuracy is "+right/25.0);
		out.println(right+" / 25, So your accuracy is "+right/25.0);
		out.println();
		input.close();
		out.close();
		System.out.println();
	}

	private static void GetQuestion() {
	
		str = "";
		sum.set(0);;
		num_i = (int) (Math.random()*3)+3;
		quesGrow();
	}

	private static void quesGrow() {
		if( num_i > 1 ) {
			int j = num_i;
			num_i--;
			quesGrow();
			
			int ck=(int)(Math.random()*4);
			number w;
			if(ck!=0)
				w=new number(1+(int)(Math.random()*numberRange));
			else w=new number(1+(int)(Math.random()*numberRange),1+(int)(Math.random()*numberRange));
			int t=1+(int)(Math.random()*100);
			int f=1+(int)(Math.random()*100);
			
			if(t>50)
			{
				if( f < 25 ) {
					sum = sum.add(w);
					str = str + "+" + w.toString();
				}
				if( f >= 25 && f < 50 ) {
					sum = sum.sub(w);
					str = str + "-" +w.toString();	
				}
				if( f >= 50 && f < 75 ) {
					if( j < 3 ) {
						sum = sum.mul(w);
						str = str + "*" + w.toString();
					}
					else {
						sum = sum.mul(w);
						str = "(" +str+ ")" + "*" + w.toString();
					}
				}
				if ( f >= 75 ) {
					
					if( j < 3 ) {
						sum = sum.div(w);
						str = str + " / " + w.toString();
					}
					else {
						sum = sum.div(w);
						str = "(" +str+ ")" + " / " + w.toString();
					}
				}
			}
			else
			{
				if( f < 25 ) {
					sum = sum.add(w);
					str = w.toString() + "+" + str;	
				}
				if( f >= 25 && f < 50 ) {
					if( j < 3 ) {
						sum = w.sub(sum);
						str = w.toString() + "-" + str;
					}
					else {
						sum = w.sub(sum);
						str = w.toString() + "-" + "(" +str+ ")";
					}
				}
				if( f >= 50 && f < 75 ) {
					if( j < 3 ) {
						sum = sum.mul(w);
						str = w.toString()+ "*" + str;
					}
					else {
						sum = sum.mul(w);
						str = w.toString() + "*" + "(" +str+ ")";
					}
				}
				if( f >= 75 ) {
					if( j < 3 ) {
						sum = w.div(sum);
						str = w.toString() + " / " + str;
					}
					else {
						sum = w.div(sum);
						str = w.toString()+ " / " + "(" +str+ ")";
					}
				}
			}
		}
		else if( num_i == 1 ) {
			int ck=(int)(Math.random()*4);
			number w;
			if(ck!=0)
				w=new number(1+(int)(Math.random()*numberRange));
			else w=new number(1+(int)(Math.random()*numberRange),1+(int)(Math.random()*numberRange));
			sum = sum.add(w);
			str = str + w.toString();
		}
	}
}