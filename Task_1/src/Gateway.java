package Task_1.src;

// import java.util.Random;

public class Gateway extends Proc {

  public Coords gatewayPos = new Coords(0, 0);
  // private Random slump = new Random();
  public double allMessages = 0, notReached = 0, reached = 0, total = 0;

  public void TreatSignal(Signal x){
    switch (x.signalType){
      case MESSAGE: {

        if(transmissionValid){    
          reached += x.report[0];
          notReached += x.report[1];
          total += reached + notReached;
          allMessages++;
        }

        SignalList.SendFeedbackSignal(FEEDBACK, nodes[x.node], time, transmissionValid);
        transmissionValid = true;
         
      } break;
    }
  }
  
  @Override
  public String toString(){
    return "Gateway";
  }
}