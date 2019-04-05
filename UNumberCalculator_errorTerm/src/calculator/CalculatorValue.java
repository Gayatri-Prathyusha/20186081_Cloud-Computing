package calculator;

import java.util.Scanner;
import java.util.*;



/**
 * <p> Title: CalculatorValue Class. </p>
 * 
 * <p> Description: A component of a JavaFX demonstration application that performs computations </p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2019 </p>
 * 
 * @author Lynn Robert Carter
 * @author Gayatri Prathyusha
 * @version 4.00	2017-10-18 Double integer implementation of the CalculatorValue class
 * @version 4.01	2019-02-08 Minor documentation update
 * @version 4.02    Double integer implementation of the CalculatorValue class with error terms.
 * 
 * 
 */
public class CalculatorValue {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	// These are the major values that define a calculator value
	UNumber measuredValue = new UNumber(0.0);
	UNumber errorValue =new UNumber(0.0);
	String errorMessage = "";
	Double temp;
	String errTermErrorMessage = "";
	String [] u ={"NoU","no unit", "meter", "kilometer","mile","foot","kilogram", "gram","pound","second","minute","hour","day","kilometer/hour","mile/hour",
			"meter/second","foot/second","meter/second�","kilometer/hour�"," mile/hour�","foot/second�", "kilometer�/second�","meter�/kilogram second�","meter�",
			"kilometer�","mile�","foot�","meter�","kilometer�","mile�","foot�","second�","kilometer/second"};
	String unit = "1";
	ArrayList<String> units;
	Unit unitObj;
	String resUnit = "-1";
	ReadExcel converter;
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/
	/*****
	 * This is the default constructor
	 */
	public CalculatorValue() {
		initialise();
	}

	/*****
	 * This constructor creates a calculator value based on a double integer. For future calculators, it
	 * is best to avoid using this constructor.
	 */
	public CalculatorValue(double v) {
		initialise();
		measuredValue = new UNumber(v);
	}
	
	/*****
	 * This copy constructor creates a duplicate of an already existing calculator value
	 */
	public CalculatorValue(CalculatorValue v) {
		initialise();
		measuredValue = v.measuredValue;
		errorMessage = v.errorMessage;
		unit = v.unit;
		this.errorValue = v.errorValue;
		
	}
	
	
	public CalculatorValue(String value, String unit) {
		initialise();
		this.setCalculatorValue(value);
		setUnit(unit);
		
	}
	/*****
	 * This constructor creates a calculator value from a string... Due to the nature
	 * of the input, there is a high probability that the input has errors, so the 
	 * routine returns the value with the error message value set to empty or the string 
	 * of an error message.
	 */
	public CalculatorValue(String s) {
		initialise();
		double temp = 0.0;
		int start = 0;										// Start at character position zero
		boolean negative = false;							// Assume the value is not negative
		if (s.charAt(start) == '+')							// See if the first character is '+'
			 start++;										// If so, skip it and ignore it
		// If the first character is a minus sign, skip over it, but remember it
		else if (s.charAt(start) == '-'){					// See if the first character is '-'
			start++;										// if so, skip it
			negative = true;								// but do not ignore it
		}
		String sign = s.substring(start);
		// See if the user-entered string can be converted into an integer value
		String errorFSM = MeasuredValueRecognizer.checkMeasureValue(sign);//get the error FSM from measurevalue recogniser
		if(errorFSM.length() > 0) {							//If error message present then set the error message and return
			errorMessage = errorFSM;		
			return;
		} else if (sign.length() > 0){						//If sign length > 0 then ,
			errorMessage = "";								//Set error message as null
			Scanner tempScanner = new Scanner(sign);
			temp = tempScanner.nextDouble();
			tempScanner.close();
		}
		if (negative)										// Return the proper value based
			temp = -temp;									// on the state of the flag that
		measuredValue = new UNumber(temp);

	}
	
	
	
	
	
	

