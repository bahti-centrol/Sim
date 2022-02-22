package panels;

import routes.Direction;
import routes.Path;
import routes.Route;

import routes.Point;
import utils.Bikes;
import utils.Customer;
import utils.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;

import static utils.Commons.getImage;
import static utils.Commons.scaleImage;

public class CanvasPanel extends JPanel implements ComponentListener {

    private Image original;
    private Image image;


    private Image test;
    private Image test2;


    private Point originalSize;
    private Point currentSize;

    private int width;
    private int height;

    public CanvasPanel()
    {
        addComponentListener(this);


        this.test = getImage("adjusted.png");
        this.original = getImage("background.png");
        this.originalSize = new Point(this.original.getWidth(this), this.original.getHeight(this));
    }

    private Color colors[] = {Color.RED, Color.BLUE, Color.MAGENTA, Color.YELLOW};

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            g.drawImage(image,0,0,this);

            //g.drawImage(test2, 0, 0, this);

            if (this.customers != null ) {

                for (Customer customer : customers) {

                    if (customer.failed()){
                        continue;
                    }


                    Route route = customer.getRoute();

                    int intervals = customer.getDuration();
                    int current = Settings.EVENT_COUNTER - customer.getStarted();

                    Path path = route.getPath(current, intervals);
                    Point point = route.getPoint(current, intervals);
                    Point adjusted = point.scale(originalSize, currentSize);

                    Image bike = Bikes.imgs[customer.getCompany()][path.getOrientation()];

                    g.drawImage(
                            bike,
                            adjusted.getX() - 10 - bike.getWidth(this)/2,
                            adjusted.getY() - 10 - bike.getHeight(this)/2,
                            this
                    );
                }

            }
        }
    }

    private void debug(Graphics g) {

        int i = 0;
        for (Route route : Direction.ROUTES) {

            g.setColor(colors[i/2]);
            for (Path path : route.getPaths()) {

                Point start = path.getStart().scale(originalSize, currentSize);
                Point end = path.getEnd().scale(originalSize, currentSize);

                g.drawLine(start.getX(), start.getY(), end.getX(), end.getY());

            }

            g.setColor(Color.BLACK);
            for (int j = 0; j <= route.getExpected(); j++) {

                Point point = route.getPoint(j, route.getExpected());

                Point adjusted = point.scale(originalSize, currentSize);

                g.fillRect(adjusted.getX() - 5, adjusted.getY() - 5, 10, 10);
            }

            i++;
        }

    }

    @Override
    public void componentResized(ComponentEvent e) {
        this.width = e.getComponent().getWidth();
        this.height = e.getComponent().getHeight();
        this.currentSize = new Point(width, height);

        Bikes.refresh(width, height);

        this.image = scaleImage(original, width, height);
        this.test2 = scaleImage(test, 30, 30);
        this.repaint();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        System.out.println(e);
    }

    @Override
    public void componentShown(ComponentEvent e) {
        System.out.println(e);

    }

    @Override
    public void componentHidden(ComponentEvent e) {
        System.out.println(e);

    }

    private List<Customer> customers;

    public void refresh(List<Customer> customers) {
        this.customers = customers;
        this.repaint();
    }
}
