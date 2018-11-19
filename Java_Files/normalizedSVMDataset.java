import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class normalizedSVMDataset {
	
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
		int[] rank = {0,3,6,9,13,16,19,22,26,29,32,35,39,42,45,48};
		int[] suit = {1,4,7,10,14,17,20,23,27,30,33,36,40,43,46,49};
		String st;
		boolean marked = false;
		boolean sameSuit = false;
		boolean sameRank = false;
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
			
			File file3 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//trainingSeeds.txt");
			br3 = new BufferedReader(new FileReader(file3));
			File file4 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//validationSeeds.txt");
			br4 = new BufferedReader(new FileReader(file4));
			File file5 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//testingSeeds.txt");
			br5 = new BufferedReader(new FileReader(file5));
			
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
			
			File file6 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//trainingData.txt");
			fw = new FileWriter(file6.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			for (int i=0; i < trainingSeeds.size(); i++) {
				double rowCount = 1.0;
				double columnCount = 1.0;
				for(int j=0; j< unsolvableSeeds.size(); j++) {
					if(trainingSeeds.get(i).intValue() == unsolvableSeeds.get(j).intValue()) {
						BirdsOfAFeatherNode node = new BirdsOfAFeatherNode(i);
						String board = node.toString();
						bw.write("-1 ");
						for(int k = 0; k < 16; k++) {
							double suitVal = suitNum(board.charAt(suit[k]));
							double rankVal = cardNum(board.charAt(rank[k]));
							bw.write(rankVal/13.0 + " ");
							bw.write(suitVal + " ");
							bw.write(rowCount/sizeOfRow + " ");
							if (k%4 == 3) {
								rowCount++;
							}
							bw.write(columnCount/sizeOfColumn + " ");
							columnCount++;
							if (columnCount > 4) {
								columnCount = 1.0;
							}
							for(int l=0; l<16; l++) {
								if ((suitVal == suitNum(board.charAt(suit[l]))) && (k != l)) {
									sameSuit = true;
									break;
								}
							}
							for(int l=0; l<16; l++) {
								if (((rankVal == cardNum(board.charAt(rank[l]))) || (rankVal == cardNum(board.charAt(rank[l]))+1) || (rankVal == cardNum(board.charAt(rank[l]))-1)) && (k != l)) {
									sameRank = true;
									break;
								}
							}
							if (sameRank == true) {
								bw.write("1 ");
							} else {
								bw.write("0 ");
							}
							if (sameSuit == true) {
								bw.write("1 ");
								
							} else {
								bw.write("0 ");
								
							}
							sameRank = false;
							sameSuit = false;
						}
						bw.newLine();
						//count = 1;
						marked = true;
						break;
					}
				}
				if (marked == false) {
					for(int j=0; j< solvableSeeds.size(); j++) {
						if(trainingSeeds.get(i).intValue() == solvableSeeds.get(j).intValue()) {
							BirdsOfAFeatherNode node = new BirdsOfAFeatherNode(i);
							String board = node.toString();
							bw.write("+1 ");
							for(int k = 0; k < 16; k++) {
								double suitVal = suitNum(board.charAt(suit[k]));
								double rankVal = cardNum(board.charAt(rank[k]));
								bw.write( rankVal/13.0 + " ");
								
								bw.write( suitVal + " ");
								
								bw.write( rowCount/sizeOfRow + " ");
								if (k%4 == 3) {
									rowCount++;
								}
								
								bw.write( columnCount/sizeOfColumn + " ");
								columnCount++;
								if (columnCount > 4) {
									columnCount = 1.0;
								}
								
								for(int l=0; l<16; l++) {
									if ((suitVal == suitNum(board.charAt(suit[l]))) && (k != l)) {
										sameSuit = true;
										break;
									}
								}
								for(int l=0; l<16; l++) {
									if (((rankVal == cardNum(board.charAt(rank[l]))) || (rankVal == cardNum(board.charAt(rank[l]))+1) || (rankVal == cardNum(board.charAt(rank[l]))-1)) && (k != l)) {
										sameRank = true;
										break;
									}
								}
								if (sameRank == true) {
									bw.write("1 ");
									
								} else {
									bw.write("0 ");
									
								}
								if (sameSuit == true) {
									bw.write("1 ");
									
								} else {
									bw.write("0 ");
									
								}
								sameRank = false;
								sameSuit = false;
							}
							bw.newLine();
							//count = 1;
							rowCount = 1.0;
							columnCount = 1.0;
							break;
						}
					}
				}
				marked = false;
			}
			bw.close();
			fw.close();
			
			File file7 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//validationData.txt");
			fw2 = new FileWriter(file7.getAbsoluteFile(), true);
			bw2 = new BufferedWriter(fw2);
			for (int i=0; i < validationSeeds.size(); i++) {
				double rowCount = 1.0;
				double columnCount = 1.0;
				for(int j=0; j< unsolvableSeeds.size(); j++) {
					if(validationSeeds.get(i).intValue() == unsolvableSeeds.get(j).intValue()) {
						BirdsOfAFeatherNode node = new BirdsOfAFeatherNode(i);
						String board = node.toString();
						bw2.write("-1 ");
						for(int k = 0; k < 16; k++) {
							double suitVal = suitNum(board.charAt(suit[k]));
							double rankVal = cardNum(board.charAt(rank[k]));
							bw2.write( rankVal/13.0 + " ");
							
							bw2.write( suitVal + " ");
							
							bw2.write( rowCount/sizeOfRow + " ");
							if (k%4 == 3) {
								rowCount++;
							}
							
							bw2.write( columnCount/sizeOfColumn + " ");
							columnCount++;
							if (columnCount > 4) {
								columnCount = 1.0;
							}
							
							for(int l=0; l<16; l++) {
								if ((suitVal == suitNum(board.charAt(suit[l]))) && (k != l)) {
									sameSuit = true;
									break;
								}
							}
							for(int l=0; l<16; l++) {
								if (((rankVal == cardNum(board.charAt(rank[l]))) || (rankVal == cardNum(board.charAt(rank[l]))+1) || (rankVal == cardNum(board.charAt(rank[l]))-1)) && (k != l)) {
									sameRank = true;
									break;
								}
							}
							if (sameRank == true) {
								bw2.write("1 ");
								
							} else {
								bw2.write("0 ");
								
							}
							if (sameSuit == true) {
								bw2.write("1 ");
								
							} else {
								bw2.write("0 ");
								
							}
							sameRank = false;
							sameSuit = false;
						}
						bw2.newLine();
						//count = 1;
						marked = true;
						break;
					}
				}
				if (marked == false) {
					for(int j=0; j< solvableSeeds.size(); j++) {
						if(validationSeeds.get(i).intValue() == solvableSeeds.get(j).intValue()) {
							BirdsOfAFeatherNode node = new BirdsOfAFeatherNode(i);
							String board = node.toString();
							bw2.write("+1 ");
							for(int k = 0; k < 16; k++) {
								double suitVal = suitNum(board.charAt(suit[k]));
								double rankVal = cardNum(board.charAt(rank[k]));
								bw2.write( rankVal/13.0 + " ");
								
								bw2.write( suitVal + " ");
								
								bw2.write( rowCount/sizeOfRow + " ");
								if (k%4 == 3) {
									rowCount++;
								}
								
								bw2.write( columnCount/sizeOfColumn + " ");
								columnCount++;
								if (columnCount > 4) {
									columnCount = 1.0;
								}
								
								for(int l=0; l<16; l++) {
									if ((suitVal == suitNum(board.charAt(suit[l]))) && (k != l)) {
										sameSuit = true;
										break;
									}
								}
								for(int l=0; l<16; l++) {
									if (((rankVal == cardNum(board.charAt(rank[l]))) || (rankVal == cardNum(board.charAt(rank[l]))+1) || (rankVal == cardNum(board.charAt(rank[l]))-1)) && (k != l)) {
										sameRank = true;
										break;
									}
								}
								if (sameRank == true) {
									bw2.write("1 ");
									
								} else {
									bw2.write("0 ");
									
								}
								if (sameSuit == true) {
									bw2.write("1 ");
									
								} else {
									bw2.write("0 ");
									
								}
								sameRank = false;
								sameSuit = false;
							}
							bw2.newLine();
							//count = 1;
							break;
						}
					}
				}
				marked = false;
			}
			bw2.close();
			fw2.close();
			
			File file8 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//testingData.txt");
			fw3 = new FileWriter(file8.getAbsoluteFile(), true);
			bw3 = new BufferedWriter(fw3);
			for (int i=0; i < testingSeeds.size(); i++) {
				double rowCount = 1.0;
				double columnCount = 1.0;
				for(int j=0; j< unsolvableSeeds.size(); j++) {
					if(testingSeeds.get(i).intValue() == unsolvableSeeds.get(j).intValue()) {
						BirdsOfAFeatherNode node = new BirdsOfAFeatherNode(i);
						String board = node.toString();
						bw3.write("-1 ");
						for(int k = 0; k < 16; k++) {
							double suitVal = suitNum(board.charAt(suit[k]));
							double rankVal = cardNum(board.charAt(rank[k]));
							bw3.write(rankVal/13.0 + " ");
							
							bw3.write(suitVal + " ");
							
							bw3.write( rowCount/sizeOfRow + " ");
							if (k%4 == 3) {
								rowCount++;
							}
							
							bw3.write(columnCount/sizeOfColumn + " ");
							columnCount++;
							if (columnCount > 4) {
								columnCount = 1.0;
							}
							
							for(int l=0; l<16; l++) {
								if ((suitVal == suitNum(board.charAt(suit[l]))) && (k != l)) {
									sameSuit = true;
									break;
								}
							}
							for(int l=0; l<16; l++) {
								if (((rankVal == cardNum(board.charAt(rank[l]))) || (rankVal == cardNum(board.charAt(rank[l]))+1) || (rankVal == cardNum(board.charAt(rank[l]))-1)) && (k != l)) {
									sameRank = true;
									break;
								}
							}
							if (sameRank == true) {
								bw3.write("1 ");
								
							} else {
								bw3.write("0 ");
								
							}
							if (sameSuit == true) {
								bw3.write("1 ");
								
							} else {
								bw3.write("0 ");
								
							}
							sameRank = false;
							sameSuit = false;
						}
						bw3.newLine();
						//count = 1;
						marked = true;
						break;
					}
				}
				if (marked == false) {
					for(int j=0; j< solvableSeeds.size(); j++) {
						if(testingSeeds.get(i).intValue() == solvableSeeds.get(j).intValue()) {
							BirdsOfAFeatherNode node = new BirdsOfAFeatherNode(i);
							String board = node.toString();
							bw3.write("+1 ");
							for(int k = 0; k < 16; k++) {
								double suitVal = suitNum(board.charAt(suit[k]));
								double rankVal = cardNum(board.charAt(rank[k]));
								bw3.write(rankVal/13.0 + " ");
								bw3.write(suitVal + " ");
								bw3.write(rowCount/sizeOfRow + " ");
								if (k%4 == 3) {
									rowCount++;
								}
								bw3.write(columnCount/sizeOfColumn + " ");
								columnCount++;
								if (columnCount > 4) {
									columnCount = 1.0;
								}
								for(int l=0; l<16; l++) {
									if ((suitVal == suitNum(board.charAt(suit[l]))) && (k != l)) {
										sameSuit = true;
										break;
									}
								}
								for(int l=0; l<16; l++) {
									if (((rankVal == cardNum(board.charAt(rank[l]))) || (rankVal == cardNum(board.charAt(rank[l]))+1) || (rankVal == cardNum(board.charAt(rank[l]))-1)) && (k != l)) {
										sameRank = true;
										break;
									}
								}
								if (sameRank == true) {
									bw3.write("1 ");
									
								} else {
									bw3.write("0 ");
									
								}
								if (sameSuit == true) {
									bw3.write("1 ");
									
								} else {
									bw3.write("0 ");
								}
								sameRank = false;
								sameSuit = false;
							}
							bw3.newLine();
							//count = 1;
							break;
						}
					}
				}
				marked = false;
			}
			bw3.close();
			fw3.close();			
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
