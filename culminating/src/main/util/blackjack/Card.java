//Name: Kenneth
//Date: May 17 2022
//Program Name: Card
//Purpose: construct a card, and be able to print, and get cards value
package main.util.blackjack;
public class Card
{
  private Suit suit; //access suit
  private Value value; //access value

  public Card(Suit suit, Value value) //constructer for card object
  {
    this.value = value;
    this.suit = suit;
  }

  public String toString() //convert card to string
  {
    return "[ " + this.value.toString() + " of " + this.suit.toString() + " ]"; //return value and suit in a formatted string
  }

  public Value getValue() //getter for card value
  {
    return this.value;
  }
}