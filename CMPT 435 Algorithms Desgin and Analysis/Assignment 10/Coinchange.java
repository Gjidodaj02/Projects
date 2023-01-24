import java.util.Scanner;

public class Coinchange {

	public static int greedycoinchange(int givenvalue, int[] givencoins)
	{
		
		// Complete the code here to make change of givenvalue using coins in the array givencoins
		// Minimize the number of coins used
		// Input: Coin denominations in array givencoins are already sorted in descending order
		// Output: The number of coins used to make change of givenvalue		
		int val = givenvalue; 
		int count = 0;
		for (int i = 0; i < givencoins.length; i++)
		{
			while(givencoins[i] <= val)
			{
				count++;
				val = val - givencoins[i];
			}//while
		}//for

		return count;
	}//greedycoinchange
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int n = 0; // n cents
		
		Scanner reader = new Scanner(System.in);  
		
		System.out.println("Please enter the value you want to make change: ");
		
		n = reader.nextInt();
		
		int[] coins = {10, 5, 1};
		// Add various denominations to test the program
		
		System.out.println("used "+ greedycoinchange(n, coins)+" coins for "+ n + " cents");

	}//main

}//Coinchange
