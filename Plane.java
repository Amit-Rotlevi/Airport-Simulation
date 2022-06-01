/*
 * @author: Amit Rotlevi
 * @last modified: 26.01.22
 */
package q2;

public class Plane 
{
	private int flightNum;
	private boolean departing;
	
	public Plane(int flightNum,  boolean departing)
	{
		this.flightNum = flightNum;
		this.departing = departing;
	}
	
	public int getFlightNum()
	{
		return flightNum;
	}

	public boolean isDeparting()
	{
		return departing;
	}


}
