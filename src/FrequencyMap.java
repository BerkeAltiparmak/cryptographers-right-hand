//Berke Altıparmak
//April 3, 2020
//Decrypter using LetterFrequency

/*
   A class to count the frequency of letters
*/
public class FrequencyMap
{
   private int[] freqs; //creates an empty array.
   private int total;

   public FrequencyMap()
   {
      total = 0;
      freqs = new int['Z' - 'A' + 1]; //this essentially sets the number of elements in the freqs array equal to 26
      //which is equal to the amount of letters between A-Z, included.
      //basically, this is an array of the alphabet.
   }
   
   
   public double getFrequency(char c)
   {
      if ('A' <= c && c <= 'Z') //run this until the every letter is assigned to c
         return 100.0 * freqs[c - 'A'] / total; //writes the in-file frequency of the letter that is assigned to c.
      else
         return 0;
   }

   
   public void add(char c)
   {
      if ('A' <= c && c <= 'Z')
      {
         freqs[c - 'A']++; //this lets c to get to the next letter.
         total++;
      }
   }
}