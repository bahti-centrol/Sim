package panels;

import net.miginfocom.swing.MigLayout;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import utils.Settings;
import utils.Stock;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StockPanel extends JPanel {

    private static final String TITLE = "Real Time Scooter Stocks";

    private ArrayList<String> locations = new ArrayList<>(Arrays.asList("Manhattan", "Queens", "Jones Beach", "Stony Brook"));

    private CategoryChart chart;
    private XChartPanel panel;

    private List<Double> xPoints;
    private List<Double> yPoints;

    private List<Double> middle;

    private int width;
    private int height;

    public StockPanel() {
        super(new MigLayout(""));
        xPoints = new ArrayList<>();
        yPoints = new ArrayList<>();
        middle = new ArrayList<>();
    }

    public void insert(double x, double y) {
        xPoints.add(x);
        yPoints.add(y);
        middle.add(0.5);
        //chart.updateXYSeries("default", xPoints, middle, null);
        //chart.updateXYSeries("pref", xPoints, yPoints, null);
        panel.repaint();
    }

    public void createDefaultChart(int w, int h){
        this.width = w;
        this.height = h;
        chart = defaultChart(width, height);
        panel = new XChartPanel(chart);
        add(panel, "growx, pushx, pushy, growy");
    }

    public void reset() {
        xPoints = new ArrayList<>();
        yPoints = new ArrayList<>();
        middle = new ArrayList<>();
        chart.getStyler().setXAxisMax(Settings.NUM_OF_DAYS*1.0);
        //chart.updateXYSeries("default", xPoints, middle, null);
        //chart.updateXYSeries("pref", xPoints, yPoints, null);
        panel.repaint();
    }

    private CategoryChart defaultChart(int w, int h) {

        // Create Chart
        CategoryChart chart = new CategoryChartBuilder()
                                        .width(w)
                                        .height(h)
                                        .title("Real Time Scooter Stock")
                                        .build();

        int x_split = Settings.COMP_X_INITIAL / 4;
        int y_split = Settings.COMP_Y_INITIAL / 4;

        int max = x_split + y_split;

        chart.getStyler().setChartBackgroundColor(new Color(244, 242, 232));
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setYAxisMin(0.0);
        chart.getStyler().setYAxisMax(max*1.0);
        chart.getStyler().setSeriesColors(new Color[]{Color.BLUE, Color.RED});



        chart.addSeries("CompanyX", locations, new ArrayList<Number>(Arrays.asList(x_split, x_split, x_split, x_split)));
        chart.addSeries("CompanyY", locations, new ArrayList<Number>(Arrays.asList(y_split, y_split, y_split, y_split)));

/*
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
*/
        return chart;
    }


    public void refresh(Stock stock) {
        //chart.updateXYSeries("default", xPoints, middle, null);
        chart.updateCategorySeries("CompanyX", locations, stock.getCompanies()[0], null);
        chart.updateCategorySeries("CompanyY", locations, stock.getCompanies()[1], null);
        panel.repaint();

    }
}
