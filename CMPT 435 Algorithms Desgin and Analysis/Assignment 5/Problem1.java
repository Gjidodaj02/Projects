public class Problem1 {

	public static int maxSubSum(int[] A, int L, int R)
	{
		// Complete the method to find the maximum sum of the contiguous subsequence in A[]
		// feel free to change the return type, parameters
		// full credits will be awarded to divide and conquer
		
		//Variables
		int mid = (L + R) / 2, Rmax = Integer.MIN_VALUE, Lmax = Integer.MIN_VALUE, sum = 0;

		//Case to check if element has 1 element
		if (R == L)
		{
			return A[L];
		}//if

		//loop through right side of array
		for (int i =  mid+1; i <= R; i++)
		{
			sum += A[i];
			if (sum > Rmax)
			{
				Rmax = sum;
			}//if
		}//for

		//loop through left side of array
		sum = 0;
		for (int i = mid; i >= L; i--)
		{
			sum += A[i];
			if (sum > Lmax)
			{
				Lmax = sum;
			}//if
		}//for

		//find the max sum for both sides, and return the max 
		int maxOfLeftRight = Integer.max(maxSubSum(A, L, mid), maxSubSum(A, mid + 1, R));
		
		//return the max
		return Integer.max(maxOfLeftRight, Lmax + Rmax);
    }//maxSubSum
	
	public static int maxSum(int[] A)
	{
		//Variables
		int len = A.length; 

		//base case to check if empty 
		if (len == 0)
		{
			return 0;
		}//if

		//look through the array and return the Sub Sum
		return maxSubSum(A, 0, len-1);
	}//maxSum
	
	public static void main(String[] args)
    {
		//Variables
		int[] A = {4,-20,12,5,4,-10,2,5,-5,3,2,1,1,-10,-4,5};
	
        System.out.println("The maximum sum of the contiguous subsequence is " + maxSum(A));
		// 21 expected
		/* Another test case
		A[]={10, -20, 3, 4, 5, -1, -1, 12, -3} 
		In the array above, the contiguous subsequence with the largest sum is
		{3, 4, 5, -1, -1, 12}, and it has a sum of
		3+4+5+-1+-1+12 = 22 
		*/

    }//main

}//Problem 1
