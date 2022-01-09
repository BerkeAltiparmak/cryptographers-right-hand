//Berke Altıparmak
//April 3, 2020
//Decrypter using LetterFrequency

/*
 This class decrypts a file using letter frequencies. 
 After getting the most frequent 4 letters in the encrypted file,
 it replaces these letters with the 4 most frequent letters 
 in the English texts, which are 'E', 'T', 'A', and 'O'.
 Since, say, the most frequent letter in the encrypted file
 does not have to be the encrypted letter of 'E', this class
 tries to decrypt using permutations of these 4 letters.
 Hence, it assumes 'E', 'T', 'A', and 'O' are the most frequent
 4 letters - without having to be in this order so it creates
 4! = 24 possible files - in the non-encrypted original plaintext.
 Then, the user can find out which of the 24 partially decrypted
 files actually corresponds to the non-encrypted original plaintext.
 */

import java.io.*;
import java.util.*;

public class DecryptUsingFrequencies
{
  public ArrayList<Character[]> permutedLetters;
  
  public DecryptUsingFrequencies(TreeMap<Double, ArrayList<String>> fm, String filename) throws IOException,FileNotFoundException
  {
    TreeMap<Double, ArrayList<String>> frequencyMap = fm; //frequency of each letter in the file
    permutedLetters = new ArrayList<Character[]>();
    
    //get an ArrayList of the 4 most frequent letters
    ArrayList<Character> frequentLetters = getMostFrequentFourLetters(fm);
    
    //get the permutation of these 4 frequent letters
    ArrayList<Character[]> permutedLetters = permuteLetterFrequencies(frequentLetters, 0);
    replaceFrequentLetterCombinations(permutedLetters, filename);
  }
  
  
  /*
   * This method gets the 4 most frequent letters in the encrypted file.
   * The reason why we are interested in 4 of the frequent letters is that
   * 4! = 24 files are relatively easy to be manually checked by the user;
   * whereas 5! = 120 files may be too many
   */
  
  private ArrayList<Character> getMostFrequentFourLetters(TreeMap<Double, ArrayList<String>> frequencyMap) 
  {
    int letterCount = 0;
    ArrayList<Character> frequencyArray = new ArrayList<Character>();
    while(letterCount < 4)
    {
      Map.Entry<Double, ArrayList<String>> entry = frequencyMap.pollFirstEntry();
      ArrayList<String> newFrequencyArray = entry.getValue(); //gets the array of the most frequent letters
      letterCount += newFrequencyArray.size();
      
      if(letterCount > 4) //if there are more than 4 letters that are equally most frequent, it becomes not feasible to decrypt using this method
      {
        System.out.println("Unfortunately, " + letterCount + " number of letters compete for the most frequency! It is impossible to guess which letter is which...");
      }
      
      else
      {
        for(int i = 0; i < newFrequencyArray.size(); i++)
        {
          char frequent = newFrequencyArray.get(0).charAt(0); //next frequent letter
          System.out.println("The letter " + frequent + " is quite frequent in this file!");
          frequencyArray.add(frequent);
        }
      }
    }
    
    return frequencyArray; //return an ArrayList of the 4 most frequent letters
  }
  
  /*
   * gets the permutations of these four most frequent letters, as it does not have to be in that order
   * in the original non-encrypted file.
   * Got help from Yevgen Yampolskiy at https://stackoverflow.com/questions/2920315/permutation-of-array for this one.
   */
  
  private ArrayList<Character[]> permuteLetterFrequencies(List<Character> arr, int k)
  {
    for(int i = k; i < arr.size(); i++)
    {
      Collections.swap(arr, i, k);
      permuteLetterFrequencies(arr, k + 1);
      Collections.swap(arr, i, k);
    }
    if (k == arr.size() - 1)
    {
      Character[] charArr = new Character[arr.size()];
      for (int i = 0; i < arr.size(); i++)
        charArr[i] = arr.get(i);
      permutedLetters.add(charArr);
    }
    
    return permutedLetters;
  }
  
  private void replaceFrequentLetterCombinations(ArrayList<Character[]> pL, String filename) throws IOException,FileNotFoundException
  {
    ArrayList<Character[]> permutedLetters = pL;
    
    for(int i = 0; i < permutedLetters.size(); i++)
    {
      Reader reader = new FileReader(filename); //reads the file
      String outputFileName = "decrypted" + i + ".txt";
      PrintWriter output =  new PrintWriter(new FileWriter(outputFileName));
      Character[] frequencyLetters = permutedLetters.get(i);
      for(Character letters: frequencyLetters)
      {
        System.out.println(letters);
      }
      boolean done = false;
      
      while (!done) //this loop finishes when done is true 
      {  
        int next = reader.read(); //reads the file
        char ch = (char) next; //converting integer into char
        if (next == -1) 
          done = true; //done is true when every character in the file is examined. no char is left in the file.
        else if (Character.isLetter((char) next) || ch == ' ')
        {
          if(ch == Character.toUpperCase(frequencyLetters[0])) //finds one of the most frequent letters
          {
            output.print("E"); //replaces it with E, the most frequent letter normally
          }
          else if(ch == Character.toLowerCase(frequencyLetters[0])) //finds one of the most frequent letters
          {
            output.print("e"); //replaces it with e, the most frequent letter normally
          }
          else if(ch == Character.toUpperCase(frequencyLetters[1])) //finds one of the most frequent letters
          {
            output.print("T"); //replaces it with T, the second second frequent letter normally
          }
          else if(ch == Character.toLowerCase(frequencyLetters[1])) //finds one of the most frequent letters
          {
            output.print("t"); //replaces it with t, the second most frequent letter normally
          }
          else if(ch == Character.toUpperCase(frequencyLetters[2])) //finds one of the most frequent letters
          {
            output.print("A"); //replaces it with A, the third most frequent letter normally
          }
          else if(ch == Character.toLowerCase(frequencyLetters[2])) //finds one of the most frequent letters
          {
            output.print("a"); //replaces it with a, the third most frequent letter normally
          }
          else if(ch == Character.toUpperCase(frequencyLetters[3])) //finds one of the most frequent letters
          {
            output.print("O"); //replaces it with O, the fourth most frequent letter normally
          }
          else if(ch == Character.toLowerCase(frequencyLetters[3])) //finds one of the most frequent letters
          {
            output.print("o"); //replaces it with o, the fourth most frequent letter normally
          }
          else if(ch == ' ')
          {
            output.print(" "); //space is left the same
          }
          else
          {
            /*
            //These lines of code was originally used to give each letter a unique number so that they can be
            //manyally decoded, but I realized it was so hard to read the partially decrypted file this way
            int asciiToIntAndShift = (int) Character.toUpperCase(ch) - 26;
            String newAscii = Character.toString((char) (asciiToIntAndShift));
            output.print(newAscii); //other letters become unique numbers so that they can be manually decoded
            */
            output.print("?"); //every other letter becomes ?, which makes it easier to read.
          }
        }
      }
      System.out.println("the outfile is completed, check your folder.");
      output.close();
    }
  }
}