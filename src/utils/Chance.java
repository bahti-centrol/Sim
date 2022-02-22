package utils;

public class Chance {

    public static int zeroOrOne() {
        return flip() ? 1 : 0;
    }

    public static boolean flip() {
        return chance(0.5);
    }

    public static boolean chance(double rate) {
        return rate >= Math.random();
    }

    public static int random(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static int range(int num) {
        int n = (int) (num * 0.33);
        return random(num - n, num + n);
    }
}
