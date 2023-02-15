//Name: Kenneth Ye
//Program name: LoginScreen
//Date: June 9 2022
//Purpose: design an account login system for gui game
package main;

//import array list and java file handling 
import java.util.ArrayList;
import java.io.*; 

public class LoginScreen {
	//main arraylist
	private static ArrayList<ArrayList<String>> dataArray = new ArrayList<ArrayList<String>>(); 
	
	//function to initialize rows in arraylist for username password and high score
	public void rowIniatialize()
	{
		dataArray.add(new ArrayList<String>()); //initialize 1 Arraylist for username
		dataArray.add(new ArrayList<String>()); //initialize ArrayList for password
		dataArray.add(new ArrayList<String>()); //initialize ArrayList for high score 
	}
	
//read a file and put its info into the database
	public static void fileToDatabase()
	{
		try //try catch for IOexception
		{
			BufferedReader reader = new BufferedReader(new FileReader("/Users/kennethye/Desktop/database.txt")); //buffered reader
			int lineCount = lineCounter("/Users/kennethye/Desktop/database.txt"); //get number of lines
			for (int i = 0; i < lineCount / 3; i++) //loop through every group of three, every three lines is information for one account
			{
				dataEntry(reader.readLine(), 0); //add the username
				dataEntry(reader.readLine(), 1); // add password
				dataArray.get(2).add(reader.readLine()); //third line is for high score
			}
			reader.close(); //close reader
			System.out.println(dataArray.get(0));
			System.out.println(dataArray.get(1));
			System.out.println(dataArray.get(2));
		}
		catch (Exception e) //handle ioexception
		{
			System.out.println("No file Found");
		}
	}
	
//method to count number of lines in a file
public static int lineCounter(String fileName)
{
	try //try catch for ioexception
	{
		File inFile = new File(fileName); //new file object
		BufferedReader reader = new BufferedReader(new FileReader(inFile)); //buffered reader
		int lineCount = 0; // var to hold number of lines
		while (reader.readLine() != null) //loop through every line
		{
			lineCount++; //increment counter
		}
		reader.close(); //close reader
		return lineCount; //return number of line  
	}
	catch (Exception e) //catch ioexceptions
	{
		System.out.println("File doesn't exist");
	}
	return 0;
}

  
  //method to add student last and first names
  public static void dataEntry(String info, int specifier) //specifier dictates if info is added as username or password
  {
    //if functon is called with specifier = 0 it enters data for username, if called with specifier = 1 it enters data into password
    if (specifier == 0)
    {
      dataArray.get(0).add(info); //add info to username arraylist
    }
    else if (specifier == 1)
    {
      dataArray.get(1).add(info);  //add info to password arraylist
    }
  }
  
  //method to check whether a username already exists in the database
  public boolean checkForUsernameDuplicate(String username)
  {
	  //loop through every username
	  for (int i = 0; i < dataArray.get(0).size(); i++)
	  {
		  //if username matches an existing username
		  if (username.equals(dataArray.get(0).get(i)))
		  {
			  return true; //duplicate found
		  }
	  }
	  return false; //if all usernames checked and no duplicates are found
  }
 
  //add a string username and password pair to the database
  public void createAccount(String username, String password)
  {
    dataEntry(username, 0); //add username
    dataEntry(password, 1); //add last name in
    dataArray.get(2).add("10000"); //default high score of 10000
  }

  //returns if a given username password pair 
  public boolean successfulLogin(String username, String password) //specifier dictates whether 
  {
    for (int i = 0; i < dataArray.get(0).size(); i++) //search through every username
    {
		if (dataArray.get(0).get(i).equals(username)) //if username exists in database
		{ 
		  if (dataArray.get(1).get(i).equals(password)) //check if password is correct for that username
		  {
			 return true; //login successful
		  }
		  else
		  {
		     return false; //if password wrong return false
		  }
		}
    }
    return false; //if all accounts are checked and no successful login occurs
  } 
  
  //method to return the index of a username within the arraylist database
  public int whereInArray(String username)
  {
	  //loop through every username
	  for (int i = 0; i < dataArray.get(0).size(); i++) 
	  {
		  if (dataArray.get(0).get(i).equals(username)) //if username found
		  {
			  return i; //return index that matches
		  }
	  }
	  return 0; 
  }
  
  //method to save database to file and close program
  public void updateFile()
  {
    try //try catch for ioexception
    {
      BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/kennethye/Desktop/database.txt")); //buffered writer object
      for (int i = 0; i < dataArray.get(0).size(); i++) //loop throug every account
      {
        writer.write(dataArray.get(0).get(i) + "\n"); //write account info into file
        writer.write(dataArray.get(1).get(i) + "\n");
        writer.write(dataArray.get(2).get(i) + "\n");
      }
      writer.close(); //close reader 
      System.out.println("Saved database to file.");
    }
    catch (Exception e) //catch exception
    {
      System.out.println("Unsuccessful in saving to file.");
    }
  }
  
  //getter for an account's high score
  public double highScoreGetter(String username) 
  {
	  return Double.parseDouble(dataArray.get(2).get(whereInArray(username)));
  }
  
  //method to set high score of an account to a new value
  public void changeScore(Double newHighScore, String username)
  {
	  dataArray.get(2).set(whereInArray(username), Double.toString(newHighScore));
  }
  
  //get the global high score out of every account
  public double globalHighScoreGetter()
  {
	  //global max var
	  Double globalMax = 0.0;
	  
	  //loop through every account's high score
	  for (int i = 0; i < dataArray.get(2).size(); i++)
	  {
		  //if the account's high score greater than current global max
		  if (globalMax < Double.parseDouble(dataArray.get(2).get(i)))
		  {
			  //then update globalMax to new globalMax 
			  globalMax = Double.parseDouble(dataArray.get(2).get(i));
		  }
	  }
	  //return the globalMax
	  return globalMax;
  }
  
}
    
