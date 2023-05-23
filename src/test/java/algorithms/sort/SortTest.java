package algorithms.sort;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class SortTest {

    @Test
    void should_void_sort_by_bubble_sort() {
        int[] arr = {-1, -2, 3, 9, 8};

        int[] sortArr = Sort.bubbleSort(arr);
        int[] except = {-2, -1, 3, 8, 9};
        assertArrayEquals(except, sortArr);
    }
}
