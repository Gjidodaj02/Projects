
public class Problem2
{

public static Max2ndMax dcfindmax2ndmax(int[] A, int i, int j)
	{
		// Complete this method to find max and 2nd max in an array A
		// At most 3/2n-2 comparisons
		// Algorithms that make more comparisons will be scored out of 10 points
		// Complete the code from here
		// Feel free to change method type, parameters
		
		// The class Max2ndMax is defined in a seperate Java class in Assignment 5 folder
		
		//Variables
		Max2ndMax theResult = new Max2ndMax();

		//base case 1
		if(i == j)
		{
			theResult.max = A[i];
			theResult.max2nd = A[i];
		}//if

		//base case 2
		else if(j == i+1 && A[j] < A[i])
		{
			if(A[0] > A[1])
			{
				theResult.max = A[i];
				theResult.max2nd = A[j];
			}//if

			else
			{
				theResult.max = A[j];
				theResult.max2nd = A[i];
			}//else
		}//else if

		//general case
		else
		{
			int mid = (i+j) / 2;
			Max2ndMax temp1 = dcfindmax2ndmax(A, i, mid), temp2 = dcfindmax2ndmax(A, mid+1, j);
		
			if(temp1.max > temp2.max)
			{
				theResult.max = temp1.max;
				theResult.max2nd = temp2.max; 
			}//if

			else
			{
				theResult.max = temp2.max;
				theResult.max2nd = temp1.max; 
			}//else
		}//else
		return theResult;
	}//dcfindmax2ndmax
	
	public static void main(String[] args) {
		// TODO Auto-generated method
		int[] givenarray = {100,2,3,4,5,6,7,67,2,32};	
		
		// Test your method
		Max2ndMax pair = new Max2ndMax();
		// Java does pass-by-value not by-reference
		// Therefore we create a class of Max2ndMax for max and 2ndmax pair
		pair = dcfindmax2ndmax(givenarray, 0, givenarray.length-1);
		int max2nd = pair.max2nd;
		int max = pair.max;
		System.out.println("The max and 2ndmax of the given array are "+ max + " and "+max2nd+".");
		// Your method should return 100 and 67

	}//main
	
}//Problem2