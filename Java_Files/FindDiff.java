import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FindDiff {
	public static void main (String args[]) {
		File file = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//longerThanTen.txt");
    	File file2 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//betweenTenAndFifteenCopy.txt");
    	File file3 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//longerThanFifteen.txt");
    	File file4 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//greaterThanFifteen.txt");

    	
    	ArrayList<Integer> longerTwo = new ArrayList<Integer>();
    	ArrayList<Integer> longerTen = new ArrayList<Integer>();
    	
    	BufferedReader br = null;
    	BufferedReader br2 = null;
    	String st;
    	String st2;
    	int index = 0;
    	int count = 0;
    	FileWriter fw = null;
    	BufferedWriter bw = null;
    	FileWriter fw2 = null;
    	BufferedWriter bw2 = null;
    	
    	try{
	    	fw = new FileWriter(file2.getAbsoluteFile(), true);
	    	bw = new BufferedWriter(fw);
	    	fw2 = new FileWriter(file4.getAbsoluteFile(), true);
	    	bw2 = new BufferedWriter(fw2);
	    	br = new BufferedReader(new FileReader(file));
	    	while ((st = br.readLine()) != null) {
	    		longerTwo.add(Integer.parseInt(st));
	    	}
	    	br2 = new BufferedReader(new FileReader(file3));
	    	while ((st2 = br2.readLine()) != null) {
	    		longerTen.add(Integer.parseInt(st2));
	    	}
	    	for (int i = 0; i < longerTwo.size(); i++) {
	    		//System.out.print("Seed " + seed + ": ");
	    		if((index < longerTen.size())) {
	    			if (longerTwo.get(i).intValue() == longerTen.get(index).intValue() ) {
	    				index++;
	    				count++;
	    				bw2.write(Integer.toString(longerTwo.get(i)));
		    			bw2.newLine();
	    				continue;
	    			}
	    		} 
	    		
	    		bw.write(Integer.toString(longerTwo.get(i)));
	    		bw.newLine();
	    	}
	    	System.out.println(count);;
    	} catch(IOException el) {
    		el.printStackTrace();
    	} finally {
    		try {
    			if (br != null) {
    				br.close();
    			}
    			if (br2 != null) {
    				br2.close();
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
