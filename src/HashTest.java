import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author nicway
 * 
 * driver program that runs test on the HashTable class
 */
public class HashTest {

	////ratio of the table expected to be filled
	private static float loadFactor;
	//the capacity of the table
	private static int capacity;
	//input file
	private static String filename; 
	//type of objects to be stored in the table
	private static int inputType;
	//amount of data to show
	private static int debugLevel;
	
	//create tables for ints
	private static HashTable<Integer> linearHashInt;
	private static HashTable<Integer> doubleHashInt;
	private static HashTable<Integer> quadHashInt;

	//creates tables for longs
	private static HashTable<Long> linearHashLong;
	private static HashTable<Long> doubleHashLong;
	private static HashTable<Long> quadHashLong;
	
	//creates tables for strings and chars
	private static HashTable<String> linearHashString;
	private static HashTable<String> doubleHashString;
	private static HashTable<String> quadHashString;
	
	/**
	 * Driver. Takes in inputs, parses them and prints out results
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//parse the inputs
		parseInput(args);
		//construct the appropriate tables
		initialize();

		//create a file
		File file = new File(filename);
		
		//scan the file for data and store it
		scan(file, inputType);

		//determine the printing needed
		if(inputType == 0) {
			printInt();
		}
		else if (inputType == 1) {
			printLong();
		}
		else {
			printString();
		}
	
	}
	
	/**
	 * method that will construct hash tables based on the input type
	 */
	private static void initialize() {
		//contructs ints
		if(inputType == 0) {
			linearHashInt = new HashTable<Integer>(capacity, loadFactor, OpenAddressType.linear);
			doubleHashInt = new HashTable<Integer>(capacity, loadFactor, OpenAddressType.doubleHashing);
			quadHashInt = new HashTable<Integer>(capacity, loadFactor, OpenAddressType.quadratic);
				
		}
		//contructs longs
		else if (inputType == 1) {
			linearHashLong = new HashTable<Long>(capacity, loadFactor, OpenAddressType.linear);
			doubleHashLong = new HashTable<Long>(capacity, loadFactor, OpenAddressType.doubleHashing);
			quadHashLong = new HashTable<Long>(capacity, loadFactor, OpenAddressType.quadratic);
			
		}
		//contructs String and chars
		else {
			linearHashString = new HashTable<String>(capacity, loadFactor, OpenAddressType.linear);
			doubleHashString = new HashTable<String>(capacity, loadFactor, OpenAddressType.doubleHashing);
			quadHashString = new HashTable<String>(capacity, loadFactor, OpenAddressType.quadratic);

		}
	}

	/**
	 * method to call int tables and print out the results in desired format
	 * 
	 */
	private static void printInt() {
		
		//calculate the average number of probes for each table
		double averageNumProbesLinear = (double) (linearHashInt.getNumProbes())/ (double) (linearHashInt.size());
		double averageNumProbesDouble = (double) (doubleHashInt.getNumProbes())/ (double) (doubleHashInt.size());
		double averageNumProbesQuad = (double) (quadHashInt.getNumProbes())/ (double) (quadHashInt.size());
		//format the average
		String averageNumPresStringLinear = String.format("%.3f", averageNumProbesLinear);
		String averageNumPresStringDouble = String.format("%.3f", averageNumProbesDouble);
		String averageNumPresStringQuad = String.format("%.3f", averageNumProbesQuad);
		
		//print out results for linear
		System.out.println("Using Linear Probing...\r\n" + 
				"       Inserted " + linearHashInt.size() + " values with " + linearHashInt.getNumDuplicates() + " duplicates\r\n" + 
						"       Average number of probes: " + averageNumPresStringLinear);
		
		//print debug data for linear
		if(debugLevel == 1) {
			System.out.println(linearHashInt.toString());			
		}
	
		//print out results for double
		System.out.println();
		System.out.println("Using Double Hashing...\r\n" + 
				"       Inserted " + doubleHashInt.size() + " values with " + doubleHashInt.getNumDuplicates() + " duplicates\r\n" + 
						"       Average number of probes: " + averageNumPresStringDouble);

		//print debug data for double
		if(debugLevel == 1) {
			System.out.println(doubleHashInt.toString());
		}
		
		//print out results for quadratic
		System.out.println();
		System.out.println("Using Quadratic Hashing...\r\n" + 
				"       Inserted " + quadHashInt.size() + " values with " + quadHashInt.getNumDuplicates() + " duplicates\r\n" + 
						"       Average number of probes: " + averageNumPresStringQuad);

		//print debug data for quadratic
		if(debugLevel == 1) {
			System.out.println(quadHashInt.toString());
		}		
	}

