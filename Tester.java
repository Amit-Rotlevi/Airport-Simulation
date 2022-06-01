/*
 * @author: Amit Rotlevi
 * @last modified: 26.01.22
 */
package q2;

import java.util.Random;

public class Tester 
{
	public static void main (String[] args)
	{	
		Airport LAX = new Airport("LAX", 3);
		Airport JFK = new Airport("JFK", 3);
		Flight flight;
		Random rand = new Random();
		
		// create and start 10 flights randomly between the airports
		for (int i = 0 ; i<10 ; i++)
		{
			if (rand.nextInt(10)%2 == 1)
				flight = new Flight(rand.nextInt(9999),LAX,JFK);
			else flight = new Flight(rand.nextInt(9999),JFK,LAX);
			
			flight.start();
		}
	}
}
