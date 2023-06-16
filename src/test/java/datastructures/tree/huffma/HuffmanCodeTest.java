package datastructures.tree.huffma;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HuffmanCodeTest {

    @Test
    void should_zip_to_huffman_code() {
        final var content = "i like like like java do you like a java";
        final var huffmanCode = new HuffmanCode(content);
        final var bytes = huffmanCode.zipToHuffmanCode();

        Byte[] except = {-87, -67, -23, -67, -23, -67, -24, -24, -52, -127, -104, 39, 77, -17, 114, 58, 6};
        assertArrayEquals(except, bytes);

        final var exceptStr = huffmanCode.unZip(except);
        assertEquals(content, exceptStr);
    }

    @Test
    void should_zip_and_unzip_file_by_huffma_code() throws IOException, ClassNotFoundException {
        Path rootPath = Paths.get("").toAbsolutePath();
        final var huffmanCode = new HuffmanCode(rootPath.resolve("README.md"));
        Path zippedPath = rootPath.resolve("README.zip");
        huffmanCode.zipFile(zippedPath);

        assertTrue(Files.exists(zippedPath));

        huffmanCode.unZipFile(zippedPath);
        assertTrue(Files.exists(zippedPath.getParent().resolve("text.md")));
    }
}