	public void setCalculatorValue(String s) {
		if(s.length()==0) {
			return;
		}
		double temp1 = 0.0;
		int start = 0;										// Start at character position zero
		boolean negative = false;							// Assume the value is not negative
		if (s.charAt(start) == '+')							// See if the first character is '+'
			 start++;										// If so, skip it and ignore it
		// If the first character is a minus sign, skip over it, but remember it
		else if (s.charAt(start) == '-'){					// See if the first character is '-'
			start++;										// if so, skip it
			negative = true;								// but do not ignore it
		}
		String sign = s.substring(start);
		// See if the user-entered string can be converted into an integer value
		String errorFSM = MeasuredValueRecognizer.checkMeasureValue(sign);
		if(errorFSM.length() > 0) {							//If error messages, return the error
			errorMessage = errorFSM;						
			return;
		} else if (sign.length() > 0){						//else set the error message to null,
			errorMessage = "";								//and set the temp value
			Scanner tempScanner = new Scanner(sign);
			temp1 = tempScanner.nextDouble();
			tempScanner.close();
		}
		if (negative)										// Return the proper value based
			temp1 = -temp1;									// on the state of the flag that
		measuredValue = new UNumber(temp1);

	}
	/*****
	 * This constructor creates a calculator error term value from a string... Due to the nature
	 * of the input, there is a high probability that the input has errors, so the 
	 * routine returns the value with the error message value set to empty or the string 
	 * of an error message.
	 */
	public void errorTerm(String v) {
		double temp = 0.0;
		int start = 0;										// Start at character position zero
		if(v.length()==0) {									//If no input then return
			return;
		}
		boolean negative = false;							// Assume the value is not negative
		if (v.charAt(start) == '+')							// See if the first character is '+'
			 start++;										// If so, skip it and ignore it
		// If the first character is a minus sign, skip over it, but remember it
		else if (v.charAt(start) == '-'){					// See if the first character is '-'
			start++;										// if so, skip it
			negative = true;								// but do not ignore it
		}
		String sign = v.substring(start);
		// See if the user-entered string can be converted into an integer value
		String errorFSM = ErrorTermRecognizer.checkErrorTerm(sign);
		
		if(errorFSM.length() > 0) {
			errTermErrorMessage = errorFSM;
			return;
		} else if (sign.length() > 0 && ErrorTermRecognizer.errorTermIndexofError == -1){
			errTermErrorMessage = "";							//Set the error message as null
			Scanner tempScanner = new Scanner(sign);		//scan the sign
			temp = tempScanner.nextDouble();				//and check for double and assign to the error term
			errorValue = new UNumber(temp);
			
			tempScanner.close();
		}
		if (negative)										// Return the proper value based
			temp = -temp;									// on the state of the flag that
		errorValue = new UNumber(temp);
	}
	
	
	/**********************************************************************************************

	Getters and Setters
	
	**********************************************************************************************/
	
	/*****
	 * This is the start of the getters and setters
	 * 
	 * Get the error message
	 */
	public String getErrorMessage(){
		return errorMessage;
	}
	/**
	 * get error term error message.
	 * @return
	 */
	public String getETErrorMessage() {
		return errTermErrorMessage;
	}
	
	/*****
	 * Set the current value of a calculator value to a specific Double integer
	 */
	public void setValue(UNumber v){
		measuredValue = v;
	}
	
	/*****
	 * Set the current value of a calculator error message to a specific string
	 */
	public void setErrorMessage(String m){
		errorMessage = m;
	}
	/*****
	 * Set the current error term value of a calculator value to a specific Double integer
	 */
	public void setErrorTerm(UNumber v) {
		errorValue = v;
	}
	/*****
	 * Set the current value of a calculator value to the value of another (copy)
	 *  Set the current error term value of a calculator value to a specific Double integer(copy)
	 */
	public void setValue(CalculatorValue v){
		measuredValue = v.measuredValue;
		errorMessage = v.errorMessage;
		errorValue = v.errorValue;
	}
	
	/**********************************************************************************************

	The toString() Method
	
	**********************************************************************************************/

	/*****
	 * This is the default toString method
	 * 
	 * When more complex calculator values are creating this routine will need to be updated
	 */
	@Override
	public String toString() {
		return measuredValue.toDecimalString() + "," + errorValue.toDecimalString() + "," + resUnit;
	}
	
	/*****
	 * This is the debug toString method
	 * 
	 * When more complex calculator values are creating this routine will need to be updated
	 */
	public String debugToString() {
		return "measuredValue = " + measuredValue + "\nerrorValue = " + errorValue + "\nerrorMessage = " + errorMessage + "\n";
	}
	/**
	 * 
	 */
	private void initialise() {
		unitObj = new Unit();
		converter = new ReadExcel();
		converter.setInputFile("ConversionFactor.xls");
		units = new ArrayList<String>();
		for(int i=0;i<u.length;i++) {
			units.add(u[i]);
		}
	}
	/**
	 * 
	 * @param unit
	 * @return
	 */
	public int getIntUnit(String unit) {
		return units.indexOf(unit);
	}
	
	/**
	 * 
	 * @param s
	 */
	public void setUnit(String s) {
		this.unit = s;
		
	}
	
