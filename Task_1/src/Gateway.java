package Task_1.src;

// import java.util.Random;

public class Gateway extends Proc {
  /**
   *
   */
  private static final long serialVersionUID = -2204039383280000131L;
  public Coords gatewayPos = new Coords(0, 0);
  // private Random slump = new Random();
  public double allMessages = 0, notReached = 0, reached = 0, total = 0;

  public void TreatSignal(Signal x){
    switch (x.signalType){
      case MESSAGE: {
          transmission = false;
          SignalList.SendFeedbackSignal(FEEDBACK, nodes[transmissionID], time, true);
          reached += x.report[0];
          notReached += x.report[1];
          total += reached + notReached;
          allMessages++;
      } break;
    }
	}
}