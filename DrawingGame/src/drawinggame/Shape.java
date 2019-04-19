package drawinggame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Shape {
    private ArrayList<Point2D> points = new ArrayList<>();
    private Color color;
    private double size;
    private boolean drawn = false;  // Identifies if the shape has been drawn.
    private BoxBorder box;
       
    // Constructors
    public Shape(Point2D point, Color color, double size){
        this.color = color;
        this.size = size;
        points.add(point);
        box = new BoxBorder(point.getX(), point.getX(),
                point.getY(), point.getY(), size);
    }
    public Shape(){}
    
    public void addPoint(Point2D point){
        points.add(point);
        box.update(point);
    }
    
    public void setAsDrawn() {drawn = true;}
    public boolean isDrawn() {return drawn;}
    
    public BoxBorder getBox() {return box;}
    
    public void drawShape(Graphics2D g2){
        g2.setPaint(color);
        g2.setStroke(new BasicStroke((float)(size)));
        if (points.isEmpty()){
            return;
        }
        Point2D start = points.get(0);
        for (Point2D end : points){
            g2.draw(new Line2D.Double(start, end));
            start = end;
        }
    }
    
    
}