	/**
	 * 
	 * @param ar
	 * @param u1
	 * @param u2
	 * @param resUnit
	 * @return
	 */
	public UNumber[] scale(UNumber[] ar,Unit u1,Unit u2,String resUnit) {
		
		if(resUnit == "Incompatible") {
			return ar;
		}
		UNumber c1 = new UNumber(Double.parseDouble(converter.ReadCell(0, u1.getUnitSpecifier(), getIntUnit(resUnit))));
		UNumber c2 = new UNumber(Double.parseDouble(converter.ReadCell(0, u2.getUnitSpecifier(), getIntUnit(resUnit))));
		ar[0].mpy(c1);
		ar[1].mpy(c1);
		ar[2].mpy(c2);
		ar[3].mpy(c2);
		UNumber[] arr = {ar[0],ar[1],ar[2],ar[3]};
		return  arr;
	}
	
	/**********************************************************************************************

	The computation methods
	
	**********************************************************************************************/
	

	/**********************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume
	 * that the caller has verified that things are okay for the operation to take place.  These
	 * methods understand the technical details of the values and their reputations, hiding those
	 * details from the business +logic and user interface modules.
	 * 
	 * Since this is addition and we do not yet support units, we don't recognize any errors.
	 */
	public void add(CalculatorValue v) {
		
		Unit u1 = new Unit(getIntUnit(this.unit));
		Unit u2 = new Unit(getIntUnit(v.unit));
		Unit u = unitObj.unitResultingFromAddition(u1,u2);
		resUnit = u.toString();
		UNumber m1 = new UNumber(measuredValue);
		UNumber m2 = new UNumber(v.measuredValue);
		UNumber e1 = new UNumber(errorValue);
		UNumber e2 = new UNumber(v.errorValue);
		UNumber[] ar = {m1,e1,m2,e2};
		UNumber[] arr = scale(ar,u1,u2,resUnit);
		m1 = arr[0];
		e1 = arr[1];
		m2 = arr[2];
		e2 = arr[3];
		UNumber result = new UNumber(m1);
		e1.add(e2);							//Set the sum the error term
		result.add(m2);
		measuredValue = result;
		errorValue = e1;
		if(u.getUnitSpecifier() == -1) {
			resUnit = "-1";
			errorMessage = "Incompatible Units for addition";
			return;
		}
		errorMessage = "";									//error message to null
	}

