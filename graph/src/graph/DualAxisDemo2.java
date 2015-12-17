package graph;
import java.awt.Color;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * An example of a time series chart.  For the most part, default settings are used, except that
 * the renderer is modified to show filled shapes (as well as lines) at each data point.
 *
 */
public class DualAxisDemo2 extends ApplicationFrame {

    /**
     * A demonstration application showing how to create a time series chart with dual axes.
     *
     * @param title  the frame title.
     */
    public DualAxisDemo2(final String title) {

        super(title);

        // create a title...
        final String chartTitle = "insulin bolus graph";
        final XYDataset dataset = createDataset1();

        final JFreeChart chart = ChartFactory.createTimeSeriesChart(
            chartTitle, 
            "Date", 
            "sugar level",
            dataset, 
            true, 
            true, 
            false
        );

  //      final StandardLegend legend = (StandardLegend) chart.getLegend();
    //    legend.setDisplaySeriesShapes(true);
        
        final XYPlot plot = chart.getXYPlot();
        final NumberAxis axis2 = new NumberAxis("bolus");
        axis2.setRange(50.0,450.0);
        plot.setRangeAxis(1, axis2);
        plot.setDataset(1, createDataset2());
        plot.mapDatasetToRangeAxis(1, 1);
        final XYItemRenderer renderer = plot.getRenderer();
        renderer.setToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
        if (renderer instanceof StandardXYItemRenderer) {
            final StandardXYItemRenderer rr = (StandardXYItemRenderer) renderer;
       //     rr.setPlotShapes(true);
            rr.setShapesFilled(true);
        }
        
        final StandardXYItemRenderer renderer2 = new StandardXYItemRenderer();
        renderer2.setSeriesPaint(0, Color.black);
      //  renderer2.setPlotShapes(true);
        renderer.setToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
        plot.setRenderer(1, renderer2);
        
        final DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
        
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    /**
     * Creates a sample dataset.
     *
     * @return The dataset.
     */
    private XYDataset createDataset1() {

        final TimeSeries s1 = new TimeSeries("sugar level", Month.class);
        s1.add(new Month(2, 2001), 181.8);
        s1.add(new Month(3, 2001), 167.3);
        s1.add(new Month(4, 2001), 153.8);
        s1.add(new Month(5, 2001), 167.6);
        s1.add(new Month(6, 2001), 158.8);
        s1.add(new Month(7, 2001), 118.3);
        s1.add(new Month(8, 2001), 103.9);
        s1.add(new Month(9, 2001), 90.7);
        s1.add(new Month(10, 2001), 153.2);
        s1.add(new Month(11, 2001), 171.8);
        s1.add(new Month(12, 2001), 139.6);
        s1.add(new Month(1, 2002), 92.9);
        s1.add(new Month(2, 2002), 58.7);
        s1.add(new Month(3, 2002), 85.3);
        s1.add(new Month(4, 2002), 75.9);
        s1.add(new Month(5, 2002), 55.8);
        s1.add(new Month(6, 2002), 50.0);
        s1.add(new Month(7, 2002), 60.8);

        final TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);

        return dataset;

    }

    /**
     * Creates a sample dataset.
     *
     * @return The dataset.
     */
    private XYDataset createDataset2() {


        final TimeSeries s2 = new TimeSeries("bolus", Month.class);
        s2.add(new Month(2, 2001), 429.6);
        s2.add(new Month(3, 2001), 323.2);
        s2.add(new Month(4, 2001), 417.2);
        s2.add(new Month(5, 2001), 424.1);
        s2.add(new Month(6, 2001), 222.6);
        s2.add(new Month(7, 2001), 419.2);
        s2.add(new Month(8, 2001), 316.5);
        s2.add(new Month(9, 2001), 212.7);
        s2.add(new Month(10, 2001), 401.5);
        s2.add(new Month(11, 2001), 306.1);
        s2.add(new Month(12, 2001), 10.3);
        s2.add(new Month(1, 2002), 111.7);
        s2.add(new Month(2, 2002), 411.0);
        s2.add(new Month(3, 2002), 309.6);
        s2.add(new Month(4, 2002), 213.2);
        s2.add(new Month(5, 2002), 111.6);
        s2.add(new Month(6, 2002), 208.8);
        s2.add(new Month(7, 2002), 301.6);

        final TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s2);

        return dataset;

    }

    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args)  {

        final DualAxisDemo2 demo = new DualAxisDemo2("Dual Axis Demo 2");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}


