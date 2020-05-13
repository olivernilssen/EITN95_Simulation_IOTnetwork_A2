package Task_1.src;

import java.util.*;

//It inherits Proc so that we can use time and the signal names without dot notation 
class Gen extends Proc{
	//The random number generator is started:
	public Random slump = new Random();
	public Proc sendTo;  //Says to which process we want to send customers

	public int[] coordinates(){
		int[] xy = new int[2];
		xy[0] = slump.nextInt(area);
		xy[1] = slump.nextInt(area);
		return xy;
	}

	public boolean checkCoords(int[] xy){
		Coords temp = new Coords(xy[0], xy[1]);

		if (positions[0] == null) { return false; }
		else { 
			for (Coords coord : positions){
				if (coord == temp) return true; //if they are already in pos, return true
			}
		}
		return false;
	}

	public void generateNodes(Proc gateway){
		for (int i = 1; i < n; i++){
			int[] xy = coordinates();
			
			while (checkCoords(xy)){
				xy = coordinates();
			}

			positions[i] = new Coords(xy[0], xy[1]);
			nodes[i] = new Node(i, xy[0], xy[1]);
			nodes[i].gateway = gateway;
			SignalList.SendBasicSignal(WAKEUP, nodes[i], time + getExpo(Tp));
			System.out.println(nodes[i].getPosition());
		}

		
	}


	public double arrival(double L) {
		double random = slump.nextDouble()*(L*2); //lower-bound being 0 and upperbound being L*2
		return (random);
	}

	private double getExpo(double lambda) {
		return Math.log(1-slump.nextDouble())/(-1/lambda);
	}

	//What to do when a signal arrives
	public void TreatSignal(Signal x){
		
	}
}