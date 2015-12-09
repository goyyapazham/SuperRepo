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
    private int[] _data;

    //position of last meaningful value
    private int _lastPos;

    //size of this instance of SuperArray
    private int _size;

		
    //~~~~~METHODS~~~~~
    //default constructor - initializes 10-item array
    public SuperArray() { 
	_data = new int[10];
	_lastPos = -1; //flag to indicate no lastpos yet
	_size = 0;	
    }

		
    //output array in [a,b,c] format, eg
    // {1,2,3}.toString() -> "[1,2,3]"
    public String toString() { 
	String retStr = "[";
	for( int i = 0; i < _size; i++ ) {
	    retStr += _data[i] + ",";
	}
	//shave off trailing comma
	if ( retStr.length() > 1 )
	    retStr = retStr.substring( 0, retStr.length()-1 );
	retStr += "]";
	return retStr;
    }

		
    //double capacity of this SuperArray
    private void expand() {
	int[] temp = new int[ _data.length * 2 ];
	for( int i = 0; i < _data.length; i++ )
	    temp[i] = _data[i];
	_data = temp;
    }

		
    //accessor -- return value at specified index
    public int get( int index ) { return _data[index]; }

		
    //mutator -- set value at index to newVal, 
    //           return old value at index
    public int set( int index, int newVal ) { 
 	int temp = _data[index];
	_data[index] = newVal;
	return temp;
    }


    // ~~~~~~~~~~~~~~ PHASE II ~~~~~~~~~~~~~~
    //adds an item after the last item
    public void add( int newVal ) {
	if (_size == _data.length) 
	    expand();
	    
	_lastPos += 1;
	_size += 1;
	_data[_lastPos] = newVal;
    }


    //inserts an item at index
    //shifts existing elements to the right
    public void add( int index, int newVal ) {
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
	    _data[_lastPos] = 0;
	    _lastPos--;
	    _size--;
	}
	else
	    System.out.println("error: index out of range");	
    }
    

    //return number of meaningful items in _data
    public int size() { return _size; }

    //main method for testing
    public static void main( String[] args ) {

        SuperArray x = new SuperArray();
	System.out.println("x pre-populate:  " + x);

	x.add(5);
	x.add(4);
	x.add(3);
	x.add(2);
	x.add(1);

	System.out.println("x post-populate: " + x);

	x.remove(3);
	System.out.println("x.remove(3):     " + x);
	x.remove(3);
	System.out.println("x.remove(3):     " + x);

	x.add(3,99);
	System.out.println("x.add(3,99):     " + x);
	x.add(2,88);
	System.out.println("x.add(2,88):     " + x);
	x.add(1,77);
	System.out.println("x.add(1,77):     " + x);
	//*****INSERT ANY ADDITIONAL TEST CALLS HERE*****

    }//end main
		
}//end class
