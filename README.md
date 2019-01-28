# BirdsOfAFeatherNN

## Installation and Running

### Installation and Extraction of data
Clone the repository to the location of your choice to obtain the files. Move the files in Java_Folder into an existing project workspace in the Java IDE of your choice. 

In Neural_Network, go into the data folder and you will be greeted with many directories. Go into each one and only extract from the file that contains the .zip extension. All data files had to be compressed into multiple parts so extracting the main zip will simply extract everything else from the parts. Once extracted,  all the zips and zip parts can be safely deleted.

Once data extraction is complete, move the files in Neural_Network into an existing project workspace in the python IDE of your choice.

### Running the code
If you wish to obtain your own seeds and data, open Experiment1.java and set the starting and ending seeds. Lines 34-37 can be commented out for the initial run. Set the start and end of the for loop on line 38 to be the startseed and endseed. Be sure to change the file locations for file1 and file2 to be the location you wish to save your data (line 17 and 18). Line 19 can be commented out if not using timer restraint. Comment out lines 58-63 if you do not require a timing buffer. This was used due to the fact that certain seeds would take too long to get a result and may result in a java out of memory error. We generated out solvable and unsolvable seeds in parts because of this. 

Once data extraction is done, now it's time to separate seeds into training, validation, and testing sets. We used a 50-30-20 ratio for our training, validation, and testing sets. 

Once data has been obtained, you can now start converting your data into feature data. Open up ConstructRank.java, ConstructSuit.java, movableCards.java, and 
