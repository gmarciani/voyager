package voyager.nove.plotter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

/**
 * @name Plotter
 *
 * @project Voyager
 *
 * @package voyager.nove.plotter
 *
 * @author Giacomo Marciani
 *
 */
public class Plotter {
	
	private static Plotter singletonPlotter;

	private Plotter() {}
	
	public static Plotter getInstance() {
		if (singletonPlotter == null) {
			singletonPlotter = new Plotter();
		}
		
		return singletonPlotter;
	}
	
	public ChartPanel plot(String title, long part, long tot) {
		PieDataset dataset = createDataset(part, tot);
        JFreeChart chart = createChart(title, dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
	}
	
	private  PieDataset createDataset(long part, long tot) {
        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("Elemento", part);
        result.setValue("Tot", tot);
        return result;        
    }
	
	private JFreeChart createChart(String title, PieDataset dataset) {        
        JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true, true, false);

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;        
    }

}
