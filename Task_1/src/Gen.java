package Task_1.src;

import java.util.*;

//It inherits Proc so that we can use time and the signal names without dot notation 
class Gen extends Proc{
	// The random number generator is started:
	public Random slump = new Random();
	public Proc sendTo;  //Says to which process we want to send customers

	public Coords getCoordinates(int id){
		int x = slump.nextInt(area);
		int y = slump.nextInt(area);
		Coords xy = new Coords(x, y, id);
		return xy;
	}

	private boolean binarySearch (Coords[] arr, int l, int r, Coords c){
		if (r > l){
				
			int mid = l + (r - l)/2;
	
			if (arr[mid].x == c.x && arr[mid].y == c.y){
				return true;
			
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
			return false;
		}
	}


	static boolean insertSorted(Coords arr[], int n, Coords key, int capacity) 
    { 
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

	public void generateNodes(Proc gateway){
		for (int i = 1; i < n; i++){
			Coords xy = getCoordinates(i);
			
			while (binarySearch(positions, 0, i, xy)){
				xy = getCoordinates(i);
			}

			insertSorted(positions, i, xy, n);
			nodes[i] = new Node(i, xy);
			nodes[i].gateway = gateway;
			SignalList.SendBasicSignal(WAKEUP, nodes[i], time + getExpo(Tp));
			// System.out.println(nodes[i].getPosition());
			// MainSimulation.cnfileSave.storeProps("allCords", positions[i].toString());
		}
		System.out.println("Array done");
		// for(Coords c : positions){
		// 	System.out.print("[" + c + "] ");
		// }
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