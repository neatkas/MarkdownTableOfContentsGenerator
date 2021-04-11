import impl.MarkdownTableOfContentsGenerator;

public class Main {
    public static void main(String[] args) {
        MarkdownTableOfContentsGenerator generator = new MarkdownTableOfContentsGenerator();
        generator.generate(args[0]);
    }
}