	/**
	 * method to call long tables and print out the results in desired format
	 * 
	 */
	private static void printLong() {

		//calculate the average number of probes for each table
		double averageNumProbesLinear = (double) (linearHashLong.getNumProbes())/ (double) (linearHashLong.size());
		double averageNumProbesDouble = (double) (doubleHashLong.getNumProbes())/ (double) (doubleHashLong.size());
		double averageNumProbesQuad = (double) (quadHashLong.getNumProbes())/ (double) (quadHashLong.size());
		//format the average
		String averageNumPresStringLinear = String.format("%.3f", averageNumProbesLinear);
		String averageNumPresStringDouble = String.format("%.3f", averageNumProbesDouble);
		String averageNumPresStringQuad = String.format("%.3f", averageNumProbesQuad);
		
		//print out results for linear
		System.out.println("Using Linear Probing...\r\n" + 
				"       Inserted " + linearHashLong.size() + " values with " + linearHashLong.getNumDuplicates() + " duplicates\r\n" + 
						"       Average number of probes: " + averageNumPresStringLinear);
		//print debug data for linear
		if(debugLevel == 1) {
			System.out.println(linearHashLong.toString());			
		}
	
		//print out results for double
		System.out.println();
		System.out.println("Using Double Hashing...\r\n" + 
				"       Inserted " + doubleHashLong.size() + " values with " + doubleHashLong.getNumDuplicates() + " duplicates\r\n" + 
						"       Average number of probes: " + averageNumPresStringDouble);

		//print debug data for linear
		if(debugLevel == 1) {
			System.out.println(doubleHashLong.toString());
		}
		
		//print out results for quadratic
		System.out.println();
		System.out.println("Using Quadratic Hashing...\r\n" + 
				"       Inserted " + quadHashLong.size() + " values with " + quadHashLong.getNumDuplicates() + " duplicates\r\n" + 
						"       Average number of probes: " + averageNumPresStringQuad);

		//print debug data for quadratic
		if(debugLevel == 1) {
			System.out.println(quadHashLong.toString());
		}		
	}
	
	/**
	 * method to call String tables and print out the results in desired format
	 * 
	 */
	private static void printString() {
		
		//calculate the average number of probes for each table
		double averageNumProbesLinear = (double) (linearHashString.getNumProbes())/ (double) (linearHashString.size());
		double averageNumProbesDouble = (double) (doubleHashString.getNumProbes())/ (double) (doubleHashString.size());
		double averageNumProbesQuad = (double) (quadHashString.getNumProbes())/ (double) (quadHashString.size());
		//format the average
		String averageNumPresStringLinear = String.format("%.3f", averageNumProbesLinear);
		String averageNumPresStringDouble = String.format("%.3f", averageNumProbesDouble);
		String averageNumPresStringQuad = String.format("%.3f", averageNumProbesQuad);
		
		//print out results for linear
		System.out.println("Using Linear Probing...\r\n" + 
				"       Inserted " + linearHashString.size() + " values with " + linearHashString.getNumDuplicates() + " duplicates\r\n" + 
						"       Average number of probes: " + averageNumPresStringLinear);
		
		//print debug data for linear
		if(debugLevel == 1) {
			System.out.println(linearHashString.toString());			
		}
	
		//print out results for double
		System.out.println();
		System.out.println("Using Double Hashing...\r\n" + 
				"       Inserted " + doubleHashString.size() + " values with " + doubleHashString.getNumDuplicates() + " duplicates\r\n" + 
						"       Average number of probes: " + averageNumPresStringDouble);

		//print debug data for double
		if(debugLevel == 1) {
			System.out.println(doubleHashString.toString());
		}
		
		//print out results for quadratic
		System.out.println();
		System.out.println("Using Quadratic Hashing...\r\n" + 
				"       Inserted " + quadHashString.size() + " values with " + quadHashString.getNumDuplicates() + " duplicates\r\n" + 
						"       Average number of probes: " + averageNumPresStringQuad);

		//print debug data for quadratic
		if(debugLevel == 1) {
			System.out.println(quadHashString.toString());
		}		
	}
	
	/**
	 * method to parse out command line arguments
	 * 
	 * @param args
	 */
	private static void parseInput(String[] args) {
		try {
			//determine if incorrect number of parameters are provided
			if (args.length < 4 || args.length > 5) {
				throw new IllegalArgumentException("Incorrect number of command line arguements");
			}
			//get the load factor
			loadFactor = Float.parseFloat(args[0]);
			//get the capacity
			capacity = Integer.parseInt(args[1]);
			//get the file name
			filename = args[2];
			//determine the data in the file
			inputType = Integer.parseInt(args[3]);
			
			//check if debug data is needed
			if(args.length == 5) {
				debugLevel = Integer.parseInt(args[4]);
			}			
		}
		catch (IndexOutOfBoundsException e){
			System.out.println("Incorrect parameters");
		}
	}
	
	/**
	 * scans a file and will populate hash table with the data
	 * uses 3 different types of probing
	 * 
	 * @param file
	 * @param input
	 */
	private static void scan(File file, int input) {
		try {
			try (Scanner fileScan = new Scanner(file)) {
				while (fileScan.hasNextLine()) {
					// partition the file by lines
					String line = fileScan.nextLine();
					try (Scanner lineScan = new Scanner(line)) {
						//partition each line by word
						while(lineScan.hasNext()) {
							//pull out data from each line
							//object
							String string = lineScan.next();
							//key
							int objectKey = lineScan.nextInt();
							//integers
							if(input == 0) {
								//get the object
								int i = Integer.parseInt(string);
								//put the object in the table
								linearHashInt.put(i, objectKey);
								doubleHashInt.put(i, objectKey);
								quadHashInt.put(i, objectKey);
							}
							//longs
							else if(input == 1) {
								//get the object
								long i = Long.parseLong(string);
								
								linearHashLong.put(i, objectKey);
								doubleHashLong.put(i, objectKey);
								quadHashLong.put(i, objectKey);
							}
							//strings
							else if(input == 2) {
								//object is already a string
								//put the object in the table
								linearHashString.put(string, objectKey);
								doubleHashString.put(string, objectKey);
								quadHashString.put(string, objectKey);
							}
							//characters
							else if(input == 3) {
								//object is already a string
								//put the object in the table
								linearHashString.put(string, objectKey);
								doubleHashString.put(string, objectKey);
								quadHashString.put(string, objectKey);
							}							
						}						
					}					
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find file " + filename);
		}	
	}
	
}
