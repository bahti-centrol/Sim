package main;

import panels.*;
import utils.Console;
import main.Core;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class SimGui {

    private static final String TITLE = "Scooter Share Simulator";
    private static final int WIDTH = 1550;
    private static final int HEIGHT = 1000;

    public JFrame frame;

    private final CanvasPanel canvasPanel;
    private final GraphPanel graphPanel;
    private final StockPanel stockPanel;
    private final JPanel consolePanel;
    private final JPanel parametersPanel;

    private final JSplitPane bottomPane;

    public final Core core;

    public SimGui(Core core) {
        this.core = core;

        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        Console.setCore(core);

        canvasPanel = new CanvasPanel();
        graphPanel = new GraphPanel();
        stockPanel = new StockPanel();
        consolePanel = new ConsolePanel(this);
        parametersPanel = new ParameterPanel(core, this);

        core.attachCanvas(canvasPanel);
        core.setGraph(graphPanel);
        core.attachStock(stockPanel);

        Console.print("Initializing components and panes...");

        bottomPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, graphPanel, stockPanel);
        bottomPane.setOneTouchExpandable(false);
        bottomPane.setDividerLocation((int)(WIDTH*0.4));

        JSplitPane leftPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, canvasPanel, bottomPane);
        leftPane.setOneTouchExpandable(false);
        leftPane.setDividerLocation((int)(HEIGHT*0.6));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, parametersPanel);
        splitPane.setOneTouchExpandable(false);
        splitPane.setResizeWeight(0.8);

        frame.getContentPane().add(splitPane);
        frame.pack();

        graphPanel.createDefaultChart(graphPanel.getWidth(), graphPanel.getHeight());
        stockPanel.createDefaultChart(stockPanel.getWidth(), stockPanel.getHeight());

        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Console.print("Simulation is ready to start");
    }

    public void swap(boolean isConsole) {
        if (isConsole) {
            this.bottomPane.setRightComponent(this.consolePanel);
        }else{
            this.bottomPane.setRightComponent(this.stockPanel);
        }

        this.bottomPane.repaint();
    }

}
