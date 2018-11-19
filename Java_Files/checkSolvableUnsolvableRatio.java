import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class checkSolvableUnsolvableRatio {
	public static void main(String[] args) {
		BufferedReader br = null;
		BufferedReader br2 = null;
		ArrayList<Integer> solvableSeeds = new ArrayList<Integer>();
		ArrayList<Integer> unsolvableSeeds = new ArrayList<Integer>();
		String st;
		int unsolvable = 0;
		int solvable = 0;
		boolean seedState = false;
		
		
		try {
			File file = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//allSolvableSeeds.txt");
			br = new BufferedReader(new FileReader(file));
			File file2 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//sortedUnsolvableSeedsV2.txt");
			br2 = new BufferedReader(new FileReader(file2));
			while ((st = br.readLine()) != null) {
	    		solvableSeeds.add(Integer.parseInt(st));
	    	}			
			while ((st = br2.readLine()) != null) {
				unsolvableSeeds.add(Integer.parseInt(st));
			}
			
			br.close();
			br2.close();
			for(int i = 1100; i<10000; i++) {
				for(int j=0; j<solvableSeeds.size(); j++) {
					if(i == solvableSeeds.get(j).intValue()) {
						seedState = true;
						solvable++;
						break;
					}
				}
				if(!seedState) {
					for(int j=0; j<unsolvableSeeds.size(); j++) {
						if(i == unsolvableSeeds.get(j).intValue()) {
							unsolvable++;
							break;
						}
					}
				}
				seedState = false;
			}
			for(int i = 0; i<1000; i++) {
				for(int j=0; j<solvableSeeds.size(); j++) {
					if(i == solvableSeeds.get(j).intValue()) {
						seedState = true;
						solvable++;
						break;
					}
				}
				if(!seedState) {
					for(int j=0; j<unsolvableSeeds.size(); j++) {
						if(i == unsolvableSeeds.get(j).intValue()) {
							unsolvable++;
							break;
						}
					}
				}
				seedState = false;
			}
			
			System.out.println("Solvable: " + solvable);
			System.out.println("Unsolvable: " + unsolvable);
		} catch(IOException el) {
			el.printStackTrace();
		}
	}
}
