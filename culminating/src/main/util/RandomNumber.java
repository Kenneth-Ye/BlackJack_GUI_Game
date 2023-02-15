package main.util;

//Name: Kenneth
//Program name : Delay
//Purpose: make a method that delays program execution, and a program that gets a random number in a range, and a method to slowprint
//Date: March 7 2022

public class RandomNumber 
{
	//method to get a random number within a range
	public int getRandomNumber(int min, int max) //create random number generator within a range
	{ 
	  return (int) ((Math.random() * (max - min)) + min); 
	}
}

