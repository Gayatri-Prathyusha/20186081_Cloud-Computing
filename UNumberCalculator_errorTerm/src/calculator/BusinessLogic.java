
package calculator;

import java.math.BigDecimal;

/**
 * <p> Title: BusinessLogic Class. </p>
 * 
 * <p> Description: The code responsible for performing the calculator business logic functions. 
 * This class performs the business logic of the calculator by using three CalculatorValues and 
 * performs actions with and on them.  The class expects data from the User Interface to arrive
 * as Strings and returns Strings to it.  This class calls the CalculatorValue class to do the 
 * computations and this class knows nothing about the actual representation of CalculatorValues,
 * that is the responsibility of the CalculatorValue class and the classes it calls.  Hiding the 
 * representation of calculator values from the business logic means that this code does not have
 * to change when there is a change in the representation changes. Similarly, the this class does
 * not need to change if aspects of the user interface changes.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2019 </p>
 * 
 * @author Lynn Robert Carter
 * @author Gayatri Prathyusha
 *  
 * @version 4.00	2014-10-18 The JavaFX-based GUI implementation of a long integer calculator
 * @version 4.01	2019-02-08 Enhancements to the documentation
 * @version 4.02	2019-02-08 The JavaFX-based GUI implementation of a double calculator
 * @version 4.03    The JavaFX-based GUI implementation of a double calculator with error terms.
 * 
 */

public class BusinessLogic {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	// These are the major calculator values 
	private CalculatorValue operand1 = new CalculatorValue(0);
	private CalculatorValue operand2 = new CalculatorValue(0);
	private CalculatorValue result = new CalculatorValue(0);
	
	private String operand1ErrorMessage = "";
	private boolean operand1Defined = false;
	private String operand2ErrorMessage = "";
	private boolean operand2Defined = false;
	private String resultErrorMessage = "";
	private String labelErrorMessage = "";
	private boolean labelDefined = false;
	
	private String ErrorTerm1Message = "";
	private boolean errorTerm1Defined = false;
	private String ErrorTerm2Message = "";
	private boolean errorTerm2Defined = false;
	private String resultErrorTermMsg = "";
	
	private UNumber temp;
	
	private String unit1 ="-1";
	private String unit2 = "-1";
	
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/
	
	/**********
	 * This method initializes all of the elements of the business logic aspect of the calculator.
	 * There is no special computational initialization required, so the initialization of the
	 * attributes above are all that are needed.
	 */
	public BusinessLogic() {
	}

	/**********************************************************************************************

	Getters and Setters
	
	**********************************************************************************************/
	
	/**********
	 * This public setter takes an input String, checks to see if there is a non-empty input string.
	 * If so, it uses it to create a new CalculatorValue and places it into operand1, any associated
	 * error message is placed into operand1ErrorMessage, and sets the defined flag accordingly.
	 * 
	 * @param value
	 * @return	True if the set did not generate an error; False if there was invalid input
	 */
	public boolean setOperand1(String value) {
		operand1Defined = false;						// Assume the operand will not be defined
		if (value.length() <= 0) {						// See if the input is empty. If so no error
			operand1ErrorMessage = "";					// message, but the operand is not defined.
			return true;								// Return saying there was no error.
		}
		temp = operand1.errorValue;
		operand1 = new CalculatorValue(value, unit1);			// If there was input text, try to convert it
		operand1.setErrorTerm(temp);
		operand1ErrorMessage = operand1.getErrorMessage();	// into a CalculatorValue and see if it
		if (operand1ErrorMessage.length() > 0) 			// worked. If there is a non-empty error 
			return false;								// message, signal there was a problem.
		operand1Defined = true;							// Otherwise, set the defined flag and
		return true;										// signal that the set worked
	}

	
	/**********
	 * This public setter takes an input String, checks to see if there is a non-empty input string.
	 * If so, it uses it to create a new CalculatorValue and places it into operand2, any associated
	 * error message is placed into operand2ErrorMessage, and sets the defined flag accordingly.
	 * 
	 * The logic of this method is the same as that for operand1 above.
	 * 
	 * @param value
	 * @return	True if the set did not generate an error; False if there was invalid input
	 */
	public boolean setOperand2(String value) {			// The logic of this method is exactly the
		operand2Defined = false;							// same as that for operand1, above.
		if (value.length() <= 0) {
			operand2ErrorMessage = "";
			return true;
		}
		temp = operand2.errorValue;
		operand2 = new CalculatorValue(value, unit2);
		operand2.setErrorTerm(temp);
		operand2ErrorMessage = operand2.getErrorMessage();
		if (operand2ErrorMessage.length() > 0)
			return false;
		operand2Defined = true;
		return true;
	}
	
