package myMath;


import java.awt.Color;
import java.awt.Font;



public class main {



	public static void main(String[] args) {

		Monom m1= new Monom(0.2, 4);
		Monom m2 = new Monom(-1.5, 3);
		Monom m3 = new Monom(3, 2);
		Monom m4 = new Monom(-1, 1);
		Monom m5 = new Monom(-5, 0);

		Polynom p1 = new Polynom();
		p1.add(m1);
		p1.add(m2);
		p1.add(m3);
		p1.add(m4);
		p1.add(m5);
		System.out.println(p1);

		//		Polynom p2 = new Polynom("3*x^3-2*x^2-1*x^1");
		//		System.out.println(p2.area(1, 2, 0.001));


		//p1.printGraph();

		//LinePlotTest frame = new LinePlotTest();
		//LinePlotTest print = new LinePlotTest(p1, -2, 6);
	//	frame.setVisible(true);
		p1.PolynomGraph(-2, 6);
		//	print.setVisible(true);
		

	}

}
