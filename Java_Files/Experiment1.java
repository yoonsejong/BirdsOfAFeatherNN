import java.util.ArrayList;
import java.util.Calendar;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Experiment1 {

	public static void main(String[] args) {
    	int startSeed = -500000;
    	int numSeeds = 1000000;
    	int numSolved = 0;
    	Searcher searcher = new DepthFirstSearcherNoRepeat();
    	ArrayList<Integer> unsolvable = new ArrayList<Integer>();
    	ArrayList<Integer> longerTwo = new ArrayList<Integer>();
    	ArrayList<Integer> longerTen = new ArrayList<Integer>();
    	File file = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//longerThanFiftyFive.txt");
    	File file2 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//betweenFiftyFiveAndSixty.txt");
    	File file3 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//longerThanSixty.txt");
    	BufferedReader br = null;
    	BufferedReader br2 = null;
    	String st;
    	String st2;
    	int index = 0;
    	FileWriter fw = null;
    	BufferedWriter bw = null;
    	FileWriter fw2 = null;
    	BufferedWriter bw2 = null;
    	try{
	    	fw = new FileWriter(file3.getAbsoluteFile(), true);
	    	bw = new BufferedWriter(fw);
	    	fw2 = new FileWriter(file2.getAbsoluteFile(), true);
	    	bw2 = new BufferedWriter(fw2);
	    	br = new BufferedReader(new FileReader(file));
	    	while ((st = br.readLine()) != null) {
	    		longerTwo.add(Integer.parseInt(st));
	    	}
	    	for (int i = 0; i < longerTwo.size(); i++) {
	    		//System.out.print("Seed " + seed + ": ");
	    		BirdsOfAFeatherNode node = new BirdsOfAFeatherNode(longerTwo.get(i));
	    		searcher = new DepthFirstSearcherNoRepeat();
	    		boolean solvable = node.hasOddBird() ? false : searcher.search(node);
	    		if (solvable) {
	    			//System.out.println("solved.");
	    			bw2.write(Integer.toString(longerTwo.get(i)));
	    			bw2.newLine();
	    			numSolved++;
	    		}
	    		else
	    		{
//	    			unsolvable.add(longerTwo.get(i));
	    			bw.write(Integer.toString(longerTwo.get(i)));
	    			bw.newLine();
//	    			System.out.println("unsolvable seed " + longerTwo.get(i) + ".");
	    			//System.out.println(node);
	    		}
	    		
	    		if (i % 10 == 0) {
	    			System.out.println("progress: " + i + " / " + longerTwo.size());
	    			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	    			Calendar calobj = Calendar.getInstance();
	    			System.out.println(df.format(calobj.getTime()));
	    		}
	    	}
	    	//System.out.printf("Seeds %d-%d: %d solved, %d not solvable\n", startSeed, startSeed + numSeeds, numSolved+1, numSeeds - numSolved);
//	    	System.out.println("          Unsolvable: " + unsolvable);
    	} catch(IOException el) {
    		el.printStackTrace();
    	} finally {
    		try {
    			if (br != null) {
    				br.close();
    			}
    			if (bw != null) {
    				bw.close();
    			}
    			if (fw != null) {
    				fw.close();
    			}
    			if (bw2 != null) {
    				bw2.close();
    			}
    			if (fw2 != null) {
    				fw2.close();
    			}
    		} catch (IOException ex) {
    			ex.printStackTrace();
    		}
    	}
    }


}
