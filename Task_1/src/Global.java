
package Task_1.src;

public class Global {
	public static final int  WAKEUP = 1, MEASURE = 2, MESSAGE = 3, FEEDBACK = 4;
	public static int n = 1000, ts = 1, Tp = 4000, area = 10000; //needs to be in a config file 
	public static double time = 0;
	public static int X = 0, Y = 1;
	public static Node [] nodes = new Node[n+1];
	public static Coords [] positions = new Coords[n+1];
	public static int GATEWAY = 0; //gateway node
	public static boolean transmission = false;
	public static int transmissionID = 0;
	public static boolean transmissionValid = true;
}
