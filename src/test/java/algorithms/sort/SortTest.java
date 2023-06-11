package algorithms.sort;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class SortTest {

    @Test
    void should_void_sort_by_bubble() {
        int[] arr = {-1, -2, 3, 9, 8};

        int[] sortArr = Sort.bubbleSort(arr);
        int[] except = {-2, -1, 3, 8, 9};
        assertArrayEquals(except, sortArr);
    }

    @Test
    void should_void_sort_by_select() {
        int[] arr = {-1, -2, 3, 9, 8};

        int[] sortArr = Sort.selectSort(arr);
        int[] except = {-2, -1, 3, 8, 9};
        assertArrayEquals(except, sortArr);
    }

    @Test
    void should_void_sort_by_insert() {
        int[] arr = {-1, -2, 3, 9, 8};

        int[] sortArr = Sort.insertSort(arr);
        int[] except = {-2, -1, 3, 8, 9};
        assertArrayEquals(except, sortArr);
    }

    @Test
    void should_void_sort_by_shell() {
        int[] arr = {-1, -2, 3, 9, 8};

        int[] sortArr = Sort.shellSort(arr);
        int[] except = {-2, -1, 3, 8, 9};
        assertArrayEquals(except, sortArr);
    }

    @Test
    void should_void_sort_by_quick() {
        int[] arr = {-1, -2, 3, 9, 8};

        int[] sortArr = Sort.quickSort(arr,0,arr.length -1);
        int[] except = {-2, -1, 3, 8, 9};
        assertArrayEquals(except, sortArr);
    }

    @Test
    void should_void_sort_by_merge() {
        int[] arr = {-1, -2, 3, 9, 8};

        int[] sortArr = Sort.mergeSort(arr);
        int[] except = {-2, -1, 3, 8, 9};
        assertArrayEquals(except, sortArr);
    }

    @Test
    void should_void_sort_by_bucket() {
        int[] arr = {68, 45, 0, 1, 893, 4, 28};

        int[] sortArr = Sort.bucketSort(arr);
        int[] except = {0, 1, 4, 28, 45, 68, 893};
        assertArrayEquals(except, sortArr);
    }

    @Test
    void should_sort_by_heap() {
        int[] arr = {68, 45, 0, 1, -89, 4, 28};

        int[] sortArr = Sort.heapSort(arr);
        int[] except = {-89, 0, 1, 4, 28, 45, 68};
        assertArrayEquals(except, sortArr);
    }
}
