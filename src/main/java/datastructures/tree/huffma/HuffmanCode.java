package datastructures.tree.huffma;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.IntStream;

public class HuffmanCode {
    private final Byte[] bytes;
    private final HuffmanTree tree;

    public HuffmanCode(String content) {
        this.bytes = IntStream.range(0, content.getBytes().length)
                .mapToObj(i -> content.getBytes()[i])
                .toArray(Byte[]::new);

        this.tree = new HuffmanTree(this.bytes);
    }

    public Byte[] zipToHuffmanCode() {
        Map<Byte, String> codes = this.tree.buildHuffmaCodes();
        return zip(this.bytes, codes);
    }

    private Byte[] zip(Byte[] bytes, Map<Byte, String> codes) {
        final var huffmanStr = Arrays.stream(bytes).map(codes::get).reduce("", (str1, str2) -> str1 + str2);
        final var BYTE_LENGTH = 8;
        final var TWO_RADIX = 2;
        int length = (huffmanStr.length() + 7) / BYTE_LENGTH;
        return IntStream.range(0, length)
                .mapToObj(i -> huffmanStr.substring(i * BYTE_LENGTH,
                        Math.min((i + 1) * BYTE_LENGTH, huffmanStr.length())))
                .map(sub -> (byte) Integer.parseInt(sub, TWO_RADIX))
                .toArray(Byte[]::new);
    }

}
