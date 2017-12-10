import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CostCalculation {
	
	static int SIZE = 0;	//Costs Array Length, static to use it in all methods
	static int[] prices;	// Costs Array, static to use it in all methods
	static int[] t;			// True Array, static to use it in all methods
	static int[] f;			//False Array, static to use it in all methods
	static String[] ts;		// True Sequence Array, static to use it in all methods
	static String[] fs;		// False Sequence Array, static to use it in all methods

	public static void load(File file) throws FileNotFoundException{
		// This method is to load a file it takes file name using file chooser
		// and do not returns anything 
		Scanner input = new Scanner(file);
		String[] split = null;
		while(input.hasNextLine())
			split = input.nextLine().split(" ");
		SIZE = split.length;
		prices = new int[SIZE + 1];
		t = new int[SIZE + 1];		// to define the actual size
		f = new int[SIZE + 1];		// to define the actual size
		ts = new String[SIZE + 1];	// to define the actual size
		fs = new String[SIZE + 1];	// to define the actual size
		for(int i=0;i<SIZE;i++){
			prices[i+1]=Integer.parseInt(split[i]);
			//copy input string to integer costs array
		}
		prices[0]=0;
		t[0]=0;f[0]=0;ts[0]="";fs[0]="";//initialize arrays
	}
	
	
	public static void calculate() throws FileNotFoundException{
		// to calculate minimum cost and best sequence
		
		for(int i=1;i<=SIZE;i++){//the first index (base case)
			if(i==1){
				t[i]=0;
				f[i]=prices[i];
				ts[i]=0 + " ";
				fs[i]=i + " ";
			}
			else{ // Recursive Case
				
				t[i] = Math.min(t[i-1]+prices[i], f[i-1]);
				f[i] = Math.min(t[i-1]+prices[i], t[i-2]+prices[i-1]);
			
				if(t[i-1]+prices[i] <= f[i-1]) //cases of true sequence
					ts[i] = ts[i-2]+ " " + i + " ";
				else
					ts[i] = fs[i-1] + " " ;
				if((t[i-1]+prices[i]) <= (t[i-2]+prices[i-1])) //cases of false sequence
					fs[i] = ts[i-1]+ " " + i + " ";
				else
					fs[i] = ts[i-2] + " " + (i - 1) + " ";
				}
			}
		report(); //report in a file
	}
	
	
	public static void report() throws FileNotFoundException{
		//to report on the file
		File file = new File("Report.txt");
		PrintWriter output = new PrintWriter(file);
		file=null;
		for(int i=1;i<SIZE+1;i++)
			output.print(prices[i]+ "\t");
		output.println();
		for(int i=1;i<SIZE+1;i++)
			output.print(t[i]+ "\t");
		output.println();
		for(int i=1;i<SIZE+1;i++)
			output.print(f[i]+ "\t");
		output.println();
		for(int i=1;i<SIZE+1;i++)	
			output.print(ts[i]+ "\t");
		output.println();
		for(int i=1;i<SIZE+1;i++)	
			output.print(fs[i]+ "\t");
		output.println();
		
		output.close();
	}
		

}
