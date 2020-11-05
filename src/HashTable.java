import java.util.NoSuchElementException;



/**
 * 
 */

/**
 * @author Nic
 * class which represents a Hash Table ADT
 */
public class HashTable<V> {

	//type of hashing done
	private OpenAddressType type;
	//table comprised of generics
	private HashObject<V>[] hashTable;
	//array to measure number of hits to a slot
	private int[] frequency;
	
	//the capacity of the table
	private int capacity;
	//ratio of the table expected to be filled
	private float loadFactor;
	//current size of the table
	private int size;
	//cumulative number of probes done to the table
	private int numProbes;
	//the maximum size the table can get 
	private int maxSize;
	
	//default values of the above variables
	private final int DEFAULT_CAPACITY = 13;
	private final float DEFAULT_LOAD_FACTOR = (float) .75;
	private final OpenAddressType DEAFAULT_TYPE = OpenAddressType.linear;
	
	//temperary index used to determine if a slot is occupied
	private int contain;
	
	/**
	 * default constructor, which creates a new HashTable with 
	 * a default capacity (m) of 13, a default load factor(α) of 0.75, 
	 * and a default OpenAddressType of linear probing.
	 */
	public HashTable() {
		//set the capacity
		capacity = DEFAULT_CAPACITY;
		//set the load factor
		loadFactor = DEFAULT_LOAD_FACTOR;
		//set the type
		type = DEAFAULT_TYPE;
		
		//calculate the max size
		maxSize = (int) (capacity * loadFactor);
		
		//set the size and number of probes
		size = 0;
		numProbes = 0;
		
		hashTable = (HashObject<V>[]) (new HashObject[capacity]);
		frequency = new int[capacity]; 
	}
	
	/**
	 * constructor takes one parameter: the capacity of the hash table.
	 * The load factor and addressing type are set to the default values
	 * @param newCapacity
	 */
	public HashTable(int newCapacity) {
		//set the capacity
		capacity = newCapacity;
		//set the load factor
		loadFactor = DEFAULT_LOAD_FACTOR;
		//set the type
		type = DEAFAULT_TYPE;
		
		//calculate the max size
		maxSize = (int) (capacity * loadFactor);
		
		//set the size and number of probes
		size = 0;
		numProbes = 0;
		
		hashTable = (HashObject<V>[]) (new HashObject[capacity]);
		frequency = new int[capacity]; 
	}
	
	
	/**
	 * constructor takes two parameteters: the capacity and load factor of the table.
	 * The addressing type is set to the default value 
	 * 
	 * @param newCapacity
	 * @param newLoadFactor
	 */
	public HashTable(int newCapacity, float newLoadFactor) {
		//set the capacity
		capacity = newCapacity;
		//set the load factor
		loadFactor = newLoadFactor;
		//set the type
		type = DEAFAULT_TYPE;

		// calculate the max size
		maxSize = (int) (capacity * loadFactor);

		// set the size and number of probes
		size = 0;
		numProbes = 0;
		
		hashTable = (HashObject<V>[]) (new HashObject[capacity]);
		frequency = new int[capacity]; 
	}
	
	
	/**
	 * constructor that will accept a capacity, load factor, and type parameter
	 * 
	 * @param newCapacity
	 * @param newLoadFactor
	 * @param newType
	 */
	public HashTable(int newCapacity, float newLoadFactor, OpenAddressType newType) {	
		//set the capacity
		capacity = newCapacity;
		//set the load factor
		loadFactor = newLoadFactor;
		//set the type
		type = newType;
		
		// calculate the max size
		maxSize = (int) (capacity * loadFactor);

		// set the size and number of probes
		size = 0;
		numProbes = 0;
		
		hashTable = (HashObject<V>[]) (new HashObject[capacity]);
		frequency = new int[capacity]; 
	}
	/**
	 * creates a HashObject instance with the given value and key and inserts that into the HashTable at the correct index. If a given value is already in the Hash Table,
	 * it is not inserted into the table but its frequency is incremented
	 * 
	 * @param newObject
	 * @param newKey
	 */
	public void put(V newObject, int newKey) {
		//temp variable to generate hashes
		int i = 0;
		//new object to be inserted into the table
		HashObject<V> temp = new HashObject<V>(newObject, newKey);
		//tracks if we can exit the loop
		boolean found = false;
		
		//determine if the table is full
		if(size == getMaxSize()) {
			throw new IllegalStateException("Hash Table Full- max number of values reached");
		}
		
		//loop to generate hashes and insert into the table
		while(found == false && i != getMaxSize()) {
			//generate an index
			int j = getHash(newKey, i);
			//check if the slot is empty
			if(hashTable[j] == null) {
				//insert the new object
				hashTable[j] = temp;
				//increase the size
				size++;
				//exit the loop
				found = true;				
			}
			//check for duplicates
			else if(hashTable[j].equals(temp)) {
				//exit the loop
				found = true;
				//increment count
				frequency[j]++;
			}
			//generate a new hash
			else {
				i++;
				j = getHash(newKey,i);
			}
			//increase the number of probes
			numProbes = numProbes + 1;
		}
	}
	
