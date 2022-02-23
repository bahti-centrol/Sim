package main;

import panels.CanvasPanel;
import panels.GraphPanel;
import panels.StockPanel;
import utils.Console;
import utils.Customer;
import utils.Settings;

import javax.swing.*;

public class Core {

    private GraphPanel graph;
    private StockPanel stock;

    private CanvasPanel canvas;

    public enum CoreState {
        INIT, RUNNING, PAUSED
    }

    private JButton primary, secondary;

    private CoreState state;
    private Thread gameThread;

    public Data data;

    public Core() {
        this.state = CoreState.INIT;
    }

    private void run() {
        final Core self = this;
        this.gameThread = new Thread(){
            @Override
            public void run() {
                super.run();
                Console.print("Core Thread has been created and started!");
                while (Settings.inBound()) {
                    if (self.state == CoreState.PAUSED) {
                        delay();
                        continue;
                    }
                    if (self.state == CoreState.INIT) {
                        break;
                    }
                    Settings.increment();
                    self.preEvent();
                    self.event();
                    self.postEvent();
                    delay();
                }
                if (self.state == CoreState.RUNNING) {
                    if (self.data.overflow()) {
                        Console.print("Simulation has overflow customers");
                        while (self.data.overflow()) {
                            Settings.increment();
                            self.preEvent();
                        }
                    }
                    Console.print("Core Thread completed its execution!");
                    self.completed();
                }else{
                    Console.print("Core Thread completed its execution!");
                }

            }
        };
        this.gameThread.start();
    }

    /* Take care of your previously collected data */
    private void preEvent() {
        this.data.compute();
    }

    /* Create new events and populate your data */
    private void event() {
        this.data.manhattan();
        this.data.queens();
        this.data.jones_beach();
        this.data.stony_brook();
    }

    /* DO NOT DO ANY COMPUTATION IN HERE */
    private void postEvent() {
        this.graph.refresh();
        this.stock.refresh(this.data.getStocks());
        this.canvas.refresh(this.data.getCustomers());
    }

    private void completed() {
        Console.print("The simulation has been completed.");

        this.data.display();

        for (Customer customer : this.data.getArchives()) {
            System.out.println(customer.csv());
        }

        this.reset();
    }

    private void restart() {
        Console.print("The simulation has been restarted.");

        this.graph.reset();
        this.stock.reset(new Data().getStocks());
        this.reset();
    }

    private void reset() {
        Console.print("Restarting simulation back to init.");
        primary.setText("Start Simulation");

        this.state = CoreState.INIT;
    }

    private void start() {
        Console.print("The simulation is started.");
        primary.setText("Pause Simulation");

        this.state = CoreState.RUNNING;
        this.data = new Data();
        this.graph.reset();
        Settings.initialize();

        Console.print("TPS is ", Settings.TPS, " and delay is ", Settings.DELAY, " ms");
        Console.print("Will be running for max of ", Settings.MAX_EVENTS, " events");

        this.run();
    }

    private void paused() {
        Console.print("The simulation was paused.");
        primary.setText("Resume Simulation");

        this.state = CoreState.PAUSED;
    }

    private void resume() {
        Console.print("The simulation is resuming.");
        primary.setText("Pause Simulation");

        this.state = CoreState.RUNNING;

    }

    public void onButton(JButton button) {
        if (button.equals(primary)) {
            if (state == CoreState.INIT) {
                this.start();
            }else if (state == CoreState.RUNNING) {
                this.paused();
            }else if (state == CoreState.PAUSED) {
                this.resume();
            }
        }else if (button.equals(secondary)) {
            this.restart();
        }
    }

    public void setGraph(GraphPanel graphPanel) {
        this.graph = graphPanel;
    }

    public void attachCanvas(CanvasPanel canvas) {
        this.canvas = canvas;
    }

    public void attachStock(StockPanel stock) {
        this.stock = stock;
    }

    public void setButtons(JButton primary, JButton secondary) {
        this.primary = primary;
        this.secondary = secondary;
    }

    public Core.CoreState getState() {
        return state;
    }

    private void delay() {
        try{
            Thread.sleep(Settings.DELAY);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
