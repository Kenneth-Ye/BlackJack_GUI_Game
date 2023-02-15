//Name: Kenneth
//Program Name: spriteSplitter
//Purpose: make a method get a subimage of the correct card from the sprite sheet 52cards.png
//Date: May 24 2022

package main;


//image handling imports
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

//gui import to return a JLabel
import javax.swing.*;

//error handling imports
import java.io.IOException;

public class SpriteSplitter
{
	BufferedImage cardSprites = null; //create a buffered image
	
	//given two ints representing position of target card within the sprite sheet return a bufffered image subimage of that card
	public BufferedImage returnCardImage(int column, int row)
	{
	  //height for each sprite is 117 pixels 
	  //width is 81 pixels
	  try
	  {
	    cardSprites = ImageIO.read(new File("/Users/kennethye/Downloads/52Cards.png")); //import the sprite sheet as buffered image
	  }
	  catch (IOException e) //catch io errors
	  {
	    System.out.println("IO Error");
	  }
	  BufferedImage resultCard = cardSprites.getSubimage((column*81)-81, (row*117)-117, 81, 117); //get subimage of the correct card
	  return resultCard; //return the subimage of the correct card
	}
	
	
	//plan to use this.cards.getString() as paramater
	//return column number from the sprite sheet of the card inputted
	public int cardColumnNumber(String cardValue)
	{
	  //planned parameter --> [ Two of Spades ]
	  //Split the string above by space character, second element is the value of card
	  String[] splitCardToString = cardValue.split(" "); 
	
	  //set cardsDeckValue to the deck value of the card
	  String cardsDeckValue = splitCardToString[1];
	  
	  switch(cardsDeckValue)
	  {
	    //return the column number in the sprite sheet for every deck value
	    case "Two": return 1; 
	    case "Three": return 2; 
	    case "Four": return 3; 
	    case "Five": return 4; 
	    case "Six": return 5; 
	    case "Seven": return 6; 
	    case "Eight": return 7; 
	    case "Nine": return 8; 
	    case "Ten": return 9; 
	    case "Jack": return 10; 
	    case "Queen": return 11; 
	    case "King": return 12; 
	    case "Ace": return 13; 
	    default: return 1;
	  }
	}
	
	//plan to use this.cards.getString() as paramater
	//return row number from the sprite sheet of the card inputted (face down has seperate method)
	public int cardRowNumber(String cardValue)
	{
	  //planned parameter --> [ Two of Spades ]
	  //Split the string above by space character
	  String[] splitCardToString = cardValue.split(" "); 
	
	  //set cardsDeckValue to the suit of the card (fourth element of string array after the split)
	  String cardSuit = splitCardToString[3];
	
	  //return row number in the spritesheet depending on suit of the card
	  switch(cardSuit)
	  {
	    case "Hearts": return 1;
	    case "Diamonds": return 2;
	    case "Clubs": return 3;
	    case "Spades": return 4; 
	    default: return 1;
	  }
	}
	
	//return a jlabel image of a card given its string representation
	public JLabel returnCardJLabel(String card)
	{
	  SpriteSplitter spriteSplitter = new SpriteSplitter();
	  
	  //get subimage of spritesheet for card as an image icon
	  ImageIcon cardImage = new ImageIcon(spriteSplitter.returnCardImage(spriteSplitter.cardColumnNumber(card), spriteSplitter.cardRowNumber(card)));
	  JLabel cardJLabel = new JLabel(cardImage); //create a jlabel with that image icon
	  return cardJLabel; //return the jlabel
	}
	
	public JLabel faceDownCardJLabel()
	{
		ImageIcon cardImage = new ImageIcon(returnCardImage(1, 5));
		JLabel cardJLabel = new JLabel(cardImage); //create a jlabel with that image icon
		return cardJLabel; //return the jlabel
	}
}