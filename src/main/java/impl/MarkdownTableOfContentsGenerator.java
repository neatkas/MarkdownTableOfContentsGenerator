package impl;

import api.TableOfContentsGenerator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MarkdownTableOfContentsGenerator implements TableOfContentsGenerator {
    private final List<String> tableOfContents = new ArrayList<>();
    private final int[] numbersAtLevels = {1, 1, 1, 1, 1, 1};

    public void generate(String filename) {
        Path path = Paths.get(filename);
        List<String> data;
        try {
            data = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Can't read file " + filename);
            return;
        }

        for (String line : data) {
            if (!line.equals("") && line.charAt(0) == '#') {
                int level = 0;
                while (line.charAt(level + 1) == '#') {
                    level++;
                }
                if (level > 5) {
                    continue;
                }

                StringBuilder tableLine = new StringBuilder();
                tableLine.append("    ".repeat(level));
                tableLine.append(numbersAtLevels[level]);
                numbersAtLevels[level]++;
                tableLine.append(". [");
                String title = line.substring(level + 1);
                if (title.indexOf('#') != -1) {
                    title = title.substring(0, title.length() - level - 2).trim();
                } else {
                    title = title.trim();
                }
                tableLine.append(title)
                        .append("](#")
                        .append(title.toLowerCase(Locale.ROOT).replace(' ', '-'))
                        .append(")");
                tableOfContents.add(tableLine.toString());
            }
        }

        for (String line : tableOfContents) {
            System.out.println(line);
        }
        System.out.println();
        for (String line : data) {
            System.out.println(line);
        }
    }
}
