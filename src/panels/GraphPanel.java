package panels;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import utils.Settings;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphPanel extends JPanel {

    private static final String TITLE = "Real Time Preference Rating";

    private XYChart chart;
    private XChartPanel panel;

    private List<Double> xPoints;
    private List<Double> yPoints;

    private List<Double> middle;

    private int width;
    private int height;

    public GraphPanel() {
        super();
        xPoints = new ArrayList<>();
        yPoints = new ArrayList<>();
        middle = new ArrayList<>();
    }

    public void insert(double x, double y) {
        xPoints.add(x);
        yPoints.add(y);
        middle.add(0.5);
        chart.updateXYSeries("default", xPoints, middle, null);
        chart.updateXYSeries("pref", xPoints, yPoints, null);
        panel.repaint();
    }

    public void createDefaultChart(int w, int h){
        this.width = w;
        this.height = h;
        chart = defaultChart(width, height);
        panel = new XChartPanel(chart);
        add(panel);
    }

    public void reset() {
        xPoints = new ArrayList<>();
        yPoints = new ArrayList<>();
        middle = new ArrayList<>();
        chart.getStyler().setXAxisMax(Settings.NUM_OF_DAYS*1.0);
        chart.updateXYSeries("default", xPoints, middle, null);
        chart.updateXYSeries("pref", xPoints, yPoints, null);
        panel.repaint();
    }

    private XYChart defaultChart(int w, int h) {

        XYChart chart = new XYChartBuilder().width(w).height(h).title(TITLE).build();

        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setAxisTitlesVisible(false);
        chart.getStyler().setYAxisMin(0.0);
        chart.getStyler().setYAxisMax(1.0);
        chart.getStyler().setXAxisMin(0.0);
        chart.getStyler().setXAxisMax(Settings.NUM_OF_DAYS*1.0);
        chart.getStyler().setMarkerSize(4);
        chart.getStyler().setSeriesColors(new Color[]{Color.RED, Color.BLUE});
        chart.getStyler().setChartBackgroundColor(new Color(244, 242, 232));
        chart.addSeries("default", new double[] {0}, new double[] {0.5});
        chart.addSeries("pref", new double[] {0}, new double[] {0.5});

        return chart;
    }


    public void refresh() {
        double event = (Settings.EVENT_COUNTER * 1.0) / (Settings.TICKS_PER_HOUR * 12);
        double pref = Settings.PREFERENCE;
        this.insert(event, pref);
    }
}
