package routes;

public class Direction {

    public static final Route MANHATTAN_TO_QUEENS = new Route(
            0, "Manhattan", "Queens", 50,
            new Path(new Point(260, 598), new Point(192, 560)),
            new Path(new Point(192, 560), new Point(712, 301))
    );

    public static final Route QUEENS_TO_MANHATTAN = new Route(
            1, "Queens", "Manhattan", 50,
            new Path(new Point(695, 280), new Point(140, 560)),
            new Path(new Point(140, 560), new Point(242, 615))
    );

    public static final Route MANHATTAN_TO_JONES = new Route(
            2, "Manhattan", "Jones", 30,
            new Path(new Point(270, 630), new Point(577, 782)),
            new Path(new Point(577, 782), new Point(694, 724))
    );

    public static final Route JONES_TO_MANHATTAN = new Route(
            3, "Manhattan", "Jones", 30,
            new Path(new Point(677, 705), new Point(577, 755)),
            new Path(new Point(577, 755), new Point(290, 613))
    );

    public static final Route JONES_TO_STONY = new Route(
            4, "Jones", "Stony", 40,
            new Path(new Point(720, 710), new Point(1365, 385))
    );

    public static final Route STONY_TO_JONES = new Route(
            5, "Stony", "Jones", 40,
            new Path(new Point(1360, 360), new Point(705, 690))
    );

    public static final Route STONY_TO_QUEENS = new Route(
            6, "Stony", "Queens", 30,
            new Path(new Point(1311, 100), new Point(1185, 37)),
            new Path(new Point(1185, 37), new Point(717, 271))
    );

    public static final Route QUEENS_TO_STONY = new Route(
            7, "Queens", "Stony", 30,
            new Path(new Point(734, 288), new Point(1185, 63)),
            new Path(new Point(1185, 63), new Point(1265, 102))
    );

    public static final Route ROUTES[] = {
            MANHATTAN_TO_QUEENS,
            QUEENS_TO_MANHATTAN,
            MANHATTAN_TO_JONES,
            JONES_TO_MANHATTAN,
            JONES_TO_STONY,
            STONY_TO_JONES,
            STONY_TO_QUEENS,
            QUEENS_TO_STONY,
    };

}
