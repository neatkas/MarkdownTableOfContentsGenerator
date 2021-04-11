package api;

import java.io.IOException;

public interface TableOfContentsGenerator {
    void generate(String filename) throws IOException;
}
