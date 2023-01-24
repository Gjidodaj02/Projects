//
// Jozef Gjidoda
// Prog 5
// Due Date and Time: 4/5/21 before 9:30 a.m.
//
// Purpose: This program will draw, and or print details of the steps. The user
// is also able to set the width, number, and filler of the steps.
//
// Input: Input W to assign the width of the steps, Input N to assign the number of steps,
// Input F to assign your step fillers
// If W: grabs users desired step width (if invalid input, prompts its wrong and asks again)
// If N: grabs users desired step height (if invalid input, prompts its wrong and asks again)
// If F: grabs users desired step filler (if invalid input, prompts its wrong and asks again)
//
// Output: Greet user and explain what program does. Then ask for their choice of w, f, a, t, d, or q
// If W: ask for desired step width (if invalid input, prompts its wrong and asks again)
// If N: ask for desired step height (if invalid input, prompts its wrong and asks again)
// If F: ask for desired step filler (if invalid input, prompts its wrong and asks again)
// If A: tells the area of the steps
// If T: prints width, number, style and area of steps
// If D: draws the steps
// If Q: says goodbye and thank you
//
// Certification of Authenticity:
// 
// I certify that this lab is entirely my own work.
//

import java.util.Scanner;

public class StepsDemoGjidoda {
	static Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args) 
		{
			//variables
			int width = 2, numSteps = 5;
			char fillStyle = '*', userInput;
			StepsGjidoda methods = new StepsGjidoda(width, numSteps, fillStyle);//initialize default step
			
			//Greet
			System.out.println("Hello, this is a program that will print stairs depending on");
			System.out.println("the width and number of steps that you choose. You can also");
			System.out.println("choose what the stairs will be printed out as!");
			System.out.println("\nInput W to assign the width of the steps");
			System.out.println("Input N to assign the number of steps");
			System.out.println("Input F to assign your step fillers");
			System.out.println("Input A to calculate and print the area");
			System.out.println("Input T to show description of the steps");
			System.out.println("Input D to draw the steps");
			System.out.println("Input Q to Quit");
			System.out.println("\nEnter which you would like to do: \n");
			userInput = keyboard.next().charAt(0);
			userInput = Character.toUpperCase(userInput);
			
			while (userInput != 'Q')
			{
				do {
					switch (userInput)
						{
							case 'W':
								do {
									System.out.println("Enter your width: "); 
									width = keyboard.nextInt();
										if(width <= 0)
											{ 
												System.out.println("\nThats an invalid input!");
											}//if invalid
								}while (width <= 0);//do while
								
								methods.setStepWidth(width);
								break;
						
							case 'N':
								do {
									System.out.println("Enter your steps: "); 
									numSteps = keyboard.nextInt();
										if(numSteps <= 0)
											{ 
												System.out.println("\nThats an invalid input!");
											}//if invalid
								}while (numSteps <= 0);//do while
								
								methods.setNumSteps(numSteps);
								break;
								
							case 'F':
								System.out.println("Enter your style: "); 
								fillStyle = keyboard.next().charAt(0);
								methods.setFillStyle(fillStyle);
								break;
								
							case 'A':
								System.out.println("Total area of fill style characters: " + methods.calcArea());
								break;
								
							case 'D':
								methods.drawSteps();
								break;
								
							case 'T':
								System.out.println(methods.toString());
								break;
					}//switch
					
					if (userInput != 'W' && userInput != 'N' && userInput != 'F' && userInput != 'A' && userInput != 'D' && userInput != 'T')
						{
							System.out.println("\nThats an invalid input!");
						}//if userInput if invalid
					
					//reprint for userInput
					System.out.println("\nInput W to assign the width of the steps");
					System.out.println("Input N to assign the number of steps");
					System.out.println("Input F to assign your step fillers");
					System.out.println("Input A to calculate and print the area");
					System.out.println("Input T to show description of the steps");
					System.out.println("Input D to draw the steps");
					System.out.println("Input Q to Quit");
					System.out.println("\nEnter which you would like to do: \n");
					userInput = keyboard.next().charAt(0);
					userInput = Character.toUpperCase(userInput);
					
				} while (userInput != 'Q');//do while
			}//while
			
			//goodbye
			System.out.println("\nThank you! Goodbye.");
			keyboard.close();
			
		}//main
}//StepsDemoGjidoda
