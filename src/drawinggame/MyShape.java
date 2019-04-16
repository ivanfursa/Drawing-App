package drawinggame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class MyShape {
    // An elementary unit of shape is a dot. For now, a dot is a rectangle.
    private ArrayList<Rectangle2D> dots = new ArrayList<>();
    private Color color;
    private double size;
    private boolean drawn = false;  // Identifies if the shape has been drawn.
    
    // Maximum and minimum x,y points (corners) of the shape.
    private double xMin, xMax, yMin, yMax;
    
    // Constructors
    public MyShape(Point2D point, Color color, double size){
        this.color = color;
        this.size = size;
        
        // Calculations for a box enclosure.
        xMin = point.getX();
        xMax = point.getX();
        yMin = point.getY();
        yMax = point.getY();
        
        // Creating a dot (a rectangle).
        addDot(point);
    }
    public MyShape(){}
    
    public void addPoint(Point2D point){
        addDot(point);
        
        // Calculations for a box enclosure.
        if (point.getX() < xMin)
            xMin = point.getX();
        else if (point.getX() > xMax)
            xMax = point.getX();
        if (point.getY() < yMin)
            yMin = point.getY();
        else if (point.getY() > yMax)
            yMax = point.getY();
    }
    
    public void addDot(Point2D point){
        double x = point.getX();
        double y = point.getY();
        
        // Otherwise, a rectangle will have 0 width and length.
        if (size > 1){
            x = x - size/2;
            y = y - size/2;
        }
        
        Rectangle2D dot = new Rectangle2D.Double(x, y, size, size);
        dots.add(dot);
    }
    
    public void setAsDrawn() {drawn = true;}
    public boolean isDrawn() {return drawn;}
    
    public void drawShape(Graphics2D g2){
        g2.setPaint(color);
        if (dots.isEmpty()){
            return;
        }
        for (Rectangle2D dot : dots){
            g2.fill(dot);
        }
    }
    
    public void drawBox(Graphics2D g2){
        g2.setPaint(Color.BLACK);
        if (dots.isEmpty()){
            return;
        }       
        // Draw a rectangle enclosing the shape.
        double width = xMax - xMin;
        double height = yMax - yMin;
        Rectangle2D box = new Rectangle2D.Double(xMin, yMin, width, height);
        g2.draw(box);
    }
}
