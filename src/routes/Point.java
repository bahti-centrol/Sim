package routes;

public class Point {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point scale(Point original, Point size) {
        int nx = (int) (((double) this.x / original.getX()) * size.getX());
        int ny = (int) (((double) this.y / original.getY()) * size.getY());
        return new Point(nx, ny);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
