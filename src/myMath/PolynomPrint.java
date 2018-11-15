package myMath;

import java.awt.Color;
import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

public class PolynomPrint extends JFrame {
	
    
    public PolynomPrint(Polynom p1, double from, double to) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        
        DataTable data = new DataTable(Double.class, Double.class);
        for (double x = from; x <= to; x+=0.25) {
            double y = p1.f(x);
            data.add(x, y);            
        }
        XYPlot plot = new XYPlot(data);
        getContentPane().add(new InteractivePanel(plot));
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderers(data, lines);
        Color color = new Color(0.0f, 0.3f, 1.0f);
        plot.getPointRenderers(data).get(0).setColor(color);
        plot.getLineRenderers(data).get(0).setColor(color);
    }
}