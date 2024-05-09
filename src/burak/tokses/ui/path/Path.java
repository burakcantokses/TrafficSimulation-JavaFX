package burak.tokses.ui.path;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private int pathIndex;
    private List<String> commands;
    private List<Double> xPoints;
    private List<Double> yPoints;

    public Path(int pathIndex) {
        this.pathIndex = pathIndex;
        this.commands = new ArrayList<>();
        this.xPoints = new ArrayList<>();
        this.yPoints = new ArrayList<>();
    }

    public void addPoint(String command, double x, double y) {
        commands.add(command);
        xPoints.add(x);
        yPoints.add(y);
    }

    public int getPathIndex() {
        return pathIndex;
    }

    public List<String> getCommands() {
        return commands;
    }

    public List<Double> getXPoints() {
        return xPoints;
    }

    public List<Double> getYPoints() {
        return yPoints;
    }
}