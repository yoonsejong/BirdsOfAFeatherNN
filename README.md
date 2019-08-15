# BirdsOfAFeatherNN

## Installation and Running

### Installation and Extraction of data
1. Clone the repository to the location of your choice to obtain the files. Move the files in Java_Folder into an existing project workspace in the Java IDE of your choice. 

2. In Neural_Network, go into the data folder and you will be greeted with many directories. Go into each one and only extract from the file that contains the .zip extension. All data files had to be compressed into multiple parts so extracting the main zip will simply extract everything else from the parts. Once extracted,  all the zips and zip parts can be safely deleted.

3. Once data extraction is complete, move the files in Neural_Network into an existing project workspace in the python IDE of your choice.

4. In the python IDE (pycharm will be used as an example), install the torch, torchvision, and matplotlib packages as these will be used in the experiemnts.

### Running the code
1. If you wish to obtain your own seeds and data, open Experiment1.java and set the starting and ending seeds. Lines 34-37 can be commented out for the initial run. Set the start and end of the for loop on line 38 to be the startseed and endseed. Be sure to change the file locations for file1 and file2 to be the location you wish to save your data (line 17 and 18). Line 19 can be commented out if not using timer restraint. Comment out lines 58-63 if you do not require a timing buffer. This was used due to the fact that certain seeds would take too long to get a result and may result in a java out of memory error. We generated out solvable and unsolvable seeds in parts because of this. 

2. Once data extraction is done, now it's time to separate seeds into training, validation, and testing sets. We used a 50-30-20 ratio for our training, validation, and testing sets. Open RandomizeSeeds.java and adjust the directories for file and file 2 in lines 24 and 26 to the locations of the solvable and unsolvable seeds that was obtained previously. This will generate 3 text files, trainingSeefs.txt, validationSeeds.txt, and testingSeeds.txt.

3. Once data has been obtained, you can now start converting your data into feature data. Open up ConstructRank.java, ConstructSuit.java, movableCards.java, and UniqueCards.java and adjust the file locations on lines 124, 126, and 128 to the locations of the previously obtained training, validation, and testing sets. Running each program will generate their own respective datasets used for training.

4. Now moving into running the Neural Network, switch over to the python IDE being used and move the converted feature datasets into the same workspace (in the data folder) and change the file locations betweek lines 34 and 61 in Birds_Of_A_Feather_NN.py to their respective dataset locations. It is highly recommended to make a copy of the dataset being used in a separate folder within the data fold as to not confuse the results for each experiment (since all files being run are named the same thing).

5. Once everything is in place, run Birds_Of_A_Feather_NN.py. Results should be printed out at the end of the program.

### Citation
If you found this code/data useful for your work, please consider citing the publication below:

- Sang, B., & Yoon, S. (2019). A Neural Network Approach for Birds of a Feather Solvability Prediction. Proceedings of the AAAI Conference on Artificial Intelligence, 33(01), 9706-9712. 
  - DOI: https://doi.org/10.1609/aaai.v33i01.33019706 
  - PDF: https://aaai.org/ojs/index.php/AAAI/article/view/5037
