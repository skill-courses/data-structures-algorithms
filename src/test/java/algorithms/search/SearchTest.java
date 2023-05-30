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
        assertEquals(-1, index2);
    }

}
