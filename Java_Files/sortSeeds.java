import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class sortSeeds {
	public static void main (String[] args) {
		BufferedReader br = null;
		BufferedReader br2 = null;
		BufferedWriter bw = null;
		FileWriter fw = null;
		ArrayList<Integer> sortedList = new ArrayList<Integer>();
		String st;
		try {
			File file1 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//unsolvableSeeds.txt");
			br = new BufferedReader(new FileReader(file1));
			File file2 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//listOfUnsolvable.txt");
			br2 = new BufferedReader(new FileReader(file2));
			
			while ((st = br.readLine()) != null) {
	    		sortedList.add(Integer.parseInt(st));
	    	}
			
			while ((st = br2.readLine()) != null) {
				sortedList.add(Integer.parseInt(st));
			}
			
			Collections.sort(sortedList);
			
			File file3 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//sortedUnsolvableSeeds.txt");
			fw = new FileWriter(file3.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			for (int i=0; i<sortedList.size(); i++) {
				bw.write(Integer.toString(sortedList.get(i)));
				bw.newLine();
			}
			
		}  catch(IOException el) {
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
    			if (br2 != null) {
    				br2.close();
    			}
    		} catch (IOException ex) {
    			ex.printStackTrace();
    		}
    	}
	}
}
