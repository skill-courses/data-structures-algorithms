package algorithms.kmp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KMPTest {

    @Test
    void should_get_partial_match_table_for_A() {
        final var table = KMP.getPartialMatchTable("A");
        int[] result = new int[] {0};
        assertArrayEquals(result, table);
    }


    @Test
    void should_get_partial_match_table_for_AB() {
        final var table = KMP.getPartialMatchTable("AB");
        int[] result = new int[] {0, 0};
        assertArrayEquals(result, table);
    }

    @Test
    void should_get_partial_match_table_for_ABA() {
        final var table = KMP.getPartialMatchTable("ABA");
        int[] result = new int[] {0, 0, 1};
        assertArrayEquals(result, table);
    }

    @Test
    void should_get_partial_match_table_for_ABAB() {
        final var table = KMP.getPartialMatchTable("ABAB");
        int[] result = new int[] {0, 0, 1, 2};
        assertArrayEquals(result, table);
    }

    @Test
    void should_get_partial_match_table_for_ABCDABD() {
        final var table = KMP.getPartialMatchTable("ABCDABD");
        int[] result = new int[] {0, 0, 0, 0, 1, 2, 0};
        assertArrayEquals(result, table);
    }

    @Test
    void should_get_max_common_string_length_for_ABAB() {
        final var maxLength = KMP.getMaxCommonSubStrLength("ABAB");
        assertEquals(2, maxLength);
    }


    @Test
    void should_not_found_index_for_ABCD_in_BDFGH() {
        final var index = KMP.kmpSearch("BDFGH", "ABCD");
        assertEquals(-1, index);
    }

    @Test
    void should_found_index_for_ABCD_in_ABCDE() {
        final var index = KMP.kmpSearch("ABCDE", "ABCD");
        assertEquals(0, index);
    }

    @Test
    void should_found_index_for_ABCD_in_AABCDE() {
        final var index = KMP.kmpSearch("AABCDE", "ABCD");
        assertEquals(1, index);
    }

    @Test
    void should_found_index_for_ABCDABD_in_BBCABCDABABCDABCDABDE() {
        final var index = KMP.kmpSearch("BBCABCDABABCDABCDABDE", "ABCDABD");
        assertEquals(13, index);
    }
}
