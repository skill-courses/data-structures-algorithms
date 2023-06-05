package algorithms.search;

import java.util.Arrays;

public class Search {
    private final int[] arr;

    public Search(int[] arr) {
        this.arr = Arrays.copyOf(arr, arr.length);
    }

    public int binarySearch(int target) {
        final var BINARY = 2;
        int left = 0;
        int right = this.arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / BINARY;

            if (this.arr[mid] < target) {
                left = mid + 1;
            } else if (this.arr[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    public int insertSearch(int target) {
        int left = 0;
        int right = this.arr.length - 1;

        while (left <= right && this.arr[left] <= target && this.arr[right] >= target) {
            int mid = left + (right - left) * ((target - this.arr[left]) / (this.arr[right] - this.arr[left]));

            if (this.arr[mid] < target) {
                left = mid + 1;
            } else if (this.arr[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    public int fibonacciSearch(int target) {
        int arrSize = this.arr.length;
        int fib2th = 0; // (m-2)'th Fibonacci number
        int fib1th = 1; // (m-1)'th Fibonacci number
        int fib = fib2th + fib1th; // m'th Fibonacci number

        while (fib < arrSize) {
            fib2th = fib1th;
            fib1th = fib;
            fib = fib2th + fib1th;
        }

        int offset = -1;

        while (fib > 1) {
            int index = Math.min(offset + fib2th, arrSize - 1);

            if (arr[index] < target) {
                fib = fib1th;
                fib1th = fib2th;
                fib2th = fib - fib1th;
                offset = index;
            } else if (arr[index] > target) {
                fib = fib2th;
                fib1th = fib1th - fib2th;
                fib2th = fib - fib1th;
            } else {
                return index;
            }
        }

        if (fib1th == 1 && arr[offset + 1] == target) {
            return offset + 1;
        }

        return -1;
    }

}
