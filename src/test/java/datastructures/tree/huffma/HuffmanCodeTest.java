package datastructures.tree.huffma;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HuffmanCodeTest {

    @Test
    void should_zip_to_huffman_code() {
        final var huffmanCode = new HuffmanCode("i like like like java do you like a java");
        final var bytes = huffmanCode.zipToHuffmanCode();

//        Byte[] except = {-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28};
        Byte[] except = {-87, -67, -23, -67, -23, -67, -24, -24, -52, -127, -104, 39, 77, -17, 114, 58, 6};
        assertArrayEquals(except, bytes);
    }

}
