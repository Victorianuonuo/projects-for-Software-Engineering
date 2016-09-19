package calc;

import java.util.Scanner;

/*初级框架，不支持分数*/

public class Question2 {

	public static String str = "";//题目
	public static int num = 4;//每题中数的个数
	public static int num_i = 0;//题目中已有数的个数	
	public static int numberRange = 50;//运算中数的最大取值	
	public static double sum = 0;//结果
	
	public static void main(String[] args) {
		
		System.out.println("Please finish the following 25 questions!");
		
		Scanner input=new Scanner(System.in);
		
		int right=0;

		for (int i = 0; i < 5; i++) {
			
			GetQuestion();
			System.out.print(i+1);
			String kongge="";
			for(int j=0;j<(30-str.length());j++)
				kongge=kongge+" ";
			System.out.print(". " + str +kongge);
			double answer=0.0;
			try{
				answer=input.nextDouble();
			}catch(Exception e){
				answer=sum+100;
			}
			System.out.print("true answer:  "+sum+"    ");
			if(Math.abs(sum-answer)<1e-5)
			{
				System.out.println("You are right!");
				right=right+1;
			}		
			else System.out.println("You are wrong!");
			
		}
		input.close();
		System.out.println(right+" / 5, So your accuracy is "+right/5.0);
	}

	private static void GetQuestion() {
	
		str = "";
		sum = 0;
		num_i = (int) (Math.random()*6)+2;
		quesGrow();
	}

	private static void quesGrow() {
		//
		if( num_i > 1 ) {
			int j = num_i;
			num_i--;
			quesGrow();
			
			int w=1+(int)(Math.random()*numberRange);
			int t=(int)(Math.random()*2);
			int f=(int)(Math.random()*4);
			
			if(t==0)
			{
				if( f == 0 ) {
					sum = sum + w;
					str = str + "+" + String.valueOf( w );
				}
				if( f == 1 ) {
					sum = sum - w;
					str = str + "-" + String.valueOf( w );	
				}
				if( f == 2) {
					if( j < 3 ) {
						sum = sum * w;
						str = str + "*" + String.valueOf( w );
					}
					else {
						sum = sum * w;
						str = "(" +str+ ")" + "*" + String.valueOf( w );
					}
				}
				if ( f == 3 ) {
					w = (int)sum;
					if(w<0)w= -w;
					if( j < 3 ) {
						sum = sum / w;
						str = str + "/" + String.valueOf( w );
					}
					else {
						sum = sum / w;
						str = "(" +str+ ")" + "/" + String.valueOf( w );
					}
				}
			}
			else
			{
				if( f == 0 ) {
					sum = w + sum;
					str = String.valueOf( w ) + "+" + str;	
				}
				if( f == 1 ) {
					if( j < 3 ) {
						sum = w - sum;
						str = String.valueOf( w ) + "-" + str;
					}
					else {
						sum = w - sum;
						str = String.valueOf( w ) + "-" + "(" +str+ ")";
					}
				}
				if( f == 2 ) {
					if( j < 3 ) {
						sum = w * sum;
						str = String.valueOf( w ) + "*" + str;
					}
					else {
						sum = w * sum;
						str = String.valueOf( w ) + "*" + "(" +str+ ")";
					}
				}
				if( f == 3 ) {
					w = (int)(((w%10)+1) * sum);
					if( j < 3 ) {
						sum = w / sum;
						str = String.valueOf( w ) + "/" + str;
					}
					else {
						sum = w / sum;
						str = String.valueOf( w ) + "/" + "(" +str+ ")";
					}
				}
			}
		}
		else if( num_i == 1 ) {
			int w=1+(int)(Math.random()*numberRange);
			sum = sum + w;
			str = str + String.valueOf( w );
		}
	}
}