	public boolean getLabelDefined() {
		return labelDefined;
	}
	/**********
	 * This public setter takes an input String, checks to see if there is a non-empty input string.
	 * If so, it uses it to create a new CalculatorValue and places it into result and any 
	 * associated error message is placed into resultErrorMessage.
	 * 
	 * The logic of this method is similar to that for operand1 above. (There is no defined flag.)
	 * 
	 * @param value
	 * @return	True if the set did not generate an error; False if there was invalid input
	 */
	public boolean setResult(String value) {			// The logic of this method is similar to
		if (value.length() <= 0) {							// that for operand1, above.
			operand2ErrorMessage = "";
			return true;
		}
		result = new CalculatorValue(value);
		resultErrorMessage = operand2.getErrorMessage();
		if (operand2ErrorMessage.length() > 0)
			return false;
		return true;
	}
	
	/**
	 * 
	 * @param value
	 * @return True if the set did not generate an error; False if there was invalid input
	 */
	public boolean setErrorTerm1(String value) {
		errorTerm1Defined = false;							// same as that for operand1, above.
		if (value.length() <= 0) {
			ErrorTerm1Message = "";
			return true;
		}
		operand1.errorTerm(value);
		
		ErrorTerm1Message = operand1.getETErrorMessage();
		//System.out.println(ErrorTerm1Message + "++++++++++++++++++");
		if (ErrorTerm1Message.length() > 0)
			return false;
		errorTerm1Defined = true;
		return true;
	}
	/**
	 * 
	 * @param value
	 * @return True if the set did not generate an error; False if there was invalid input
	 */
	public boolean setErrorTerm2(String value) {
		errorTerm2Defined = false;							// same as that for operand1, above.
		if (value.length() <= 0) {
			ErrorTerm2Message = "";
			return true;
		}
		operand2.errorTerm(value);
		ErrorTerm2Message = operand2.getETErrorMessage();
		//System.out.println(ErrorTerm2Message + "++++++++++++++++++");
		if (ErrorTerm2Message.length() > 0)
			return false;
		errorTerm2Defined = true;
		return true;
	}
	
	
	public void setUnit1(String unit) {
		this.unit1 = unit;
		operand1.setUnit(unit1);
		//System.out.println("Unit1 BL"+operand1.unit);

	}
	public void setUnit2(String unit) {
		this.unit2 = unit;
		operand2.setUnit(unit2);
		//System.out.println("Unit2 BL"+operand2.unit);

	}

	
	
	/**********
	 * This public setter sets the String explaining the current error in operand1.
	 * 
	 * @return	This method returns nothing, but the operand1ErrorMessage has been set
	 */
	public void setOperand1ErrorMessage(String m) {
		operand1ErrorMessage = m;
		return;
	}
	
	/**********
	 * This public getter fetches the String explaining the current error in operand1, it there is one,
	 * otherwise, the method returns an empty String.
	 * 
	 * @return an error message or an empty String if there was no error
	 */
	public String getOperand1ErrorMessage() {
		return operand1ErrorMessage;
	}
	
	/**********
	 * This public setter sets the String explaining the current error into operand2.
	 * 
	 * @return	This method returns nothing, but the operand2ErrorMessage has been set
	 */
	public void setOperand2ErrorMessage(String m) {
		operand2ErrorMessage = m;
		return;
	}
	
	/**********
	 * This public getter fetches the String explaining the current error in operand2, it there is one,
	 * otherwise, the method returns an empty String.
	 *
	 * @return an error message or an empty String if there was no error
	 */
	public String getOperand2ErrorMessage() {
		return operand2ErrorMessage;
	}
	
