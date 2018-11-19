import java.util.ArrayList;
import java.io.*;

public class MakeScript {

	public static void main(String[] args) {
    	int x = 1;
    	ArrayList<Integer> longerTwo = new ArrayList<Integer>();
    	File file = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//longerThanSixty.txt");
    	File file2 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//scripts//seed1.sh");
    	File file3 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//scripts//seedScript1.sh");
    	BufferedReader br = null;
    	String st;
    	int index = 1;
    	FileWriter fw = null;
    	BufferedWriter bw = null;
    	FileWriter fw2 = null;
    	BufferedWriter bw2 = null;
    	try{
    		fw = new FileWriter(file3.getAbsoluteFile(), true);
	    	bw = new BufferedWriter(fw);
	    	bw.write("#!/bin/bash");
    		bw.newLine();
    		bw.newLine();
    		bw.write("#SBATCH --workdir=/home/hpc/sangb1/BirdsOfAFeather/");
    		bw.newLine();
    		bw.write("#SBATCH --job-name=seed_batch_job1");
    		bw.newLine();
    		bw.write("#SBATCH --output=job.%j.out");
    		bw.newLine();
    		bw.write("#SBATCH --partition=nolimit");
    		bw.newLine();
    		bw.write("#SBATCH --time=10-12:00:00");
    		bw.newLine();
    		bw.newLine();
    		fw2 = new FileWriter(file2.getAbsoluteFile(), true);
	    	bw2 = new BufferedWriter(fw2);
	    	br = new BufferedReader(new FileReader(file));
	    	while ((st = br.readLine()) != null) {
	    		longerTwo.add(Integer.parseInt(st));
	    	}
	    	for (int i = 0; i < longerTwo.size(); i++) {
	    		if (i%10 == 0) {
	    			bw2.close();
	    			fw2.close();
	    			file2 = new File("/home/benjaminsang/eclipse-workspace/BirdsOfAFeather/scripts/seed"+x+".sh");
	    			fw2 = new FileWriter(file2.getAbsoluteFile(), true);
	    	    	bw2 = new BufferedWriter(fw2);
	    			bw.write("sbatch seed"+x+".sh");
	    			bw.newLine();
	    			bw.write("sleep 3");
	    			bw.newLine();
		    		if(x%10==0) {
		    			index++;
		    			bw.close();
		    			fw.close();
		    			file3 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//scripts//seedScript"+index+".sh");
		    			fw = new FileWriter(file3.getAbsoluteFile(), true);
		    	    	bw = new BufferedWriter(fw);
		    	    	bw.write("#!/bin/bash");
		        		bw.newLine();
		        		bw.newLine();
		        		bw.write("#SBATCH --workdir=/home/hpc/sangb1/BirdsOfAFeather/");
		        		bw.newLine();
		        		bw.write("#SBATCH --job-name=seed_batch_job"+index);
		        		bw.newLine();
		        		bw.write("#SBATCH --output=job.%j.out");
		        		bw.newLine();
		        		bw.write("#SBATCH --partition=nolimit");
		        		bw.newLine();
		        		bw.write("#SBATCH --time=10-12:00:00");
		        		bw.newLine();
		        		bw.newLine();
		    		}
	    			x++;
		    		bw2.write("#!/bin/bash");
		    		bw2.newLine();
		    		bw2.newLine();
		    		bw2.write("#SBATCH --workdir=/home/hpc/sangb1/BirdsOfAFeather/");
		    		bw2.newLine();
		    		bw2.write("#SBATCH --job-name=seed_job_"+(x-1));
		    		bw2.newLine();
		    		bw2.write("#SBATCH --output=job.%j.out");
		    		bw2.newLine();
		    		bw2.write("#SBATCH --partition=nolimit");
		    		bw2.newLine();
		    		bw2.write("#SBATCH --time=10-12:00:00");
		    		bw2.newLine();
		    		bw2.newLine();
		    		bw2.write("module add jdk");
		    		bw2.newLine();
		    		bw2.newLine();
		    		bw2.write("seed=" + longerTwo.get(i));
		    		bw2.newLine();
		    		bw2.newLine();
		    		bw2.write("java DepthFirstSearchWithSeed $seed -Xms512M -Xmx8192M");
		    		bw2.newLine();
		    		bw2.newLine();
	    		} else {
	    			bw2.write("seed=" + longerTwo.get(i));
		    		bw2.newLine();
		    		bw2.newLine();
		    		bw2.write("java DepthFirstSearchWithSeed $seed -Xms512M -Xmx8192M");
		    		bw2.newLine();
		    		bw2.newLine();
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
