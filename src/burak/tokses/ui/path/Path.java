package burak.tokses.ui.path;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private int pathIndex;
    private List<String> commands;
    private List<Point2D> points;

    public Path(int pathIndex) {
        this.pathIndex = pathIndex;
        this.commands = new ArrayList<>();
        this.points = new ArrayList<>();
    }

    public void addPoint(String command, double x, double y) {
        commands.add(command);
        points.add(new Point2D(x, y));
    }

    public int getPathIndex() {
        return pathIndex;
    }

    public List<String> getCommands() {
        return commands;
    }

    public List<Point2D> getPoints() {
        return points;
    }

    public Point2D getStartPoint() {
        return points.get(0);
    }
}