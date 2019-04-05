package calculator;

/**
 * <p> Title: TestCalculatorValue </p>
 * 
 * <p> Description: A component of the Calculator application </p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2017 </p>
 * 
 * @author Lynn Robert Carter
 * @author Gayatri Prathyusha
 * @version 4.00	2017-10-18	Initial baseline 
 * 
 */

public class TestCalculatorValue {

	/**********
	 * This class roots the execution of the test of the CalculatorValue class.  The application 
	 * tests the class by invoking the class methods and checking the result to see if the results 
	 * are proper.
	 * 
	 */
	
	/*********************************************************************************************/
	
	/**********
	 * The check method compares an Expected String to an Actual String and returns true if the 
	 * Strings match and false otherwise.  In addition, the Strings are displayed to the console
	 * and a message is display stating whether or not there is a difference.  If there is a
	 * difference, the character at the point of the difference in the actual String is replaced
	 * with a "?" and both are displayed making it clear what character is the start of the
	 * difference
	 * 
	 * @param Expected	The String object of the expected value
	 * @param Actual		The String object of the actual value
	 */
	private static boolean check(String expected, String actual) {
		// Display the input parameters
		System.out.println("***Expected String");
		System.out.println(expected);
		System.out.println("***Actual String");
		System.out.println(actual);
		
		// Check to see if there is a difference
		int lesserLength = expected.length();
		if (lesserLength > actual.length()) lesserLength = actual.length();
		int ndx = 0;
		while (ndx < lesserLength && expected.charAt(ndx) == actual.charAt(ndx))
			ndx++;
		
		// Explain why the loop terminated and if there is a difference make it clear to the user
		if (ndx < lesserLength || lesserLength < expected.length() || lesserLength < actual.length()) {
			System.out.println("*** There is a difference!\n" + expected.substring(0, ndx) + "? <-----");
			return false;
		}
		System.out.println("*** There is no difference!\n");
		return true;
	}
	
	/*********************************************************************************************/
	
