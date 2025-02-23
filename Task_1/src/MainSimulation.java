package Task_1.src;

import java.io.*;
import java.text.DecimalFormat;

//It inherits Proc so that we can use time and the signal names without dot notation
public class MainSimulation extends Global{
	static DecimalFormat df = new DecimalFormat("#.#####");
	public static configFile cnfile = new configFile("Task_1/config/config.properties", true); 
	public static configFile cnfileSave = new configFile("Task_1/config/configSaved.properties", true); 

	public static void main(String[] args) throws IOException {
		// The signal list is started and actSignal is declaree. actSignal is the latest
		// signal that has been fetched from the
		// signal list in the main loop below.
		Signal actSignal;
		new SignalList();

		//get values from config file
		n = Integer.parseInt(cnfile.props.getProperty("nodes"));
		ts = Integer.parseInt(cnfile.props.getProperty("ts"));
		Tp = Integer.parseInt(cnfile.props.getProperty("Tp"));
		radius = Integer.parseInt(cnfile.props.getProperty("r"));
		area = Integer.parseInt(cnfile.props.getProperty("area"));
		strategy = Integer.parseInt(cnfile.props.getProperty("strategy"));
		min_wait = Integer.parseInt(cnfile.props.getProperty("min_wait_time"));
		max_wait = Integer.parseInt(cnfile.props.getProperty("max_wait_time"));
		
		nodes = new Node[n+1];
		positions = new Coords[n+1];
		allNeighbours = new int[n+1][n+1];

		//initialize the generator and set values (this can also be done in the gen as 
		//the values are now global)
		Gen Generator = new Gen();
		Gateway Gateway = new Gateway();
		positions[0] = new Coords(0, area/2, area/2);
		Generator.generateNodes(Gateway);
		int N = 100000; //simulation time
		
		// This is the main loop
		while (time < N) {
			actSignal = SignalList.FetchSignal();
			time = actSignal.arrivalTime;
			//System.out.println(time + " " + actSignal);
			actSignal.destination.TreatSignal(actSignal);
		}

		cnfile.close();
		Gateway.writer.close();

		double reachedM = Gateway.reached;
		double noreachedM = Gateway.notReached;
		double allM = noreachedM+reachedM;
		double arrivalR = allM/N;
		double onlyRR = reachedM/N;
		double RofM = reachedM/allM;

		System.out.println("Reached: " + reachedM);
		System.out.println("Not reached " + noreachedM);
		System.out.println("arrival rate " + arrivalR );
		System.out.println("Throughput " + onlyRR);
		System.out.println("Prob of not reaching: " + df.format(noreachedM/allM));
	}
}