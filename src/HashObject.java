import java.util.Objects;

/**
 * 
 */

/**
 * @author nicway
 *class to hold objects in the Hash Table
 */
public class HashObject<V>{

	//generic object
	private V object;
	private int key;
	
	//constructor
	public HashObject(V value, int key) {
		object = value;
		this.key = key;
	}
	
	//return the object
	public V getValue() {
		return object;
	}
	
	//return the key
	public int getKey() {
		return key;
	}
	
	//set the object
	public void setValue(V newValue) {
		object = newValue;
	} 
	
	//set the key
	public void setKey(int newKey) {
		key = newKey;
	}
	
	// return the object and the key
	@Override
	public String toString() {
		return object.toString() + ' ' + key;
	}
	
	//determines if two generic objects are the same
	@Override
	public boolean equals(Object o) {
		
		//check if addresses of Objects are the same
		//then they must be equal
		if(o == this)
		{
			return true;
		}
		//if not an instance of HashObject class then it must not be equal
		if(!(o instanceof HashObject<?>))
		{
			return false;
		}
		
		HashObject<V> hashObject = (HashObject<V>) o;
		
		//compares values of the variables to determine equivalence
		return (key == hashObject.key)
				&&(Objects.equals(object, hashObject.object));	
		
	}
	
	//generate hash code
	@Override 
	public int hashCode() {
		//use Objects class hash method to
		//generate hash code using the 
		//value of the class variables
		return Objects.hash(key,object);
	}
}
