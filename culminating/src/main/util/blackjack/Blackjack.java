package main.util.blackjack;

//Name: Kenneth
//Date: May 17 2022
//Program name: Blackjack
//Purpose: create the main blackjack game code


public class Blackjack
{
	//determines if player or dealer has busted
	public boolean hasBusted(int handValue)// String playerOrDealer
	{
	  if (handValue > 21) //if busted
	  {
	    return true; 
	  }
	  return false; 
	}

} 