package tests.gitlab.ckpt2;

import java.util.Comparator;

import p2.sorts.QuickSort;
import org.junit.Test;
import static org.junit.Assert.*;

public class QuickSortTests {

	@Test(timeout = 3000)
	public void integer_sorted() {
		Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		Integer[] arr_sorted = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		QuickSort.sort(arr, Integer::compareTo);
		for(int i = 0; i < arr.length; i++) {
			assertEquals(arr[i], arr_sorted[i]);
		}
	}

	@Test(timeout = 3000)
	public void integer_random() {
		Integer[] arr = {3, 1, 4, 5, 9, 2, 6, 7, 8};
		Integer[] arr_sorted = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		QuickSort.sort(arr, Integer::compareTo);
		for(int i = 0; i < arr.length; i++) {
			assertEquals(arr[i], arr_sorted[i]);
		}
	}


}