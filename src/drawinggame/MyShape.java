package drawinggame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class MyShape {
    private ArrayList<Point2D> points = new ArrayList<>();
    private Color color;
    private double size;
    private boolean drawn = false;  // Identifies if the shape has been drawn.
    
    // Maximum and minimum x,y points (corners) of the shape.
    private double xMin, xMax, yMin, yMax;
    
    // Constructors
    public MyShape(Point2D point, Color color, double size){
        this.color = color;
        this.size = size;
        points.add(point);
        xMin = point.getX();
        xMax = point.getX();
        yMin = point.getY();
        yMax = point.getY();
    }
    public MyShape(){}
    
    public void addPoint(Point2D point){
        points.add(point);
        if (point.getX() < xMin)
            xMin = point.getX();
        else if (point.getX() > xMax)
            xMax = point.getX();
        if (point.getY() < yMin)
            yMin = point.getY();
        else if (point.getY() > yMax)
            yMax = point.getY();
    }
    
    public void setAsDrawn() {drawn = true;}
    public boolean isDrawn() {return drawn;}
    
    public void drawShape(Graphics2D g2){
        g2.setPaint(color);
        if (points.isEmpty()){
            return;
        }
        Point2D start = points.get(0);
        for (Point2D end : points){
            g2.draw(new Line2D.Double(start, end));
            start = end;
        }
    }
    
    public void drawBox(Graphics2D g2){
        g2.setPaint(Color.BLACK);
        if (points.isEmpty()){
            return;
        }       
        // Draw a rectangle enclosing the shape.
        double width = xMax - xMin;
        double height = yMax - yMin;
        Rectangle2D box = new Rectangle2D.Double(xMin, yMin, width, height);
        g2.draw(box);
    }
}
