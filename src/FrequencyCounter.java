//Berke Altıparmak
//April 3, 2020
//Decrypter using LetterFrequency

/*
 This class counts letter frequencies in a file.
 */

import java.io.*;
import java.util.*;

public class FrequencyCounter
{  
  public FrequencyCounter() throws IOException,FileNotFoundException
  {  
    Scanner in = new Scanner(System.in);
    System.out.println("Filename: "); //prints "Filename: "
    String filename = in.next(); //lets the user enter the name of the file to be investigated (for example, mary).
    
    TreeMap<Double, ArrayList<String>> frequencyMap = new TreeMap<Double, ArrayList<String>>(Collections.reverseOrder());
    HashMap<Double, ArrayList<String>> lastFrequencyMap = new HashMap<Double, ArrayList<String>>();
    
    PrintWriter outFile =  new PrintWriter(new FileWriter(filename + "_Frequency.txt"));
    
    Reader reader = new FileReader(filename);
    FrequencyMap map = new FrequencyMap();
    boolean done = false;
    while(!done) //this loop finishes when done is true 
    {  
      int next = reader.read(); //reads the file
      if (next == -1) 
        done = true; //done is true when every character in the file is examined. no char is left in the file.
      else if (Character.isLetter((char) next))
        map.add(Character.toUpperCase((char) next)); //first uppercases the character, then adds it to the map.
    }
    reader.close(); //stops reading the file
    
    for(char c = 'A'; c <= 'Z'; c++)
    {
      double frequency = map.getFrequency(c);
      outFile.printf("%c:%5.2f%%%n", c, frequency); //writes out the frequency of each letter in the file
      
      boolean frequencyPresent = false;
      
      //this is used to check whether this target already exists:
      for(Double key : frequencyMap.keySet() ) 
      {
        if(frequency == key)
        {
          frequencyPresent = true;
          break;
        }
      }
      
      if(!frequencyPresent)
      {
        ArrayList<String> letter = new ArrayList<String>();
        letter.add(Character.toString(c));
        frequencyMap.put(frequency, letter); //adds the letter and its frequency to the map
      }
      
      else if(frequencyPresent)
      {
      frequencyMap.get(frequency).add(Character.toString(c));
      }
    }
    
    outFile.close();
    
    Set set = frequencyMap.entrySet();
    Iterator iterator = set.iterator();
    while(iterator.hasNext()) 
    {
      Map.Entry mentry = (Map.Entry)iterator.next();
      System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
      System.out.println(mentry.getValue());
    }
    
    
    DecryptUsingFrequencies decrypt = new DecryptUsingFrequencies(frequencyMap, filename);
  }
}