package routes;

public class Route {

    private final int id;
    private final String start;
    private final String end;
    private final int expected;

    private final Path paths[];

    private final double distance;

    public Route(int id, String start, String end, int expected, Path ...paths) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.expected = expected;
        this.paths = paths;

        double sum = 0;
        for (Path path : paths)
            sum += path.length();

        this.distance = sum;

        for (Path path : paths) {
            System.out.println(start+"->"+end+" orientation: "+path.getOrientation());
        }
    }

    /* Gets the position'th Point relative to Route if it was broken into 'portions' */
    public Point getPoint(int position, int portions) {

        double interval = this.distance / portions;
        double current = interval * position;

        double sum = 0;
        for (Path path : paths) {
            sum += path.length();

            if (current < sum) {
                double leftover = sum - current;
                double relative = (path.length() - leftover) / path.length();

                return path.getPoint(relative);
            }
        }

        return paths[paths.length-1].getEnd();
    }

    public Path getPath(int position, int portions) {

        double interval = this.distance / portions;
        double current = interval * position;

        double sum = 0;
        for (Path path : paths) {
            sum += path.length();

            if (current < sum) {
                return path;
            }
        }

        return paths[paths.length-1];
    }

    public double getDistance() {
        return distance;
    }

    public int getId() {
        return id;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public int getExpected() {
        return expected;
    }

    public Path[] getPaths() {
        return paths;
    }

    public String toString() {
        return this.start + "->" + this.end;
    }
}
