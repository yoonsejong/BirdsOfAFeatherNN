import java.io.*;

public class DepthFirstSearchWithSeed {
	static int seedCheck;
	
	public static void setSeed(int seed) {
		seedCheck = seed;
	}
	
	public static int getSeed() {
		return seedCheck;
	}

	public static void main(String[] args) {
		int input = Integer.parseInt(args[0]);
		System.out.println(input);
		setSeed(input);
    	Searcher searcher = new DepthFirstSearcherNoRepeat();
    	File file2 = new File(".//results//unsolvableGreaterThanSixty.txt");
    	File file = new File(".//results//listOfUnsolvable.txt");
    	FileWriter fw = null;
    	BufferedWriter bw = null;
    	FileWriter fw2 = null;
    	BufferedWriter bw2 = null;
    	try{
	    	fw = new FileWriter(file.getAbsoluteFile(), true);
	    	bw = new BufferedWriter(fw); 
	    	fw2 = new FileWriter(file2.getAbsoluteFile(), true);
	    	bw2 = new BufferedWriter(fw2);
	    		//System.out.print("Seed " + seed + ": ");
    		BirdsOfAFeatherNode node = new BirdsOfAFeatherNode(getSeed());
    		searcher = new DepthFirstSearcherNoRepeat();
    		boolean solvable = node.hasOddBird() ? false : searcher.search(node);
    		if (solvable) {
    			//System.out.println("solved.");
    			bw2.write("Seed: ");
    			bw2.write(Integer.toString(getSeed()) + " solved.");
    			bw2.newLine();
    			bw2.write("Solution path: " + ((BirdsOfAFeatherNode) searcher.goalNode).solutionString());
    			bw2.newLine();
    		} else {
    			bw2.write("Seed: ");
    			bw2.write(Integer.toString(getSeed()) + " unsolvable.");
    			bw2.newLine();
    			bw.write("Seed: " + Integer.toString(getSeed()));
    			bw.newLine();
    		}
	    	
	    	//System.out.printf("Seeds %d-%d: %d solved, %d not solvable\n", startSeed, startSeed + numSeeds, numSolved+1, numSeeds - numSolved);
//	    	System.out.println("          Unsolvable: " + unsolvable);
    	} catch(IOException el) {
    		el.printStackTrace();
    	} finally {
    		try {
    			if (bw2 != null) {
    				bw2.close();
    			}
    			if (fw2 != null) {
    				fw2.close();
    			}
    			if (bw != null) {
    				bw.close();
    			}
    			if (fw != null) {
    				fw.close();
    			}
    		} catch (IOException ex) {
    			ex.printStackTrace();
    		}
    	}
    }


}
