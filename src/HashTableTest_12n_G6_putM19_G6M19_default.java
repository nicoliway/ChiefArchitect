import java.util.NoSuchElementException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Testing for instances of HashTable class.  
 * Tests for Change Scenario: ('G', 6) -> put('M', 19) -> ('G', 6), ('M', 19)
 * with default values. 
 * @author CS 321
 */

public class HashTableTest_12n_G6_putM19_G6M19_default
{
	// HashTable running tests on
	HashTable<Character> table;
	
	//****** Constants used in tests *****************
	// Values and keys in hash table, for initialization 
	private static final Character FIRST_VALUE = TestCase.G6.getValue(); 
	private static final int FIRST_KEY = TestCase.G6.getKey(); 
	private static final Character SECOND_VALUE = TestCase.M19.getValue(); 
	private static final int SECOND_KEY = TestCase.M19.getKey(); 
	// Valid value / key pairs 
	private static final Object[][] VALID_VALUES_KEYS = { {TestCase.G6.getValue(), TestCase.G6.getKey() }, 
			{TestCase.M19.getValue(), TestCase.M19.getKey()} };
	// Expected hashes for given keys and attempts 
	private static final Object[][] EXPECTED_HASHES = { {TestCase.G6.getKey(), 0, 6}, {TestCase.G6.getKey(), 1, 7}, 
			{TestCase.G6.getKey(), 2, 8}, {TestCase.G6.getKey(), 3, 9},{TestCase.G6.getKey(), 4, 10}, 
			{TestCase.G6.getKey(), 5, 11}, {TestCase.G6.getKey(), 6, 12},{TestCase.G6.getKey(), 7, 0}, 
			{TestCase.G6.getKey(), 8, 1},{TestCase.G6.getKey(), 9, 2}, {TestCase.G6.getKey(), 10, 3},
			{TestCase.G6.getKey(), 11, 4}, {TestCase.G6.getKey(), 12, 5}, {TestCase.G6.getKey(), 13, 6}, 
			{TestCase.M19.getKey(), 0, 6}, {TestCase.M19.getKey(), 1, 7}, 
			{TestCase.M19.getKey(), 2, 8}, {TestCase.M19.getKey(), 3, 9},{TestCase.M19.getKey(), 4, 10}, 
			{TestCase.M19.getKey(), 5, 11}, {TestCase.M19.getKey(), 6, 12},{TestCase.M19.getKey(), 7, 0}, 
			{TestCase.M19.getKey(), 8, 1},{TestCase.M19.getKey(), 9, 2}, {TestCase.M19.getKey(), 10, 3},
			{TestCase.M19.getKey(), 11, 4}, {TestCase.M19.getKey(), 12, 5}, {TestCase.M19.getKey(), 13, 6} }; 
	// Valid value / key pairs with frequencies 
	private static final Object[][] VALID_FREQUENCIES = { {TestCase.G6.getValue(), TestCase.G6.getKey(), 0}, 
			{TestCase.M19.getValue(), TestCase.M19.getKey(), 0} };	
	// OpenAddressType for hash table  
	private static final OpenAddressType EXPECTED_TYPE = OpenAddressType.linear; 
	// Capacity of the hash table 
	private static final int EXPECTED_CAPACITY = 13; 
	// Number of probes made   
	private static final int EXPECTED_NUM_PROBES = 3; 
	// Frequency of lookup for invalid VALUE/KEY pair   
	private static final int INVALID_EXPECTED_FREQUENCY = -1; 
	// Maximum number of values in hash table for given load factor and capacity 
	private static final int EXPECTED_MAX_SIZE = 9; 
	// Number of duplicates found in lookup, equals total frequencies for all values   
	private static final int EXPECTED_NUM_DUPLICATES = 0; 
	// Number of objects in hash table
	private static final int SIZE = 2;
	// Alpha (load factor) for hash table   
	private static final float EXPECTED_ALPHA = (float) SIZE / EXPECTED_CAPACITY; 
	
	//****** Constants used in tests *****************
	// A value adding to table
	private static final Character NEW_VALUE = TestCase.Y0.getValue(); 
	// Key of value adding to table 
	private static final int NEW_KEY = TestCase.Y0.getKey(); 
	// A value not in table
	private static final Character INVALID_VALUE = TestCase.Z12.getValue(); 
	// Key of element not in table
	private static final int INVALID_KEY = TestCase.Z12.getKey(); 

	
	//********************Before Each Test Method********************
	/**
	 * Sets up table for testing.
	 */
	@BeforeMethod
	public void initialize()
	{
		// create an empty hash table
		table = TestCase.newHashTable("linear");
		// state of hash table before change 
		table.put(FIRST_VALUE, FIRST_KEY);
		// apply change
		table.put(SECOND_VALUE, SECOND_KEY);
	}
	
	//******************* Tests ***************************
	/**
	 * Test: put(value, key) - puts given object into hash table
	 * Expected Result: No exceptions
	 */
	@Test()
	public void testPut()
	{
		TestCase.put(table, NEW_VALUE, NEW_KEY);
	}

	/**
	 * Test: remove(value, key) - removes valid value from hash table
	 * Expected Result: Reference to valid value
	 */
	@Test(dataProvider = "validValuesKeys")
	public void testRemove_Valid(Character value, int key)
	{
		TestCase.remove(table, value, key);
	}

