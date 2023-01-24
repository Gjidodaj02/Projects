//
// Jozef Gjidoda
// Prog 2
// Due Date and Time: 3/8/21 before 11:59 a.m.
//
// Purpose: This program will compute taxes for you, and even show 
// average numbers if multiple taxes are inputs
//
// Input: taxID, g_income, exemptions, filingStatus, tax_income, tax_owed,
// num_taxpayers, total_taxes, avg_tax_paid, highest_taxID, highest_tax_paid,
// tax_owed_curr, tax_income_curr, avg_tax_curr, highest_tax_curr, tax_total_curr,
// PATTERN2, house_status, format, df
//
// Output: intro. ask for Id, gross income, exemptions, filing status. then 
// show tax income, taxes owed. reprint id and filing status. once there
// are no more taxpayers, print, # of tax payers, total taxes, average tax,
// highest tax id, high tax paid. Then a goodbye
//
// Certification of Authenticity:
// 
// I certify that this lab is entirely my own work.

import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Scanner;

public class TaxesGjidoda 
{

	
	//declare one keyboard for the project
		static Scanner keyboard = new Scanner(System.in);
		
		public static void main(String[] args)
			{
				// Variables
				int taxID = 0;
				int g_income = 0;
				int exemptions = 0;
				int tax_rate = 0;
				int num_taxpayers = 0;
				int highest_taxID = 0;
				double tax_income = 0.0;
				double tax_owed = 0.0;
				double total_taxes = 0.0;
				double avg_tax_paid = 0.0;
				double highest_tax_paid = 0;
				char filingStatus;
				String tax_income_curr, avg_tax_curr, highest_tax_curr, tax_total_curr, tax_owed_curr;
				String PATTERN2 = "$#,##0.00;-$#,##0.00"; 		
				String house_status = null;
				NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);	
				DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
				
				//Greet User
				System.out.println("Hello, this is a fun program that will");
				System.out.println("help compute taxes for you!");
				
				// Getting ID number
				System.out.println("Enter your ID: ");
				taxID = keyboard.nextInt();
				while (taxID != 0)
					{
						do 
							{
								
								// Getting filing status
								System.out.println("What is your Filing Status? ");
								System.out.println("I for individual\nM for Marries filing Jointly\nH for Head of household");
								filingStatus = keyboard.next().charAt(0);
								filingStatus = Character.toUpperCase(filingStatus);
								// If filing status is inputed wrong,
								// it will ask continuously until correct
								while (filingStatus != 'I' && filingStatus != 'M' && filingStatus != 'H')
									{
										do
											{
												System.out.println("That was an invalid input, try again.");
												System.out.println("What is your Filing Status? ");
												System.out.println("I for individual\nM for Marries filing Jointly\nH for Head of household");
												filingStatus = keyboard.next().charAt(0);
												filingStatus = Character.toUpperCase(filingStatus);
											} while (filingStatus != 'I' && filingStatus != 'M' && filingStatus != 'H');// do-whilee
									}//while filing status
								
								// Get Gross Income
								System.out.println("Enter your gross income: ");
								g_income = keyboard.nextInt(); 
								
								// Get amount of exemptions
								System.out.println("Enter your exemptions: ");
								exemptions = keyboard.nextInt();
								// If exemptions is below 0 or greater than 10
								// It will prompt user to re print exemptions correctly
								while (exemptions < 0 || exemptions > 10)
									{
										do
											{
												System.out.println("There can only be 0-10 exemptions");
												System.out.println("Enter your exemptions: ");
												exemptions = keyboard.nextInt();
											} while (exemptions < 0 || exemptions > 10);// do-while for exemptions
									}//while exemptions
								// multiplies exemptions by 1500 to get total exemption 
								exemptions = exemptions * 1500;
								
								// Computes for tax income
								tax_income = g_income - 2275;
								tax_income = tax_income - exemptions;
									
								switch (filingStatus)
									{
									case 'I':		
										if (tax_income < 15000 && tax_income > 0)
											{
												tax_rate = 13;
												tax_owed = tax_income * .13;
											}// if for tax income < 15,000 (case I)
										else if (tax_income <= 78000)
											{
												tax_rate = 21;
												tax_owed = tax_income * .21;
											}// else tax_income
										else 
											{
												tax_rate = 30;
												tax_owed = tax_income * .30;
											}// else tax_income
										break;
										
									case 'M':
										if (tax_income < 20000 && tax_income > 0)
											{
												tax_rate = 17;
												tax_owed = tax_income * .17;
											}// if for tax income < 20,000 (case M)
										else if (tax_income <= 90000)
											{
												tax_rate = 24;
												tax_owed = tax_income * .24;
											}// else tax_income
										else 
											{
												tax_rate = 35;
												tax_owed = tax_income * .35;
											}// else tax_income
										break;
										
									case 'H':				
										if (tax_income < 24000 && tax_income > 0)
											{
												tax_rate = 16;
												tax_owed = tax_income * .16;
											}// if for tax income < 24,000 (case H)
										else if (tax_income <= 85000)
											{
												tax_rate = 27;
												tax_owed = tax_income * .27;
											}// else tax_income
										else 
											{
												tax_rate = 38;
												tax_owed = tax_income * .38;
											}// else tax_income
										break;
								
									}// switch()
			
								// converter, double into currency
								tax_income_curr = format.format(tax_income);
								if (tax_income <= 0)
									{
										tax_rate = 0;
										tax_owed = 0;
										df.applyPattern(PATTERN2);
										tax_income_curr = df.format(tax_income);
									}//if tax_income
								
								if (filingStatus == 'I') 
									{
										house_status = "Individual.";
									}//if Individual
								
								if (filingStatus == 'M') 
									{
										house_status = "Married filing Jointly.";
									}//if Married filing jointly
								
								if (filingStatus == 'H') 
									{
										house_status = "Head of Household.";
									}//if head of household
								
								tax_owed_curr = format.format(tax_owed);
								// all outputs for the inside of the do-while
								System.out.println("Your ID: " + taxID);
								System.out.println("Your Filing Status is listed as " + house_status);
								System.out.println("Taxable Income " + tax_income_curr);
								System.out.println ("Tax Rate: " + tax_rate + "%");
								System.out.println("Tax amount owed = " + tax_owed_curr);
								
								// Keeps track of num of tax payers
								// and sum of all the taxes
								num_taxpayers ++;
								total_taxes = total_taxes + tax_owed;
								
								if (tax_owed > highest_tax_paid)
									{
										highest_tax_paid = tax_owed;
										highest_taxID = taxID;
									}//if for calculating highest paid tax
								
								// Getting ID number
								System.out.println("Enter your ID: ");
								taxID = keyboard.nextInt();
							} while (taxID != 0); //do
					}//while
				tax_total_curr = format.format(total_taxes);
				highest_tax_curr = format.format(highest_tax_paid);
				avg_tax_paid = total_taxes / num_taxpayers;
				if (num_taxpayers == 0)
					{
						avg_tax_paid = 0;
					}//if num_taxpayers
				avg_tax_curr = format.format(avg_tax_paid);
				
				System.out.println("Summary:\nNumber of Taxpayers: " + num_taxpayers);
				System.out.println("Highest Tax Paid: " + highest_tax_curr);
				System.out.println("Highest Tax ID Number: " + highest_taxID);
				System.out.println("Total Taxes Paid: " + tax_total_curr);
				System.out.println("Average Tax Paid: " + avg_tax_curr);
				System.out.println("\nThank you! Goodbye.");
				keyboard.close();
			}// main()
}// TaxesGjidoda