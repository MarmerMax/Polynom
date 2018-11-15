package myMath;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

/**
 * This class create setting for our graph. Add function and minimum and maximum points to graph.
 * @author Max Marmer
 *
 */
public class PolynomPrint extends JFrame {
	
    /**
     * This method do all work for create a rigth graph.
     * @param p1 polynom that we want to graph
     * @param from x0
     * @param to x1
     * @param minP array list of minimum points
     * @param maxP array list of maximum points
     */
    public PolynomPrint(Polynom p1, double from, double to, ArrayList<Double> minP, ArrayList<Double> maxP) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        
        DataTable data = new DataTable(Double.class, Double.class); // create database that include all x,y
        for (double x = from; x <= to; x+=0.25) {
            double y = p1.f(x); //count the y=f(x)
            data.add(x, y);   //add x,y to database
        }
        
        DataTable minmax = new DataTable(Double.class, Double.class); //create database for min max points
        ArrayList<Double> sortedList = this.sortLists(minP, maxP); //create common array list for min and max after sort 
        for(Double x: sortedList) {
        	minmax.add(x, p1.f(x)); // add x,y of min and max to database
        }
        
        
        XYPlot plot = new XYPlot(data, minmax); // create new plot
        getContentPane().add(new InteractivePanel(plot));
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderers(data, lines); //add database to plot
        Color color1 = new Color(0.0f, 0.3f,1.0f); // set color1 
        Color color2 = new Color(255,0,0); // set color2
        plot.getPointRenderers(data).get(0).setColor(color1); // color1 for polynom function 
        plot.getPointRenderers(minmax).get(0).setColor(color2); // color2 for min max points
        plot.getLineRenderers(data).get(0).setColor(color1);
    }
	/**
	 * That private function create common array list for min and max point 
	 * @param minP array list of minimum points
	 * @param maxP array list of maximum points
	 * @return sorted array list with all points
	 */
	private ArrayList<Double> sortLists(ArrayList<Double> minP, ArrayList<Double> maxP) {
		boolean result = minP.addAll(maxP);
		Collections.sort(minP);
		return minP;
	}
}