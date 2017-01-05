//@author Selwyn Jacob
package csci355project;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CSCI355Project {
    
    //ArrayList for ID values
    ArrayList<Integer>ID = new ArrayList<Integer>();
    //ArrayList for ID, Name, Age, GPA
    ArrayList<String>list = new ArrayList<String>();
    
    public static void main(String[] args) {
            CSCI355Project binaryInsertionSort = new CSCI355Project();
            binaryInsertionSort.insert();
        }
    
    public void insert(){
        
        //Open .txt file
        String fileName = "studentsrec.txt";
        System.out.println(fileName + " is now opened");
        Scanner ipStream = null;
        String dataLine = null;
        //define position int
        int position;
        //define key int
        int key;
        //define ArrayList 'list'
        String value;
        //try and catch .txt file
        try{
            ipStream = new Scanner(new File (fileName));
        }
        catch(IOException bad){
            System.out.println("Error opening the file for read: " + fileName);
            System.exit(0);
        }
        
        //output .txt file to console
        while(ipStream.hasNextLine()){
            dataLine = ipStream.nextLine();
            //add .txt lines to 'list' ArrayList
            list.add(dataLine);
        }
        //print lines to console
        for(int i = 0; i < 10;i++ ){
        System.out.println(list.get(i));
        
        }
        //parse ID into interger values
        for (int i = 0; i < 10; i++){
        String Id = list.get(i).substring(0,7);
        int studentID = Integer.parseInt(Id);
        //add ID int values to 'ID' ArrayList
        ID.add(studentID);
        }
        //binary insertion sort
        //iterate through key values
        for (int i = 1; i< ID.size(); i++){
            key = ID.get(i);
            value = list.get(i);
            position = binarySearch(0, i - 1, key);
            //use binary search to place key values into correct position
            for(int j = i; j>position;j--)
                ID.set(j, ID.get(j-1));
                ID.set(position, key);
            //add ID, Name, Age, GPA into 'list' ArrayList   
            for(int j = i; j > position; j--)
                list.set(j, list.get(j-1));
                list.set(position, value); 
            //after sorting 
        }
        //print out sorted .txt file into console
        System.out.println("-----------------------------------------------------------------");
        System.out.println("AFTER BINARY INSERTION SORT");
            for(int i = 0; i < 10; i++){
                System.out.println(list.get(i));
            }
        //open and add into new .txt file
        String newFile = "studentsupdate.txt";
        PrintWriter outputStream = null;
        try{
            outputStream = new PrintWriter(newFile);
        }
        catch(FileNotFoundException e){
            System.out.println("Error opening the file: " + newFile);
            System.exit(0);
        }
        //add text into .txt file and close file
        for (int i = 0; i < 10; i++){
        outputStream.println(list.get(i));
        }
        outputStream.close();
    }
    //binary search used in insertion()
    public int binarySearch(int low, int high, int key){
        int mid;
        //execute while low values is less than high value
        while(low <= high){
            mid = (low+high)/2;
            if(key > ID.get(mid))
                low = mid + 1;
            else if(key < ID.get(mid))
                high = mid-1;
            else
                return mid;
        }
        return low;
    }
}
    