package algorithms.sort;

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


}
