//
// Jozef Gjidoda
// Prog 3
// Due Date and Time: 3/22/21 before 9:30 a.m.
//
// Purpose: This program will compute hospital bills for you, and show 
// average numbers if multiple patients are inputed
//
// Input: houseIncomem, patientID, insurancePlan, numDays
//
// Output: numberPat, highBill, highID, lowBill, lowID, totals, average, idNum, 
// homeIncome, plan, numberDays, diemPer, servFee, discount, totalBill, 
// ask for: ID, household income, insurance plan, number of days, 
// if input is wrong, print that it was invalid
//
// Certification of Authenticity:
// 
// I certify that this lab is entirely my own work.
//

import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Locale;


public class HospitalGjidoda {
	
	static Scanner keyboard = new Scanner(System.in);
	
	// method that calculates and returns per diem, depending on income
	public static double calcPerDiem(String insurancePlan, double income)
		{
			double ans = 0.0;
			
			switch (insurancePlan)
				{
					case "Green Plus":
						if (income < 20000)
							{
								ans = 65;
							}//if
						else if (income <= 77500)
							{
								ans = 80;
							}//else if
						else 
							{
								ans = 150;
							}//else
						
						break;
					
					case "Doc-Health":
						if (income < 40000)
							{
								ans = 60;
							}//if
						else if (income <= 75000)
							{
								ans = 90;
							}//else if
						else 
							{
								ans = 140;
							}//else
						
						break;
						
					case "Health Plan":
						if (income < 17500)
							{
								ans = 55;
							}//if
						else if (income <= 53000)
							{
								ans = 70;
							}//else if
						else
							{
								ans = 130;
							}//else
						
						break;
						
					case "No Insurance":
						
						ans = 400;
						
						break;
						
				}//switch
			return ans;
		}//calPerDiem
		
	// method that calculates service fee
	public static double calcServiceFee(double perDiem, int numDays)
		{
			double sum = 0.0;
			
			sum = perDiem * numDays;
			
			return sum;
		}//calcServiceFee
	
	// method that calculates discount
	public static double calcDiscount(int numDays)
		{
			double total = 0.0;
			int weeks;
			
			if (numDays > 21)
				{
					weeks = numDays / 7; 
					total = weeks * 250;
				}//if
		
			return total;
		}//calcDiscount
	
	// method that calculated total bill
	public static double calcTotalBill(double servFee, double disc)
		{
			double totalBill = 0.0;
			
			totalBill = (servFee + 400) - disc;
			
			return totalBill;
		}//calcTotalBill
	
	// method that prints all info that is specific to each patient, (in main do-while)
	public static void outputResults(int idNum, String homeIncome, String plan, 
			int numberDays, String diemPer, String servFee, String discount, String totalBill, String admitt)
		{
			System.out.println("Your ID: " + idNum);
			System.out.println("Your Income: " + homeIncome);
			System.out.println("Your Insurance Plan is " + plan);
			System.out.println("Number of Days in hospital: " + numberDays);
			System.out.println("Your Admittance: " + admitt);
			System.out.println("Your Per Diem rate: " + diemPer);
			System.out.println("Your Service Fee: " + servFee);
			System.out.println("Your discount: " + discount);
			System.out.println("Your total bill: " + totalBill);
			
			
		}//outputResults
	
	// method that prints all final data after the do-while loop
	public static void finalOutput(int numberPat, String highBill, int highID, String lowBill, 
			int lowID, String totals, String average)
		{
			System.out.println("Number of Patients Processed: " + numberPat);
			System.out.println("Highest Bill: " + highBill);
			System.out.println("Patient ID associated with highest bill: " + highID);
			System.out.println("Lowest Bill: " + lowBill);
			System.out.println("Patient ID associated with lowest bill: " + lowID);
			System.out.println("Total cost of all bills: " + totals);
			System.out.println("Average cost of all bills: " + average);
			// goodbye
			System.out.println("");
			System.out.println("Thank you! Goodbye.");
		}//finalOutput
	