	/**********
	 * This public setter sets the String explaining the current error in the result.
	 * 
	 * @return	This method returns nothing, but the resultErrorMessage has been set
	 */
	public void setResultErrorMessage(String m) {
		resultErrorMessage = m;
		return;
	}
	
	/**********
	 * This public getter fetches the String explaining the current error in error term 1, it there is one,
	 * otherwise, the method returns an empty String.
	 *
	 * @return an error message or an empty String if there was no error
	 */
	public String getErrorTerm1ErrorMessage() {
		return ErrorTerm1Message;
	}
	/**********
	 * This public getter fetches the String explaining the current error in error term 2, it there is one,
	 * otherwise, the method returns an empty String.
	 *
	 * @return an error message or an empty String if there was no error
	 */
	public String getErrorTerm2ErrorMessage() {
		return ErrorTerm2Message;
	}

	/**********
	 * This public getter fetches the String explaining the current error in the result, it there is one,
	 * otherwise, the method returns an empty String.
	 * 
	 * @return and error message or an empty String if there was no error
	 */
	public String getResultErrorMessage() {
		return resultErrorMessage;
	}
	
	/**********
	 * This public getter fetches the defined attribute for operand1. You can't use the lack of an error 
	 * message to know that the operand is ready to be used. An empty operand has no error associated with 
	 * it, so the class checks to see if it is defined and has no error before setting this flag true.
	 * 
	 * @return true if the operand is defined and has no error, else false
	 */
	public boolean getOperand1Defined() {
		return operand1Defined;
	}
	
	/**********
	 * This public getter fetches the defined attribute for operand2. You can't use the lack of an error 
	 * message to know that the operand is ready to be used. An empty operand has no error associated with 
	 * it, so the class checks to see if it is defined and has no error before setting this flag true.
	 * 
	 * @return true if the operand is defined and has no error, else false
	 */
	public boolean getOperand2Defined() {
		return operand2Defined;
	}
	/**
	 * This public getter fetches the defined attribute for error term 1. You can't use the lack of an error 
	 * message to know that the operand is ready to be used. An empty operand has no error associated with 
	 * it, so the class checks to see if it is defined and has no error before setting this flag true.
	 * @return true if the error term is defined and has no error, else false
	 */
	public boolean geterrorTerm1Defined() {
		return errorTerm1Defined;
	}
	/**
	 * This public getter fetches the defined attribute for error term 2. You can't use the lack of an error 
	 * message to know that the operand is ready to be used. An empty operand has no error associated with 
	 * it, so the class checks to see if it is defined and has no error before setting this flag true.
	 *@return true if the error term is defined and has no error, else false
	 */
	public boolean geterrorTerm2Defined() {
		return errorTerm2Defined;
	}
	
	
	public String getUnit1() {
		return this.unit1;
	}
	public String getUnit2() {
		return this.unit2;
	}
	/**********************************************************************************************

	The toString() Method
	
	**********************************************************************************************/
	
	/**********
	 * This toString method invokes the toString method of the result type (CalculatorValue is this 
	 * case) to convert the value from its hidden internal representation into a String, which can be
	 * manipulated directly by the BusinessLogic and the UserInterface classes.
	 */
	@Override
	public String toString() {
		String[] resultArray = result.toString().split(",");
		//System.out.println(resultArray[0]+"-------"+resultArray[1]);
		String error = roundErrorTerm(Double.parseDouble(resultArray[1]));
		return Double.parseDouble(resultArray[0]) + "," + error + "," + resultArray[2];
	}
	
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	public String checkErrorTerm(double input){
        if(input <= 9000 && input >= 0.001 && (input+"").contains("E")){
            return toNumber(input);
        }else if(!(input <= 9000 && input >= 0.001) && !(input+"").contains("E")){
            return toScientific(input);
        }else{
            return input+ "";
        }
    }
	
	/**
	 * 
	 * @param num
	 * @return
	 */
    public String toScientific(double num) {
          String result="";
          String sign="";
        int power=(int)(Math.log(num)/Math.log(10));
          if(power < 0) power--;
          double fraction=num/Math.pow(10,power);
        result += fraction + "e" + sign + power;
        return result;
    }
    
