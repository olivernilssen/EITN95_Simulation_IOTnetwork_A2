
package Task_1.src;

public class Global {
	//Global final variables
	public static final int  GATEWAY = 0, WAKEUP = 1, PREPMESSAGE = 2, MESSAGE = 3, FEEDBACK = 4;
	public static double time = 0;
	public static final int X = 0, Y = 1;

	//variables that will be filled once read from the config file
	public static int n, ts, Tp, area, radius, strategy, min_wait, max_wait; //stored in config file
	public static String node_placement, node_interval; 

	//arrays that will be filled once info from config has been filled
	public static Node [] nodes;
	public static Coords [] positions;
	public static int [][] allNeighbours;

	//variables to keep track of transmissions
	public static boolean gatewayRecieving = false;
	public static int transmissionID = 0;
	public static boolean transmissionValid = true; //false is transmission has failed, eg. a crash happend while sending
}
