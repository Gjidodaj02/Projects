public class Problem1 {

	public static void intersection(int[] s1, int[] s2) {
		// complete the intersection() method to output
		// elements that occur in both s1 and s2
		// feel free to change method type and parameters
		// Full credit will awarded to algorithms O(n) and in-place

		//Variables
		int i = 0, j = 0, m = s1.length, n = s2.length;

		while (i < m && j < n) {
			if (s1[i] < s2[j]) {
				i++;
			}//if

			else if (s2[j] < s1[i]) {
				j++;

			}//else-if

			else {
				System.out.print(s2[j++] + " ");
				i++;
			}//else
			
		}//while

	}//intersection

	public static void union(int[] s1, int[] s2) {
		// complete the union() method to output
		// the union s1 and s2
		// feel free to change method type and parameters
		// Full credit will awarded to algorithms O(n) and in-place

		//variables
		int m = s1[s1.length - 1], n = s2[s2.length - 1], ans = 0;

		if (m > n) {
			ans = m;
		}//if

		else {
			ans = n;
		}//if

		int newtable[] = new int[ans + 1];

		System.out.print(s1[0] + " ");
		++newtable[s1[0]];

		for (int i = 1; i < s1.length; i++) {

			if (s1[i] != s1[i - 1]) {
				System.out.print(s1[i] + " ");
				++newtable[s1[i]];
			} //if
		}//for

		for (int j = 0; j < s2.length; j++) {

			if (newtable[s2[j]] == 0) {
				System.out.print(s2[j] + " ");
				++newtable[s2[j]];
			} // if
		}//for
	}//union

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Test your intersection() method here
		int[] testarray1 = { 0, 0, 0, 1, 2, 3, 97, 98 };
		int[] testarray2 = { 0, 1, 2, 3, 4, 4, 10, 98, 100, 100 };

		System.out.println("intersection of testarray1 and testarray2: ");
		intersection(testarray1, testarray2);
		// should output 0, 1, 2, 3, 98
		System.out.println("\nunion of testarray1 and testarray2: ");
		union(testarray1, testarray2);
		// should output 0, 1, 2, 3, 4, 10, 97, 98, 100
	}//main

}//Problem1
