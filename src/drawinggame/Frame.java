package drawinggame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Frame extends JFrame {
    public Canvas canvas;
    
    public Frame() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setSize(600, 600);
        setLayout(new BorderLayout());
        
        canvas = new Canvas();
        canvas.setFocusable(true);  // Allowing keyListener
        add(canvas, BorderLayout.CENTER);
        
        // Adding a manu that provides brush color and size options
        JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);
        
        JMenu colorMenu = new JMenu("Color");
        JMenu sizeMenu = new JMenu("Size");
        bar.add(colorMenu);
        bar.add(sizeMenu);
        
        JMenuItem green = new JMenuItem("Green");
        JMenuItem red = new JMenuItem("Red");
        JMenuItem blue = new JMenuItem("Blue");
        colorMenu.add(green);
        colorMenu.add(red);
        colorMenu.add(blue);
        
        JMenuItem s1 = new JMenuItem("1");
        JMenuItem s2 = new JMenuItem("2");
        JMenuItem s5 = new JMenuItem("5");      
        sizeMenu.add(s1);
        sizeMenu.add(s2);
        sizeMenu.add(s5);
        
        // Action listeners for the menu items.
        green.addActionListener(new ColorListener(Color.GREEN));
        red.addActionListener(new ColorListener(Color.RED));
        blue.addActionListener(new ColorListener(Color.BLUE));
        s1.addActionListener(new SizeListener(1));
        s2.addActionListener(new SizeListener(2));
        s5.addActionListener(new SizeListener(5));
        
        // A panel for optional buttons.
        JPanel options = new JPanel();
        
        // Adding reset button that removes all the shapes from canvas.
        JButton repaint = new JButton("repaint");
        // Not letting the button taking a focus of keyboard.
        repaint.setFocusable(false);
        options.add(repaint);
        repaint.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                canvas.getShapes().clear();
                canvas.repaint();
            }
        });
        
        // Adding buttons that change the canvas mode
        JButton modeEdit = new JButton("edit mode");
        JButton modeDraw = new JButton("draw mode");
        // Not letting the buttons taking a focus of keyboard.
        modeEdit.setFocusable(false);
        modeDraw.setFocusable(false);
        options.add(modeEdit);
        options.add(modeDraw);
        modeEdit.addActionListener(new ModeListener('e'));
        modeDraw.addActionListener(new ModeListener('d'));
                
        add(options, BorderLayout.SOUTH);
    }
    
    // ActionListeners for menu items and buttons
    class ColorListener implements ActionListener {
        private Color color;
        
        public ColorListener() {color = Color.RED;}
        public ColorListener(Color color) {this.color = color;}
        
        @Override
        public void actionPerformed(ActionEvent e){
            canvas.changeColor(color);
        }
    }
    class SizeListener implements ActionListener {
        private double size;
        
        public SizeListener() {size = 1;}
        public SizeListener(double size) {this.size = size;}
        
        @Override
        public void actionPerformed(ActionEvent e){
            canvas.changeWidth(size);
        }
    }
    class ModeListener implements ActionListener {
        private char mode;
        
        public ModeListener() {mode = 'd';}
        public ModeListener(char mode) {this.mode = mode;}
        
        @Override
        public void actionPerformed(ActionEvent e){
            canvas.setMode(mode);
            if (mode == 'd')
                canvas.unselectShape();
                canvas.repaint();
        }
    }
}
