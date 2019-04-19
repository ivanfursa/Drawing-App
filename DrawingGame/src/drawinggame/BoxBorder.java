package drawinggame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

class BoxBorder {
    // The corners of the box enclosure.
    private double xMin, xMax, yMin, yMax;
    private double size;
    
    public BoxBorder(double xMin, double xMax,
            double yMin, double yMax, double size){
        this.xMin = xMin - size;
        this.xMax = xMax + size;
        this.yMin = yMin - size;
        this.yMax = yMax + size;
        this.size = size;
    }
    
    public void update(Point2D point){
        if (point.getX() < xMin)
            xMin = point.getX() - size;
        else if (point.getX() > xMax)
            xMax = point.getX() + size;
        if (point.getY() < yMin)
            yMin = point.getY() - size;
        else if (point.getY() > yMax)
            yMax = point.getY() + size;
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
