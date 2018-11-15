package myMath;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;


import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Max Marmer 334013968
 *
 */
public class Polynom implements Polynom_able{
	/**
	 * This is empty construction function
	 */
	public Polynom() {
		myPolynom = new ArrayList<Monom>();
	}
	/**
	 * Construction new Polynom via other polynom.
	 * @param p
	 * @throws RuntimeException
	 */
	public Polynom(Polynom p) throws RuntimeException {
		if(p == null) throw new RuntimeException("Error: null can't donate him Polynom");

		Polynom temp = (Polynom)p.copy();
		this.myPolynom = temp.myPolynom;
	}
	/**
	 * This construction function receive from user the Polynom in string format and change
	 * him to Monom format. The correct format of Monom look like "a*x^b", a - coefficient (can be double format), 
	 * b - power (can be integer positive number )
	 * @param inputPol the string type polynom that we need to compute to Monoms type.
	 */

	public Polynom(String inputPol) {  // assume correct input. The correct input is "6*x^4+5*x^3-2*x^5..." (without spaces)
		boolean firstSignOfArray = false; //we need to check first sign of string. he can be '+' or '-' or number
		if (inputPol.charAt(0) == '-' || inputPol.charAt(0) == '+') { // if he's '+' or '-'
			if(inputPol.charAt(0) == '+') {         
				firstSignOfArray = true;  //first sign '+'
			}
			inputPol = inputPol.substring(1, inputPol.length());       // cut the first sing we don't need him
		}
		else {
			firstSignOfArray = true;     //first sign '+'
		}
		String regex = "[+-]+";
		String [] p = inputPol.split(regex);  // split the input to Monoms by '+' and '-' 

		int indexOfSign = 1;                  
		boolean [] signs = new boolean[p.length];// boolean array to indicate signs of Monoms true='+' and false='-'
		signs[0] = firstSignOfArray;

		for(int i = 1; i < inputPol.length(); i++) {  // getting signs of Monoms
			if(inputPol.charAt(i) == '+') {
				signs[indexOfSign] = true;
				indexOfSign++;
			}
			else if (inputPol.charAt(i) == '-'){
				indexOfSign++;
			}
		}

		Polynom temp = new Polynom(); // create new Polynom
		for(int j = 0; j < p.length; j++) {
			Monom m = new Monom(p[j]); // send string Monom to Monom construction by string 
			if(!signs[j]) {    // if this monom have the negative sign make him negative
				m.toNegative();
			}
			temp.add(m);  //add the actual monom
		}

		myPolynom = temp.myPolynom;
	}

	/**
	 * Compute the polynom to String type.
	 * @return String type.
	 */

	public String toString() {
		String s = "";
		this.iterator();
		if(this.isZero()) {
			return "0";
		}
		Iterator<Monom> it = this.iterator();
		Monom m = it.next();
		s += "( " + m.toString();
		while(it.hasNext()) {
			m = it.next();
			if (m.get_coefficient() < 0) { //  negative Monom
				s += " " + m.toString(); 
			}
			else {
				s += " +" + m.toString(); // positive Monom
			}
		}
		return s + " )";
	}
	/**
	 * This method return size of this polynom.
	 * @return Size of this Polynom.
	 */
	public int getSize() {
		return this.myPolynom.size();
	}

	@Override
	public double f(double x) {
		if(this.getSize() == 0) {
			return 0;
		}
		double ans = 0;
		Iterator<Monom> it = this.iterator();
		while(it.hasNext()) {  
			Monom m = it.next(); 
			ans += m.f(x); 		//count result one by one
		}
		return ans;
	}

