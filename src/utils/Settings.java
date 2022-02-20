package utils;

public class Settings {

    public static int NUM_OF_DAYS = 5;
    public static int SECS_PER_DAY = 5;
    public static int TICKS_PER_HOUR = 12;

    public static double TPS = 0;
    public static int DELAY = 0;

    public static int EVENT_COUNTER = 0;
    public static int MAX_EVENTS = 0;

    public static double PREFERENCE = 0.5;

    private static int INITIAL_SCORE = 500;

    public static int COMP_X_SCORE = INITIAL_SCORE;
    public static int COMP_Y_SCORE = INITIAL_SCORE;

    public static int SCOOTER_MAX = 300;

    public static int COMP_X_INITIAL = 70;
    public static double COMP_X_QUALITY = 0.90;

    public static int COMP_Y_INITIAL = 160;
    public static double COMP_Y_QUALITY = 0.60;


    public static double MANHATTAN_SPAWN_RATE = 0.35;
    public static double QUEENS_SPAWN_RATE = 0.30;
    public static double JONES_BEACH_SPAWN_RATE = 0.20;
    public static double STONY_BROOK_SPAWN_RATE = 0.15;

    public static void initialize() {
        TPS = (TICKS_PER_HOUR * 12) / (SECS_PER_DAY * 1.0);
        DELAY = (int)(1000 / TPS);

        MAX_EVENTS = TICKS_PER_HOUR * 12 * NUM_OF_DAYS;
        EVENT_COUNTER = 0;

        PREFERENCE = 0.5;
        COMP_X_SCORE = INITIAL_SCORE;
        COMP_Y_SCORE = INITIAL_SCORE;
    }

    public static void addRating(int company, int score) {
        if (company == 0) {
            COMP_X_SCORE += score;
        }else {
            COMP_Y_SCORE += score;
        }

        PREFERENCE = (COMP_X_SCORE * 1.0) / (COMP_X_SCORE + COMP_Y_SCORE);

        Console.print("PREFERENCE IS: ",PREFERENCE, ". X:", COMP_X_SCORE, " Y:", COMP_Y_SCORE);

    }

    public static void increment() {
        EVENT_COUNTER++;
    }

    public static boolean inBound() {
        return EVENT_COUNTER < MAX_EVENTS;
    }
}
