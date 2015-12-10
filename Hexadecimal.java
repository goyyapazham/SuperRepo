//Team Manish: Nalanda Sharadjaya and Jack Schluger
//APCS1 pd09
//HW44 -- This or That or Fourteen Other Things
//2015-12-08

public class Hexadecimal implements Comparable {

    private final static String HEXDIGITS = "0123456789ABCDEF";

    private int _decNum;
    private String _hexNum;

    //default constructor
    public Hexadecimal() {
	_decNum = 0;
	_hexNum = "0";
    }

    //first overloaded constructor
    public Hexadecimal( int n ) {
	_decNum = n;
	_hexNum = decToHexR(n);
    }

    //second overloaded constructor
    public Hexadecimal( String s ) {
	_decNum = hexToDecR(s);
	_hexNum = s;
    }

    //accessor
    public int getDec() {
	return _decNum;
    }

    //string representation
    public String toString() {
	return _hexNum;
    }

    //iterative decimal to hexadecimal converter
    public static String decToHex( int n ) {
	String s = "";
	int rem = 0;
	while (n != 0) {
	    //store remainder
	    rem = n % 16;
	    //convert rem to hex representation, add to string (right-justify)
	    s = HEXDIGITS.substring(rem, rem + 1) + s;
	    //divide n by 16 (reset)
	    n /= 16;
	}
	return s;
    }

    //recursive decimal to hexadecimal converter
    public static String decToHexR( int n ) {
	if (n == 0) return "";
	else {
	    int rem = n % 16;
	    return decToHexR( n / 16 ) + HEXDIGITS.substring(rem, rem + 1); 
	}
    }
    
    //iterative hexadecimal to decimal converter
    public static int hexToDec( String s ) {
	//initialize sum
	int n = 0;
	for(int i = s.length(); i > 0; i--) {
	    //convert most recent hex digit to decimal representation
	    int temp = HEXDIGITS.indexOf(s.substring(i - 1, i));
	    //add decimal value of most recent hex digit to sum
	    n += temp * Math.pow(16, s.length() - i);
	}
	return n;
    }

    //recursive hexadecimal to decimal converter
    public static int hexToDecR( String s ) {
	if (s.length() == 0) return 0;
	else {
	    int digit = HEXDIGITS.indexOf( s.substring(0,1) );
	    return digit * (int)Math.pow(16,s.length() - 1) + hexToDecR(s.substring(1));
	}
    }
    
    //overwritten .equals() method
    public boolean equals( Object other ) {
	return compareTo(other) == 0;
    }

    public int compareTo(Object other) {
	//first, check for aliasing...
	if (this == other) return 0;
	//if other is comparable...
	else if (other instanceof Comparable) {
	    //if other is rational, compare dec representation to float value
	    if (other instanceof Rational) {
		if (_decNum > ((Rational)other).floatValue()) return 1;
		if (_decNum == ((Rational)other).floatValue()) return 0;
		if (_decNum < ((Rational)other).floatValue()) return -1;
	    }
	    //otherwise compare one dec representation to the other
	    if (other instanceof Binary) {
		if (_decNum > ((Binary)other).getDec()) return 1;
		if (_decNum == ((Binary)other).getDec()) return 0;
		if (_decNum < ((Binary)other).getDec()) return -1;
	    }
	    if (other instanceof Hexadecimal) {
		if (_decNum > ((Hexadecimal)other).getDec()) return 1;
		if (_decNum == ((Hexadecimal)other).getDec()) return 0;
		if (_decNum < ((Hexadecimal)other).getDec()) return -1;
	    }
	}
	//otherwise, other is not comparable
	else throw new ClassCastException("\ninput not comparable");
	return 999; //never reached
    }

    public static void main( String[] args ) {

	//first overloaded constructor
	Hexadecimal h1 = new Hexadecimal(31);
	Hexadecimal h2 = new Hexadecimal(4);
	Hexadecimal h3 = new Hexadecimal(19);
	Hexadecimal h4 = new Hexadecimal(144);

	//second overloaded constructor
	Hexadecimal h5 = new Hexadecimal("1");
	Hexadecimal h6 = new Hexadecimal("1C");
	Hexadecimal h7 = new Hexadecimal("DEF");
	Hexadecimal h8 = new Hexadecimal("90");

	//to test ==
	Hexadecimal h9 = h1;

	//testing toString()
	System.out.println("h1: " + h1);
	System.out.println("h2: " + h2);
	System.out.println("h3: " + h3);
	System.out.println("h4: " + h4);
	System.out.println("h5: " + h5);
	System.out.println("h6: " + h6);
	System.out.println("h7: " + h7);
	System.out.println("h8: " + h8);

	//testing ==
	System.out.println("\n==...");
	System.out.println(h1 == h2); //should be false
	System.out.println(h1 == h9); //should be true

	//testing .equals()
	System.out.println("\n.equals()...");
	System.out.println(h1.equals(h3)); //should be false
	System.out.println(h1.equals(h9)); //should be true
	System.out.println(h4.equals(h6)); //should be false
	System.out.println(h4.equals(h8)); //should be true

	//testing .compareTo()
	System.out.println("\n.compareTo()... (Hexadecimal)");
	System.out.println(h1.compareTo(h9)); //should be 0
	System.out.println(h4.compareTo(h8)); //should be 0
	System.out.println(h3.compareTo(h1)); //should be neg
	System.out.println(h1.compareTo(h3)); //should be pos

	Rational r1 = new Rational(144, 36); //4
	Rational r2 = new Rational(1536, 24); //64
	Rational r3 = new Rational(961, 31); //31
	Rational r4 = new Rational(129, 4); //32.25
	System.out.println("\n.compareTo()... (Rational)");
	System.out.println( h2.compareTo(r1) ); //should be 0
	System.out.println( h1.compareTo(r3) ); //should be 0
	System.out.println( h1.compareTo(r4) ); //should be neg
	System.out.println( h3.compareTo(r1) ); //should be pos

	Binary b1 = new Binary(4);
	Binary b2 = new Binary(19);
	Binary b3 = new Binary("10011");
	Binary b4 = new Binary("100101");
	System.out.println("\n.compareTo()... (Binary)");
	System.out.println( h3.compareTo(b2) ); //should be 0
	System.out.println( h2.compareTo(b1) ); //should be 0
	System.out.println( h2.compareTo(b4) ); //should be neg
	System.out.println( h4.compareTo(b3) ); //should be pos
	
    }//end main()

}//end class
