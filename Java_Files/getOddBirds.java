import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class getOddBirds {
	public static void main(String[] args) {
		BufferedReader br = null;
		BufferedWriter bw = null;
		FileWriter fw = null;
		String st;
		ArrayList<Integer> unsolvableSeeds = new ArrayList<Integer>();
		ArrayList<Integer> trainingSeeds = new ArrayList<Integer>();
		ArrayList<Integer> validationSeeds = new ArrayList<Integer>();
		ArrayList<Integer> testingSeeds = new ArrayList<Integer>();
		int withOddBirds = 0;
		try {
			File file1 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//sortedUnsolvableSeedsV2.txt");
			br = new BufferedReader(new FileReader(file1));
			while ((st = br.readLine()) != null) {
	    		unsolvableSeeds.add(Integer.parseInt(st));
	    	}
			
			br.close();
			
			File file2 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//trainingSeeds.txt");
			br = new BufferedReader(new FileReader(file2));
			while ((st = br.readLine()) != null) {
				int seed = Integer.parseInt(st);
				for(int i=0; i<unsolvableSeeds.size(); i++) {
					if(unsolvableSeeds.get(i).intValue() == seed) {
						trainingSeeds.add(seed);
						break;
					}
				}
			}
			br.close();
			
			File file3 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//validationSeeds.txt");
			br = new BufferedReader(new FileReader(file3));
			while ((st = br.readLine()) != null) {
				int seed = Integer.parseInt(st);
				for(int i=0; i<unsolvableSeeds.size(); i++) {
					if(unsolvableSeeds.get(i).intValue() == seed) {
						validationSeeds.add(seed);
						break;
					}
				}
			}
			br.close();
			
			File file4 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//testingSeeds.txt");
			br = new BufferedReader(new FileReader(file4));
			while ((st = br.readLine()) != null) {
				int seed = Integer.parseInt(st);
				for(int i=0; i<unsolvableSeeds.size(); i++) {
					if(unsolvableSeeds.get(i).intValue() == seed) {
						testingSeeds.add(seed);
						break;
					}
				}
			}
			br.close();
			
			File file5 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//oddBirdsCount.txt");
			fw = new FileWriter(file5);
			bw = new BufferedWriter(fw);
			
			for(int i=0; i<trainingSeeds.size(); i++) {
				BirdsOfAFeatherNode node = new BirdsOfAFeatherNode(trainingSeeds.get(i).intValue());
				if (node.hasOddBird()) {
					withOddBirds++;
					int oddCount = node.oddBirdCount();
					System.out.println("Seed " + trainingSeeds.get(i) + " has " + oddCount + " odd birds");
					//bw.write("Seed " + unsolvableSeeds.get(i) + " has " + oddCount + " odd birds");
				} else {
					System.out.println("Seed " + trainingSeeds.get(i) + " does not have an odd bird");
					//bw.write("Seed " + unsolvableSeeds.get(i) + " does not have an odd bird");
				}
				bw.newLine();
			}
			System.out.println("There are " + withOddBirds + " seeds with odd birds out of " + trainingSeeds.size() + " training seeds");
			withOddBirds = 0;
			
			for(int i=0; i<validationSeeds.size(); i++) {
				BirdsOfAFeatherNode node = new BirdsOfAFeatherNode(validationSeeds.get(i).intValue());
				if (node.hasOddBird()) {
					withOddBirds++;
					int oddCount = node.oddBirdCount();
					System.out.println("Seed " + validationSeeds.get(i) + " has " + oddCount + " odd birds");
					//bw.write("Seed " + unsolvableSeeds.get(i) + " has " + oddCount + " odd birds");
				} else {
					System.out.println("Seed " + validationSeeds.get(i) + " does not have an odd bird");
					//bw.write("Seed " + unsolvableSeeds.get(i) + " does not have an odd bird");
				}
				bw.newLine();
			}
			System.out.println("There are " + withOddBirds + " seeds with odd birds out of " + validationSeeds.size() + " validation seeds");
			withOddBirds = 0;
			
			for(int i=0; i<testingSeeds.size(); i++) {
				BirdsOfAFeatherNode node = new BirdsOfAFeatherNode(testingSeeds.get(i).intValue());
				if (node.hasOddBird()) {
					withOddBirds++;
					int oddCount = node.oddBirdCount();
					System.out.println("Seed " + testingSeeds.get(i) + " has " + oddCount + " odd birds");
					//bw.write("Seed " + unsolvableSeeds.get(i) + " has " + oddCount + " odd birds");
				} else {
					System.out.println("Seed " + testingSeeds.get(i) + " does not have an odd bird");
					//bw.write("Seed " + unsolvableSeeds.get(i) + " does not have an odd bird");
				}
				bw.newLine();
			}
			System.out.println("There are " + withOddBirds + " seeds with odd birds out of " + testingSeeds.size() + " testing seeds");
			withOddBirds = 0;
			
			bw.close();
			fw.close();
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
