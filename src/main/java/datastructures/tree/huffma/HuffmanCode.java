package datastructures.tree.huffma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HuffmanCode {
    private final Byte[] bytes;
    private final HuffmanTree tree;
    private Map<Byte, String> codes;

    public HuffmanCode(String content) {
        this.bytes = IntStream.range(0, content.getBytes().length)
                .mapToObj(i -> content.getBytes()[i])
                .toArray(Byte[]::new);

        this.tree = new HuffmanTree(this.bytes);
    }

    public Byte[] zipToHuffmanCode() {
        this.codes = this.tree.buildHuffmaCodes();
        return zip(this.bytes, codes);
    }

    public String unZip(Byte[] huffmanBits) {
        final var huffmanStr = this.parseToHuffmanStr(huffmanBits);
        final var decode = this.decode(this.codes, huffmanStr);
        return Arrays.stream(decode)
                .filter(b -> b != null)
                .map(b -> Character.toString((char) b.byteValue()))
                .collect(Collectors.joining());
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

    private String byteToBitString(byte bit) {
        return this.byteToBitString(bit, 8);
    }

    private String byteToBitString(byte bit, int length) {
        int temp = bit;
        temp |= (int)Math.pow(2, length);
        final var str = Integer.toBinaryString(temp);
        return str.substring(str.length() - length);
    }

    private String parseToHuffmanStr(Byte[] huffmanBytes) {
        final var leftBytes = Arrays.copyOf(huffmanBytes, huffmanBytes.length - 1);
        String huffmaStr =
                Arrays.stream(leftBytes).map(this::byteToBitString).reduce("", (str1, str2) -> str1 + str2);
        huffmaStr += byteToBitString(huffmanBytes[huffmanBytes.length - 1], 5);
        return huffmaStr;
    }

    private Byte[] decode(Map<Byte, String> huffmaCodes, String huffmanStr) {
        Map<String, Byte> swappedMap = huffmaCodes.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

        List<Byte> bytes = new ArrayList<>();
        for (int i = 0; i < huffmanStr.length(); ) {
            int count = 1;
            boolean flag = true;
            while (flag) {
                String key = huffmanStr.substring(i, i + count);
                if (!swappedMap.containsKey(key)) {
                    count++;
                } else {
                    bytes.add(swappedMap.get(key));
                    flag = false;
                }
            }
            i += count;
        }

        return bytes.stream().toArray(Byte[]::new);
    }

}
