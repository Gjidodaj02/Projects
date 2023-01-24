
public class Problem2 {

	// The original partition function
	public static int partition(int[] A, int s, int e) {
		int pivot = A[s];
		int i = s + 1;
		int j = e;

		while (i <= j) {

			while (i < e & A[i] < pivot) {
				i = i + 1;
			}//while

			while (j > s & A[j] >= pivot) {
				j = j - 1;
			}//while

			if (i >= j) {
				break;
			}//if

			swap(A, i, j);

		}//while

		swap(A, s, j);
		return j;

	}//partition

	// The original quicksort function
	public static void quicksort(int[] A, int s, int e) {
		if (s < e) {
			int p = partition(A, s, e);

			quicksort(A, s, p - 1);
			quicksort(A, p + 1, e);

		}//if

	}//quicksort

	public static void swap(int[] A, int i, int j) {
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;

	}//swap

	public static int[] partition_2pivots(int[] A, int s, int e) {
		// Randomly select 2 pivots and partition the array
		// Return the position of 2 pivots after partition
		// Complete the function
		// Feel free to change the return type and parameters

		if (A[s] > A[e]) {
			swap(A, s, e);
		}//if

		//Variables
		int j = s + 1, g = e - 1, k = s + 1, p = A[s], q = A[e];

		while (k <= g) {

			// < the left pivot
			if (A[k] < p) {
				swap(A, k, j);
				j++;
			}//if

			//>= to the right pivot
			else if (A[k] >= q) {
				while (A[g] > q && k < g) {
					g--;
				}//while

				swap(A, k, g);
				g--;

				if (A[k] < p) {
					swap(A, k, j);
					j++;
				}//if

			}//else-if

			k++;

		}//while

		j--;
		g++;
		swap(A, s, j);
		swap(A, e, g);

		return new int[] { j, g };

	}//partition_2pivots

	public static void quicksort_2pivots(int[] A, int s, int e) {
		// quicksort that uses the modified partition_2pivots()
		// Complete the function
		// Feel free to change the return type and parameters
		if (s < e) {

			//Variable
			int[] piv;
			piv = partition_2pivots(A, s, e);

			quicksort_2pivots(A, s, piv[0] - 1);
			quicksort_2pivots(A, piv[0] + 1, piv[1] - 1);
			quicksort_2pivots(A, piv[1] + 1, e);
		}//if

	}//quicksort_2pivots

	public static void main(String[] args) {
		//TODO Auto-generated method stub

		//Test

		int[] testarray1 = { 2, 4, 1, 6, 3, 7, 8 };

		int[] testarray2 = { 6, 3, 4, 5, 1 };

		quicksort_2pivots(testarray1, 0, testarray1.length - 1);
		quicksort_2pivots(testarray2, 0, testarray2.length - 1);

		//Output sorted arrays
		for (int i = 0; i < testarray1.length; i++) {
			System.out.print(testarray1[i] + " ");
		}//for

		System.out.println();

		for (int i = 0; i < testarray2.length; i++) {
			System.out.print(testarray2[i] + " ");
		}//for

	}//main
}//Problem2
