package burak.tokses.ui.traffic;

// TrafficLight
//TrafficLight is a shape that consists of a line and a circle. In the input file the start and end posi
//tions to draw the line will be given. The circle needs to be at the center point of the line. Traffic
//Light should also be able to change color when it’s clicked. Red and green are the two color op
//tions.
public class TrafficLight {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private String color;

    public TrafficLight(double x1, double y1, double x2, double y2, String color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    public void print() {
        System.out.println("TrafficLight at x1: " + x1 + ", y1: " + y1 + ", x2: " + x2 + ", y2: " + y2 + ", color: " + color);
    }

    public void changeColor() {
        if (color.equals("red")) {
            color = "green";
        } else {
            color = "red";
        }
    }

}
