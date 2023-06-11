package algorithms.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Sort {

    private Sort() {

    }

    public static int[] bubbleSort(int[] arr) {
        boolean isExchange = false;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + i] = temp;
                    isExchange = true;
                }
            }

            if (isExchange) {
                break;
            } else {
                isExchange = true;
            }
        }
        return arr;
    }

    public static int[] selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    minIndex = j;
                    min = arr[j];
                }
            }

            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }

        return arr;
    }

    public static int[] insertSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            // Move elements greater than key to the right
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
        return arr;
    }

    public static int[] shellSort(int[] arr) {
        final int STEP = 2;
        for (int gap = arr.length / STEP; gap > 0; gap /= STEP) {
            for (int i = gap; i < arr.length; i++) {
                swapShell(arr, gap, i);
            }
        }
        return arr;
    }

    private static void swapShell(int[] arr, int gap, int i) {
        for (int j = i - gap; j >= 0; j -= gap) {
            int temp;
            if (arr[j] > arr[j + gap]) {
                temp = arr[j + gap];
                arr[j + gap] = arr[j];
                arr[j] = temp;
            }
        }
    }

    public static int[] quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);

            quickSort(arr, low, pivotIndex -1);
            quickSort(arr, pivotIndex + 1, high);
        }
        return arr;
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);

        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }

        final int MID_INDEX = 2;
        int mid = arr.length / MID_INDEX;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(left);
        mergeSort(right);
        return merge(arr, left, right);
    }

    private static int[] merge(int[] arr, int[] left, int[] right) {
        int index = 0;
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            arr[index++] = left[leftIndex] <= right[rightIndex] ? left[leftIndex++] : right[rightIndex++];
        }

        while (leftIndex < left.length) {
            arr[index++] = left[leftIndex++];
        }

        while (rightIndex < right.length) {
            arr[index++] = right[rightIndex++];
        }

        return arr;
    }

    public static int[] bucketSort(final int[] arr) {
        final int DECIMAL = 10;
        final List<ArrayList<Integer>> buckets =
                IntStream.range(0, DECIMAL).mapToObj(ArrayList<Integer>::new).collect(Collectors.toList());
        int max = Arrays.stream(arr).max().getAsInt();
        final int size = (max + "").length();

        for (int i = 0, n = 1; i < size; i++, n *= DECIMAL) {
            for (int k : arr) {
                int digitOfElement = k / n % DECIMAL;
                buckets.get(digitOfElement).add(k);
            }

            mergeBuckets(arr, buckets);
        }

        return arr;
    }

    private static void mergeBuckets(int[] arr, List<ArrayList<Integer>> buckets) {
        int index = 0;
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
            for (int num : bucket) {
                arr[index++] = num;
            }
            bucket.clear();
        }
    }

    public static int[] heapSort(int[] arr) {
        int n = arr.length;

        // 构建最大堆（Max Heap）
        for (int i = n / 2 - 1; i >= 0; i--) {
            siftDown(arr, n, i);
        }

        // 逐步将堆顶元素（最大值）交换到数组末尾，并重新调整堆
        for (int i = n - 1; i > 0; i--) {
            // 将堆顶元素与当前未排序部分的最后一个元素交换
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // 重新调整堆
            siftDown(arr, i, 0);
        }
        return arr;
    }

    private static void siftDown(int[] array, int size, int index) {
        int largest = index;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        // 找出当前节点、左子节点和右子节点中的最大值
        if (leftChild < size && array[leftChild] > array[largest]) {
            largest = leftChild;
        }
        if (rightChild < size && array[rightChild] > array[largest]) {
            largest = rightChild;
        }

        // 如果最大值不是当前节点，则交换它们，并继续向下调整堆
        if (largest != index) {
            int temp = array[index];
            array[index] = array[largest];
            array[largest] = temp;
            siftDown(array, size, largest);
        }
    }
}
