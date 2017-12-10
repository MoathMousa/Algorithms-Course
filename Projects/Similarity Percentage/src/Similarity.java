import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Similarity {
	
	static ArrayList<String> patterns = new ArrayList<>();
	static ArrayList pattern = new ArrayList<>();
	static Object[][] textFiles;
	static char[] onceOccurence;
	static char[] rowsChar;
	static int[][] patternTable;
	
	static int numberOfPatterns = 0;
	static int numberOfOccurance = 0;
	static int I=1;
	
	public static void LoadPatternFile(File file) throws FileNotFoundException{
		
		Scanner input = new Scanner (file);
		String pattern = "";
		while(input.hasNextLine()){
			pattern = input.nextLine();
			patterns.add(pattern);
		}
	}
	
	public static void LoadTextFiles(){
		
		int i=0;
		File file = new File("Text" + i + ".txt");
		textFiles[i][0] = file;
		while(file.exists()){
			file = new File("Text" + ++i +".txt");
			textFiles[i][0] = file;
		}
			
	}
	
	public static void BuildPattern(int I){
					
			onceOccurence = ToCharArray(patterns.get(I));
			rowsChar = ToRowChar(patterns.get(I));
			BuilPatternTable();		
	}
	
	public static char[] ToCharArray(String s){
		char[] x = null;
		s.replaceAll("[\\w]", "").toLowerCase();
		String[] split = s.split("");
		String s1;
		String[] s2;
		ArrayList toChar = new ArrayList<>();
		for (int i=0; i<split.length; i++)
			toChar.add(split[i]);
		s1 = (String) toChar.toArray().toString();
		s2 = s1.split("");
		for(int i=0 ; i < s2.length;i++)
			x[i] =  s2[i].charAt(0);
		return x;
		
	}
	
	public static char[] ToRowChar(String s){
		char[] x = null;
		s.replaceAll("[\\w]", "").toLowerCase();
		String[] split = s.split("");
		for(int i=0 ; i < split.length;i++)
			x[i] =  split[i].charAt(0);
		return x;
	}
	
	public static int nextState(int i,int j){
		if(i < rowsChar.length && onceOccurence[j] == rowsChar[i])
			return i + 1;
		int nextState,x;
		for( nextState = i; nextState > 0 ; nextState--){
			if(rowsChar[nextState-1] == onceOccurence[j]){
				for(x = 0 ; x < nextState - 1 ; x++){
					if(rowsChar[x] != rowsChar[i - nextState + 1 +x ])
						break;
				}
				if(x == nextState - 1)
					return nextState;
			}
		}
		return 0;
	}
	
	public static void BuilPatternTable(){
		patternTable = new int[rowsChar.length][onceOccurence.length];
		for(int i= 0; i < rowsChar.length ; i++)
			for(int j=0 ; j < onceOccurence.length; j++)
				patternTable[i][j] = nextState(i,j);
	}
	
	public static boolean Check(char[] text,String pattern ){
		int counter = 0;
		for(int i=0;i <text.length;i++){
			if(text[i] == rowsChar[i])
				counter++;
		}
		if(counter == rowsChar.length)
			return true;
		return false;
	}

	public static void Search() throws FileNotFoundException{
		
		File file = null;
		Scanner input = new Scanner(file);
		String s;
		String[] split;
		char[] x = null;
		boolean result;
		
		for(I = 1 ; I <= pattern.size() ; I++){
			BuildPattern(I);
			file = new File((String) textFiles[I][0]);
			while(input.hasNextLine()){
				s = input.nextLine().replaceAll("[\\w]", "").toLowerCase();
				split = s.split("");
				for(int i=0;i<split.length;i++)
					x[i] = split[i].charAt(0);
				result = Check(x,patterns.get(I));
				if(result == true)
					numberOfOccurance++;
				numberOfPatterns++;
				textFiles[I][1] = numberOfOccurance;
				textFiles[I][2] = numberOfPatterns;
			}
			numberOfOccurance = 0;
			numberOfPatterns = 0;
		}		
	}
	
	public static void CalculateSimilarity(){
		for(int i=0; i< textFiles.length;i++)
			textFiles[i][3] = Integer.parseInt((String) textFiles[i][1]) / Integer.parseInt((String) textFiles[i][2])*100;
	}
	
	public static double CalculateOverSimilarity(){
		
		int sum =0;
		int i=0;
		while(i < textFiles.length){
			sum += Double.parseDouble((String) textFiles[i][3]);
			i++;
		}
		return sum / (i+1);	
	}
}
