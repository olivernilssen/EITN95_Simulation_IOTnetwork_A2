package Task_1.src;

import javax.swing.JFrame;
import java.awt.*;       // AWT package is responsible for creating GUI
import javax.swing.*;    // Java swing package is responsible to provide UI components
// AWT class extents Jframe which is part of Swing package
public class GUI extends Global {
    /**
    *
    */
    // Defining all the static variables
    private static final long serialVersionUID = 1L;
    public static final int SAMPLE_CANVAS_WIDTH  = 500; //meters*
    public static final int SAMPLE_CANVAS_HEIGHT = 500; //meters*
    // The program enters from the main method
    //Here we are creating an instance of the drawing canvas inner class called DrawCanwas
    private DrawCanvas sampleCanvas;
    public GUI() {
        sampleCanvas = new DrawCanvas();
        sampleCanvas.setPreferredSize(new Dimension(SAMPLE_CANVAS_WIDTH, SAMPLE_CANVAS_HEIGHT));
        Container containerPane = getContentPane();
        containerPane.add(sampleCanvas);
        setDefaultCloseOperation(EXIT_ON_CLOSE);   // setting up the default close mechanism
        pack();
        setTitle("......");  // set the desired title of the JFrame
        setVisible(true);    // setVisible method will be set the visibility of the Jframe to true
    }
    /**
    * here drawCanvas is the inner class of the Jpanel which is used for custom drawing
    */
    private class DrawCanvas extends JPanel {
        /**
        *
        */
        private static final long serialVersionUID = 1L;
        // Overriding paintComponent will let you to design your own painting
        @Override
        public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            setBackground(Color.WHITE);  // setting the background color to black
            graphics.setColor(Color.BLACK);  // setting up the color to green
            graphics.fillRect(area/2, area/2, 2, 2); //Gateway
            graphics.setColor(Color.RED); 
            for (int i = 0; i < nodes.length; i++) {
                Coords coords = nodes[i].getPosition();
                graphics.fillOval(coords.x, coords.y, 1, 1);
            }
            graphics.setFont(new Font("Monospaced", Font.PLAIN, 12)); // setting up the font style and font size
            graphics.drawString("Java Graphics in 2D ...", 10, 20);
        }
    }
}