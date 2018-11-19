import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SortSolvableUnsolvable {
	public static void main (String[] args) {
		BufferedReader br = null;
		BufferedReader br2 = null;
		BufferedWriter bw = null;
		FileWriter fw = null;
		int startSeed = -500000;
    	int numSeeds = 1000000;
    	int index = 0;
		ArrayList<Integer> sortedList = new ArrayList<Integer>();
		String st;
		try {
			File file1 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//sortedUnsolvableSeedsV2.txt");
			br = new BufferedReader(new FileReader(file1));
			
			while ((st = br.readLine()) != null) {
	    		sortedList.add(Integer.parseInt(st));
	    	}

			
			File file2 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//allSolvableSeeds.txt");
			fw = new FileWriter(file2.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			
			for (int i = startSeed; i < startSeed+numSeeds; i++) {
				if(index < sortedList.size()) {
					if (i == sortedList.get(index).intValue()) {
						index++;
						continue;
					} else {
						bw.write(Integer.toString(i));
						bw.newLine();
					}
				} else {
					bw.write(Integer.toString(i));
					bw.newLine();
				}
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