	/**
	 * the method is a subtraction method. it doesn't support any units.
	 * @param v
	 */ 
	public void sub(CalculatorValue v) {
		
		Unit u1 = new Unit(getIntUnit(this.unit));
		Unit u2 = new Unit(getIntUnit(v.unit));
		Unit u = unitObj.unitResultingFromSubtraction(u1,u2);
		resUnit = u.toString();
		UNumber m1 = new UNumber(measuredValue);
		UNumber m2 = new UNumber(v.measuredValue);
		UNumber e1 = new UNumber(errorValue);
		UNumber e2 = new UNumber(v.errorValue);
		UNumber[] ar = {m1,e1,m2,e2};
		UNumber[] arr = scale(ar,u1,u2,resUnit);
		m1 = arr[0];
		e1 = arr[1];
		m2 = arr[2];
		e2 = arr[3];
		UNumber result = new UNumber(m1);
		e1.add(e2);								//sum the error terms,
		result.sub(m2);
//		measuredValue -= v.measuredValue;					//subtraction of measured value
		measuredValue = result;
		errorValue = e1;
		if(u.getUnitSpecifier() == -1) {
			resUnit = "-1";
			errorMessage = "Incompatible Units for Subtraction";
			return;
		}
		errorMessage = "";									//error message as null
		//errorMessage = "***Error*** Subtraction has not yet been implemented";
	}
	/**
	 * the method is a multiplication method. it doesn't support any units.
	 * @param v
	 */
	public void mpy(CalculatorValue v) {
		
		Unit u1 = new Unit(getIntUnit(this.unit));
		Unit u2 = new Unit(getIntUnit(v.unit));
		Unit u = unitObj.unitResultingFromMultiplication(u1,u2);
		resUnit = u.toString();
		UNumber m1 = new UNumber(measuredValue);
		UNumber m2 = new UNumber(v.measuredValue);
		UNumber e1 = new UNumber(errorValue);
		UNumber e2 = new UNumber(v.errorValue);
		UNumber[] ar = {m1,e1,m2,e2};
		UNumber[] arr = scale(ar,u1,u2,resUnit);
		m1 = arr[0];
		e1 = arr[1];
		m2 = arr[2];
		e2 = arr[3];
		UNumber zero = new UNumber(0.0);
		if(e1.equal(zero) && e2.equal(zero) ) {
			m1.mpy(m2);
			measuredValue = m1;
			errorValue = zero;
			errorMessage = "";
			if(u.getUnitSpecifier() == -1) {
				resUnit = "-1";
				errorMessage = "Incompatible Units for Multiplication";
				return;
			}
			return;
		}
		e1.mpy(m2);
		e2.mpy(m1);
		e1.add(e2);
		m1.mpy(m2);
		measuredValue = m1;
		e1.abs();
		errorValue = e1;
		if(u.getUnitSpecifier() == -1) {
			resUnit = "-1";
			errorMessage = "Incompatible Units for Multiplication";
			return;
		}
		errorMessage = "";
	}
	/**
	 * the method is a division method. it doesn't support any units.
	 * the errors that are checked are 0 by 0 error and a number by 0 error. divide by zero error 
	 * @param v
	 */
	public void div(CalculatorValue v) {
		
		Unit u1 = new Unit(getIntUnit(this.unit));
		Unit u2 = new Unit(getIntUnit(v.unit));
		Unit u = unitObj.unitResultingFromDivision(u1,u2);
		resUnit = u.toString();
		
		UNumber m1 = new UNumber(measuredValue);
		UNumber m2 = new UNumber(v.measuredValue);
		UNumber e1 = new UNumber(errorValue);
		UNumber e2 = new UNumber(v.errorValue);
		UNumber[] ar = {m1,e1,m2,e2};
		UNumber[] arr = scale(ar,u1,u2,resUnit);
		m1 = arr[0];
		e1 = arr[1];
		m2 = arr[2];
		e2 = arr[3];
		if(m2 == new UNumber(0.0)) {							//if operand 2 is zero then throw the error
			if(m1 == new UNumber(0.0)) {
				errorMessage = "0 by 0 not valid";
				m1 = new UNumber(-1.0);
			} else {
				errorMessage = "divide by zero error";
				m1 = new UNumber(-1.0);}
			} else {											//Else perform the division operation
				UNumber temp = new UNumber(m1);
				e1.div(temp);
				temp =m2;
				e2.div(temp);
				e1.add(e2);
				m1.div(m2);				//Set the measured value 
				e1.mpy(m1);		//Set the error term
				measuredValue = m1;
				e1.abs();
				errorValue = e1;
				
				if(u.getUnitSpecifier() == -1) {
					resUnit = "-1";
					errorMessage = "Incompatible Units for Division";
					return;
				}
				
				errorMessage = "";
		}
	}
	/**
	 * the method is a square Root  method. it doesn't support any units.
	 * the errors that are finding square root for negative numbers.
	 */
	public void sqrt() {
		
		Unit u1 = new Unit(getIntUnit(this.unit));
		Unit u = unitObj.unitResultingFromSquareRoot(u1);
		resUnit = u.toString();
		
		UNumber m1 = new UNumber(measuredValue);
		UNumber zero = new UNumber(0.0);
		UNumber e1 = new UNumber(errorValue);
		UNumber[] ar = {m1,e1,zero,zero};
		UNumber[] arr = scale(ar,u1,new Unit(1),resUnit);
		m1 = arr[0];
		e1 = arr[1];
		if(Double.parseDouble(m1.toDecimalString()) < 0.0 || Double.parseDouble(e1.toDecimalString()) < 0.0 ) {								//If measured value is negative then throw error,
			m1 = new UNumber(-1.0);								//by setting the measured value as -1 and 
			measuredValue = new UNumber(-1);
			errorMessage = "Invalid input for negative numbers";					//Error message as invalid input
			return;											//then return
		}
		if(m1.equal(zero)) {
			measuredValue = zero;
			errorValue = zero;
			if(u.getUnitSpecifier() == -1) {
				resUnit = "-1";
				errorMessage = "Incompatible Unit for Sqrt";
				return;
			}
			return;
		}
		DemoSquareRootWithUNumberWIthMissingCode temp = new DemoSquareRootWithUNumberWIthMissingCode(m1.toDecimalString()); 
		measuredValue = new UNumber(temp.res);			//else perform the squareroot,
		if(!e1.equal(zero)) {
			DemoSquareRootWithUNumberWIthMissingCode temp2 = new DemoSquareRootWithUNumberWIthMissingCode(e1.toDecimalString()); 
			errorValue = new UNumber(temp2.res);					//Set the error message to null
			if(u.getUnitSpecifier() == -1) {
				resUnit = "-1";
				errorMessage = "Incompatible Unit for Sqrt";
				return;
			}
			return;
		}
		
		errorValue = zero;
		errorMessage = "";
		if(u.getUnitSpecifier() == -1) {
			resUnit = "-1";
			errorMessage = "Incompatible Unit for Sqrt";
			return;
		}
	}

}