	/**********
	 * This main method roots the execution of this test.  The method ignores the program
	 * parameters.  After initializing several local variables, it performs a sequence of
	 * tests, displaying information accordingly and tallying the number of successes and
	 * failures.
	 * 
	 * @param args	Ignored by this application.
	 */
	public static void main(String[] args) {
		// Display the header message to the console and initialize local variables
		System.out.println("Test CalculatorValue Class\n");
		int numPassed = 0;
		int numFailed = 0;
		
		// 1. Perform a default constructor test
		CalculatorValue test = new CalculatorValue();						// Perform the test
		
		System.out.println("1. No input given");								// No input that was given

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("measuredValue = 0.0\nerrorValue = 0.0\nerrorMessage = \n", test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();

		
		// 2. Perform a constructor test with a long
		test = new CalculatorValue(1234567890123456D);						// Perform the test
		
		System.out.println("2. Input: 1234567890123456D");	

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("measuredValue = 1.234567890123456E15\nerrorValue = 0.0\nerrorMessage = \n", test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();

		
		// 3. Perform a copy constructor test
		CalculatorValue t = new CalculatorValue(1234567890123456D);			// Set up the test
		t.setErrorMessage("The error message string");
		
		test = new CalculatorValue(t);										// Perform the test
		
		System.out.println("3. Input:\n1234567890123456L\nThe error message string\n");	

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("measuredValue = 1.234567890123456E15\nerrorValue = 0.0\nerrorMessage = The error message string\n", test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();

		
		// 4. Perform a constructor test with a string
		test = new CalculatorValue("1234567890123456");						// Perform the test
		
		System.out.println("4. Input: \"1234567890123456\"");	

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("measuredValue = 1.234567890123456E15\nerrorValue = 0.0\nerrorMessage = \n", test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();

		
		// 5. Perform addition
		CalculatorValue left = new CalculatorValue("1");						// Set up the test
		CalculatorValue right = new CalculatorValue("2");
		
		left.add(right);														// Perform the test
		
		System.out.println("5. Addition Input: \n1\n2");	

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("measuredValue = 3.0\nerrorValue = 0.0\nerrorMessage = \n", left.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();

		
		// 6. Perform subtraction
		left = new CalculatorValue("1");										// Set up the test
		right = new CalculatorValue("2");
		
		left.sub(right);														// Perform the test
		
		System.out.println("5. Subtraction Input: \n1\n2");	

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("measuredValue = -1.0\nerrorValue = 0.0\nerrorMessage = \n", left.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();

		
		// 7. Perform multiplication
		left = new CalculatorValue("1");										// Set up the test
		right = new CalculatorValue("2");
		
		left.mpy(right);														// Perform the test
		
		System.out.println("7. Multiplication Input: \n1\n2");	

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("measuredValue = 2.0\nerrorValue = 0.0\nerrorMessage = \n", left.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();

		
		// 8. Perform division
		left = new CalculatorValue("1");										// Set up the test
		right = new CalculatorValue("2");
		
		left.div(right);														// Perform the test
		
		System.out.println("8. Division Input: \n1\n2");	

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("measuredValue = 0.5\nerrorValue = 0.0\nerrorMessage = \n", left.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
		
		// 9. Perform division with 1/0
		left = new CalculatorValue("1");										// Set up the test
		right = new CalculatorValue("0");
		
		left.div(right);														// Perform the test
		
		System.out.println("9. Division Input: \n1\n2");	

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("measuredValue = 1.0\nerrorValue = 0.0\nerrorMessage = divide by zero error\n", left.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
		
		// 10. Perform division with 0/0
		left = new CalculatorValue(0);										// Set up the test
		right = new CalculatorValue(0);
		
		left.div(right);														// Perform the test
		
		System.out.println("10. Division Input: \n1\n2");	

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("measuredValue = 0.0\nerrorValue = 0.0\nerrorMessage = 0 by 0 not valid\n", left.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
//		//11.addition 
//		left = new CalculatorValue("1273.5");						// Set up the test
//		left.setErrorTerm(0.5);
//		right = new CalculatorValue("12559.0");
//		right.setErrorTerm(1.0);
//		
//		left.add(right);														// Perform the test
//		
//		System.out.println("11. Addition Input: \n1\n2");	
//
//		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
//		// message and tally the result
//		if (check("measuredValue = 13832.5\nerrorValue = 1.5\nerrorMessage = \n", left.debugToString())) {
//			numPassed++;
//			System.out.println("\tPass");
//		}
//		// If they do not match, display that there was a failure and tally that result
//		else {
//			numFailed++;
//			System.out.println("\tFail");
//		}
//		System.out.println();
//		
//		// 12. Perform addition with ERROR TERM for 3.2176E5 � 0.5  +  7.285E6 � 0.1 = 7606760  �  0.6.
//		left = new CalculatorValue("3.2176E5");						// Set up the test
//		left.setErrorTerm(0.5);
//		right = new CalculatorValue("7.285E6");
//		right.setErrorTerm(0.1);
//		
//		left.add(right);														// Perform the test
//		
//		System.out.println("12. Addition Input: \n1\n2");	
//
//		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
//		// message and tally the result
//		if (check("measuredValue = 7606760.0\nerrorValue = 0.6\nerrorMessage = \n", left.debugToString())) {
//			numPassed++;
//			System.out.println("\tPass");
//		}
//		// If they do not match, display that there was a failure and tally that result
//		else {
//			numFailed++;
//			System.out.println("\tFail");
//		}
//		System.out.println();
//
//		// 13. Perform addition with ERROR TERM for 1E10�0 + 1E10�0 = 1E10�0
//			left = new CalculatorValue("1E10");						// Set up the test
//			left.setErrorTerm(0);
//			right = new CalculatorValue("1E10");
//			right.setErrorTerm(0);
//			
//			left.add(right);														// Perform the test
//			
//			System.out.println("13. Addition Input: \n1\n2");	
//
//			// Check the actual output against the expected.  If they match, the test has been passed and display the proper
//			// message and tally the result
//			if (check("measuredValue = 2.0E10\nerrorValue = 0.0\nerrorMessage = \n", left.debugToString())) {
//				numPassed++;
//				System.out.println("\tPass");
//			}
//			// If they do not match, display that there was a failure and tally that result
//			else {
//				numFailed++;
//				System.out.println("\tFail");
//			}
//			System.out.println();
//		
//		
//		
//		// 14. Perform subtraction with ERROR TERM for 1273.5 � 0.5 - 1255.9 � 0.1 =  -17.6  �  0.4
//			left = new CalculatorValue("1273.5");						// Set up the test
//			left.setErrorTerm(0.5);
//			right = new CalculatorValue("1255.9");
//			right.setErrorTerm(0.1);
//			
//			left.sub(right);														// Perform the test
//			
//			System.out.println("14. Addition Input: \n1\n2");	
//
//			// Check the actual output against the expected.  If they match, the test has been passed and display the proper
//			// message and tally the result
//			if (check("measuredValue = 17.59999999999991\nerrorValue = 0.6\nerrorMessage = \n", left.debugToString())) {
//				numPassed++;
//				System.out.println("\tPass");
//			}
//			// If they do not match, display that there was a failure and tally that result
//			else {
//				numFailed++;
//				System.out.println("\tFail");
//			}
//			System.out.println();
//			
//		// 15. Perform subtraction with ERROR TERM for 1E20 - 1E-20 = 1E20.
//			left = new CalculatorValue("1E20");						// Set up the test
//			left.setErrorTerm(0);
//			right = new CalculatorValue("1E-20");
//			right.setErrorTerm(0);
//			
//			left.sub(right);														// Perform the test
//			
//			System.out.println("15. Addition Input: \n1\n2");	
//
//			// Check the actual output against the expected.  If they match, the test has been passed and display the proper
//			// message and tally the result
//			if (check("measuredValue = 1.0E20\nerrorValue = 0.0\nerrorMessage = \n", left.debugToString())) {
//				numPassed++;
//				System.out.println("\tPass");
//			}
//			// If they do not match, display that there was a failure and tally that result
//			else {
//				numFailed++;
//				System.out.println("\tFail");
//			}
//			System.out.println();
//			
//		// 16. Perform multiplication for the input 173.5 � 0.05 * 19.0 � 0.1 = 3296.5  � 18.3
//			left = new CalculatorValue("173.5");						// Set up the test
//			left.setErrorTerm(0.05);
//			right = new CalculatorValue("19.0");
//			right.setErrorTerm(0.1);
//			
//			left.mpy(right);														// Perform the test
//			
//			System.out.println("16. Multiplication Input: \n1\n2");	
//	
//			// Check the actual output against the expected.  If they match, the test has been passed and display the proper
//			// message and tally the result
//			if (check("measuredValue = 3296.5\nerrorValue = 18.3\nerrorMessage = \n", left.debugToString())) {
//				numPassed++;
//				System.out.println("\tPass");
//			}
//			// If they do not match, display that there was a failure and tally that result
//			else {
//				numFailed++;
//				System.out.println("\tFail");
//			}
//			System.out.println();
//			
//		// 17. Perform multiplication for the input 3.2E5 � 0.5 * 7.2E6 � 0.1 = 2.304E+12  �  0.000002
//			left = new CalculatorValue("3.2E5");						// Set up the test
//			left.setErrorTerm(0.5);
//			right = new CalculatorValue("7.2E6");
//			right.setErrorTerm(0.1);
//			
//			left.mpy(right);														// Perform the test
//			
//			System.out.println("17. Multiplication Input: \n1\n2");	
//	
//			// Check the actual output against the expected.  If they match, the test has been passed and display the proper
//			// message and tally the result
//			if (check("measuredValue = 2.304E12\nerrorValue = 3632000.0000000005\nerrorMessage = \n", left.debugToString())) {
//				numPassed++;
//				System.out.println("\tPass");
//			}
//			// If they do not match, display that there was a failure and tally that result
//			else {
//				numFailed++;
//				System.out.println("\tFail");
//			}
//			System.out.println();
//		
//			
//		// 18. Perform multiplication for the input 1E20 * 1E-20 = 1.0
//			left = new CalculatorValue("1E20");						// Set up the test
//			left.setErrorTerm(0);
//			right = new CalculatorValue("1E-20");
//			right.setErrorTerm(0);
//			
//			left.mpy(right);														// Perform the test
//			
//			System.out.println("17. Multiplication Input: \n1\n2");	
//	
//			// Check the actual output against the expected.  If they match, the test has been passed and display the proper
//			// message and tally the result
//			if (check("measuredValue = 1.0\nerrorValue = 0.0\nerrorMessage = \n", left.debugToString())) {
//				numPassed++;
//				System.out.println("\tPass");
//			}
//			// If they do not match, display that there was a failure and tally that result
//			else {
//				numFailed++;
//				System.out.println("\tFail");
//			}
//			System.out.println();
//		
//		// 19. Perform division for the input 127.3 � 0.5 / 5.9 � 0.1 = 21.5762 �  0.45
//			left = new CalculatorValue("127.3");						// Set up the test
//			left.setErrorTerm(0.5);
//			right = new CalculatorValue("5.9");
//			right.setErrorTerm(0.1);
//			
//			left.div(right);														// Perform the test
//			
//			System.out.println("19. Division Input: \n1\n2");	
//
//			// Check the actual output against the expected.  If they match, the test has been passed and display the proper
//			// message and tally the result
//			if (check("measuredValue = 21.576271186440675\nerrorValue = 0.4504452743464521\nerrorMessage = \n", left.debugToString())) {
//				numPassed++;
//				System.out.println("\tPass");
//			}
//			// If they do not match, display that there was a failure and tally that result
//			else {
//				numFailed++;
//				System.out.println("\tFail");
//			}
//			System.out.println();
//			
//	
//		// 20. Perform division for the input 1E20 /1E-20 = 1E40
//			left = new CalculatorValue("1E20");						// Set up the test
//			left.setErrorTerm(0);
//			right = new CalculatorValue("1E-20");
//			right.setErrorTerm(0);
//			left.div(right);														// Perform the test
//			
//			System.out.println("20. Division Input: \n1\n2");	
//
//			// Check the actual output against the expected.  If they match, the test has been passed and display the proper
//			// message and tally the result
//			if (check("measuredValue = 1.0E40\nerrorValue = 0.0\nerrorMessage = \n", left.debugToString())) {
//				numPassed++;
//				System.out.println("\tPass");
//			}
//			// If they do not match, display that there was a failure and tally that result
//			else {
//				numFailed++;
//				System.out.println("\tFail");
//			}
//			System.out.println();
//		// 21. Perform test case for 5th equation from Hohmann
//			left = new CalculatorValue("149600000");						// Set up the test
//			left.setErrorTerm(0);
//			right = new CalculatorValue("227920000");
//			right.setErrorTerm(0);
//			left.add(right);														// Perform the test
//			
//			left.div(new CalculatorValue("2"));
//			
//			System.out.println("21. Division Input: \n1\n2");	
//
//			// Check the actual output against the expected.  If they match, the test has been passed and display the proper
//			// message and tally the result
//			if (check("measuredValue = 1.8876E8\nerrorValue = 0.0\nerrorMessage = \n", left.debugToString())) {
//				numPassed++;
//				System.out.println("\tPass");
//			}
//			// If they do not match, display that there was a failure and tally that result
//			else {
//				numFailed++;
//				System.out.println("\tFail");
//			}
//			System.out.println();
		System.out.println("Number of tests passed: " + numPassed);
		System.out.println("Number of tests failed: " + numFailed);

	}
}
