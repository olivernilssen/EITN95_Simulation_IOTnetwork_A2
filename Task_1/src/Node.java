package Task_1.src;

import java.util.Random;

public class Node extends Proc {
    private int id;
    private Coords position;
    private Random slump = new Random();
    public Proc gateway;
    public int reached = 0, notReached = 0;

    public Node (int id, int x, int y){
        this.id = id;
        position = new Coords(x, y);
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
                if(transmission){
                    transmissionValid = false;
                    SignalList.SendFeedbackSignal(FEEDBACK, this, time, false);
                } else {
                    transmission = true;
                    transmissionID = id;
                    int[] report = {reached, notReached};
                    SignalList.SendReportSignal(MESSAGE, gateway, time + ts, id, report);
                }
            } break;
            case FEEDBACK:{
                transmission = false;
                transmissionID = 0;

                if (x.feedback){
                    reached++;
                }
                else {
                    notReached++;
                }

                SignalList.SendBasicSignal(WAKEUP, this, time + getExpo(Tp));
            }
		}
    }

    @Override
    public String toString(){
        return "" + id;
    }
}