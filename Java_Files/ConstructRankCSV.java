import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ConstructRankCSV {
	
	public static double suitNum(char suit) {
		double suitVal = 0;
		switch (suit) {
		case 'C':
			suitVal = 1/4.0;
			break;
		case 'D':
			suitVal = 2/4.0;
			break;
		case 'H':
			suitVal = 3/4.0;
			break;
		case 'S':
			suitVal = 4/4.0;
			break;
		}
		return suitVal;
	}
	
	public static double cardNum(char card) {
		double cardVal = 0;
		switch (card) {
			case '-':
				cardVal = 0;
				break;
			case 'A':
				cardVal = 1;
				break;
			case '2':
				cardVal = 2;
				break;
			case '3':
				cardVal = 3;
				break;
			case '4':
				cardVal = 4;
				break;
			case '5':
				cardVal = 5;
				break;
			case '6':
				cardVal = 6;
				break;
			case '7':
				cardVal = 7;
				break;
			case '8':
				cardVal = 8;
				break;
			case '9':
				cardVal = 9;
				break;
			case 'T':
				cardVal = 10;
				break;
			case 'J':
				cardVal = 11;
				break;
			case 'Q':
				cardVal = 12;
				break;
			case 'K':
				cardVal = 13;
				break;
		}
		return cardVal;	
	}
	
	public static void main (String[] args) {
		BufferedReader br = null;
		BufferedReader br2 = null;
		BufferedReader br3 = null;
		BufferedReader br4 = null;
		BufferedReader br5 = null;
		BufferedReader br6 = null;
		BufferedReader br7 = null;
		BufferedReader br8 = null;
		BufferedWriter bw = null;
		FileWriter fw = null;
		BufferedWriter bw2 = null;
		FileWriter fw2 = null;
		BufferedWriter bw3 = null;
		FileWriter fw3 = null;
		ArrayList<Integer> solvableSeeds = new ArrayList<Integer>();
		ArrayList<Integer> unsolvableSeeds = new ArrayList<Integer>();
		ArrayList<Integer> trainingSeeds = new ArrayList<Integer>();
		ArrayList<Integer> validationSeeds = new ArrayList<Integer>();
		ArrayList<Integer> testingSeeds = new ArrayList<Integer>();
		ArrayList<String[]> boaf_0_to_100_solvable = new ArrayList<String[]>();
		ArrayList<String[]> boaf_0_to_100_unsolvable = new ArrayList<String[]>();
		ArrayList<String[]> boaf_100_to_1000_solvable = new ArrayList<String[]>();
		ArrayList<String[]> boaf_100_to_1000_unsolvable = new ArrayList<String[]>();
		ArrayList<String[]> boaf_1100_to_10000_solvable = new ArrayList<String[]>();
		ArrayList<String[]> boaf_1100_to_10000_unsolvable = new ArrayList<String[]>();
		ArrayList<String[]> boaf_training = new ArrayList<String[]>();
		ArrayList<String[]> boaf_validation = new ArrayList<String[]>();
		ArrayList<String[]> boaf_testing = new ArrayList<String[]>();
		int[] rank1 = {0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30};
		int[] suit = {1,4,7,10,14,17,20,23,27,30,33,36,40,43,46,49};
		String st;
		boolean marked = false;
		boolean sameSuit = false;
		boolean sameRank = false;
		Random rnd = new Random();
		rnd.setSeed(10);
		//int count = 1;
		double sizeOfRow = 4.0;
		double sizeOfColumn = 4.0;
		
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
			
			File file3 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//boaf-data-0-100.csv");
			br3 = new BufferedReader(new FileReader(file3));
			File file4 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//boaf-data-100-1000.csv");
			br4 = new BufferedReader(new FileReader(file4));
			File file5 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//boaf-data-1100-10000.csv");
			br5 = new BufferedReader(new FileReader(file5));
			
			while ((st = br3.readLine()) != null) {
				String[] board_and_state = st.split(",");
				board_and_state[0] = board_and_state[0].replaceAll("\"", "");
				if(board_and_state[1].equals("1")) {
					boaf_0_to_100_solvable.add(board_and_state);
				} else {
					boaf_0_to_100_unsolvable.add(board_and_state);
				}
			}
			while ((st = br4.readLine()) != null) {
				String[] board_and_state = st.split(",");
				board_and_state[0] = board_and_state[0].replaceAll("\"", "");
				if(board_and_state[1].equals("1")) {
					boaf_100_to_1000_solvable.add(board_and_state);
				} else {
					boaf_100_to_1000_unsolvable.add(board_and_state);
				}
			}
			while ((st = br5.readLine()) != null) {
				String[] board_and_state = st.split(",");
				board_and_state[0] = board_and_state[0].replaceAll("\"", "");
				if(board_and_state[1].equals("1")) {
					boaf_1100_to_10000_solvable.add(board_and_state);
				} else {
					boaf_1100_to_10000_unsolvable.add(board_and_state);
				}
			}
			
			br3.close();
			br4.close();
			br5.close();
			
			File file9 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//trainingSeeds.txt");
			br3 = new BufferedReader(new FileReader(file9));
			File file10 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//validationSeeds.txt");
			br4 = new BufferedReader(new FileReader(file10));
			File file11 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//testingSeeds.txt");
			br5 = new BufferedReader(new FileReader(file11));
			
			while ((st = br3.readLine()) != null) {
				trainingSeeds.add(Integer.parseInt(st));
			}
			while ((st = br4.readLine()) != null) {
				validationSeeds.add(Integer.parseInt(st));
			}
			while ((st = br5.readLine()) != null) {
				testingSeeds.add(Integer.parseInt(st));
			}
			
			br3.close();
			br4.close();
			br5.close();
			
			for (int i=0; i < trainingSeeds.size(); i++) {
				for(int j=0; j< unsolvableSeeds.size(); j++) {
					if(trainingSeeds.get(i).intValue() == unsolvableSeeds.get(j).intValue()) {
						BirdsOfAFeatherNode node = new BirdsOfAFeatherNode(trainingSeeds.get(i).intValue());
						String board = node.toString();
						board = board.replaceAll(" ", "");
						board = board.replaceAll("\n", "");
						String[] boardToString = {board, "0"};
						boaf_training.add(boardToString);
						//count = 1;
						marked = true;
						break;
					}
				}
				if (marked == false) {
					for(int j=0; j< solvableSeeds.size(); j++) {
						if(trainingSeeds.get(i).intValue() == solvableSeeds.get(j).intValue()) {
							BirdsOfAFeatherNode node = new BirdsOfAFeatherNode(trainingSeeds.get(i).intValue());
							String board = node.toString();
							board = board.replaceAll(" ", "");
							board = board.replaceAll("\n", "");
							String[] boardToString = {board, "1"};
							boaf_training.add(boardToString);
							//count = 1;
							break;
						}
					}
				}
				marked = false;
			}
			
			for (int i=0; i < validationSeeds.size(); i++) {
				for(int j=0; j< unsolvableSeeds.size(); j++) {
					if(validationSeeds.get(i).intValue() == unsolvableSeeds.get(j).intValue()) {
						BirdsOfAFeatherNode node = new BirdsOfAFeatherNode(validationSeeds.get(i).intValue());
						String board = node.toString();
						board = board.replaceAll(" ", "");
						board = board.replaceAll("\n", "");
						String[] boardToString = {board, "0"};
						boaf_validation.add(boardToString);
						//count = 1;
						marked = true;
						break;
					}
				}
				if (marked == false) {
					for(int j=0; j< solvableSeeds.size(); j++) {
						if(validationSeeds.get(i).intValue() == solvableSeeds.get(j).intValue()) {
							BirdsOfAFeatherNode node = new BirdsOfAFeatherNode(validationSeeds.get(i).intValue());
							String board = node.toString();
							board = board.replaceAll(" ", "");
							board = board.replaceAll("\n", "");
							String[] boardToString = {board, "1"};
							boaf_validation.add(boardToString);
							//count = 1;
							break;
						}
					}
				}
				marked = false;
			}
			
			for (int i=0; i < testingSeeds.size(); i++) {
				for(int j=0; j< unsolvableSeeds.size(); j++) {
					if(testingSeeds.get(i).intValue() == unsolvableSeeds.get(j).intValue()) {
						BirdsOfAFeatherNode node = new BirdsOfAFeatherNode(testingSeeds.get(i).intValue());
						String board = node.toString();
						board = board.replaceAll(" ", "");
						board = board.replaceAll("\n", "");
						String[] boardToString = {board, "0"};
						boaf_testing.add(boardToString);
						//count = 1;
						marked = true;
						break;
					}
				}
				if (marked == false) {
					for(int j=0; j< solvableSeeds.size(); j++) {
						if(testingSeeds.get(i).intValue() == solvableSeeds.get(j).intValue()) {
							BirdsOfAFeatherNode node = new BirdsOfAFeatherNode(testingSeeds.get(i).intValue());
							String board = node.toString();
							board = board.replaceAll(" ", "");
							board = board.replaceAll("\n", "");
							String[] boardToString = {board, "1"};
							boaf_testing.add(boardToString);
							//count = 1;
							break;
						}
					}
				}
				marked = false;
			}
			
			Collections.shuffle(boaf_0_to_100_unsolvable, rnd);
			Collections.shuffle(boaf_0_to_100_solvable, rnd);
			Collections.shuffle(boaf_100_to_1000_unsolvable, rnd);
			Collections.shuffle(boaf_100_to_1000_solvable, rnd);
			Collections.shuffle(boaf_1100_to_10000_unsolvable, rnd);
			Collections.shuffle(boaf_1100_to_10000_solvable, rnd);
			
			//split data from csv file and randomize			
			for (int i=0; i< (int)(boaf_0_to_100_unsolvable.size()*0.5); i++) {
				boaf_training.add(boaf_0_to_100_unsolvable.get(i));
			}
			for (int i=0; i< (int)(boaf_0_to_100_solvable.size()*0.5); i++) {
				boaf_training.add(boaf_0_to_100_solvable.get(i));
			}
			for (int i=(int)(boaf_0_to_100_unsolvable.size()*0.5); i< (int)(boaf_0_to_100_unsolvable.size()*0.8); i++) {
				boaf_validation.add(boaf_0_to_100_unsolvable.get(i));
			}
			for (int i=(int)(boaf_0_to_100_unsolvable.size()*0.5); i< (int)(boaf_0_to_100_solvable.size()*0.8); i++) {
				boaf_validation.add(boaf_0_to_100_solvable.get(i));
			}
			for (int i=(int)(boaf_0_to_100_solvable.size()*0.8); i< (int)(boaf_0_to_100_unsolvable.size()); i++) {
				boaf_testing.add(boaf_0_to_100_unsolvable.get(i));
			}
			for (int i=(int)(boaf_0_to_100_solvable.size()*0.8); i< (int)(boaf_0_to_100_solvable.size()); i++) {
				boaf_testing.add(boaf_0_to_100_solvable.get(i));
			}
			
			for (int i=0; i< (int)(boaf_100_to_1000_unsolvable.size()*0.5); i++) {
				boaf_training.add(boaf_100_to_1000_unsolvable.get(i));
			}
			for (int i=0; i< (int)(boaf_100_to_1000_solvable.size()*0.5); i++) {
				boaf_training.add(boaf_100_to_1000_solvable.get(i));
			}
			for (int i=(int)(boaf_100_to_1000_unsolvable.size()*0.5); i< (int)(boaf_100_to_1000_unsolvable.size()*0.8); i++) {
				boaf_validation.add(boaf_100_to_1000_unsolvable.get(i));
			}
			for (int i=(int)(boaf_100_to_1000_unsolvable.size()*0.5); i< (int)(boaf_100_to_1000_solvable.size()*0.8); i++) {
				boaf_validation.add(boaf_100_to_1000_solvable.get(i));
			}
			for (int i=(int)(boaf_100_to_1000_solvable.size()*0.8); i< (int)(boaf_100_to_1000_unsolvable.size()); i++) {
				boaf_testing.add(boaf_100_to_1000_unsolvable.get(i));
			}
			for (int i=(int)(boaf_100_to_1000_solvable.size()*0.8); i< (int)(boaf_100_to_1000_solvable.size()); i++) {
				boaf_testing.add(boaf_100_to_1000_solvable.get(i));
			}
			
			for (int i=0; i< (int)(boaf_1100_to_10000_unsolvable.size()*0.5); i++) {
				boaf_training.add(boaf_1100_to_10000_unsolvable.get(i));
			}
			for (int i=0; i< (int)(boaf_1100_to_10000_solvable.size()*0.5); i++) {
				boaf_training.add(boaf_1100_to_10000_solvable.get(i));
			}
			for (int i=(int)(boaf_1100_to_10000_unsolvable.size()*0.5); i< (int)(boaf_1100_to_10000_unsolvable.size()*0.8); i++) {
				boaf_validation.add(boaf_1100_to_10000_unsolvable.get(i));
			}
			for (int i=(int)(boaf_1100_to_10000_unsolvable.size()*0.5); i< (int)(boaf_1100_to_10000_solvable.size()*0.8); i++) {
				boaf_validation.add(boaf_1100_to_10000_solvable.get(i));
			}
			for (int i=(int)(boaf_1100_to_10000_solvable.size()*0.8); i< (int)(boaf_1100_to_10000_unsolvable.size()); i++) {
				boaf_testing.add(boaf_1100_to_10000_unsolvable.get(i));
			}
			for (int i=(int)(boaf_1100_to_10000_solvable.size()*0.8); i< (int)(boaf_1100_to_10000_solvable.size()); i++) {
				boaf_testing.add(boaf_1100_to_10000_solvable.get(i));
			}
			
			Collections.shuffle(boaf_training, rnd);
			Collections.shuffle(boaf_validation, rnd);
			Collections.shuffle(boaf_testing, rnd);
			
			File file6 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//trainingDataRank.txt");
			fw = new FileWriter(file6.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			for (int i=0; i < boaf_training.size(); i++) {
				double rowCount = 1.0;
				double columnCount = 1.0;
				String[] line = boaf_training.get(i);
				String board = line[0];
				if(line[1].equals("0")) {
					bw.write("-1 ");
				} else {
					bw.write("+1 ");
				}
				for(int k = 0; k < 16; k++) {
					double rankVal = cardNum(board.charAt(rank1[k]));
					bw.write(rankVal/13.0 + " ");
				}
				bw.newLine();
				//count = 1;
			}
			bw.close();
			fw.close();
			
			File file7 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//validationDataRank.txt");
			fw = new FileWriter(file7.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			for (int i= 0; i < boaf_validation.size(); i++) {
				double rowCount = 1.0;
				double columnCount = 1.0;
				String[] line = boaf_validation.get(i);
				String board = line[0];
				if(line[1].equals("0")) {
					bw.write("-1 ");
				} else {
					bw.write("+1 ");
				}
				for(int k = 0; k < 16; k++) {
					double rankVal = cardNum(board.charAt(rank1[k]));
					bw.write(rankVal/13.0 + " ");
				}
				bw.newLine();
				//count = 1;
			}
			bw.close();
			fw.close();
			
			File file8 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//testingDataRank.txt");
			fw = new FileWriter(file8.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			for (int i= 0; i < boaf_testing.size(); i++) {
				double rowCount = 1.0;
				double columnCount = 1.0;
				String[] line = boaf_testing.get(i);
				String board = line[0];
				if(line[1].equals("0")) {
					bw.write("-1 ");
				} else {
					bw.write("+1 ");
				}
				for(int k = 0; k < 16; k++) {
					double rankVal = cardNum(board.charAt(rank1[k]));
					bw.write(rankVal/13.0 + " ");
				}
				bw.newLine();
				//count = 1;
			}
			bw.close();
			fw.close();		
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
    			if (br2 != null) {
    				br2.close();
    			}
    			if (br3 != null) {
    				br3.close();
    			}
    			if (br4 != null) {
    				br4.close();
    			}
    			if (br5 != null) {
    				br5.close();
    			}
    			if (bw2 != null) {
    				bw2.close();
    			}
    			if (fw2 != null) {
    				fw2.close();
    			}
    			if (bw3 != null) {
    				bw3.close();
    			}
    			if (fw3 != null) {
    				fw3.close();
    			}
    		} catch (IOException ex) {
    			ex.printStackTrace();
    		}
    	}
	}
}
