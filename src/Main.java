import burak.tokses.parser.LevelParser;

public class Main {
    public static void main(String[] args) {
        LevelParser levelParser = new LevelParser();
        levelParser.parseFile("metadata.txt");
    }
}