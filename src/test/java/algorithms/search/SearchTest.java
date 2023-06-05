package algorithms.search;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SearchTest {

    @Test
    void should_can_search_by_binary() {
        int[] arr = new int[]{3, 5, 8, 10, 34, 567, 900, 1000};

        final var search = new Search(arr);

        final int index = search.binarySearch(900);
        assertEquals(6, index);

        final int index2 = search.binarySearch( 9000);
        assertEquals(-1, index2);
    }

    @Test
    void should_can_search_by_insert() {
        int[] arr = new int[]{3, 5, 8, 10, 34, 567, 900, 1000};

        final var search = new Search(arr);

        final int index = search.insertSearch(900);
        assertEquals(6, index);

        final int index2 = search.insertSearch( 9000);
        assertEquals(-1, index2);

        final int index3 = search.insertSearch( 789);
        assertEquals(-1, index3);
    }

    @Test
    void should_can_search_by_fibonacci() {
        int[] arr = new int[]{3, 5, 8, 10, 34, 567, 900, 1000};

        final var search = new Search(arr);

        final int index0 = search.fibonacciSearch(3);
        assertEquals(0, index0);

        final int index = search.fibonacciSearch(900);
        assertEquals(6, index);

        final int index2 = search.fibonacciSearch( 9000);
        assertEquals(-1, index2);

        final int index3 = search.fibonacciSearch( 789);
        assertEquals(-1, index3);

        final int index4 = search.fibonacciSearch( 1000);
        assertEquals(7, index4);
    }

}
