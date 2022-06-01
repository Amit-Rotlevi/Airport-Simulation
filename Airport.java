/*
 * @author: Amit Rotlevi
 * @last modified: 26.01.22
 */
package q2;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Airport 
{
	private final int NUM_OF_RUNWAYS; 
	private String name; 
	private boolean[] isRunwayTaken;
	private ConcurrentLinkedQueue <Plane> queue = new ConcurrentLinkedQueue<Plane>();
	
	// constructor
	public Airport(String name,int runwaysNum)
	{
		this.name = name;
		NUM_OF_RUNWAYS = runwaysNum;
		isRunwayTaken = new boolean[NUM_OF_RUNWAYS];
		System.out.println("Airport " + name + " with " + runwaysNum + " runways initialized");
	}
	
	public int depart(int flightNum)
	{	
		// adds the flight to the queue for using a runway
		queue.add(new Plane(flightNum,true));
		
		synchronized(this)
		{
			// if there are no free runways, wait for one
			while(getFreeRunway()== -1 || !queue.peek().isDeparting())
			{
				try 
				{
					wait();
				}
				catch (InterruptedException e)
				{
					System.out.println("Interrupted");
				}
			}
			int clrRunway = getFreeRunway(); 
			isRunwayTaken[clrRunway] = true;  
			System.out.println("Flight " + queue.remove().getFlightNum() + 
							   " departs from runway " + (clrRunway + 1) +	
							   " from Airport: " + name + "\n");
			return clrRunway;
		}
	}
		
	public int land(int flightNum)
	{	
		// adds the flight to the queue for using a runway
		queue.add(new Plane(flightNum,false));
		
		synchronized(this)
		{
			// if there are no free runways, wait for one
			while(getFreeRunway() == -1 || queue.peek().isDeparting())
			{
				try
				{
					wait();
				} 
				catch(InterruptedException e)
				{
					System.out.println("Interrupted");
				}
			}
			
			int clrRunway = getFreeRunway();
			isRunwayTaken[clrRunway] = true;
			System.out.println("Flight " + queue.remove().getFlightNum() + 
							   " departs from runway " + (clrRunway + 1) +	
							   " from Airport: " + name + "\n");
			return clrRunway;
		}
	}
		
	public void freeRunway(int runway, int flightNum)
	{
		synchronized(this) 
		{
			isRunwayTaken[runway] = false;
			System.out.println("Flight " + flightNum + " cleared from runway " + (runway + 1) + 
							   " from Airport: " + name + "\n");
			notifyAll();
		}
	}
	
	// checks for the next free runway. if all are occupied, returns -1
	private int getFreeRunway()
	{
		for(int i = 0 ; i < NUM_OF_RUNWAYS ; i++)
			if(!isRunwayTaken[i])
				return i;
		return -1;
	}
}
