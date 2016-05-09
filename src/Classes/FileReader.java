package Classes;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class FileReader {
	ProjectData data;
	public FileReader(){
		data = new ProjectData();
	}
	
	// returns InputData init.
	public ProjectData readFile(){
		
		try {
        	
            Scanner input = new Scanner(System.in);
            
            //The following code block can be added to have the user input the file to be used.
            System.out.println("Current files in source are: input.txt and input2.txt");
            System.out.print("Enter the which file you want with extention : ");
            File file = new File(input.nextLine());
            
            
            //This block of code iterates through the file to get the total number of lines.
            Scanner initScan = new Scanner(file);
            int totalLines = 0;
            while (initScan.hasNextLine()) {
                totalLines++;
                initScan.nextLine();
            }
            initScan.close();
            
            //initialize 2d array
            int[][] dataArray = new int[totalLines-1][3];

            input = new Scanner(file);
            
            
            
            System.out.println("\n\nReading initial text file values...");
            
            int lineTrack = 0;
            int source = 0;
            
            while (input.hasNextLine()) {
            	if(lineTrack == 0){
            		source = input.nextInt();
            	} 
            	
            	//go to next line.
            	String line = input.nextLine();
        		System.out.print(line);
  
            	// Places values into 2d array.
            	dataArray[lineTrack][0] = input.nextInt();
            	dataArray[lineTrack][1] = input.nextInt();
            	dataArray[lineTrack][2] = input.nextInt();     
                
                lineTrack = lineTrack + 1;
            }
            
            System.out.print("\nThe source node is " + source);
            System.out.println("\nTotal Lines: " + totalLines);
            System.out.println(Arrays.deepToString(dataArray));
            
            //Put the array in our InputData class
            data.sourceNode = source;
            data.setInitRowsColumns(totalLines-1, 3);
            data.setInitialArray(dataArray);
            data.printNodes();
            
            input.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
		// If we get to this point something went wrong, there is nothing to return.
		return data;
		
		
	}
	
	// For stand-alone testing.
    public static void main(String[] args) {
    	
        try {
        	
            Scanner input = new Scanner(System.in);
            
            //The following code block can be added to have the user input the file to be used.
            System.out.println("Current files in source are: input.txt and input2.txt");
            System.out.print("Enter the which file you want with extention : ");
            File file = new File(input.nextLine());
            
            
            //This block of code interates through the file to get the total number of lines.
            Scanner initScan = new Scanner(file);
            int totalLines = 0;
            while (initScan.hasNextLine()) {
                totalLines++;
                initScan.nextLine();
            }
            
            //initialize 2d array
            int[][] dataArray = new int[totalLines-1][3];

            input = new Scanner(file);
            
            
            
            System.out.println("\n\nReading initial text file values...");
            
            int lineTrack = 0;
            int source = 0;
            
            while (input.hasNextLine()) {
            	if(lineTrack == 0){
            		source = input.nextInt();
            	} 
            	
            	//go to next line.
            	String line = input.nextLine();
        		System.out.print(line);
  
            	// Places values into 2d array.
            	dataArray[lineTrack][0] = input.nextInt();
            	dataArray[lineTrack][1] = input.nextInt();
            	dataArray[lineTrack][2] = input.nextInt();     
                
                lineTrack = lineTrack + 1;
            }
            
            System.out.print("\nThe source node is " + source);
            System.out.println("\nTotal Lines: " + totalLines);
            System.out.println(Arrays.deepToString(dataArray));
            
            //Put the array in our InputData class
            ProjectData init = new ProjectData();
            init.sourceNode = source;
            init.setInitRowsColumns(totalLines-1, 3);
            init.setInitialArray(dataArray);
            init.printNodes();
            
            

            input.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
