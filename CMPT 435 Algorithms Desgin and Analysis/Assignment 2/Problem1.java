import java.util.Arrays;

public class Problem1 {

	public static int[] removeduplicates(int[] A)
	{	
		// Complete this method
		// Return a new array with all duplicates in A[] removed 
		

		

		//A more efficient way to go about this is by changing "int" to Integer variables 
		//importing LinkedHashSet and using the code below:

		//LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>( Arrays.asList(A) );
        //Integer[] numbersWithoutDuplicates = linkedHashSet.toArray(new Integer[] {});  

		//however, this is a way you could go about doing it with int

		//variables
		int len = A.length, count = 0;
		Arrays.sort(A);

		//loop thorugh the length of the list 
		for (int i = 0; i < len; i++)
		{
			//if its not the first num 
			//and the current num != the last num
			//count the dupes
			if (i != 0 && A[i] == A[i-1])
			{
				count++;
			}//if

		}//for

		//variables
		int newLen = len - count, h = 0;
		int[] newArray = new int[newLen];
		
		//loop through length of original list
		for (int i = 0; i < len; i++)
		{	
			//if it is 0, assign the first num
			if (i == 0)
			{
				newArray[h] = A[i];
				h++;
			}//if

			// if it != 0 and != the last num
			else if (i != 0 && A[i] != A[i-1])
			{
				newArray[h] = A[i];
				h++;
			}//else-if

		}//for

		//return the new array
		return newArray;
		
	}//removeduplicates
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Test removeduplicates()
		
		int[] testarray1 = {-5, 2, 3, 1, 2, 2, 1};
		
		System.out.println("The array after removing duplicates "+ Arrays.toString(removeduplicates(testarray1)));
		// expected output: {2, 1, 3, -5}	

	}//main

}//Problem1
