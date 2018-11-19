import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class specificMovementCSV {
	static String[][] board = new String[4][4];
	
	public static double suitNum(char suit) {
		double suitVal = 0;
		switch (suit) {
			case '-':
				suitVal = 0/4.0;
				break;
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
	public static boolean legalMove(int row1, int col1, int row2, int col2) {
		String suit1 = Character.toString(board[row1][col1].charAt(1));
		String suit2 = Character.toString(board[row2][col2].charAt(1));
		double rank1 = cardNum(board[row1][col1].charAt(0));
		double rank2 = cardNum(board[row2][col2].charAt(0));
		return (((row1 != row2) || (col1 != col2)) // not same cells
				&& ((row1 == row2) || (col1 == col2)) // row or column move
				&& (board[row1][col1] != null && board[row2][col2] != null) // neither stack empty
				&& (suit1.equals(suit2) // same suit ... 
				    || (Math.abs(rank1-rank2) <= 1))); // or same/adjacent rank.
	}
	
	public static void main (String[] args) {
		BufferedReader br = null;
		BufferedReader br2 = null;
		BufferedReader br3 = null;
		BufferedReader br4 = null;
		BufferedReader br5 = null;
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
		int[] rank = {0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30};
		int[] suit = {1,3,5,7,9,11,13,15,17,19,21,23,25,27,29,31};
		String st;
		Random rnd = new Random();
		rnd.setSeed(10);
		boolean marked = false;
		//boolean sameSuit = false;
		//boolean sameRank = false;
		int count = 1;
		//double sizeOfRow = 4.0;
		//double sizeOfColumn = 4.0;
		//double upDown = 0;
		//double leftRight = 0;
		//boolean upDown = false;
		boolean up = false;
		boolean down = false;
		//boolean leftRight = false;
		boolean left = false;
		boolean right = false;
		
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
			
			File file6 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//trainingDataMovement.txt");
			fw = new FileWriter(file6.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			for (int i=0; i < boaf_training.size(); i++) {
				int rowCount = 0;
				int columnCount = 0;
				String[] line = boaf_training.get(i);
				String boardString = line[0];
				for(int k = 0; k < 16; k++) {
					if(columnCount > 3) {
						rowCount++;
						columnCount = 0;
					}
					String cardSuit = Character.toString(boardString.charAt(suit[k]));
					String cardRank = Character.toString(boardString.charAt(rank[k]));
					String card = cardRank + cardSuit;
					board[rowCount][columnCount] = card;
					columnCount++;					
				}
				if(line[1].equals("0")) {
					bw.write("-1 ");
				} else {
					bw.write("+1 ");
				}
				for(int j = 0; j<4; j++) {
					for (int k=0; k<4; k++) {
						if(board[j][k].equals("--")) {
							bw.write("0 ");
						} else {
							for (int l = 0; l<j; l++) {
								if (legalMove(j,k,l,k)) {
									up = true;
									break;
								}
							}
							for (int l=j; l<4; l++) {
								if (legalMove(j,k,l,k)) {
									down = true;
									break;
								}
							}
							for (int l=0; l<k; l++) {
								if (legalMove(j,k,j,l)) {
									left = true;
									break;
								}
							}
							for (int l=k; l<4; l++) {
								if(legalMove(j,k,j,l)) {
									right = true;
									break;
								}
							}
							if ((right || left) && (up || down)) {
								bw.write("1 ");
							} else if (!(right || left) && (up || down)) {
								bw.write("0.66666 ");
							} else if ((right || left) && !(up || down)) {
								bw.write("0.33333 ");
							} else {
								bw.write("0 ");
							}
							up = false;
							down = false;
							left = false;
							right = false;
						}
					}
				}
				bw.newLine();
				//count = 1;
			}
			bw.close();
			fw.close();
			File file7 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//validationDataMovement.txt");
			fw = new FileWriter(file7.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			for (int i=0; i < boaf_validation.size(); i++) {
				int rowCount = 0;
				int columnCount = 0;
				String[] line = boaf_validation.get(i);
				String boardString = line[0];
				for(int k = 0; k < 16; k++) {
					if(columnCount > 3) {
						rowCount++;
						columnCount = 0;
					}
					String cardSuit = Character.toString(boardString.charAt(suit[k]));
					String cardRank = Character.toString(boardString.charAt(rank[k]));
					String card = cardRank + cardSuit;
					board[rowCount][columnCount] = card;
					columnCount++;					
				}
				if(line[1].equals("0")) {
					bw.write("-1 ");
				} else {
					bw.write("+1 ");
				}
				for(int j = 0; j<4; j++) {
					for (int k=0; k<4; k++) {
						if(board[j][k].equals("--")) {
							bw.write("0 ");
						} else {
							for (int l = 0; l<j; l++) {
								if (legalMove(j,k,l,k)) {
									up = true;
									break;
								}
							}
							for (int l=j; l<4; l++) {
								if (legalMove(j,k,l,k)) {
									down = true;
									break;
								}
							}
							for (int l=0; l<k; l++) {
								if (legalMove(j,k,j,l)) {
									left = true;
									break;
								}
							}
							for (int l=k; l<4; l++) {
								if(legalMove(j,k,j,l)) {
									right = true;
									break;
								}
							}
							if ((right || left) && (up || down)) {
								bw.write("1 ");
							} else if (!(right || left) && (up || down)) {
								bw.write("0.66666 ");
							} else if ((right || left) && !(up || down)) {
								bw.write("0.33333 ");
							} else {
								bw.write("0 ");
							}
							up = false;
							down = false;
							left = false;
							right = false;
						}
					}
				}
				bw.newLine();
				//count = 1;
			}
			bw.close();
			fw.close();	File file8 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//testingDataMovement.txt");
			fw = new FileWriter(file8.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			for (int i=0; i < boaf_testing.size(); i++) {
				int rowCount = 0;
				int columnCount = 0;
				String[] line = boaf_testing.get(i);
				String boardString = line[0];
				for(int k = 0; k < 16; k++) {
					if(columnCount > 3) {
						rowCount++;
						columnCount = 0;
					}
					String cardSuit = Character.toString(boardString.charAt(suit[k]));
					String cardRank = Character.toString(boardString.charAt(rank[k]));
					String card = cardRank + cardSuit;
					board[rowCount][columnCount] = card;
					columnCount++;					
				}
				if(line[1].equals("0")) {
					bw.write("-1 ");
				} else {
					bw.write("+1 ");
				}
				for(int j = 0; j<4; j++) {
					for (int k=0; k<4; k++) {
						if(board[j][k].equals("--")) {
							bw.write("0 ");
						} else {
							for (int l = 0; l<j; l++) {
								if (legalMove(j,k,l,k)) {
									up = true;
									break;
								}
							}
							for (int l=j; l<4; l++) {
								if (legalMove(j,k,l,k)) {
									down = true;
									break;
								}
							}
							for (int l=0; l<k; l++) {
								if (legalMove(j,k,j,l)) {
									left = true;
									break;
								}
							}
							for (int l=k; l<4; l++) {
								if(legalMove(j,k,j,l)) {
									right = true;
									break;
								}
							}
							if ((right || left) && (up || down)) {
								bw.write("1 ");
							} else if (!(right || left) && (up || down)) {
								bw.write("0.66666 ");
							} else if ((right || left) && !(up || down)) {
								bw.write("0.33333 ");
							} else {
								bw.write("0 ");
							}
							up = false;
							down = false;
							left = false;
							right = false;
						}
					}
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
