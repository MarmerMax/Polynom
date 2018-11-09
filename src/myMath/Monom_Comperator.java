package myMath;

import java.util.Comparator;
/**
 * This class represent a Comparator that we need to our project
 * @author Max Marmer 334013968
 *
 */

public class Monom_Comperator implements Comparator<Monom> {
	/**
	 * Find the bigger Monom of two actual monoms.
	 * @param m1 First Monom
	 * @param m2 Second Monom
	 * @return parameter to sort function
	 */
	@Override
	public int compare(Monom m1, Monom m2) {
		Integer power1 = new Integer(m1.get_power());
		Integer power2 = new Integer(m2.get_power());
		return power2.compareTo(power1);
	}
	

	// ******** add your code below *********

}
