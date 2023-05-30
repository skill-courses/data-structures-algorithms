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
}
