
package Task_1.src;

import javax.swing.JFrame;

public class Global extends JFrame {
	public static final int ARRIVAL = 1, READY = 2, MEASURE = 3, WAKEUP = 4, SLEEP = 5, SENDM = 6, MESSAGE = 7, FEEDBACK = 8;
	public static int n = 1000, ts = 1, Tp = 4000, area = 10000; //needs to be in a config file 
	public static double time = 0;
	public static int X = 0, Y = 1;
	public Node [] nodes = new Node[n+1];
	public static Coords [] positions = new Coords[n+1];
	public static int GATEWAY = 0; //gateway node
	public boolean transmission = false;
	public int transmissionID;
}