	/**
	 * This function adding two polynoms.
	 * @param p1 polynom that we adding to this polynom.
	 */
	@Override
	public void add(Polynom_able p1) { // like to add some amount of Monoms
		if(p1 == null) {
			return;
		}
		Iterator<Monom> iter = p1.iterator();
		while(iter.hasNext()) { 
			Monom m = iter.next();
			this.add(m); // add one by one
		}
	}
	/**
	 * This function adding Monom to our polynom.
	 * @param m1 monom that we adding to this polynom.
	 */
	@Override
	public void add(Monom m1) {
		if(m1 == null) {
			return;
		}
		if(this.myPolynom.size() == 0 && m1.is_exist()) { //if this polynom empty add first Monom without sort
			myPolynom.add(m1);
		}
		else{
			Iterator<Monom> temp = this.iterator();
			boolean samePower = false;
			while(!samePower && temp.hasNext()) {  // run on Monoms of this polynom and find the same power
				Monom current = temp.next();
				if(current.get_power() == m1.get_power()) { //same power so add them
					current.add(m1);
					samePower = true;
					if (!current.is_exist()) {  // if the coefficient after adding is zero so remove this monom
						temp.remove();
					}
				}
			}
			if(!samePower && m1.is_exist()) {
				myPolynom.add(m1);
				this.myPolynom.sort(cmpMonon); // sort this polynom by Comparator
			}
		}
	}
	/**
	 * This method subtract the input polynom of our polynom.
	 * @param p1 polynom that we need to subtract of this polynom.
	 */
	@Override
	public void substract(Polynom_able p1) { // subtract same to adding but we need to add a negative polynom
		Polynom_able temp = p1.copy();
		Iterator<Monom> it = temp.iterator();
		while(it.hasNext()) {
			Monom m = it.next();
			m.toNegative();             // take this Monom and do it negative
		}
		this.add(temp);
	}
	/**
	 * This method multiply our this polynom to inputs monom.
	 * @param m Monom that we multiply by this polynom.
	 * @throws RuntimeException
	 */
	@Override
	public void multiply(Monom m) throws RuntimeException{
		if(m == null) {
			throw new RuntimeException("Error: can't mupltiply by null monom!!!");
		}
		if (m.get_coefficient() == 0) {
			this.myPolynom = new ArrayList<>();
		}
		else {
			Polynom ans = (Polynom)this.copy();
			Iterator<Monom> it = ans.iterator();
			while(it.hasNext()) {   //run on Monoms of this polynom add multiply one by one
				Monom temp = it.next();
				temp.multiply(m);
			}
			ans.myPolynom.sort(cmpMonon);
			this.myPolynom = ans.myPolynom;
		}
	}
	/**
	 * This method multiply two polynoms.
	 * @param p1 polynom that we need to multiply by this polynom.
	 * @throws RuntimeException
	 */
	@Override
	public void multiply(Polynom_able p1) throws RuntimeException{
		if(p1 == null) {
			throw new RuntimeException("Error: can't mupltiply by null polynom!!!");
		}
		Polynom temp2 = (Polynom)p1.copy();
		Polynom res = new Polynom();
		Iterator<Monom> it = temp2.iterator();   
		while(it.hasNext()) {        // same to multiply by monom but now we have several monoms to multiply
			Polynom temp1 = (Polynom)this.copy();
			Monom m = it.next(); // chose the actual Monom
			temp1.multiply(m);	 // multiply this polynom by actual Monom
			res.add(temp1);		 // add result to new Polynom
		}
		res.myPolynom.sort(cmpMonon);
		this.myPolynom = res.myPolynom;
	}
	/**
	 * This method check if our polynom is "zero".
	 * @return true - if polynom composed of zero monoms or don't have any monom, false - if this polynom not empty
	 */
	@Override
	public boolean isZero() {
		if (this.getSize() == 0) {
			return true;
		}
		return false;
	}
	/**
	 * This method create copy of this polynom.
	 * @return new polynom same to this polynom.
	 */
	@Override
	public Polynom_able copy() {
		if(this.getSize() == 0) {
			return null;
		}
		Iterator<Monom> it = this.iterator();
		Polynom_able temp = new Polynom();
		while(it.hasNext()) {  // to do copy we need a new place in member
			Monom m = it.next();
			Monom mTemp = m.copy(); // create a new monom
			temp.add(mTemp);
		}
		return temp;
	}
	/**
	 * This method derivative this polynom one time.
	 * @return this polynom after derivative.
	 */
	@Override
	public Polynom_able derivative() {
		Polynom p1 = (Polynom)this.copy();
		Polynom ans = new Polynom();
		Iterator<Monom> it = p1.iterator();
		while(it.hasNext()) {
			Monom m = it.next();
			if(m.get_power() > 0) {
				m = m.derivative();	// derivative this Monom and add him to his polynom
				ans.add(m);
			}
			else {
				it.remove();
			}
		}
		return ans;
	}
	/**
	 * This method check if two polynoms equals.
	 * @param p1 polynom that we check with this polynom.
	 * @return true - if them equals, false - not equals.
	 */
	@Override
	public boolean equals(Polynom_able p1) {
		if(this.getSize() == 0 && p1.isZero()) {
			return true;
		}
		Polynom temp1 = (Polynom)this.copy();
		Polynom temp2 = (Polynom)p1.copy();
		if(temp1.getSize() != temp2.getSize()) {  // if other sizes of polynoms so them not equals
			return false;
		}

		Iterator<Monom> it1 = temp1.iterator();
		Iterator<Monom> it2 = temp2.iterator();
		while(it1.hasNext() && it2.hasNext()) {   
			Monom m1 = it1.next();
			Monom m2 = it2.next();
			if(!m1.equals(m2)) {      // checking Monoms one by one
				return false;
			}
		}
		return true;
	}
	/**
	 * This method count area of this polynom between two points on X axis.
	 * This method can throw error if this polynom empty.
	 * @param x0 first point.
	 * @param x1 second point.
	 * @param eps size of step.
	 */
	@Override
	public double area(double x0, double x1, double eps) {
		if(this.getSize() == 0) {
			throw new RuntimeException("Error: Can't count area of empty polynom");
		}
		else {
			double area = 0; // count of area by many little trapezoids
			double step1 = x0;
			double step2 = step1 + eps;

			while(step2 <= x1) { 					
				if(f(step1) > 0 && f(step2) > 0) {  // check if the sides of trapezoid a positives
					area += trapezArea(f(step1), f(step2), eps); // send (y0, y1, h) to formula of trapezoid area
				}
				step1 += eps;
				step2 += eps;
			}
			return area;
		}
	}
	/**
	 * This function count area of trapezoids.
	 * @param a left side.
	 * @param b right side.
	 * @param h height of trapezoid.
	 * @return area of trapezoid.
	 */
	private double trapezArea(double a, double b, double h) { //count area of polynom by trapezoids, formula is s=((a+b)*h)/2
		return h * (a + b) / 2;
	}
	/**
	 * This method search the root between two points on X axis. In root point we have value of function f equals to zero.
	 * @param x0 first point.
	 * @param x1 second point.
	 * @param eps the size of step.
	 * @return Point on x axis that give zero in function f.
	 */
	@Override
	public double root(double x0, double x1, double eps) {
		if(this.getSize() == 0) {
			throw new RuntimeException("Error: Can't count root of empty polynom");
		}

		double y0 = this.f(x0); // find y0
		double y1 = this.f(x1); // find y1
		double dX = Math.abs(x0 - x1); //distance between x0 and x1
		double dY = Math.abs(y0 - y1); //distance between y0 and y1

		if(y0 * y1 > 0) { //check if them in another sides of x axis
			throw new RuntimeException("Error: y0 and y1 placed in common side");
		}

		while(dX > eps || dY > eps) { //check if them near to point
			double xMid = (x0 + x1) / 2; 
			double yMid = this.f(xMid);
			double res = y0 * yMid;
			if(res < 0) {   //check in which sides we need to find
				return root(x0, xMid, eps);
			}
			else {
				return root(xMid, x1, eps);
			}
		}

		return x0;
	}
	/**
	 * This method do iterator to this polynom.
	 * @return Polynom with iterator methods.
	 */
	@Override
	public Iterator<Monom> iterator() {
		return this.myPolynom.iterator();
	}

	
//	public void printGraph() {
//		PlotSettings p = new PlotSettings(-2, 6, -7, 7);
//		p.setPlotColor(Color.RED);
//		p.setGridSpacingX(2);
//		p.setGridSpacingY(2);
//		p.setTitle("Polynom");
//		Graph graph = new Graph(p);
//		Polynomial polToPrint = new Polynomial(this);
//		graph.functions.add(polToPrint);
//		polToPrint.searchMinMax(this, -2, 6);
//		System.out.println("xMin: " + polToPrint.getXmin() + " yMin: " + polToPrint.getYmin() +"\n" +
//							"xMax:" + polToPrint.getXmax() + " yMax: " + polToPrint.getYmax());
////		MinMaxPoints minMaxPol = new MinMaxPoints(this);
////		minMaxPol.getPoints(polToPrint.getXmin(), polToPrint.getYmin(), polToPrint.getXmax(), polToPrint.getYmax());
////		graph.functions.add(minMaxPol);
//		//graph.functions.add(1, g);;
//		new GraphApplication(graph);
//	}
	
