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
    // Index of a selected shape. -1 means no shape is selected
    private int selectedShape = -1;
    // Shapes that user might have wanted to select.
    private ArrayList<Shape> underSelection = new ArrayList<>();
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
                // Shape is selected using left-click. Only in edit mode.
                else if(e.getButton() == 1 && mode == 'e'){
                    underSelection.clear();
                    selectedShape = -1;
                    int count = 0;
                    while (count < shapes.size()){
                        if (shapes.get(count).isSelected(e.getPoint())){
                            selectedShape = 0;
                            repaint();  // to show selection box immediately
                            underSelection.add(shapes.get(count));
                            underSelection.get(selectedShape).setShiftReference(e.getPoint());
                        }
                        count ++;
                    }
                }
                else if (e.getButton() == 2 && mode == 'e'
                        && !underSelection.isEmpty()){
                    if (selectedShape < underSelection.size()-1){
                        selectedShape++;
                        repaint();
                    }
                    else
                        selectedShape = 0;{
                        repaint();
                    }
                }
            }
            
            public void mouseClicked(MouseEvent e){}
        });
        
        addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e){
                // Add points to the shape being drawn. Only in drawing mode.
                if((MouseEvent.BUTTON1_DOWN_MASK & e.getModifiersEx())!= 0
                        && mode == 'd'){
                   shapes.get(shapes.size()-1).addPoint(e.getPoint());
                   repaint();
                }
                // Move the shape. Only in edit mode.
                if((MouseEvent.BUTTON1_DOWN_MASK & e.getModifiersEx())!= 0
                        && mode == 'e'){
                    if (selectedShape != -1){
                        underSelection.get(selectedShape).shift(e.getPoint());
                        repaint();
                    }
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
    public void unselectShape() {selectedShape = -1;}
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Shape s : shapes){
            s.drawShape(g2);
            if (selectedShape != -1)
                underSelection.get(selectedShape).getBox().draw(g2);
        }
    }
    
}
