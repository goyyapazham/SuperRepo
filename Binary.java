/* Nalanda Sharadjaya
   APCS1 pd9
   HW43 -- This or That
   2015-12-08
*/

public class Binary implements Comparable {

    private int _decNum;
    private String _binNum;

    public Binary() { 
        _decNum = 0;
	_binNum = "0";
    }

    public Binary( int n ) {
	_decNum = n;
	_binNum = decToBin(n);
    }

    public Binary( String s ) {
	_decNum = binToDec(s);
	_binNum = s;
    }

    public String toString() {
	return _decNum + " -- " + _binNum;
    }

    public int getDec() {
	return _decNum;
    }
    
    public static String decToBin( int n ) {
	String s = "";
	int rem = 0;
	while (n != 0) {
	    rem = n % 2;
	    n /= 2;
	    s = rem + s;
	}
	return s;
    }
    
    public static String decToBinR( int n ) {
	int rem = n % 2;
	String s = "" + rem;
	if (n != 0) {
	    return decToBinR(n / 2) + rem;
	}
	return s.substring(0, s.length() - 1);
    }
    
    public static int binToDec( String s ) {
	int n = 0;
	for(int i = s.length(); i > 0; i--) {
	    int temp = Integer.parseInt(s.substring(i - 1, i));
	    n += temp * Math.pow(2, s.length() - i);
	}
	return n;
    }
    
    public static int binToDecR( String s ) {
	int n = 0;
	if (s.length() > 0) {
	    int temp = Integer.parseInt(s.substring(0, 1));
	    n += temp * Math.pow(2, s.length() - 1) + binToDecR(s.substring(1));
	}
	return n;
    }
    
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

    //main method for testing
    public static void main( String[] args ) {
	
	System.out.println();
	System.out.println( "Testing ..." );

	Binary b1 = new Binary(5);
	Binary b2 = new Binary(4);
	Binary b3 = new Binary(19);
	Binary b4 = new Binary(7);
	Binary b5 = new Binary(88);
	Binary b6 = new Binary(123);
	Binary b7 = new Binary("1010101");
	Binary b8 = new Binary("1101111");
	Binary b9 = new Binary("110101");

	System.out.println( "b1: " + b1 );
	System.out.println( "b2: " + b2 );
	System.out.println( "b3: " + b3 );       
	System.out.println( "b4: " + b4 );       
	System.out.println( "b5: " + b5 );       
	System.out.println( "b6: " + b6 );       
	System.out.println( "b7: " + b7 );       
	System.out.println( "b8: " + b8 );       
	System.out.println( "b9: " + b9 );
        
	System.out.println( "\n==..." );
	System.out.println( b1 == b2 ); //should be false
	System.out.println( b1 == b3 ); //should be true

	System.out.println( "\n.equals()..." );
	System.out.println( b1.equals(b2) ); //should be true
	System.out.println( b1.equals(b3) ); //should be true
	System.out.println( b3.equals(b1) ); //should be true
	System.out.println( b4.equals(b2) ); //should be false
	System.out.println( b1.equals(b4) ); //should be false

	System.out.println( "\n.compareTo..." );
	System.out.println( b1.compareTo(b2) ); //should be 0
	System.out.println( b1.compareTo(b3) ); //should be 0
	System.out.println( b1.compareTo(b4) ); //should be neg
	System.out.println( b4.compareTo(b1) ); //should be pos
	
    }//end main()

} //end class
