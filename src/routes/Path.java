package routes;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Path {

    private final Point start;
    private final Point end;

    private final double length;

    private final double m;
    private final double b;

    private final int orientation;

    public Path(Point start, Point end) {
        this.start = start;
        this.end = end;

        this.length = sqrt(pow(start.getY() - end.getY(), 2) + pow(start.getX() - end.getX(), 2));
        this.m = (1.0 * start.getY() - end.getY()) / (start.getX() - end.getX());
        this.b = start.getY() - (m * start.getX());

        int direction = 0;
        if (start.getX() < end.getX()) direction += 1;
        if (start.getY() < end.getY()) direction += 2;

        this.orientation = direction;
    }

    public int getOrientation() {
        return orientation;
    }

    public Point getPoint(double position) {
        double x =  (position * (end.getX() - start.getX())) + start.getX();
        double y = this.m * x + b;
        return new Point((int)x, (int)y);
    }

    public double length() {
        return length;
    }

    public double getSlope() {
        return m;
    }

    public double getIntercept() {
        return b;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

}
