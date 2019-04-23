package drawinggame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Shape {
    // Hand-drawn shape is defined by a collection of points (their coordinates)
    // on canvas. 
    private ArrayList<Point2D> points = new ArrayList<>();
    private Color color;
    private double strokeWidth;
    private BoxBorder box;
    
    // A point in relation to which a shape is shifted.
    private Point2D shiftReference;
       
    // Constructors
    public Shape(Point2D point, Color color, double strokeWidth){
        this.color = color;
        this.strokeWidth = strokeWidth;
        points.add(point);
        box = new BoxBorder(point.getX(), point.getX(),
                point.getY(), point.getY(), strokeWidth);
    }
    public Shape(){}
    
    public void addPoint(Point2D point, ImageProcessor im){
        points.add(point);
        im.addPixel(point, color);  // Update pixel canvas in Image Processor
        box.update(point);
    }
    
    // Box getter.
    public BoxBorder getBox() {return box;}
    
    // Check if a mouse was clicked on a rectangular area of the shape.
    public boolean isSelected(Point2D point){
        if (point.getX() < box.xMax && point.getX() > box.xMin){
            if (point.getY() < box.yMax && point.getY() > box.yMin)
                return true;
            else
                return false;
        }
        else
            return false;
    }
    
    public void drawShape(Graphics2D g2){
        g2.setPaint(color);
        g2.setStroke(new BasicStroke((float)(strokeWidth)));
        if (points.isEmpty()){
            return;
        }
        Point2D start = points.get(0);
        for (Point2D end : points){
            g2.draw(new Line2D.Double(start, end));
            start = end;
        }
    }
    
    // Setter for shift reference.
    public void setShiftReference(Point2D point) {shiftReference = point;}
    
    // The mouse position point is compared to the shift reference point
    // and all the points in the shape are moved according to the difference
    public void shift(Point2D shiftPoint){
        double xShift = shiftPoint.getX() - shiftReference.getX();
        double yShift = shiftPoint.getY() - shiftReference.getY();
        
        for (int i = 0; i < points.size(); i++){
            Point2D point = points.get(i);
            points.add(i, new Point2D.Double(point.getX()+xShift,
                    point.getY()+yShift));
            points.remove(i+1);
        }
        
        // Box must also be updated.
        box.xMax += xShift;
        box.xMin += xShift;
        box.yMax += yShift;
        box.yMin += yShift;
        
        shiftReference = shiftPoint;
    }
    
}
