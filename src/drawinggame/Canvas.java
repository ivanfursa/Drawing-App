package drawinggame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    
    private boolean moveShape = false;
    
    
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
                                        
                    for (int i = 0; i < shapes.size(); i++){
                        if (shapes.get(i).isSelected(e.getPoint())){
                            selectedShape = 0;
                            addToUnderSelection(i);
                            underSelection.get(selectedShape).setShiftReference(e.getPoint());
                            repaint();  // to show selection box immediately
                        }
                    }
                    
                    repaint(); // Removes previous selection if clicked ouside
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
                else if (e.getButton() == 3 && mode == 'e'){
                    moveShape = false;
                    repaint();
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
            
            public void mouseMoved(MouseEvent e){
                if (moveShape == true && mode == 'e'){
                    if (selectedShape != -1){
                        underSelection.get(selectedShape).shift(e.getPoint());
                        repaint();
                    }
                }
            }
        });
        
        addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                if (e.getKeyChar() == 'm'){
                    if (moveShape == false)
                        moveShape = true;
                    else
                        moveShape = false;
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
    public void unselectShape() {selectedShape = -1;}
    
    public void addToUnderSelection(int i){
        // We want underSelection to be sorted by size: smaller > larger
        // Therefore, given an index i of the shape to be placed in the list,
        // we compare its area to the shapes' that are already there
        if (underSelection.size() > 0){
            double iArea = shapes.get(i).getBox().getArea();
            double cArea;
            for (int c = 0; c < underSelection.size(); c++){
                cArea = underSelection.get(c).getBox().getArea();
                if (iArea > cArea)
                    ;
                else
                    underSelection.add(c, shapes.get(i));
                    break;
            }
        } else underSelection.add(shapes.get(i));
    }
    
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
