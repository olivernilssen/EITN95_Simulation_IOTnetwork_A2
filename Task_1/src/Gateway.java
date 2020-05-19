package Task_1.src;

import java.text.DecimalFormat;

// import java.util.Random;

public class Gateway extends Proc {
  static DecimalFormat df = new DecimalFormat("#.####");
  public Coords gatewayPos = new Coords(0, area/2, area/2);
  public SimpleFileWriter writer = new SimpleFileWriter("Task_1/src/matlab/arrivals.m", false); 
  // private Random slump = new Random();
  public double allMessages = 0, notReached = 0, reached = 0, total = 0;

  //variables for measurment in batches
  public int batchsize = 20, count = 0, totalBatches = 0; 
  public double NR_batch = 0, R_batch, NR_allBatches = 0, R_allBatches = 0; 

  public void TreatSignal(Signal x){
    switch (x.signalType){
      case MESSAGE: {

        //check if the transmission is valid (AKA. That there were no collisions)
        if(transmissionValid){    
          NR_batch += x.report;
          R_batch += 1;
          count++; 

          if(count > batchsize){
            R_allBatches += R_batch/time;
            NR_allBatches += NR_batch/time;
            totalBatches++;
            R_batch = 0; 
            NR_batch = 0;
          }

          reached++; 
          notReached += x.report;
        }

        SignalList.SendFeedbackSignal(FEEDBACK, nodes[x.node], time, transmissionValid);
        transmissionValid = true;
        gatewayRecieving = false;
      } break;
    }
  }
  
  @Override
  public String toString(){
    return "Gateway";
  }
}