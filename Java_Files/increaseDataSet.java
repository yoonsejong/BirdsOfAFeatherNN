import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class increaseDataSet {
	public static void main (String[] args) {
		BufferedReader br = null;
		BufferedReader br2 = null;
		BufferedWriter bw = null;
		FileWriter fw = null;
		ArrayList<Integer> unsolvableSeeds = new ArrayList<Integer>();
		ArrayList<Integer> solvableSeeds = new ArrayList<Integer>();
		ArrayList<Integer> training = new ArrayList<Integer>();
		ArrayList<Integer> validation = new ArrayList<Integer>();
		ArrayList<Integer> testing = new ArrayList<Integer>();
		String st;
		
		try {
			File file1 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//sortedUnsolvableSeedsV2.txt");
			br = new BufferedReader(new FileReader(file1));
			File file2 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//allSolvableSeeds.txt");
			br2 = new BufferedReader(new FileReader(file2));
			
			while ((st = br.readLine()) != null) {
	    		unsolvableSeeds.add(Integer.parseInt(st));
	    	}
			while ((st = br2.readLine()) != null) {
				solvableSeeds.add(Integer.parseInt(st));
			}

			Collections.shuffle(unsolvableSeeds);
			Collections.shuffle(solvableSeeds);
			
			/*for (int i=0; i< unsolvableSeeds.size(); i++) {
				if (i<931) {
					training.add(unsolvableSeeds.get(i).intValue());
				} else if (931<=i && i<1491) {
					validation.add(unsolvableSeeds.get(i).intValue());
				} else {
					testing.add(unsolvableSeeds.get(i).intValue());
				}
			}
			
			for (int i=0; i< solvableSeeds.size(); i++) {
				if (i<931) {
					training.add(solvableSeeds.get(i).intValue());
				} else if (931<=i && i<1491) {
					validation.add(solvableSeeds.get(i).intValue());
				} else {
					testing.add(solvableSeeds.get(i).intValue());
				}
			}*/
			int notFirstThousand = 0;
			for (int i=0; i<unsolvableSeeds.size(); i++) {
				if(notFirstThousand<931) {
					if((unsolvableSeeds.get(i).intValue()<0) || (unsolvableSeeds.get(i).intValue()>10000)) {
						training.add(unsolvableSeeds.get(i).intValue());
						notFirstThousand++;
					}					
				} else if(notFirstThousand<1491) {
					if((unsolvableSeeds.get(i).intValue()<0) || (unsolvableSeeds.get(i).intValue()>10000)) {
						validation.add(unsolvableSeeds.get(i).intValue());
						notFirstThousand++;
					}
				} else {
					if((unsolvableSeeds.get(i).intValue()<0) || (unsolvableSeeds.get(i).intValue()>10000)) {
						testing.add(unsolvableSeeds.get(i).intValue());
						notFirstThousand++;
					}
				}
			}
			int datasize = training.size()+validation.size()+testing.size();
			int missedSeeds = 0;
			notFirstThousand = 0;
			for (int i=0; i<(solvableSeeds.size()); i++) {
				if(notFirstThousand<931) {
					if((solvableSeeds.get(i).intValue()<0) || (solvableSeeds.get(i).intValue()>10000)) {
						training.add(solvableSeeds.get(i).intValue());
						notFirstThousand++;
					} else {
						missedSeeds += 1;
					}
				} else if(notFirstThousand<1491) {
					if((solvableSeeds.get(i).intValue()<0) || (solvableSeeds.get(i).intValue()>10000)) {
						validation.add(solvableSeeds.get(i).intValue());
						notFirstThousand++;
					} else {
						missedSeeds += 1;
					}
				} else {
					if(i>=(datasize + missedSeeds)) {
						break;
					}
					if((solvableSeeds.get(i).intValue()<0) || (solvableSeeds.get(i).intValue()>10000)) {
						testing.add(solvableSeeds.get(i).intValue());
						notFirstThousand++;
					} else {
						missedSeeds += 1;
					}
				}
			}
			
			Collections.shuffle(training);
			Collections.shuffle(validation);
			Collections.shuffle(testing);
			
			File file3 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//trainingSeeds.txt");
			fw = new FileWriter(file3.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			for(int i=0; i< training.size(); i++) {
				bw.write(Integer.toString(training.get(i)));
				bw.newLine();
			}
			bw.close();
			fw.close();
			
			File file4 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//validationSeeds.txt");
			fw = new FileWriter(file4.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			for(int i=0; i< validation.size(); i++) {
				bw.write(Integer.toString(validation.get(i)));
				bw.newLine();
			}
			bw.close();
			fw.close();
			
			File file5 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//testingSeeds.txt");
			fw = new FileWriter(file5.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			for(int i=0; i< testing.size(); i++) {
				bw.write(Integer.toString(testing.get(i)));
				bw.newLine();
			}
			bw.close();
			fw.close();
			
			training.clear();
			validation.clear();
			testing.clear();
			
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
