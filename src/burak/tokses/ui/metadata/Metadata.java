package burak.tokses.ui.metadata;

public class Metadata {
    private double width;
    private double height;
    private int gridX;
    private int gridY;
    private int paths;
    private int carsToWin;
    private int allowedCrashes;

    public Metadata(double width, double height, int gridX, int gridY, int paths, int carsToWin, int allowedCrashes) {
        this.width = width;
        this.height = height;
        this.gridX = gridX;
        this.gridY = gridY;
        this.paths = paths;
        this.carsToWin = carsToWin;
        this.allowedCrashes = allowedCrashes;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public int getPaths() {
        return paths;
    }

    public int getCarsToWin() {
        return carsToWin;
    }

    public int getAllowedCrashes() {
        return allowedCrashes;
    }
}
