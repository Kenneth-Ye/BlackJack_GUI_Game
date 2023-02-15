
//Name: Kenneth Ye
//Date: May 17 2022
//Program name: Deck
//Purpose: construct a  6 deck shoe of cards object for blackjack

//packaging and imports 
package main.util.blackjack;
import java.util.ArrayList;
import main.util.RandomNumber;

public class Deck 
{
private ArrayList<Card> cards; // instance variable arraylist of cards

public Deck() // deck constructer
{
  this.cards = new ArrayList<Card>();
}

public void createFullDeck() // fill arraylist with 312 cards (6 decks of 52)
{
  for (int i = 0; i < 6; i++) //fill 6 decks
  {
    for (Suit cardSuit : Suit.values()) // for each suit in enum
    {
      for (Value cardValue : Value.values()) // for each value in enum
      {
        this.cards.add(new Card(cardSuit, cardValue)); // populate deck
      }
    }
  }
}

// return deck as a formatted string
public String toString()
{
  String cardListOutput = ""; //create string to hold card info
  for (Card aCard : this.cards) //for each card in the arraylist of cards
  {
    cardListOutput += "\n" + aCard.toString(); //convert card to string and concatenate 52 cards
  }
  return cardListOutput; //return deck as string
}

public ArrayList<String> toArrayList()
{
  ArrayList<String> cardListOutput = new ArrayList<String>(); //create arraylist to hold card info
  for (Card aCard : this.cards) //for each card in the arraylist of cards
  {
    cardListOutput.add(aCard.toString()); //convert card to string and add to arraylist 52 cards
  }
  return cardListOutput; //return deck as arraylist of strings
}

//shuffle the deck
public void shuffle() 
{
  //Collections.shuffle(this.cards, new Random());
  ArrayList<Card> tmpDeck = new ArrayList<Card>(); //create a temporary deck to store shuffled cards
  RandomNumber randomNumber = new RandomNumber(); //initialize delay and random objects
  int randomCardIndex = 0; //represents random card index
  int originalSize = this.cards.size(); //store original size of deck
  for (int i = 0; i < originalSize; i++)
  {
    randomCardIndex = randomNumber.getRandomNumber((this.cards.size()-1), 0); //get a random index
    tmpDeck.add(this.cards.get(randomCardIndex)); //move picked card to temp deck
    this.cards.remove(randomCardIndex); //take picked card out of original deck
  }
  this.cards = tmpDeck; //set this.cards deck to shuffled deck (temp deck)
}

public void removeCard(int index) //setter to remove a card
{
  this.cards.remove(index);
}

public Card getCard(int index) //getter to get a card
{
  return this.cards.get(index);
}

public void addCard(Card addCard) //setter to add card
{
  this.cards.add(addCard);
}

public void draw(Deck comingFrom) //draw card from deck
{
  this.cards.add(comingFrom.getCard(0)); //add card that was drawn from the deck it is coming from
  comingFrom.removeCard(0); //remove card from deck it is coming from
}

public int cardsValue() //return total value of cards in deck
{
  int totalValue = 0; //total value of deck
  int aces = 0; //number of aces, aces can be 1 or 11 so they are valued seperately

  for (Card aCard: this.cards)
  {
    switch(aCard.getValue()) //test all diffrent cards and execute the specific block
    {
      case Two: totalValue += 2; break;
      case Three: totalValue += 3; break;
      case Four: totalValue += 4; break;
      case Five: totalValue += 5; break;
      case Six: totalValue += 6; break;
      case Seven: totalValue += 7; break;
      case Eight: totalValue += 8; break;
      case Nine: totalValue += 9; break;
      case Ten: totalValue += 10; break;
      case Jack: totalValue += 10; break;
      case Queen: totalValue += 10; break;
      case King: totalValue += 10; break;
      case Ace: aces += 1; break; //add 1 to number of aces
    }
  }
  for(int i = 0; i < aces; i++) //determine if ace has value 1 or 11, loop through every ace
  {
    if (totalValue > 10) //if totalvalue is more than 10, ace = 1 , because adding 11 would make the player bust
    {
      totalValue += 1; //ace valued at one
    }
    else //if adding ace as 11 wont bust the player
    {
      totalValue += 11; //ace valued at eleven
    }
  } 
  return totalValue;
} 

//return number of cards in the deck
public int deckSize() 
{
  return this.cards.size();
}

//transfer all cards from one deck to another
public void moveAllCardsToDeck(Deck moveTo)
{
  int thisDeckSize = this.cards.size();
  for (int i = 0; i < thisDeckSize; i++) //loop through every card to be put back
  {
      moveTo.addCard(this.getCard(i)); //add all cards to the deck they need to be moved to 
  }
  for (int i = 0; i < thisDeckSize; i++) //loop through every card in the deck to be emptied
  {
    this.removeCard(0); //empty the deck
  }
} 
} 