package burak.tokses.ui.traffic;

// TrafficLight
//TrafficLight is a shape that consists of a line and a circle. In the input file the start and end posi
//tions to draw the line will be given. The circle needs to be at the center point of the line. Traffic
//Light should also be able to change color when it’s clicked. Red and green are the two color op
//tions.
public class TrafficLight {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private String color;

    public TrafficLight(int x1, int y1, int x2, int y2, String color) {
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
