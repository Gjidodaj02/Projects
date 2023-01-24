public class SubsetSumDP {

	public static boolean subsetSum(int[] A, int x) {

		boolean[][] Sum = new boolean[A.length + 1][x + 1];
		// Complete the function from here
		// Feel free to change the return type and parameters
		// Implement the dynamic programming Subset Sum Algorithm
		// Return true if there exists a subset in A with sum = x
		// Hint: Follow the pseudocode of subsetSum in the slides

		for (int i = 1; i < A.length; i++) {
			Sum[i][0] = true;
		} // for

		for (int j = 1; j <= x; j++) {
			Sum[0][j] = false;
		} // for

		for (int i = 1; i <= A.length; i++) {
			for (int j = 1; j <= x; j++) {
				if (A[i - 1] > j) {
					Sum[i][j] = Sum[i - 1][j];
				}

				else {
					Sum[i][j] = Sum[i - 1][j] || Sum[i - 1][j - A[i - 1]];
				} // else
			} // for
		} // for

		return Sum[A.length][x];
	}//subsetSum

	public static void main(String[] args) {

		int[] A = { 1, 3, 5, 2, 8 };

		int x = 9;

		System.out.println("There exists a subset in A[] with sum = " + x + " : " + subsetSum(A, x));
		// Expected output: true
	}//main

}//SubsetSumDP
