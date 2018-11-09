
package myMath;
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Max Marmer 334013968
 *
 */
public class Monom implements function{

	/**
	 * Construction function
	 * @param a coefficient
	 * @param b power
	 */
	public Monom(double a, int b) throws RuntimeException{
		if(b<0) throw new RuntimeException("Error: power is negative!");
		this.set_coefficient(a);
		this.set_power(b);
	}
	/**
	 * Construction copy function. Make same Monom like current 
	 * @param cur Monom that we need to make same 
	 */
	public Monom(Monom cur) {
		this(cur.get_coefficient(), cur.get_power());
	}
	/**
	 * Construction function. Getting input in String format and compute him to Monom
	 * @param s Monom in string type
	 */
	public Monom(String s) {
		String regex = "[*x^]+"; 
		String [] temp = s.split(regex);  // brake string monom to coefficient and power 
		if(Integer.parseInt(temp[1]) < 0) throw new RuntimeException("Error: power is negative!");
		this.set_coefficient(Double.parseDouble(temp[0]));
		this.set_power(Integer.parseInt(temp[1]));
	}
	// ***************** add your code below **********************
	/**
	 * Get coefficient
	 * @return coefficient of this monom
	 */
	public double get_coefficient() {
		return _coefficient;
	}
	/**
	 * Get power
	 * @return power of this monom
	 */
	public int get_power() {
		return _power;
	}
	/**
	 * This method count value of this monom with parameter x
	 * @param x the parameter .
	 */
	public double f(double x) {
		double res = 1;
		res = Math.pow(x, get_power());
		res *= get_coefficient();
		return res;
	} 
	/**
	 * Compute Monom format to String format
	 */
	public String toString() {
		if(get_coefficient() == 0) return "0";
		if(get_power() == 0) return "" + get_coefficient();
		if(get_power() == 1) return get_coefficient() + "*x";
		return get_coefficient() + "*x^" + get_power();  
	}
	/**
	 * Compute the derivative of this Monom
	 * @return new Monom after derivative
	 */
	public Monom derivative() throws RuntimeException{
//		Monom m = this.copy();
//		if (m == null) throw new RuntimeException("Error: you can't derivative null Object"); 
		
		double der = this.get_coefficient() * this.get_power();
		int pow = this.get_power() - 1;
		
		return new Monom(der, pow);
	}
	/**
	 * Adding to monoms together, if them have different powers we can't add them.
	 * @param cur the monom that we adding to this monom
	 */
	public void add(Monom cur) throws RuntimeException {
		if(this.get_power()!=cur.get_power()) {
			throw new RuntimeException("Error: can not add two monoms with different coefficients");
		}
		this.set_coefficient(cur.get_coefficient() + this.get_coefficient());
	}
	/**
	 * The monom exist if him coefficient not equal to zero and him power not negative,
	 * in other way we don't need him.
	 * @return true - if his coefficient not zero and power not negative, false - other case.
	 */
	public boolean is_exist() {
		if(this.get_coefficient() == 0 || this.get_power() < 0) {
			return false;
		}
		return true;
	}
	/**
	 * Multiply this monom by current monom.
	 * @param cur the monom that we multiply by this monom
	 */
	public void multiply(Monom cur) {
		this.set_coefficient(this.get_coefficient() * cur.get_coefficient());
		this.set_power(this.get_power()+cur.get_power());
	}
	/**
	 * Check if this monom same to current monom. If them coefficient and power same so we have
	 * two same monoms. And return the answer true or false
	 * @param cur Monom that we check with this Monom.
	 * @return true or false
	 */
	public boolean equals(Monom cur) {
		if(cur != null) {
			return (this.get_coefficient() == cur.get_coefficient() &&
					this.get_power() == cur.get_power());
		}
		return false;
	}
	/**
	 * Compute the coefficient of this monom to negative.
	 */
	public void toNegative() {
		this._coefficient = (this._coefficient * -1);
	}
	/**
	 * Make new monom like this monom.
	 * @return new same monom 
	 */
	public Monom copy() {
		Monom m = new Monom(this._coefficient, this.get_power());
		return m;
	}



	//****************** Private Methods and Data *****************
	/**
	 * Set coefficient if this monom.
	 * @param c coefficient
	 */
	private void set_coefficient(double c){
		this._coefficient = c;
	}
	/**
	 * Set power of this monom.
	 * @param p power
	 */
	private void set_power(int p) {
		this._power = p;
	}

	private double _coefficient; // 
	private int _power;

}
