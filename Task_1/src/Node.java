package Task_1.src;

import java.util.Random;

public class Node extends Proc {
    private int id;
    private Coords position;
    private Random slump = new Random();
    public Proc gateway;
    public int reached = 0, notReached = 0;
    public boolean isTransmitting = false;
    public boolean hasWaited = false; //if the node has already gone to sleep once == true

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
		return slump.nextInt(high)+(high-low);
	}

    public void TreatSignal(Signal x){
		switch (x.signalType){
			case WAKEUP:{
                    
                wakeupAction();

            } break;
            case FEEDBACK:{
                isTransmitting = false;
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

    public void wakeupAction() {
        //only run this is strategy 2 is selected, else go to next if statement
        //insert && hasWaited == false here if needed
        if(strategy == 2 && isNeighbourTransmitting()) {
            SignalList.SendBasicSignal(WAKEUP, this, time + getUniform(max_wait, min_wait));
            hasWaited = true;
            return;
        }
        
        if (gatewayRecieving) {
            transmissionValid = false; //tell gateway that the transmission failed
            SignalList.SendFeedbackSignal(FEEDBACK, this, time, false); //send feedback to self
        } 
        else {
            isTransmitting = true;
            gatewayRecieving = true; //tell system that transmission is in progress
            transmissionID = id; //set ID of the current transmitter
            int report = notReached; //Send report with how many transmissions didn't reach 
            SignalList.SendReportSignal(MESSAGE, gateway, time + Tp, id, report);
        }
        hasWaited = false;
    }


    public boolean isNeighbourTransmitting(){
        boolean isNeighbour = false;
        if(gatewayRecieving)
        {
            isNeighbour = allNeighbours[this.id][transmissionID] == transmissionID;
        }
        return isNeighbour;
    }

    @Override
    public String toString(){
        return "" + id;
    }
}