//
// Jozef Gjidoda
// Prog 4
// Due Date and Time: 3/29/21 before 9:30 a.m.
//
// Purpose: This program will compute golf score. Max, min, and avg of a number of grades.
// As well as a determine the max and how many times it appears in an array.
//
// Input: Input 1 for Let's Go Golfing!, 2 for Handle Grades, 3 for: How Many Maxes?, and 0 to Quit
// If 1: ask for par for each hole, then followed by users score for each hole
// If 2: ask for 10 grades 
// If 3: ask for maximum of 8 values, or input a negative if done early
//
// Output: Greet user and explain what program does. Then ask for their choice of 1,2,3 or 0 for quit
// If 1: ask for par for all holes, followed by ask for score of each hole 
// (if invalid input, prompts its wrong and asks again)
// then show eagles, birdies, pars, bogeys, double bogeys and other puts the user got
// If 2: ask for 10 grade scores (if invalid input, prompts its wrong and asks again), 
// then shows max, min, and average score
// If 3: ask for numbers for each array value (if invalid input, prompts its wrong and asks again)
// then shows max and how many times it appears
// If 0: says goodbye and thank you
//
// Certification of Authenticity:
// 
// I certify that this lab is entirely my own work.
//

import java.util.Arrays;
import java.util.Scanner;

public class ArraysGjidoda {

