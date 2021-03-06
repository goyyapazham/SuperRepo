//Team Bring it O.N. -- Owen Zeng and Nalanda Sharadjaya
//APCS1 pd9
//HW41 -- In America, the Driver Sits on the Left
//2015-12-04

public class Rational implements Comparable {

    //instance vars
    private int num;
    private int den;
    
    //default constructor
    public Rational() {
	num = 0;
	den = 1;
    }
    
    //overloaded constructor
    public Rational (int x, int y) {
	this();
	if (y==0) {
	    System.out.println("Error: divide by zero");
	}
	else {
	    num = x;
	    den = y;
	}
    }

    //evaluates gcd
    public static int gcd(int a, int b) {
	if (b == 0) return a;
	//if b > a, they will flip
	return gcd(b, a%b);
    }

    //returns simplest form of fraction
    public void reduce() {
	int gcd = gcd(num, den);
	if (gcd != 1) {
	    num /= gcd; //updates num
	    den /= gcd; //updates den
	}
    }

    public String toString() {
	reduce();
        return num  + "/" + den;
	//return "" + floatValue();
    }
    
    public double floatValue() {
	return num * 1.0 / den;
    } 
    
    public void multiply (Rational og) {
	num *= og.num; //updates num
	den *= og.den; //updates den
    }
    
    public void divide(Rational r) {
	if (r.num == 0) {
	    System.out.println("Error: divide by zero");
	}
	else {
	    num *= r.den; //updates num
	    den *= r.num; //updates den
	}
    	//when dividing r by s, r is multiplied by reciprocal of s
    }

    public void add(Rational r) {
	int gcd = gcd(den, r.den);
	int x = den / gcd;
	int y = r.den / gcd;
        num = num * y + r.num * x;
	den *= y;
    }

    public void subtract(Rational r) {
	int gcd = gcd(den, r.den);
	int x = den / gcd;
	int y = r.den / gcd;
	num = num * y - r.num * x;
	den *= y;
    }
    
    //re: add() and subract()
    /* by multiplying the num and den of one factor by the den/gcd of 
       the other we are essentially finding the lcm of both denominators */

    public int compareTo(Object other) {
	//first, check for aliasing...
	if (this == other) return 0;
	//if other is comparable...
	if (other instanceof Comparable) {
	    //if other is rational, cross-multiply and compare
	    if (other instanceof Rational) {
		int x = num * ((Rational)other).den;
		int y = den * ((Rational)other).num;
		if (x > y) return 1;
		if (x == y) return 0;
	    }
	    //otherwise compare float value to dec representation of other
	    if (other instanceof Binary) {
		if (floatValue() > ((Binary)other).getDec()) return 1;
		if (floatValue() == ((Binary)other).getDec()) return 0;
		if (floatValue() < ((Binary)other).getDec()) return -1;
	    }
	    if (other instanceof Hexadecimal) {
		if (floatValue() > ((Hexadecimal)other).getDec()) return 1;
		if (floatValue() == ((Hexadecimal)other).getDec()) return 0;
		if (floatValue() < ((Hexadecimal)other).getDec()) return -1;
	    }
	}
	//otherwise, other is not comparable
	else if (! (other instanceof Comparable) )
	    throw new ClassCastException("\ninput not comparable");
	throw new NullPointerException("\nnull input received");
    }

    public boolean equals(Object o) {
	return compareTo(o) == 0;
    }
    
