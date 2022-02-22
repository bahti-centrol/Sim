package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static utils.Commons.getImage;

public class Travis {

    public static JFrame getTravis() {
        try {
            ImageIcon imageIcon = new ImageIcon(getImage("travis.jpg"));

            JFrame frame = new JFrame();

            frame.setLayout(new FlowLayout());

            frame.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
            JLabel jLabel = new JLabel();

            frame.setLocationRelativeTo(null);

            jLabel.setIcon(imageIcon);
            frame.add(jLabel);
            frame.setVisible(true);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            return frame;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}