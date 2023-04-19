package datastructures.sparsearray;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SparseArrayTest {

    @Test
    void should_not_parse_to_sparse_array_when_array_is_null() {
        assertThrows(RuntimeException.class, () -> new SparseArray(null));
    }

    @Test
    void should_can_parse_to_sparse_array_when_array_has_only_one_element() {
        int[][] array = {{0}};
        SparseArray sparseArray = new SparseArray(array);

        final int[][] result = sparseArray.getSparseArr();
        final int[][] expect = {{1, 1, 0}};

        assertArrayEquals(expect, result);
    }

    @Test
    void should_can_parse_to_sparse_array_when_array_has_normal_elements() {
        int[][] array = {
                {0, 1, 2},
                {0, 1, 0},
                {0, 0, 1}
        };
        SparseArray sparseArray = new SparseArray(array);
        final int[][] result = sparseArray.getSparseArr();

        final int[][] expect = {
                {3, 3, 4},
                {0, 1, 1},
                {0, 2, 2},
                {1, 1, 1},
                {2, 2, 1}
        };
        assertArrayEquals(expect, result);
    }

    @Test
    void should_can_parse_to_sparse_array_when_array_has_no_0_elements() {
        int[][] array = {
                {1, 1, 2},
                {3, 1, 3},
                {4, 4, 1}
        };
        SparseArray sparseArray = new SparseArray(array);

        final int[][] result = sparseArray.getSparseArr();
        final int[][] expect = {
                {3, 3, 9},
                {0, 0, 1},
                {0, 1, 1},
                {0, 2, 2},
                {1, 0, 3},
                {1, 1, 1},
                {1, 2, 3},
                {2, 0, 4},
                {2, 1, 4},
                {2, 2, 1},
        };

        assertArrayEquals(expect, result);
    }

    @Test
    void should_covert_to_two_dimensional_arr() {
        int[][] array = {
                {1, 1, 2},
                {3, 1, 3},
                {4, 4, 1}
        };
        SparseArray sparseArray = new SparseArray(array);

        final int[][] result = sparseArray.covertTo2dimensionalArr();
        assertArrayEquals(array, result);
    }
}
