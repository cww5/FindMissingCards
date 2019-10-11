import java.util.*;
import java.io.*;

public class CardsFinder
{
  private static void check(ArrayList<String> arr, String toCheckValue) 
  { 
    // Check if the specified element is present in the array or not using contains() method 
    //boolean test = Arrays.asList(arr).contains(toCheckValue); 
    boolean test = arr.contains(toCheckValue); 
    System.out.println("Is " + toCheckValue + " present in the array: " + test); 
  }
  
  public static void main(String[] args)
  {
    // String fileName = "C:\\Users\\watson\\Desktop\\CS 100 Laptop\\normalDeck.txt";
    String fileName = "C:\\Users\\watson\\Desktop\\findCards\\randomDeck.txt";
    String line = "";
    // Create a HashMap of each letter mapped to the four different cards it can take on.
    HashMap<String, String[]> allGroups = new HashMap<String, String[]>();
    allGroups.put("K", new String[] {"K of Spades", "K of Clubs", "K of Diamonds", "K of Hearts" });
    allGroups.put("Q", new String[] {"Q of Spades", "Q of Clubs", "Q of Diamonds", "Q of Hearts" });
    allGroups.put("J", new String[] {"J of Spades", "J of Clubs", "J of Diamonds", "J of Hearts" });
    allGroups.put("10", new String[] {"10 of Spades", "10 of Clubs", "10 of Diamonds", "10 of Hearts" });
    allGroups.put("9", new String[] {"9 of Spades", "9 of Clubs", "9 of Diamonds", "9 of Hearts" });
    allGroups.put("8", new String[] {"8 of Spades", "8 of Clubs", "8 of Diamonds", "8 of Hearts" });
    allGroups.put("7", new String[] {"7 of Spades", "7 of Clubs", "7 of Diamonds", "7 of Hearts" });
    allGroups.put("6", new String[] {"6 of Spades", "6 of Clubs", "6 of Diamonds", "6 of Hearts" });
    allGroups.put("5", new String[] {"5 of Spades", "5 of Clubs", "5 of Diamonds", "5 of Hearts" });
    allGroups.put("4", new String[] {"4 of Spades", "4 of Clubs", "4 of Diamonds", "4 of Hearts" });
    allGroups.put("3", new String[] {"3 of Spades", "3 of Clubs", "3 of Diamonds", "3 of Hearts" });
    allGroups.put("2", new String[] {"2 of Spades", "2 of Clubs", "2 of Diamonds", "2 of Hearts" });
    allGroups.put("A", new String[] {"A of Spades", "A of Clubs", "A of Diamonds", "A of Hearts" });
    
    Map<String, ArrayList<String>> readGroups = new HashMap<String, ArrayList<String>>();
    readGroups.put("K", new ArrayList<String>());
    readGroups.put("Q", new ArrayList<String>());
    readGroups.put("J", new ArrayList<String>());
    readGroups.put("10", new ArrayList<String>());
    readGroups.put("9", new ArrayList<String>());
    readGroups.put("8", new ArrayList<String>());
    readGroups.put("7", new ArrayList<String>());
    readGroups.put("6", new ArrayList<String>());
    readGroups.put("5", new ArrayList<String>());
    readGroups.put("4", new ArrayList<String>());
    readGroups.put("3", new ArrayList<String>());
    readGroups.put("2", new ArrayList<String>());
    readGroups.put("A", new ArrayList<String>());
    
    String [] readLines = new String[52];
    int pos = 0;
    // Try to read the file, otherwise exit the program.
    try 
    {
        // FileReader reads text files in the default encoding.
        FileReader fileReader = new FileReader(fileName);
        // Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while((line = bufferedReader.readLine()) != null) 
        {
          line = line.replace("\n", "").replace("\r", "");
          readLines[pos] = line;
          pos++;
          
          String[] parts = line.split(":", 2);
          ArrayList<String> cardArr = readGroups.get(parts[1]);
          cardArr.add(parts[1] + " of " + parts[0]);
          readGroups.put(parts[1], cardArr);
        }   
        // Always close files.
        bufferedReader.close();         
    }
    catch(FileNotFoundException ex) 
    {
        System.out.println("Unable to open file '" + fileName + "'"); 
        System.exit(0);
    }
    catch(IOException ex) 
    {
        System.out.println("Error reading file '" + fileName + "'");                  
        // Or we could just do this: 
        // ex.printStackTrace();
        System.exit(0);
    }
    
    // Populate an array of only the cards that were found.
    String [] readCards = new String[pos];
    pos = 0;
    for (int i = 0; i < 52; i++)
    {
      String readLine = readLines[i];
      if (readLine != null)
      {
        readCards[pos] = readLine;
        pos++;
        //System.out.println(readLines[i]);
      }
    }
    
    int numMissing = 52 - readCards.length;
    System.out.println(readCards.length + " cards read from the file");
    System.out.println(numMissing + " cards are missing from the deck\n");
        
    // This array will have all of the missing cards from the text file.
    String[] missingCards = new String[numMissing];
    
    // For each unique value of card (A, 2, 3...etc), check if the value+suit is in the value
    // of the HashMap which stores the combos. If not, save the card in missingCards.
    
    for (Map.Entry<String,ArrayList<String>> entry : readGroups.entrySet())
    {
      System.out.println("Amount of \"" + entry.getKey() + "\" read from file: " + entry.getValue().size());
      for (String card : entry.getValue())
      {
        System.out.println(card);
      }
    }
    
    for (Map.Entry<String,String[]> cardPair : allGroups.entrySet())
    {
      String cardAndSuit = cardPair.getKey();
      String[] parts = cardAndSuit.split(" of ", 2);
      String val = parts[0];
      System.out.println(readGroups.get(val).size());
      for (String card : cardPair.getValue())
      {
        check(readGroups.get(val), card);
      }
    }
    
  }
}