	/**
	 * removes an element from the table
	 * 
	 * @param newObject
	 * @param newKey
	 * @return V a generic object
	 */
	public V remove(V newObject, int newKey) {
		//temp variable to generate hashes
		int i =0;
		// temp object representing what needs to be removed
		HashObject<V> temp = new HashObject<V>(newObject, newKey);
		//tracks if we can exit the loop
		boolean found = false;
		//temp object that will be removed
		V objectReturn = null;
		
		//loop to find the object and remove from the table
		while(found == false && i != capacity) {
			//get the hash of the object
			int j = getHash(newKey, i);
			//throw an error if the object cannot be found
			if(hashTable[j] == null) {
				throw new NoSuchElementException("the value is not in the table");
			}
			//remove the object
			if(hashTable[j].equals(temp)) {
				hashTable[j] = null;
				size--;
				found = true;
				objectReturn = newObject;
			}
			//recalculate the hash
			else {
				i++;
			}
		}
		//throw an error if the value cannot be found
		if(i == capacity) {
			throw new NoSuchElementException("the value is not in the table");
		}		
		return objectReturn;
	}
	
	/**
	 * checks to determine if an object is in the table
	 * 
	 * @param newObject
	 * @param newKey
	 * @return a boolean that is true if the object is in the table
	 */
	public boolean contains(V newObject, int newKey) {
		int i =0;
		//create a temp object to find the object
		HashObject<V> temp = new HashObject<V>(newObject, newKey);
		//tracks if we can exit the loop
		boolean found = false;
		
		//loop to find the object
		while(found == false && i != capacity) {
			//generate the hash
			contain = getHash(newKey, i);
			//determine if the object is in the table
			if(hashTable[contain] == null) {
				return found;
			}
			if(hashTable[contain].equals(temp)) {
				found = true;
				
			}
			//increment i to generate a new hash
			else {
				i++;
			}
		}
		return found;		
	}
	
	/**
	 *  removes all of the elements from the hash table
	 *  resets the size and number of probes
	 */
	public void clear() {
		//for loop to empty the contents of the table
		for(int i = 0; i<capacity;i++) {
			hashTable[i] = null;
			frequency[i] = 0;
		}
		//reset numProbs and size
		numProbes = 0;
		size = 0;
	}

	/**
	 * an int representing the key of a value, and another int,
	 * which is the number of attempts made to insert a given value, i. 
	 * The value of i starts at 0 and can range up to (m - 1)
	 * @param a
	 * @param b
	 * @return int which is the new index
	 */
	public int getHash(int a, int b) {
		int index = -1;
		//determine the type and perform the appropriate calculation
		if(type == OpenAddressType.linear) {
			index = (hash1(a, capacity) + b) % capacity;
		}
		else if(type == OpenAddressType.doubleHashing) {
			index = (hash1(a, capacity) + (b*hash2(a, capacity))) % capacity;
		}
		else if (type == OpenAddressType.quadratic) {
			index = (int) ((hash1(a, capacity) + (.5*b) + .5*(Math.pow(a, 2))) % capacity);
		}
		return index;
	}
	
	/**
	 * implements the auxiliary hash function described in the Background section.
	 * It's parameters represent the key of a given value (a)
	 * and the capacity of the table (b)
	 * 
	 * @param a
	 * @param b
	 * @return int 
	 */
	private int hash1(int a, int b) {
		return (a % b);
	}
	
	/**
	 *  implements the hash functions used for double hashing,
	 *  as described in the Background section above.
	 *  It takes the same parameters as the hash1 method
	 * @param a
	 * @param b
	 * @return int
	 */
	private int hash2(int a, int b) {
		return 1+(a % (b-2));
	}
	
	/** indicates the type of Open Addressing, Collision Avoidance probing used in the Hash Table
	 * 
	 * @return
	 */
	public OpenAddressType getType() {
		return type;
	}
	
	/**
	 * returns the capacity of the table
	 * 
	 * @return
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * returns the load factor of the table
	 * 
	 * @return
	 */
	public float getLoadFactor() {
		
		
		return (float) size()/capacity;
	}
	
	/**
	 * returns the current size of the table
	 * 
	 * @return
	 */
	public int size() {
		return size;
	}
	
	/**
	 * returns a boolean for if the table is empty
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * returns the number of probes made by the Hash Table
	 * 
	 * @return
	 */
	public int getNumProbes() {
		return numProbes;
	}
	
	/**
	 * returns the maximum size of the table as determined by the capacity and load factor
	 * 
	 * @return
	 */
	public int getMaxSize() {
		return maxSize;
	}
	
	/**
	 *  returns the current frequency for a given value and its key. 
	 *  If the value is not found in the hash table, the method should return -1
	 * 
	 * @param newObject
	 * @param newKey
	 * @return
	 */
	public int getFrequency(V newObject, int newKey) {
		if(contains(newObject, newKey)){
			return frequency[contain];
		}
		else {
			return -1;
		}
	}
	
	/**
	 * returns the total number of probes for all of the values in the table.
	 * It's equal to the total of all of the frequencies
	 * 
	 * @return
	 */
	public int  getNumDuplicates() {
		int numDuplicates = 0;
		//iterate over the frequencies array and add up the values
		for(int i =0; i < capacity; i++) {
			numDuplicates = numDuplicates + frequency[i];
		}
		
		return numDuplicates;
	}
	
	/**
	 * returns a String consisting of the word table followed by the table index of a value in square braces,
	 * then the return value of the toString method for the value followed by a space and the frequency of that value,
	 * one value on each line. If a slot in the hash table is vacant, that slot is skipped. 
	 * There should be no empty lines in the String returned
	 */
	public String toString() {

		String str = "";
		//iterate over the table
		for (int i = 0; i < capacity; i++) {
			if (hashTable[i] != null) {
				//parse out the data in the correct format
				str += "\ntable[" + i +"]: " + hashTable[i].getValue().toString() + " " + getFrequency(hashTable[i].getValue(), hashTable[i].getKey());
			}
		}
		return str;
	}
}
