/*
 * @author: Amit Rotlevi
 * @last modified: 26.01.22
 */
package q2;

import java.util.Random;

public class Flight extends Thread
{
	private int flightNum; 
	private Airport departedAirport; 
	private Airport landingAirport; 
	private Random rand = new Random();
	
	// constructor
	public Flight(int flightNum, Airport departedAirport, Airport landingAirport)
	{
		this.flightNum = flightNum;
		this.departedAirport = departedAirport;
		this.landingAirport = landingAirport;
	}
	
	public void run()
	{
		// departing phase
		int departedRunway = departedAirport.depart(flightNum);
		try 
		{
			sleep((rand.nextInt(3)+2)*1000);
		}
		catch(InterruptedException e)
		{
			System.out.println("Interrupted");
		}
		departedAirport.freeRunway(departedRunway, flightNum);
		
		// flight phase
		try 
		{
			sleep(rand.nextInt(10)*1000);
		}
		catch (InterruptedException e)
		{
			System.out.println("Interrupted");
		}
		
		// landing phase
		int landingRunway = landingAirport.land(flightNum);
		try 
		{
			sleep((rand.nextInt(3)+2)*1000);
		}
		catch(InterruptedException e)
		{
			System.out.println("Interrupted");
		}
		landingAirport.freeRunway(landingRunway, flightNum);
	}
}
