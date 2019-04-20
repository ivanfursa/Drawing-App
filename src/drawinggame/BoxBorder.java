package drawinggame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

// This object is a rectangular box around a shape which is used to denote that
// the shape is selected
class BoxBorder {
    // The corners of the box enclosure.
    public double xMin, xMax, yMin, yMax;
    // To fully enclose the shape, its stroke width must be taken to account
    private double shapeStrokeWidth;
    
    public BoxBorder(double xMin, double xMax,
            double yMin, double yMax, double width){
        this.xMin = xMin - width;
        this.xMax = xMax + width;
        this.yMin = yMin - width;
        this.yMax = yMax + width;
        shapeStrokeWidth = width;
    }
    
    // While a shape is being drawn, the dimensions of its box are updated.
    public void update(Point2D point){
        if (point.getX() < xMin)
            xMin = point.getX() - shapeStrokeWidth;
        else if (point.getX() > xMax)
            xMax = point.getX() + shapeStrokeWidth;
        if (point.getY() < yMin)
            yMin = point.getY() - shapeStrokeWidth;
        else if (point.getY() > yMax)
            yMax = point.getY() + shapeStrokeWidth;
    }
    
    public double getArea(){
        return (xMax - xMin) * (yMax - yMin);
    }
    
    public void draw(Graphics2D g2){
        g2.setPaint(Color.BLACK);
        g2.setStroke(new BasicStroke(1));
        
        double width = xMax - xMin;
        double height = yMax - yMin;
        
        if (!(width == 0 || height == 0)) {
            Rectangle2D box = new Rectangle2D.Double(xMin, yMin, width, height);
            g2.draw(box);
        }
    }
}
