package main;

import panels.CanvasPanel;
import panels.ConsolePanel;
import panels.GraphPanel;
import panels.ParameterPanel;
import utils.Console;
import main.Core;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class SimGui {

    private static final String TITLE = "Scooter Share Simulator";
    private static final int WIDTH = 1350;
    private static final int HEIGHT = 900;

    private JFrame frame;

    private final CanvasPanel canvasPanel;
    private final GraphPanel graphPanel;
    private final JPanel consolePanel;
    private final JPanel parametersPanel;

    private final Core core;

    public SimGui(Core core) {
        this.core = core;

        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        Console.setCore(core);

        canvasPanel = new CanvasPanel();
        graphPanel = new GraphPanel();
        consolePanel = new ConsolePanel();
        parametersPanel = new ParameterPanel(core);

        core.setGraph(graphPanel);

        Console.print("Initializing components and panes...");

        JSplitPane bottomPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, graphPanel, consolePanel);
        bottomPane.setOneTouchExpandable(false);
        bottomPane.setDividerLocation((int)(WIDTH*0.4));

        JSplitPane leftPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, canvasPanel, bottomPane);
        leftPane.setOneTouchExpandable(false);
        leftPane.setDividerLocation((int)(HEIGHT*0.6));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, parametersPanel);
        splitPane.setOneTouchExpandable(false);
        splitPane.setResizeWeight(1.0);

        frame.getContentPane().add(splitPane);
        frame.pack();

        graphPanel.createDefaultChart(bottomPane.getLeftComponent().getWidth(), bottomPane.getLeftComponent().getHeight());
        canvasPanel.setImage(getImage(), leftPane.getLeftComponent().getWidth(), leftPane.getLeftComponent().getHeight());

        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Console.print("Simulation is ready to start");
    }

    private Image getImage(){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("background.png"));
            return img;

        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
