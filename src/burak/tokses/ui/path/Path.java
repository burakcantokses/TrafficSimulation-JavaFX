package burak.tokses.ui.path;

public class Path {
    private int pathIndex;
    private String command;
    private double x;
    private double y;

    public Path(int pathIndex, String command, double x, double y) {
        this.pathIndex = pathIndex;
        this.command = command;
        this.x = x;
        this.y = y;
    }

    // ... diğer metotlar ...
}