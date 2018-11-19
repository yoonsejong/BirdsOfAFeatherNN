import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class movableCards {
	
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
		//int[] rank = {0,3,6,9,13,16,19,22,26,29,32,35,39,42,45,48};
		//int[] suit = {1,4,7,10,14,17,20,23,27,30,33,36,40,43,46,49};
		String st;
		boolean marked = false;
		boolean legal = false;
		//boolean sameSuit = false;
		//boolean sameRank = false;
		//int count = 1;
		//double sizeOfRow = 4.0;
		//double sizeOfColumn = 4.0;
		
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
			
			File file6 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//trainingDataMovement.txt");
			fw = new FileWriter(file6.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			for (int i=0; i < trainingSeeds.size(); i++) {
				//double rowCount = 1.0;
				//double columnCount = 1.0;
				for(int j=0; j< unsolvableSeeds.size(); j++) {
					if(trainingSeeds.get(i).intValue() == unsolvableSeeds.get(j).intValue()) {
						BirdsOfAFeatherNode node = new BirdsOfAFeatherNode(i);
						bw.write("-1 ");
						for(int k = 0; k < 4; k++) {
							for(int l = 0; l < 4;l++) {
								for(int m=0; m < 4; m++) {
									for(int n=0; n<4; n++) {
										if(node.isLegalMove(k, l, m, n)) {
											bw.write("1 ");
											//count++;
											legal = true;
											break;
										}
									}
									if(legal == true) {
										break;
									}
								}
								if(legal == false) {
									bw.write("0 ");
									//count++;
								}
								legal = false;
							}
						}
						//count = 1;
						marked = true;
						bw.newLine();
						break;
					}
				}
				if (marked == false) {
					for(int j=0; j< solvableSeeds.size(); j++) {
						if(trainingSeeds.get(i).intValue() == solvableSeeds.get(j).intValue()) {
							BirdsOfAFeatherNode node = new BirdsOfAFeatherNode(i);
							bw.write("+1 ");
							for(int k = 0; k < 4; k++) {
								for(int l = 0; l < 4;l++) {
									for(int m=0; m < 4; m++) {
										for(int n=0; n<4; n++) {
											if(node.isLegalMove(k, l, m, n)) {
												bw.write("1 ");
												//count++;
												legal = true;
												break;
											}
										}
										if(legal == true) {
											break;
										}
									}
									if(legal == false) {
										bw.write("0 ");
										//count++;
									}
									legal = false;
								}
							}
							//count = 1;
							bw.newLine();
							break;
						}
					}
				}
				marked = false;
			}
			bw.close();
			fw.close();
			
			File file7 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//validationDataMovement.txt");
			fw2 = new FileWriter(file7.getAbsoluteFile(), true);
			bw2 = new BufferedWriter(fw2);
			for (int i=0; i < validationSeeds.size(); i++) {
				//double rowCount = 1.0;
				//double columnCount = 1.0;
				for(int j=0; j< unsolvableSeeds.size(); j++) {
					if(validationSeeds.get(i).intValue() == unsolvableSeeds.get(j).intValue()) {
						BirdsOfAFeatherNode node = new BirdsOfAFeatherNode(i);
						bw2.write("-1 ");
						for(int k = 0; k < 4; k++) {
							for(int l = 0; l < 4;l++) {
								for(int m=0; m < 4; m++) {
									for(int n=0; n<4; n++) {
										if(node.isLegalMove(k, l, m, n)) {
											bw2.write("1 ");
											//count++;
											legal = true;
											break;
										}
									}
									if(legal == true) {
										break;
									}
								}
								if(legal == false) {
									bw2.write("0 ");
									//count++;
								}
								legal = false;
							}
						}
						//count = 1;
						marked = true;
						bw2.newLine();
						break;
					}
				}
				if (marked == false) {
					for(int j=0; j< solvableSeeds.size(); j++) {
						if(validationSeeds.get(i).intValue() == solvableSeeds.get(j).intValue()) {
							BirdsOfAFeatherNode node = new BirdsOfAFeatherNode(i);
							bw2.write("+1 ");
							for(int k = 0; k < 4; k++) {
								for(int l = 0; l < 4;l++) {
									for(int m=0; m < 4; m++) {
										for(int n=0; n<4; n++) {
											if(node.isLegalMove(k, l, m, n)) {
												bw2.write("1 ");
												//count++;
												legal = true;
												break;
											}
										}
										if(legal == true) {
											break;
										}
									}
									if(legal == false) {
										bw2.write("0 ");
										//count++;
									}
									legal = false;
								}
							}
							//count = 1;
							bw2.newLine();
							break;
						}
					}
				}
				marked = false;
			}
			bw2.close();
			fw2.close();
			
			File file8 = new File("//home//benjaminsang//eclipse-workspace//BirdsOfAFeather//text//testingDataMovement.txt");
			fw3 = new FileWriter(file8.getAbsoluteFile(), true);
			bw3 = new BufferedWriter(fw3);
			for (int i=0; i < testingSeeds.size(); i++) {
				//double rowCount = 1.0;
				//double columnCount = 1.0;
				for(int j=0; j< unsolvableSeeds.size(); j++) {
					if(testingSeeds.get(i).intValue() == unsolvableSeeds.get(j).intValue()) {
						BirdsOfAFeatherNode node = new BirdsOfAFeatherNode(i);
						bw3.write("-1 ");
						for(int k = 0; k < 4; k++) {
							for(int l = 0; l < 4;l++) {
								for(int m=0; m < 4; m++) {
									for(int n=0; n<4; n++) {
										if(node.isLegalMove(k, l, m, n)) {
											bw3.write("1 ");
											//count++;
											legal = true;
											break;
										}
									}
									if(legal == true) {
										break;
									}
								}
								if(legal == false) {
									bw3.write("0 ");
									//count++;
								}
								legal = false;
							}
						}
						//count = 1;
						bw3.newLine();
						marked = true;
						break;
					}
				}
				if (marked == false) {
					for(int j=0; j< solvableSeeds.size(); j++) {
						if(testingSeeds.get(i).intValue() == solvableSeeds.get(j).intValue()) {
							BirdsOfAFeatherNode node = new BirdsOfAFeatherNode(i);
							bw3.write("+1 ");
							for(int k = 0; k < 4; k++) {
								for(int l = 0; l < 4;l++) {
									for(int m=0; m < 4; m++) {
										for(int n=0; n<4; n++) {
											if(node.isLegalMove(k, l, m, n)) {
												bw3.write("1 ");
												//count++;
												legal = true;
												break;
											}
										}
										if(legal == true) {
											break;
										}
									}
									if(legal == false) {
										bw3.write("0 ");
										//count++;
									}
									legal = false;
								}
							}
							//count = 1;
							bw3.newLine();
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