	public static void main(String[] args)
		{
			//variables
			int patientID, highestID = 0, lowestID = 0, numDays, numPatients = 0;
			double houseIncome, admittance = 400, totalCost, perDiem, servFee, totalDiscount, avgBill = 0.0,
			totalBills = 0.0, lowestBill = Integer.MAX_VALUE, highestBill = Integer.MIN_VALUE;
			char insurancePlan;
			String fullInsurePlan = null, totalCurr, admittCurr, avgCurr, highBillCurr, lowBillCurr, 
			houseIncomeCurr, perDiemCurr, servFeeCurr, totalDiscCurr, totalCostCurr;
			NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
			
			// Greet
			System.out.println("Hello, this is a program that will calculate your hospital bills");
			System.out.println("As well as show avg bill costs of multiple patients!");
			
			System.out.println("Your ID: ");
			patientID = keyboard.nextInt();
			
			
			while (patientID != 0)
				{
					//main do-while
					do
					{
						
						do
						{
						System.out.println("What is your household income? ");
						houseIncome = keyboard.nextDouble();
							if (houseIncome < 0)
								{
									System.out.println("That was an invalid input, try again.");
								}//if
						}while (houseIncome < 0);//do-while
						
						do
						{
							System.out.println("What is your insurance plan? ");
							System.out.println("G for Green Plus\nD for Doc-Healthy\nH for Health Plan\nN for No Insurance");
							insurancePlan = keyboard.next().charAt(0);
							insurancePlan = Character.toUpperCase(insurancePlan);
							
								if (insurancePlan == 'G') 
									{
										fullInsurePlan = "Green Plus";
									}//if Green Plus
							
								else if (insurancePlan == 'D') 
									{
										fullInsurePlan = "Doc-Health";
									}//if Doc-Health
								
								else if (insurancePlan == 'H') 
									{
										fullInsurePlan = "Health Plan";
									}//if Health Plan
								
								else if (insurancePlan == 'N') 
									{
										fullInsurePlan = "No Insurance";
									}//if No Insurance
								
								if (insurancePlan != 'G' && insurancePlan != 'D' && insurancePlan != 'H' && insurancePlan != 'N')
									{
										System.out.println("That was an invalid input, try again.");
									}
						} while (insurancePlan != 'G' && insurancePlan != 'D' && insurancePlan != 'H' && insurancePlan != 'N');// do-while
						
						do 
						{
							System.out.println("How many days have you been in the hospital? ");
							numDays = keyboard.nextInt();
							
								if (numDays < 1 || numDays > 365)
									{
										System.out.println("That was an invalid input, try again.");
									}//if
						}while (numDays < 1 || numDays > 365);// do-while
						
						perDiem = calcPerDiem(fullInsurePlan, houseIncome);
						
						servFee = calcServiceFee(perDiem, numDays);
						
						totalDiscount = calcDiscount(numDays);
					
						totalCost = calcTotalBill(servFee, totalDiscount);
						
						// converts variables to currency
						admittCurr = format.format(admittance);
						houseIncomeCurr = format.format(houseIncome);
						perDiemCurr = format.format(perDiem);
						servFeeCurr = format.format(servFee);
						totalDiscCurr = format.format(totalDiscount);
						totalCostCurr = format.format(totalCost);
						outputResults(patientID, houseIncomeCurr, fullInsurePlan, numDays, perDiemCurr, 
						servFeeCurr, totalDiscCurr, totalCostCurr, admittCurr);
							
						// determines the highest or lowest bill and ID
							if (totalCost > highestBill)
								{
									highestBill = totalCost;
									highestID = patientID;
								}//if
							
							if (totalCost < lowestBill) 
							{
								lowestBill = totalCost;
								lowestID = patientID;
							}//if
	
						System.out.println("Your ID: ");
						patientID = keyboard.nextInt();
						
						totalBills = totalBills + totalCost;
						numPatients ++;
						
					}while (patientID != 0); //do-while
				
				avgBill = totalBills / numPatients;
				
			}//while
				
				// If the immediately input 0 as patient id
				if (numPatients == 0)
					{
						highestBill = 0;
						lowestBill = 0;	
					}//if
				
			totalCurr = format.format(totalBills);
			highBillCurr = format.format(highestBill);
			lowBillCurr = format.format(lowestBill);
			avgCurr = format.format(avgBill);
			
			finalOutput(numPatients, highBillCurr, highestID, lowBillCurr, lowestID, totalCurr, avgCurr);
			keyboard.close();
		}//main
}//HospitalGjidoda
