package Task_1.src;

import java.io.*;
import java.text.DecimalFormat;

//It inherits Proc so that we can use time and the signal names without dot notation
public class MainSimulation extends Global{
	static DecimalFormat df = new DecimalFormat("#.####");

	public static void main(String[] args) throws IOException {
		// The signal list is started and actSignal is declaree. actSignal is the latest
		// signal that has been fetched from the
		// signal list in the main loop below.
		Signal actSignal;
		new SignalList();

		//initialize the generator and set values (this can also be done in the gen as 
		//the values are now global)
		Gen Generator = new Gen();
		Gateway Gateway = new Gateway();
		positions[0] = new Coords(area/2, area/2);
		Generator.generateNodes(Gateway);

		// This is the main loop
		while (time < 100000) {
			actSignal = SignalList.FetchSignal();
			time = actSignal.arrivalTime;
			actSignal.destination.TreatSignal(actSignal);
		}

		double reachedM = Gateway.reached;
		double noreachedM = Gateway.notReached;
		double allM = Gateway.allMessages;

		System.out.println("Reached: " + reachedM);
		System.out.println("Not reached " + noreachedM);
		System.out.println("Prob of not reaching: " + allM/noreachedM); //THIS IS WRONG
		System.out.println("arrival rate " + reachedM/100000);
	}
}