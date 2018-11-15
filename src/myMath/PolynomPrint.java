package myMath;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

public class PolynomPrint extends JFrame {
	
    
    public PolynomPrint(Polynom p1, double from, double to, ArrayList<Double> minP, ArrayList<Double> maxP) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        
        DataTable data = new DataTable(Double.class, Double.class);
        for (double x = from; x <= to; x+=0.25) {
            double y = p1.f(x);
            data.add(x, y);            
        }
        DataTable minmax = new DataTable(Double.class, Double.class);
        double [] arr = new double[minP.size() + maxP.size()];
        Iterator<Double> it1 = minP.iterator();
        Iterator<Double> it2 = maxP.iterator();
        int index = 0;
        double yX = 0;
        while(it1.hasNext() || it2.hasNext()) {
        	if(it1.hasNext()) {
        		arr[index] = it1.next();
        		yX = p1.f(arr[index]);
        	}
        	else {
        		arr[index] = it2.next();
        		yX = p1.f(arr[index]);
        	}
    		minmax.add(arr[index], yX);
        	index++;
        }
        System.out.println(Arrays.toString(arr));
        XYPlot plot = new XYPlot(data);
        getContentPane().add(new InteractivePanel(plot));
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderers(data, lines);
        Color color = new Color(50,100,150);
        plot.getPointRenderers(data).get(0).setColor(color);
     //   plot.getPointRenderers(minmax).get(0).setColor(color);
        plot.getLineRenderers(data).get(0).setColor(color);
        plot.getMapping(minmax);
      //  color = new Color(55, 55, 55);
      //  plot.getPointRenderers(minmax).get(0).setColor(color);
    }
}