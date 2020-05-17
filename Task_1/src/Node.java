package Task_1.src;

import java.util.Random;

public class Node extends Proc {
    private int id;
    private Coords position;
    private Random slump = new Random();
    public Proc gateway;
    public int reached = 0, notReached = 0;
    public boolean isTransmitting = false;

    public Node (int id, Coords xy){
        this.id = id;
        position = xy;
    }

    public Coords getPosition(){
        return this.position;
    }

    private double getExpo(double lambda) {
		return Math.log(1-slump.nextDouble())/(-1/lambda);
    }
    
    private double getUniform(int high, int low) {
		return Math.log(1-slump.nextInt(high)+(high-low));
	}

    public void TreatSignal(Signal x){
		switch (x.signalType){
			case WAKEUP:{
                if(strategy == 2 && isNeighbourTransmitting()){
                    SignalList.SendBasicSignal(WAKEUP, this, time + getUniform(3, 1));
                }
                else if(gatewayRecieving) {
                    transmissionValid = false; //tell gateway that the transmission failed
                    SignalList.SendFeedbackSignal(FEEDBACK, this, time, false); //send feedback to self
                } else {
                    isTransmitting = true;
                    gatewayRecieving = true; //tell system that transmission is in progress
                    transmissionID = id; //set ID of the current transmitter
                    int report = notReached; //Send report with how many transmissions didn't reach 
                    SignalList.SendReportSignal(MESSAGE, gateway, time + Tp, id, report);
                }
            } break;
            case FEEDBACK:{
                isTransmitting = false;
                gatewayRecieving = false;
                transmissionID = 0;

                if(x.feedback) {
                    notReached = 0;
                }
                else{
                    notReached++;
                }

                SignalList.SendBasicSignal(WAKEUP, this, time + getExpo(ts));
            }
		}
    }

    //this code is really bad. it uses too much memory
    //trying a different method
    public boolean checkNeighbours(){
        for (int i = 1; i < n; i++){
            //int id = allNearest[this.id][i];
            if(id == 0){ 
                return false;
            }
            else if (nodes[id].isTransmitting){
                return true;
            }
        }
        return false;
    }

    public boolean isNeighbourTransmitting(){
        boolean isNeighbour = false;
        if(gatewayRecieving)
        {
            isNeighbour = allNearest.get(this.id).get(transmissionID) != null;
        }
        return isNeighbour;
    }

    @Override
    public String toString(){
        return "" + id;
    }
}