package drawinggame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyFrame extends JFrame {
    public MyPanel p;
    
    public MyFrame() {
        setSize(300, 300);
        p = new MyPanel();
        add(p);
        
        JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);
        JMenu colorMenu = new JMenu("Color");
        JMenu sizeMenu = new JMenu("Size");
        bar.add(colorMenu);
        bar.add(sizeMenu);
        JMenuItem green = new JMenuItem("Green");
        JMenuItem red = new JMenuItem("Red");
        JMenuItem blue = new JMenuItem("Blue");
        JMenuItem s1 = new JMenuItem("1");
        JMenuItem s2 = new JMenuItem("2");
        JMenuItem s5 = new JMenuItem("5");
        
        colorMenu.add(green);
        colorMenu.add(red);
        colorMenu.add(blue);
        sizeMenu.add(s1);
        sizeMenu.add(s2);
        sizeMenu.add(s5);
        
        green.addActionListener(new ColorListener(Color.GREEN));
        red.addActionListener(new ColorListener(Color.RED));
        blue.addActionListener(new ColorListener(Color.BLUE));
        s1.addActionListener(new SizeListener(1));
        s2.addActionListener(new SizeListener(2));
        s5.addActionListener(new SizeListener(5));
    }
    
    class ColorListener implements ActionListener {
        private Color color;
        
        public ColorListener() {color = Color.RED;}
        public ColorListener(Color color) {this.color = color;}
        
        @Override
        public void actionPerformed(ActionEvent e){
            p.changeColor(color);
        }
    }
    class SizeListener implements ActionListener {
        private double size;
        
        public SizeListener() {size = 1;}
        public SizeListener(double size) {this.size = size;}
        
        @Override
        public void actionPerformed(ActionEvent e){
            p.changeSize(size);
        }
    }
}