	static Scanner keyboard = new Scanner(System.in);

// Method that calculates the number of eagles, birdies, pars, bogeys, double bogeys and other puts the user got
public static int[] letsGolfCalc(int[] holesArray,  int[] putsArray)
{	
	int i, numEagles = 0, numBirdies = 0, numPars = 0, numBogeys = 0, numDoubleBogeys = 0, numOther = 0;
	int[] scoreArray = new int[9];
	
	scoreArray[0] = holesArray[0] - putsArray[0];
	scoreArray[1] = holesArray[1] - putsArray[1];
	scoreArray[2] = holesArray[2] - putsArray[2];
	scoreArray[3] = holesArray[3] - putsArray[3];
	scoreArray[4] = holesArray[4] - putsArray[4];
	scoreArray[5] = holesArray[5] - putsArray[5];
	scoreArray[6] = holesArray[6] - putsArray[6];
	scoreArray[7] = holesArray[7] - putsArray[7];
	scoreArray[8] = holesArray[8] - putsArray[8];

	for (i = 0; i < scoreArray.length; i++)
	{
		
		if (scoreArray[i] == 2)
			{
				numEagles++;
			}//if
		
		else if (scoreArray[i] == 1)
			{
				numBirdies++;
			}//else-if
		
		else if (scoreArray[i] == 0)
			{
				numPars++;
			}//else-if
		
		else if (scoreArray[i] == -1)
			{
				numBogeys++;
			}//else-if
		
		else if (scoreArray[i] == -2)
			{
				numDoubleBogeys++;
			}//else-if
		
		else 
			{
				numOther++;
			}//else
		
	}//for
	
	int[] totals = {numEagles, numBirdies, numPars, numBogeys, numDoubleBogeys, numOther};
	
	return totals;
}//letsGolfCalc

//Method that outputs the eagles, birdies, pars, bogeys, double bogeys and other puts the user got
public static void letsGolfOutput(int[] scores)
{
	System.out.println("Your Eagles: " + scores[0]);
	System.out.println("Your Birdies: " + scores[1]);
	System.out.println("Your Pars: " + scores[2]);
	System.out.println("Your Bogyes: " + scores[3]);
	System.out.println("Your Double Bogyes: " + scores[4]);
	System.out.println("Other: " + scores[5]);
}//letsGolfOutput

//Method that calculates the max grade in the array 
public static double calcMaxGrade(double [] gradeArrayMax)
{
	double max = gradeArrayMax[0];
	int i;
	
	for (i = 0; i < gradeArrayMax.length ; i++)
		{
			if (gradeArrayMax[i] > max)
				{
					max = gradeArrayMax[i];
				}//if
		}//for
	
	return max;
}//calcMaxGrade

//Method that calculates the min grade in the array 
public static double calcMinGrade(double [] gradeArrayMin)
{
	double min = gradeArrayMin[0];
	int i;
	
	for (i = 0; i < gradeArrayMin.length ; i++)
		{
			if (gradeArrayMin[i] <= min)
				{
					min = gradeArrayMin[i];
				}//if
		}//for
	
	return min;
}//calcMinGrade

//Method that calculates the avg grade in the array 
public static double calcAvgGrade(double [] gradeArrayAvg)
{
	double sum = 0.0, avg = 0.0;
	int i;
	
	for (i = 0; i < gradeArrayAvg.length ; i++)
		{
			sum+= gradeArrayAvg[i];
		}//for
		avg = sum / gradeArrayAvg.length;
	
	return avg;
}//calcAvgGrade

//Method that outputs the max, min and avg grades of the array
public static void gradeOutput(double gradeMaxNum, double gradeMinNum, double gradeAvgNum)
{
	System.out.println("The maximum grade: " + gradeMaxNum);
	System.out.println("The minimum grade: " + gradeMinNum);
	System.out.println("The average grade: " + gradeAvgNum);
}//gradeOutput

//Method that calculates and prints thee max value and how many times it appears
public static void calcMaxArray(int[] maxNum)
{
	int max = maxNum[0];
	int i, maxAppears = 0;
	
	for (i = 0; i < maxNum.length ; i++)
		{
			if (maxNum[i] > max)
				{
					max = maxNum[i];
				}//if
		}//for
	
	for(i = 0; i < maxNum.length; i++)
	    {
	        if(maxNum[i] != 0 && maxNum[i] == max)
		        {
		            maxAppears++;
		        }//if
	    
		}//for
	
	System.out.println("Max number: " +  max);
	System.out.println("Amount of times the max appears: " + maxAppears);
}//calcMaxArray

//main
public static void main(String[] args)
{
	//variables
	int[] parHoleArray = new int[9], holeScoreArray = new int[9], golfTotals, maxesArray = new int[8];
	double[] gradeArray = new double[10];
	int userID, i, maxesNum;
	double maxGradeNum, minGradeNum, avgGradeNum;

	//Greet
	System.out.println("Hello, this is a program that can tell you what your golf scores are.");
	System.out.println("Give you the highest, lowest, and average grades for a number of tests.");
	System.out.println("As well as detmine the max value of an array of numbers and the");
	System.out.println("number of times that maximum number appears.");
	System.out.println("\nInput 1 for: Let's Go Golfing!");
	System.out.println("Input 2 for: Hnadle Grades.");
	System.out.println("Input 3 for: How Many Maxes?");
	System.out.println("Input 0 to Quit");
	
	System.out.println("Your ID: ");
	userID = keyboard.nextInt();
	
	while (userID != 0)
		{
			//main do-while
			do
			{
				//Lets Go Golfing!
				if (userID == 1)
					{
						//Loop for length of array
						for (i = 0; i < parHoleArray.length; i++)
							{
								System.out.println("Enter par for hole " + (i + 1) + ": ");
								parHoleArray[i] = keyboard.nextInt();
								
								// if less or = to 0 it will ask to re-input a valid input
								if (parHoleArray[i] <= 0)
									{
										do
										{
										System.out.println("\nThats an invalid input!");
										System.out.println("Enter par for hole " + (i + 1) + ": ");
										parHoleArray[i] = keyboard.nextInt();
										}while(parHoleArray[i] <= 0);//do-while
									}//if
							}//for
						
						//Loop for length of array
						for (i = 0; i < holeScoreArray.length; i++)
							{
								System.out.println("Enter score for hole " + (i + 1) + ": ");
								holeScoreArray[i] = keyboard.nextInt();
								
								// if less or = to 0 it will ask to re-input a valid input
								if (holeScoreArray[i] <= 0)
									{
										do
										{
										System.out.println("\nThats an invalid input!");
										System.out.println("Enter score for hole " + (i + 1) + ": ");
										holeScoreArray[i] = keyboard.nextInt();
										}while(holeScoreArray[i] <= 0);//do-while
									}//if
							}//for
					
					//calls letsGolfCalc method
					golfTotals = letsGolfCalc(parHoleArray, holeScoreArray);
					//outputs
					letsGolfOutput(golfTotals);
					}//if
				
				//Handle Grades
				else if (userID == 2)
					{
						//Loop for length of array
						for (i = 0; i < gradeArray.length; i++)
							{
								System.out.println("Enter grade "  + (i + 1) + ": ");
								gradeArray[i] = keyboard.nextDouble();
								
								// if less than 0 or greater than 100 it will ask to re-input a valid input
								if (gradeArray[i] < 0 || gradeArray[i] > 100)
									{
										do
										{
											System.out.println("\nThats an invalid input!");
											System.out.println("Enter grade "  + (i + 1) + ": ");
											gradeArray[i] = keyboard.nextDouble();
										}while(gradeArray[i] < 0 || gradeArray[i] > 100);//do-while
									}//if
							}//for
						
						//call calculation methods
						maxGradeNum = calcMaxGrade(gradeArray);
						minGradeNum = calcMinGrade(gradeArray);
						avgGradeNum = calcAvgGrade(gradeArray);
						//outputs
						gradeOutput(maxGradeNum, minGradeNum, avgGradeNum);
					}//else-if
				
				//How Many Maxes?
				else if (userID == 3)
				{
					i = 0;
					do
					{
						System.out.println("Enter your number for value " + (i + 1) + ": ");
						maxesNum = keyboard.nextInt();
						
						//if less than 0 break
						if (maxesNum > 0) 
							{
								maxesArray[i] = maxesNum;
								i++;
							}//if

					}while(maxesNum > 0 && i < maxesArray.length);//do-while

					//call method for calculations and outputs
					calcMaxArray(maxesArray);
				}//else-if					
				
				//if user doesn't input correct number that correlates to 1,2,3, or 0
				else
				{
					System.out.println("\nThats an invalid input!");
				}//else
				
				System.out.println("\nInput 1 for: Let's Go Golfing!");
				System.out.println("Input 2 for: Handle Grades.");
				System.out.println("Input 3 for: How Many Maxes?");
				System.out.println("Input 0 to Quit");
				System.out.println("Your ID: ");
				userID = keyboard.nextInt();
				
			}while (userID != 0);//do-while
			
		}//while
	
		//goodbye
		System.out.println("");
		System.out.println("Thank you! Goodbye.");
		keyboard.close();
		
	}//main		

}//ArraysGjidoda