	public void PolynomGraph(double x0, double x1) {
		PolynomPrint polPrint = new PolynomPrint(this, x0, x1);
		this.findMinMax(x0, x1);
		polPrint.setVisible(true);
	}
	
	private void findMinMax(double x0, double x1) {
		MaxPoints = new ArrayList<Double>();
		MinPoints = new ArrayList<Double>();
		double temp = x0;
		double MinTemp;
		double MaxTemp;
		while(temp <= x1) {
			MinTemp = temp;
			
			while(this.f(MinTemp) > this.f(temp + 0.01) && temp <= x1) {
				MinTemp = temp;
				temp += 0.1;
			}

			if(MinPoints.size() == 0 || MinTemp != MinPoints.get(MinPoints.size() - 1)) {
				MinPoints.add(MinTemp);	
			}
			MaxTemp = temp;
			while(this.f(MaxTemp) < this.f(temp + 0.01) && temp <= x1) {
				MaxTemp = temp;
				temp += 0.1;
			}
			
			if(MaxPoints.size() == 0 || MaxTemp != MaxPoints.get(MaxPoints.size() - 1)) {
				MaxPoints.add(MaxTemp);
			}

		}
		System.out.println(MinPoints.toString());
		System.out.println(MaxPoints.toString());

	}

	// ********** add your code below ***********
	/**
	 * This method return myPolynom.
	 * @return Polynom.
	 */


	private ArrayList<Double> MaxPoints;
	private ArrayList<Double> MinPoints;
	private ArrayList<Monom> myPolynom;
	private static Monom_Comperator cmpMonon= new Monom_Comperator();

}
