package myMath;



public class main {

	public static void main(String[] args) {
		
		//Test testPolynom = new Test();

		Monom m1= new Monom(2, 0);
		Monom m2 = new Monom(4, 3);
		Monom m3 = new Monom(3, 2);
		Monom m4 = new Monom(1, 9);
		Monom m5 = new Monom(3, 6);
		Monom m6 = new Monom(0,0);
		m5.multiply(m6);
		Polynom p1 = new Polynom();
		p1.add(m1);
		p1.add(m2);
		p1.add(m3);
		p1.add(m4);
		p1.add(m5);
		System.out.println(p1);

		
	}
}
