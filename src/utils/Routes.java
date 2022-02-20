package utils;

public class Routes {

    public enum Company {
        COMPANY_X(0), COMPANY_Y(1);

        public final int val;
        Company(int v){
            this.val = v;
        }
    }

    public enum Types {
        MANHATTAN_TO_QUEENS(0,"Manhattan", "Queens"),
        QUEENS_TO_MANHATTAN(1, "Queens", "Manhattan"),
        MANHATTAN_TO_JONES(2, "Manhattan", "Jones"),
        JONES_TO_MANHATTAN(3, "Jones", "Manhattan"),
        JONES_TO_STONY(4,"Jones", "Stony"),
        STONY_TO_JONES(5, "Stony","Jones"),
        STONY_TO_QUEENS(6, "Stony","Queens"),
        QUEENS_TO_STONY(7, "Queens","Stony");

        public final int val;
        public final String start;
        public final String end;
        Types(int val, String start, String end) {
            this.val = val;
            this.start = start;
            this.end = end;
        }
    }

    public static final int durations[] = {10, 10, 8, 8, 15, 15, 15, 15};

}
