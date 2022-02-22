package utils;

import java.awt.*;

import static utils.Commons.*;

public class Bikes {

    // 1550 x 900

    private static final double actual[] = { 50, 55 };

    private static String files[][] = {
            {"x1.png", "x2.png", "x3.png", "x4.png"},
            {"y1.png", "y2.png", "y3.png", "y4.png"},
    };

    public static Image imgs[][] = new Image[2][4];

    public static void refresh(int w, int h) {
        int aW = (int) (w / 1550.0 * actual[0]);
        int aH = (int) (h / 900.0 * actual[1]);
        for (int i = 0; i < files.length; i++) {
            for (int j = 0; j < files[i].length; j++) {
                imgs[i][j] = image(files[i][j], aW, aH);
            }
        }
    }

    private static Image image(String name, int w, int h) {
        return scaleImage(getImage(name), w, h);
    }
}
