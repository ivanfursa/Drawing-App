package drawinggame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

class BoxBorder {
    // The corners of the box enclosure.
    private double xMin, xMax, yMin, yMax;
    
    public BoxBorder(double xMin, double xMax, double yMin, double yMax){
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }
    
    public void update(Point2D point){
        if (point.getX() < xMin)
            xMin = point.getX();
        else if (point.getX() > xMax)
            xMax = point.getX();
        if (point.getY() < yMin)
            yMin = point.getY();
        else if (point.getY() > yMax)
            yMax = point.getY();
    }
    
    public void draw(Graphics2D g2){
        g2.setPaint(Color.BLACK);
        g2.setStroke(new BasicStroke(1));
        
        // Draw a rectangle enclosing the shape.
        double width = xMax - xMin;
        double height = yMax - yMin;
        
        if (!(width == 0 || height == 0)) {
            Rectangle2D box = new Rectangle2D.Double(xMin, yMin, width, height);
            g2.draw(box);
        }
    }
}
