import burak.tokses.parser.MetadataParser;

public class Main {
    public static void main(String[] args) {
        MetadataParser levelParser = new MetadataParser();
        levelParser.parseFile("level0.txt");
    }
}