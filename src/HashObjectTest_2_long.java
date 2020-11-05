import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Testing for instances of HashObject<Long> class.  
 *
 * @author CS 321
 */

public class HashObjectTest_2_long
{
	// HashObject object running tests on
	HashObject<Long> hashObject;
	
	//****** Constants used in tests *****************
	// Value in HashObject
	private static final Long VALUE = TestCase.L_12;  
	// Key for value in HashObject 
	private static final int KEY = TestCase.K_1;  
	// New value for HashObject
	private static final Long NEW_VALUE = TestCase.L_34;   
	// New key for HashObject
	private static final int NEW_KEY = TestCase.K_2;  
	// Equivalent HashObject for testing equality 
	private static final HashObject<Long> EQUAL_HASHOBJECT = new HashObject<Long>(VALUE, KEY); 
	// Another HashObject to test for equality   
	private static final HashObject<Long> NOTEQUAL_HASHOBJECT = new HashObject<Long>(TestCase.L_56, TestCase.K_3);  
	// Hash code for hashObject
	private static final long HASH_CODE = 1004; 

	//********************Before Each Test Method********************
	/**
	 * Sets up HashObject for testing.
	 */
	@BeforeMethod
	public void initialize()
	{
		// create HashObject
		hashObject = new HashObject<Long>(VALUE, KEY); 
		
	}
	
	//******************* Tests ***************************

	/**
	 * Test: getValue - returns reference to value in hashObject
	 * Expected Result: Returns reference to VALUE
	 */
	@Test()
	public void testGetValue()
	{
		TestCase.getValue(hashObject, VALUE);
	}

	/**
	 * Test: getKey - returns key for hashObject
	 * Expected Result: Returns KEY
	 */
	@Test()
	public void testGetKey()
	{
		TestCase.getKey(hashObject, VALUE, KEY);
	}

	/**
	 * Test: setValue - sets value to given new value 
	 * Expected Result: No exceptions
	 */
	@Test()
	public void testSetValue()
	{
		TestCase.setValue(hashObject, NEW_VALUE);
	}

	/**
	 * Test: setKey - sets key to given new key 
	 * Expected Result: No exceptions
	 */
	@Test()
	public void testSetKey()
	{
		TestCase.setKey(hashObject, VALUE, NEW_KEY);
	}

	/**
	 * Test: equals - tests equality to HashObject with same value, key 
	 * Expected Result: HashObjects are equal 
	 */
	@Test()
	public void testEquals_Same()
	{
		TestCase.equals(hashObject, EQUAL_HASHOBJECT, VALUE, true);
	}

	/**
	 * Test: equals - tests equality to HashObject with different value, key 
	 * Expected Result: HashObjects are not equal 
	 */
	@Test()
	public void testEquals_NotSame()
	{
		TestCase.equals(hashObject, NOTEQUAL_HASHOBJECT, VALUE, false);
	}

	/**
	 * Test: hashCode - tests hashCode method
	 * Expected Result: hashCode returns correct hash value 
	 */
	@Test()
	public void testHashCode()
	{
		TestCase.hashCode(hashObject, VALUE, HASH_CODE);
	}
	

}
