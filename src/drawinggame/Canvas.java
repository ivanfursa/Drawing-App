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
    private double currentStrokeWidth = 1;
    private Shape selectedShape = null;
    private char mode = 'd';
    
    public Canvas(){
        
        addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                // Instantiating a new shape. Only in drawing mode.
                if(e.getButton() == 1 && mode == 'd'){
                    shapes.add(new Shape(e.getPoint(), currentColor, 
                        currentStrokeWidth));
                   repaint();
                }
            }
            
            public void mouseClicked(MouseEvent e){
                // Shape is selected using left-click. Only in edit mode.
                if(e.getButton() == 1 && mode == 'e'){
                    selectedShape = null;
                    int count = 0;
                    while (count < shapes.size() && selectedShape == null){
                        if (shapes.get(count).isSelected(e.getPoint())){
                            selectedShape = shapes.get(count);
                            repaint();  // to show selection box immediately
                        }
                        count ++;
                    }
                }
           }
        });
        
        addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e){
                // Add points to the shape being drawn. Only in drawing mode.
                if((MouseEvent.BUTTON1_DOWN_MASK & e.getModifiersEx())!= 0
                        && mode == 'd'){
                   shapes.get(shapes.size()-1).addPoint(e.getPoint());
                   repaint();
                }
            }  
        });
    }
    
    // Setters for drawing attributes.
    public void changeColor(Color newColor) {currentColor = newColor; }
    public void changeWidth(double newWidth) {currentStrokeWidth = newWidth; }
    // Getter for shapes arraylist.
    public ArrayList<Shape> getShapes() {return shapes;}
    // Setter for canvas mode.
    public void setMode(char mode) {this.mode = mode;}
    // Unselect shape to remove the box around it.
    public void unselectShape() {selectedShape = null;}
    
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
