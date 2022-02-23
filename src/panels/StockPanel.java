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


    private int width;
    private int height;

    public StockPanel() {
        super(new MigLayout(""));
    }

    public void createDefaultChart(int w, int h){
        this.width = w;
        this.height = h;
        chart = defaultChart(width, height);
        panel = new XChartPanel(chart);
        add(panel, "growx, pushx, pushy, growy");
    }

    public void reset(Stock stock) {
        chart.updateCategorySeries("Red Company", locations, stock.getCompanies()[0], null);
        chart.updateCategorySeries("Blue Company", locations, stock.getCompanies()[1], null);
        panel.repaint();
    }

    private CategoryChart defaultChart(int w, int h) {

        CategoryChart chart = new CategoryChartBuilder().width(w).height(h).title(TITLE).build();

        int x_split = Settings.COMP_X_INITIAL / 4;
        int y_split = Settings.COMP_Y_INITIAL / 4;

        int max = x_split + y_split;

        chart.getStyler().setChartBackgroundColor(new Color(244, 242, 232));
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setYAxisMin(0.0);
        chart.getStyler().setYAxisMax(max*1.0);
        chart.getStyler().setSeriesColors(new Color[]{Color.BLUE, Color.RED});

        chart.addSeries("Red Company", locations, new ArrayList<Number>(Arrays.asList(x_split, x_split, x_split, x_split)));
        chart.addSeries("Blue Company", locations, new ArrayList<Number>(Arrays.asList(y_split, y_split, y_split, y_split)));

        return chart;
    }

    public void refresh(Stock stock) {
        chart.updateCategorySeries("Red Company", locations, stock.getCompanies()[0], null);
        chart.updateCategorySeries("Blue Company", locations, stock.getCompanies()[1], null);
        panel.repaint();
    }
}