    public static void main(String[] args) {

	Rational r = new Rational();
	Rational s = new Rational(8, 18);
	Rational t = new Rational(4, 6);
	Rational v = new Rational(7, 21);
	Rational w = new Rational(7, 21);
	Rational x = new Rational(7, 21);

	System.out.println("--------------------------------");
	
	System.out.println("Testing toString(): ");
	System.out.println();
	System.out.println("Rational r:");
	System.out.println(r);
	System.out.println("Rational s:");
	System.out.println(s);
	System.out.println("Rational t:");
	System.out.println(t);
	System.out.println("Rational v:");
	System.out.println(v);
	System.out.println("Rational w:");
	System.out.println(w);
	
	System.out.println("--------------------------------");

	System.out.println("Testing multiply(): ");
	System.out.println();
	System.out.println("s * v");
	System.out.println();
	System.out.println("BEFORE:");
	System.out.println("Rational s:");
	System.out.println(s);
	System.out.println("Rational v:");
	System.out.println(v);
	s.multiply(v);
	System.out.println("AFTER:");
	System.out.println("Rational s:");
	System.out.println(s);
	System.out.println("Rational v:");
	System.out.println(v);

	System.out.println("--------------------------------");

	System.out.println("Testing divide(): ");
	System.out.println();
	System.out.println("v / s");
	System.out.println();
	System.out.println("BEFORE:");
	System.out.println("Rational s:");
	System.out.println(s);
	System.out.println("Rational v:");
	System.out.println(v);
	v.divide(s);
	System.out.println("AFTER:");
	System.out.println("Rational s:");
	System.out.println(s);
	System.out.println("Rational v:");
	System.out.println(v);
	
	System.out.println("--------------------------------");

	System.out.println("Testing add(): ");
	System.out.println();
	System.out.println("r + t");
	System.out.println();
	System.out.println("BEFORE:");
	System.out.println("Rational r:");
	System.out.println(r);
	System.out.println("Rational t:");
	System.out.println(t);
	r.add(t);
	System.out.println("AFTER:");
	System.out.println("Rational r:");
	System.out.println(r);
	System.out.println("Rational t:");
	System.out.println(t);
	
	System.out.println("--------------------------------");

	System.out.println("Testing subtract(): ");
	System.out.println();
	System.out.println("t - r");
	System.out.println();
	System.out.println("BEFORE:");
	System.out.println("Rational t:");
	System.out.println(t);
	System.out.println("Rational r:");
	System.out.println(r);
	t.subtract(r);
	System.out.println("AFTER:");
	System.out.println("Rational t:");
	System.out.println(t);
	System.out.println("Rational r:");
	System.out.println(r);
	
	System.out.println("--------------------------------");

	System.out.println("Testing compareTo(): ");
	System.out.println();
	System.out.println("Rational r:");
	System.out.println(r);
	System.out.println("Rational w:");
	System.out.println(w);
	System.out.print("compare r and w: ");
	System.out.print(r.compareTo(w) + "\n\n");
	
	System.out.println("Rational v:");
	System.out.println(v);
	System.out.println("Rational w:");
	System.out.println(w);
	System.out.print("compare v and w: ");
	System.out.print(v.compareTo(w) + "\n\n");
	
	System.out.println("Rational t:");
	System.out.println(t);
	System.out.println("Rational s:");
	System.out.println(s);
	System.out.print("compare t and s: ");
	System.out.print(t.compareTo(s) + "\n\n");

	Hexadecimal h = new Hexadecimal(100);
	System.out.println("Rational r:");
	System.out.println(r);
	System.out.println("Hexadecimal h:");
	System.out.println(h);
	System.out.println("compare r and h:");
	System.out.println(r.compareTo(h) + "\n\n");

	Binary b = new Binary(35);
	System.out.println("Rational r:");
	System.out.println(r);
	System.out.println("Binary b:");
	System.out.println(b);
	System.out.println("compare r and b:");
	System.out.println(r.compareTo(b) + "\n\n");

	String str = new String("aslkj");
	System.out.println("Rational r:");
	System.out.println(r);
	System.out.println("String str:");
	System.out.println(str);
	System.out.println("compare r and str:");
	//System.out.println(r.compareTo(str) + "\n\n");

	Object o = new Object();
	System.out.println("Rational r:");
	System.out.println(r);
	System.out.println("Object o:");
	System.out.println(o);
	System.out.println("compare r and o:");
	System.out.println(r.compareTo(o) + "\n\n");

        System.out.println("--------------------------------");

	System.out.println("Testing equals():");

	System.out.println("Rational r:");
	System.out.println(r);
	System.out.println("Rational s:");
	System.out.println(s);
	
	System.out.println("Rational r equals s:");
	System.out.println(r.equals(s));

	System.out.println();

	System.out.println("Rational w:");
	System.out.println(w);
	System.out.println("Rational x:");
	System.out.println(x);

	System.out.println("Rational w equals x:");
	System.out.println(w.equals(x));

    }
  
}

