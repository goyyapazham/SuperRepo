// Team Bring It O.N. -- Owen Zeng and Nalanda Sharadjaya
// APCS1 pd9
// HW42 -- Array of Titanium
// 2015-12-04

/*****************************
 * SKELETON for
 * class SuperArray --  A wrapper class for an array. 
 * Maintains functionality:
 *  access value at index
 *  overwrite value at index
 *  report number of meaningful items
 * Adds functionality to std Java array:
 *  resizability
 *  ability to print meaningfully
 *  add item (at end)
 *  insert item
 *  remove item (while maintaining "left-justification")
 *****************************/

public class SuperArray {
 
    //~~~~~INSTANCE VARS~~~~~
    //underlying container, or "core" of this data structure:
    private Comparable[] _data;

    //position of last meaningful value
    private int _lastPos;

    //size of this instance of SuperArray
    private int _size;
		
    //~~~~~METHODS~~~~~
    //default constructor - initializes 10-item array
    public SuperArray() { 
	_data = new Comparable[10];
	_lastPos = -1; //flag to indicate no lastpos yet
	_size = 0;	
    }
		
    //output array in [a,b,c] format, eg
    // {1,2,3}.toString() -> "[1,2,3]"
    public String toString() { 
	String retStr = "[";
	for( int i = 0; i < _size; i++ ) {
	    retStr += _data[i] + ", ";
	}
	//shave off trailing comma
	if ( retStr.length() > 1 )
	    retStr = retStr.substring( 0, retStr.length() - 2 );
	retStr += "]";
	return retStr;
    }
		
    //double capacity of this SuperArray
    private void expand() {
	Comparable[] temp = new Comparable[ _data.length * 2 ];
	for( int i = 0; i < _data.length; i++ )
	    temp[i] = _data[i];
	_data = temp;
    }
		
    //accessor -- return value at specified index
    public Comparable get( int index ) { return _data[index]; }

		
    //mutator -- set value at index to newVal, 
    //           return old value at index
    public Comparable set( int index, Comparable newVal ) { 
 	Comparable temp = _data[index];
	_data[index] = newVal;
	return temp;
    }

    // ~~~~~~~~~~~~~~ PHASE II ~~~~~~~~~~~~~~
    //adds an item after the last item
    public void add( Comparable newVal ) {
	if (_size == _data.length) 
	    expand();
	    
	_lastPos += 1;
	_size += 1;
	_data[_lastPos] = newVal;
    }


    //inserts an item at index
    //shifts existing elements to the right
    public void add( int index, Comparable newVal ) {
	if (_size == _data.length)
	    expand();
	else if (index < _size && index > -1) {
	    for (int i = _lastPos; i > index - 1; i--) {
	        _data[i + 1] = _data[i];
	    }
	    _data[index] = newVal;
	    _lastPos++;
	    _size++;
	}
	else
	    System.out.println("error: index out of range");	
    }

    //removes the item at index
    //shifts elements left to fill in newly-empted slot
    public void remove( int index ) {
        if (index < _size && index > -1) {
	    for (int i = index; i < _lastPos; i++) {
		_data[i] = _data[i+1];
	    }
	    _data[_lastPos] = new Rational();
	    _lastPos--;
	    _size--;
	}
	else
	    System.out.println("error: index out of range");	
    }
    
    //return number of meaningful items in _data
    public int size() { return _size; }

    //return true if SuperArray assorted in ascending order
    public boolean isSorted() {
	for(int i = 0; i < _size - 1; i++) {
	    if (_data[i].compareTo(_data[i + 1]) == 1)
		return false;
	}
	return true;
    }

    //return index of target if target in array; -1 otherwise
    public int linSearch(Comparable target) {
	for(int i = 0; i < _size; i++) {
	    if (_data[i].equals(target)) return i;
	}
	return -1; //reaches if target not in SuperArray
    }

    //main method for testing
    public static void main( String[] args ) {

	//Comparable array
        SuperArray arr = new SuperArray();
	System.out.println("arr pre-populate:     " + arr);
	
	arr.add(new Rational(144, 42));
	arr.add(new Rational(24, 153));
	arr.add(new Binary(31));
	arr.add(new Binary("1011101"));
	arr.add(new Hexadecimal(91));
	arr.add(new Hexadecimal("10A"));

	System.out.println("arr post-populate:    " + arr);

	arr.remove(3);
	System.out.println("remove index 3:       " + arr);
	arr.remove(3);
	System.out.println("remove index 3:       " + arr);
	
	arr.add(3, new Rational(3, 5));
	System.out.println("insert Rational:      " + arr);
	arr.add(2, new Binary(16));
	System.out.println("insert Binary:        " + arr);
	arr.add(1, new Hexadecimal(135));
	System.out.println("insert Hex:           " + arr);

	//testing isSorted()
	System.out.println("\ntesting isSorted()...");

	Rational x1 = new Rational(1, 2);
	Rational x2 = new Rational(3, 5);
	Binary x3 = new Binary(27);
	Binary x4 = new Binary(37);
	Hexadecimal x5 = new Hexadecimal(27);
	Hexadecimal x6 = new Hexadecimal(37);

	SuperArray a1 = new SuperArray();
	a1.add(x1);
	a1.add(x2);
	a1.add(x3);
	a1.add(x5);
	a1.add(x4);
	a1.add(x6);

	SuperArray a2 = new SuperArray();
	a2.add(x1);
	a2.add(x3);
	a2.add(x5);
	a2.add(x2);
	a2.add(x4);
	a2.add(x6);

	System.out.println("a1 post-populate:     " + a1);
	System.out.println("a1 is sorted:         " + a1.isSorted()); //true
	System.out.println("a2 post-populate:     " + a2);
	System.out.println("a2 is sorted:         " + a2.isSorted()); //false

	//testing linSearch()
	System.out.println("\ntesting linSearch()...");

	System.out.println("a1 post-populate:     " + a1);
	System.out.println("search for 3/5:       " +
			   a1.linSearch(new Rational(3, 5)) ); //1
	System.out.println("search for 25 (Bin):  " +
			   a1.linSearch(new Binary(27)) ); //2
	System.out.println("search for 25 (Hex):  " +
			   a1.linSearch(new Hexadecimal(27)) ); //2
	System.out.println("search for 90/7:      " +
			   a1.linSearch(new Rational(90, 7)) ); //-1
	
    }//end main
		
}//end class
