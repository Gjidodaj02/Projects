class Problem1
{     
    
    public static boolean Equal(int[] A, int s, int e)
    {
		
		if (s == e)  // base case 1: there is just one element in the array A[s,...,e]
		// s refers to starting index, e ending index 
			return true;
		else if (e== s+1 && A[s] != A[e]) // base case 2: there are just two elements in the array A[s,...,e], and A[s]!= A[e]
			return false;
		else if (e== s+1 && A[s] == A[e]) // base case 3: there are just two elements in the array A[s,...,e], and A[s]= A[e]
			return true;
		// Complete it from here
		// The base cases are all done
		// Write the general case when there are >= 3 elements in A[s,...,e]
		// Return true if all elements in A[] are equal, false otherwise
		// Full credits (30 points) will be awarded to Recursive Algorithm ONLY
		// Iterative Algorithm Will be scored out of 5 points
		// Feel free to change the return type, the parameters of the function

		else
		{

			if (s==e && A[s] == A[e]) 
			{
				return true;
			}//if

			if (A[s] != A[e])
			{
				return false;
			}//if

			return Equal(A, s+1, e);
		}//else

    }//Equal     
     
    public static void main (String[] args)
    {
        // Test the function 
        int test1[] = {7, 7, 7, 2};
        System.out.println("Are all elements in array test1[] equal?  " + Equal(test1, 0, test1.length -1));
		// False expected
		int test2[] = {5, 5, 5, 5};
        System.out.println("Are all elements in array test2[] equal?  " + Equal(test2, 0, test2.length -1));
		// True expected
		
    }//main
    
}//Problem1
 