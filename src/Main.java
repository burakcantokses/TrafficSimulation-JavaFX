import burak.tokses.parser.MetadataParser;

public class Main {
    public static void main(String[] args) {
        MetadataParser levelParser = new MetadataParser();
        levelParser.parseFile("metadata.txt");
    }
}