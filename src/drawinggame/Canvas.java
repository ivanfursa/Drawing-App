package drawinggame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Canvas extends JPanel{
    private ArrayList<Shape> shapes = new ArrayList<>();
    private Color currentColor = Color.RED;
    private double currentSize = 1;
    private Shape selectedShape = null;
    
    public Canvas(){
        addMouseListener(new MouseAdapter(){
           public void mousePressed(MouseEvent e){
                if(e.getButton() == 1){
                    shapes.add(new Shape(e.getPoint(), currentColor, 
                        currentSize));
                   repaint();
               }
           }
           public void mouseReleased(MouseEvent e){
                if(e.getButton() == 1)
                    shapes.get(shapes.size()-1).setAsDrawn();
           }
           public void mouseClicked(MouseEvent e){
                if(e.getButton() == 1){
                    selectedShape = null;
                    int count = 0;
                    Shape shape;
                    while (count < shapes.size() && selectedShape == null){
                        shape = shapes.get(count);
                        if (shape.isSelected(e.getPoint()))
                            selectedShape = shape;
                        count ++;
                    }
                }
           }
        });
        
        addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e){
                if((MouseEvent.BUTTON1_DOWN_MASK & e.getModifiersEx())!= 0){
                   shapes.get(shapes.size()-1).addPoint(e.getPoint());
                   repaint();
                }
            }  
        });
    }
    
    // Setters for drawing attributes.
    public void changeColor(Color newColor) {currentColor = newColor; }
    public void changeSize(double newSize) {currentSize = newSize; }
    
    public ArrayList<Shape> getShapes() {return shapes;}
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Shape s : shapes){
            s.drawShape(g2);
            if (selectedShape != null)
                selectedShape.getBox().draw(g2);
        }
    }
    
}
