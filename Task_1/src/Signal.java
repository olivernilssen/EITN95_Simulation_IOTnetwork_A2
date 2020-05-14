// Denna klass definerar vad som ska finnas i en signal. Det som finns h�r �r ett minimum. Man kan l�gga till mer
// om man vill att en signal ska kunna skicka mer information.

// This class defines a signal. What can be seen here is a mainimum. If one wants to add more
// information just do it here. 
package Task_1.src;
class Signal{
	public Proc destination;
	public double arrivalTime;
	public int signalType;
	public Signal next;
	public boolean feedback;
	public int report;
	public int node;

	@Override 
	public String toString(){
		return "Signaltype: " + signalType + " dest: " + destination;
	}
}
