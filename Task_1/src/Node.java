package Task_1.src;

import java.util.Random;

public class Node extends Proc {
    private int id;
    private Coords position;
    private Random slump = new Random();
    public Proc gateway;
    public int reached = 0, notReached = 0;

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

    public void TreatSignal(Signal x){
		switch (x.signalType){
			case WAKEUP:{
                if (sentTime == time + Tp){
                    SignalList.SendFeedbackSignal(FEEDBACK, this, time, false); //send feedback to self
                }
                else {
                    int report = notReached;
                    sentTime = time + Tp;
                    SignalList.SendReportSignal(MESSAGE, gateway, time + Tp, id, report);
                }
                // if(transmission){
                //     transmissionValid = false; //tell gateway that the transmission failed
                //     SignalList.SendFeedbackSignal(FEEDBACK, this, time, false); //send feedback to self
                // } else {
                //     transmission = true; //tell system that transmission is in progress
                //     transmissionID = id; //set ID of the current transmitter
                //     int report = notReached; //Send report with how many transmissions didn't reach 
                //     SignalList.SendReportSignal(MESSAGE, gateway, time + Tp, id, report);
                // }
            } break;
            case FEEDBACK:{
                transmission = false;
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

    @Override
    public String toString(){
        return "" + id;
    }
}