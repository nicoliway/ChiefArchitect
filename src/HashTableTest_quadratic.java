import org.testng.annotations.Test;

/**
 * Testing for instances of HashTable class.  
 * Tests constructor with Open Address Type quadratic probing
 * @author CS 321
 */

public class HashTableTest_quadratic
{
	// HashTable running tests on
	HashTable<Character> table;
	
//************************** Test ***************************************
	/**
	 * Test: new table that uses quadratic hashing - throw exception
	 * Expected Result: IllegalArgumentException
	 */
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testConstructorWithQuadraticHashing()
	{
		// try to create an empty hash table 
		table = TestCase.newHashTable("quadratic");
	}

}
