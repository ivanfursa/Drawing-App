package drawinggame;

import java.awt.Color;
import java.awt.geom.Point2D;

public class ImageProcessor {
    private final double[][] GAUSSIAN_KERNEL = {{1,2,1},{2,4,2},{1,2,1}};
    private int[][] pixels;
    private final int WIDTH, HEIGHT; // Dimensions of canvas.
    
    public ImageProcessor(int width, int height, Color color){
        WIDTH = width;
        HEIGHT = height;
        pixels = new int[(WIDTH+1)*(HEIGHT+1)][3];
        
        // Create the initial 2D array of pixels' RGB of the background.
        for (int i=0; i<WIDTH*HEIGHT; i++){
            pixels[i][0] = color.getRed();
            pixels[i][1] = color.getGreen();
            pixels[i][2] = color.getBlue();
        }
    }
    
    public int[][] getPixels(){return pixels;}
    
    // Updates pixel image during shape creation and transformation.
    public void addPixel(Point2D point, Color color){
        int x = (int) point.getX();
        int y = (int) point.getY();
        int place = x * (WIDTH+1) + y;
        
        pixels[place][0] = color.getRed();
        pixels[place][1] = color.getGreen();
        pixels[place][2] = color.getBlue();
    }
    
    public void applyGaussian(){};
}