	/**
	 * Test: remove(value, key) - tries to remove invalid value from hash table
	 * Expected Result: NoSuchElementException
	 */
	@Test(expectedExceptions = NoSuchElementException.class)
	public void testRemove_Invalid()
	{
		TestCase.remove(table, INVALID_VALUE, INVALID_KEY);
	}

	/**
	 * Test: contains(value, key) - whether valid value is in hash table
	 * Expected Result: true 
	 */
	@Test(dataProvider = "validValuesKeys")
	public void testContains_Valid(Character value, int key)
	{
		TestCase.contains(table, value, key, true);
	}

	/**
	 * Test: contains(value, key) - whether invalid value is in hash table
	 * Expected Result: false 
	 */
	@Test()
	public void testContains_Invalid()
	{
		TestCase.contains(table, INVALID_VALUE, INVALID_KEY, false);
	}

	/**
	 * Test: clear() - clears contents of hash table
	 * Expected Result: No exceptions
	 */
	@Test()
	public void testClear()
	{
		TestCase.clear(table);
	}

	/**
	 * Test: getHash() - returns hash for given value/key pair and attempts 
	 * Expected Result: expectedHash  
	 */
	@Test(dataProvider = "expectedHashes")
	public void testGetHash(int key, int attempt, int expectedHash)
	{
		TestCase.getHash(table, key, attempt, expectedHash);
	}

	/**
	 * Test: getType() - returns OpenAddressType for hash table 
	 * Expected Result: EXPECTED_TYPE
	 */
	@Test()
	public void testGetType()
	{
		TestCase.getType(table, EXPECTED_TYPE);
	}

	/**
	 * Test: getCapacity() - returns capacity for hash table 
	 * Expected Result: EXPECTED_CAPACITY  
	 */
	@Test()
	public void testGetCapacity()
	{
		TestCase.getCapacity(table, EXPECTED_CAPACITY);
	}

	/**
	 * Test: getLoadFactor() - returns load factor (alpha) for hash table 
	 * Expected Result: EXPECTED_ALPHA  
	 */
	@Test()
	public void testGetLoadFactor()
	{
		TestCase.getLoadFactor(table, EXPECTED_ALPHA);
	}

	/**
	 * Test: size() - returns size for hash table 
	 * Expected Result: SIZE   
	 */
	@Test()
	public void testSize()
	{
		TestCase.size(table, SIZE);
	}

	/**
	 * Test: isEmpty() - whether hash table is empty 
	 * Expected Result: false
	 */
	@Test()
	public void testIsEmpty()
	{
		TestCase.isEmpty(table, false);
	}

	/**
	 * Test: getNumProbes() - returns number of probes for hash table 
	 * Expected Result: EXPECTED_NUM_PROBES
	 */
	@Test()
	public void testGetNumProbes()
	{
		TestCase.getNumProbes(table, EXPECTED_NUM_PROBES);
	}

	/**
	 * Test: getMaxSize() - returns maximum size for hash table 
	 * Expected Result: EXPECTED_MAX_SIZE
	 */
	@Test()
	public void testGetMaxSize()
	{
		TestCase.getMaxSize(table, EXPECTED_MAX_SIZE);
	}

	/**
	 * Test: getFrequency() - returns frequency for valid value/key pair 
	 * Expected Result: VALID_EXPECTED_FREQUENCY   
	 */
	@Test(dataProvider = "validFrequencies")
	public void testGetFrequency_Valid(Character value, int key, int expectedFrequency)
	{
		TestCase.getFrequency(table, value, key, expectedFrequency);
	}

	/**
	 * Test: getFrequency() - returns frequency for invalid value/key pair 
	 * Expected Result: INVALID_EXPECTED_FREQUENCY   
	 */
	@Test()
	public void testGetFrequency_Invalid()
	{
		TestCase.getFrequency(table, INVALID_VALUE, INVALID_KEY, INVALID_EXPECTED_FREQUENCY);
	}

	/**
	 * Test: getNumDuplicates() - returns number of duplicates for hash table 
	 * Expected Result: EXPECTED_NUM_DUPLICATES  
	 */
	@Test()
	public void testGetNumDuplicates()
	{
		TestCase.getNumDuplicates(table, EXPECTED_NUM_DUPLICATES);
	}
	
	//********** Data Providers ***************************
	/**
	 * Data: Valid value/key pairs. 
	 *  
	 * @return 2D array - value/key pairs in hash table 
	 */

	@DataProvider
	public static Object[][] validValuesKeys() 
	{
		return VALID_VALUES_KEYS;
	}	

	/**
	 * Data: Key with given number of attempts and expected hash. 
	 *  
	 * @return 2D array - keys, attempts, expected hashes for values in hash table  
	 */

	@DataProvider
	public static Object[][] expectedHashes() 
	{
		return EXPECTED_HASHES;
	}	

	/**
	 * Data: Valid value/key pairs with their frequencies. 
	 *  
	 * @return 2D array - value/key pairs in hash table with frequencies  
	 */

	@DataProvider
	public static Object[][] validFrequencies() 
	{
		return VALID_FREQUENCIES;
	}	

}
