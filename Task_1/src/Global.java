
package Task_1.src;

public class Global {
	public static final int  WAKEUP = 1, PREPMESSAGE = 2, MESSAGE = 3, FEEDBACK = 4;
	public static int n, ts, Tp, area, radius, strategy; //stored in config file
	public static double time = 0;
	public static int X = 0, Y = 1;
	public static Node [] nodes;
	public static Coords [] positions;
	public static int[][] allNearest;
	public static int GATEWAY = 0; //gateway node
	public static boolean gatewayRecieving = false;
	public static int transmissionID = 0;
	public static boolean transmissionValid = true;
	public static double sentTime = 0.0;
}