    /**
     * 
     * @param num
     * @return
     */
    public String toNumber(double num){
        double temp = new BigDecimal(num+"").doubleValue();
        return temp +"";
    }

    /**
     * 
     * @param errorVal
     * @return
     */
	public double MeasureScientific(String errorVal) {
		return Double.parseDouble("" + errorVal);
	}
	/**
	 * to round off the error term to one significant digit.
	 * @param err
	 */
	public String roundErrorTerm(double err) {
		if(err == 0.0) {
			return "0.0";
		}
		errorterm obj = new errorterm(err);
		Double res = Double.parseDouble(obj.getValue());
		return checkErrorTerm(res);
	}

	
	
	/**********
	 * This public toString method is used to display all the values of the BusinessLogic class in a
	 * textual representation for debugging purposes.
	 * 
	 * @return a String representation of the class
	 */
	public String debugToString() {
		String r = "\n******************\n*\n* Business Logic\n*\n******************\n";
		r += "operand1 = " + operand1.toString() + "\n";
		r += "     operand1ErrorMessage = " + operand1ErrorMessage+ "\n";
		r += "     operand1Defined = " + operand1Defined+ "\n";
		r += "operand2 = " + operand2.toString() + "\n";
		r += "     operand2ErrorMessage = " + operand2ErrorMessage+ "\n";
		r += "     operand2Defined = " + operand2Defined+ "\n";
		r += "result = " + result.toString() + "\n";
		r += "     resultErrorMessage = " + resultErrorMessage+ "\n";
		r += "*******************\n\n";
		return r;
	}

	/**********************************************************************************************

	Business Logic Operations (e.g. addition)
	
	**********************************************************************************************/
	
	/**********
	 * This public method computes the sum of the two operands using the CalculatorValue class method 
	 * for addition. The goal of this class is to support a wide array of different data representations 
	 * without requiring a change to this class, user interface class, or the Calculator class.
	 * 
	 * This method assumes the operands are defined and valid. It replaces the left operand with the 
	 * result of the computation and it leaves an error message, if there is one, in a String variable
	 * set aside for that purpose.
	 * 
	 * This method does not take advantage or know any detail of the representation!  All of that is
	 * hidden from this class by the ClaculatorValue class and any other classes that it may use.
	 * 
	 * @return a String representation of the result
	 */
	public String addition() {
		result = new CalculatorValue(operand1);
		result.add(operand2);
		resultErrorMessage = result.getErrorMessage();
		return toString();
	}
	
	/**********
	 * The following methods are method stubs that need to be implemented.
	 * the method is a subtraction method. it doesn't support any units.
	 * @return a String representation of the result
	 */
	public String subtraction() {
		result = new CalculatorValue(operand1);
		result.sub(operand2);
		resultErrorMessage = result.getErrorMessage();
		// replace this comment with the code required to compute the result.
		
		return toString();
	}
	/**
	 * the method is a multiplication method. it doesn't support any units.
	 * @return a String representation of the result
	 */
	public String multiplication() {
		result = new CalculatorValue(operand1);
		result.mpy(operand2);
		resultErrorMessage = result.getErrorMessage();
		// replace this comment with the code required to compute the result.
		
		return toString();
	}
	/**
	 * the method is a division method. it doesn't support any units.
	 * the errors that are checked are 0 by 0 error and a number by 0 error.
	 * @return a String representation of the result
	 */
	public String division() {
		result = new CalculatorValue(operand1);
		result.div(operand2);
		resultErrorMessage = result.getErrorMessage();
		if (resultErrorMessage.length() > 0) {
			return "";
		}
		// replace this comment with the code required to compute the result.
		
		return toString();
	}
	/**
	 * the method is a square Root  method. it doesn't support any units.
	 * the errors that are finding square root for negative numbers.
	 * @return a String representation of the result
	 */
	public String squareRoot() {
		result = new CalculatorValue(operand1);
		result.sqrt();
		if (result.getErrorMessage().length()>0) {
			resultErrorMessage = result.getErrorMessage();
			return "";
		}
		// replace this comment with the code required to compute the result.
		resultErrorMessage = result.getErrorMessage();
		return toString();
	}
}
