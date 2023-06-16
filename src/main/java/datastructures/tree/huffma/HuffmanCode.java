package datastructures.tree.huffma;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
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
    private int lastByteLength;

    public HuffmanCode(String content) {
        this.bytes = this.covertToBytes(content.getBytes());

        this.tree = new HuffmanTree(this.bytes);
    }

    public HuffmanCode(Path path) throws IOException {
        final var bits = Files.readAllBytes(path);
        this.bytes = this.covertToBytes(bits);
        this.tree = new HuffmanTree(this.bytes);
    }

    public Byte[] zipToHuffmanCode() {
        this.codes = this.tree.buildHuffmaCodes();
        return zip(this.bytes, codes);
    }

    public void zipFile(Path path) throws IOException {
        this.codes = this.tree.buildHuffmaCodes();
        final var zippedBits = zip(this.bytes, this.codes);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
            oos.writeObject(zippedBits);
        }
    }

    public void unZipFile(Path path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream((new FileInputStream(path.toFile())))) {
            Byte[] bits = (Byte[])ois.readObject();
            final var content = this.unZip(bits);
            final var location = path.getParent();
            final var newPath = location.resolve("text.md");
            Files.write(newPath, content.getBytes(), StandardOpenOption.CREATE);
        }
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
                .mapToObj(i -> {
                    final var leftLength = huffmanStr.length() - (i * BYTE_LENGTH);
                    if (leftLength < 8) {
                        lastByteLength = leftLength;
                        return huffmanStr.substring(i * BYTE_LENGTH, huffmanStr.length());
                    } else {
                        return huffmanStr.substring(i * BYTE_LENGTH, (i + 1) * BYTE_LENGTH);
                    }
                })
                .map(sub -> (byte) Integer.parseInt(sub, TWO_RADIX))
                .toArray(Byte[]::new);
    }

    private String byteToBitString(byte bit) {
        return byteToBitString(bit, 8);
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
        huffmaStr += byteToBitString(huffmanBytes[huffmanBytes.length - 1], this.lastByteLength);
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

    private Byte[] covertToBytes(byte[] bits) {
        return IntStream.range(0, bits.length)
                .mapToObj(i -> bits[i])
                .toArray(Byte[]::new);
    }
}
