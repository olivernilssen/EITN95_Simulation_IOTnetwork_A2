package Task_1.src;

import java.util.*;

//It inherits Proc so that we can use time and the signal names without dot notation 
class Gen extends Proc{
	//The random number generator is started:
	public Random slump = new Random();

	public Coords getCoordinates(int id) {
		int x = slump.nextInt(area);
		int y = slump.nextInt(area);
		Coords xy = new Coords(id, x, y);
		return xy;
	}

	private int binarySearch (Coords[] arr, int l, int r, Coords c) {
		if (r > l){
				
			int mid = l + (r - l)/2;
	
			if (arr[mid].x == c.x && arr[mid].y == c.y){
				return mid;
			
			// # If element is smaller than mid, then it 
			// # can only be present in left subarray
			}else if (arr[mid].x > c.x){
				return binarySearch(arr, l, mid-1, c);
	
			// # Else the element can only be present 
			// # in right subarray
			}else{
				return binarySearch(arr, mid+1, r, c);
			}
		}
		else{
			return -1;
		}
	}

	static boolean insertSorted(Coords arr[], int n, Coords key, int capacity) { 
        // Cannot insert more elements if n is already 
        // more than or equal to capcity 
        if (n >= capacity) 
            return false; 
  
        int i; 
        for (i = n - 1; (i >= 0 && arr[i].x > key.x); i--) 
			arr[i + 1] = arr[i]; 
  
        arr[i + 1] = key; 
  
        return true; 
    }

	public void generateNodes(Proc gateway) {
		for (int i = 1; i < n+1; i++){
			Coords xy = getCoordinates(i);
			
			while (binarySearch(positions, 0, i, xy) != -1){
				xy = getCoordinates(i);
			}

			insertSorted(positions, i, xy, n + 1);
			nodes[i] = new Node(i, xy);
			nodes[i].gateway = gateway;
			SignalList.SendBasicSignal(WAKEUP, nodes[i], time + getExpo(ts));
		}
		// System.out.println("Array done");
		// for(Coords c : positions){
		// 	System.out.print("[" + c + "] ");
		// }
		findNearestPs();
	}

	public void findNearestPs(){
		int r = radius;
		int i = 1;
		double rPoint = r * r;

		for (Node c : nodes){
			if(c == null){ continue; } //the first one is null, because then i == id (i as in iteration)
			Coords node_c = c.getPosition();

			double h = node_c.x;
			double k = node_c.y;
			int j = 0;
			for (Coords p : positions){
				if(p.id == 0) { continue; } //skip the gateway
				if(p.id == node_c.id) { continue; } //skip itself 
				double checkR = Math.pow((p.x - h), 2) + Math.pow((p.y - k), 2);
				if(checkR < rPoint){
					allNearest[i][j] = p.id;
					j++;
				}
			}
			i++;
		}
		System.out.println("Points done");
	}

	private double getExpo(double lambda) {
		return Math.log(1-slump.nextDouble())/(-1/lambda);
	}

	//What to do when a signal arrives
	public void TreatSignal(Signal x) {
		
	}
}