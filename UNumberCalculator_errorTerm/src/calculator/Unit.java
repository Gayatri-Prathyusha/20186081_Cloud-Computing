package calculator;
import java.util.*;
/**
 * Unit class with specified operations like addition, subtraction, multiplication, division.
 * returns the valid units for all the respective operations.
 * @author Gayatri Prathyusha
 *
 */
public class Unit{
	int intUnit;
	
	String [] u = {"no unit", "meter", "kilometer","mile","foot","kilogram", "gram","pound","second","minute","hour","day","kilometer/hour","mile/hour",
		"meter/second","foot/second","meter/second�","kilometer/hour�"," mile/hour�","foot/second�", "kilometer�/second�","meter�/kilogram second�","meter�",
		"kilometer�","mile�","foot�","meter�","kilometer�","mile�","foot�","second�","kilometer/second"};
	ArrayList<String> units;
	String stringUnit = "-1";
	ReadExcel reader;
	/**
	 * This is a default constructor.
	 * Constructor to read the units.
	 */
	Unit(){
		units = new ArrayList<String>();
		for(int i=0;i<u.length;i++) {
			units.add(u[i]);
		}
		reader = new ReadExcel();
		reader.setInputFile("resultantUnit.xls");
		intUnit = -1;
	}
	/**
	 * This is a default constructor to set the values.
	 * @param unitSpecifier
	 */
	Unit(int unitSpecifier){
		units = new ArrayList<String>();
		for(int i=0;i<u.length;i++) {
			units.add(u[i]);
		}
		reader  = new ReadExcel();
		reader.setInputFile("resultantUnit.xls");
		intUnit = unitSpecifier;
	}
	/**
	 * setter method to set the units.
	 * @param unitSpecifier
	 */
	public void setUnitSpecifier(int unitSpecifier) {
		this.intUnit = unitSpecifier;
	}
	/**
	 * getter method to get the units.
	 * @return integer representation of unit.
	 */
	public int getUnitSpecifier() {
		return intUnit;
	}
	/**
	 * toString method to return the units when object is called.
	 * @return String representation of units
	 */
	@Override
	public String toString() {
		if(intUnit==-1) {
			return "Incompatible";
		}
		return units.get(intUnit);
	}
	
	/**
	 * validation method for checking whether the units are valid or not for addition operation.
	 * @param operand1Unit
	 * @param operand2Unit
	 * @return boolean, true if units are valid and false if not valid.
	 */
	public boolean checkIfValidForAddition(Unit operand1Unit, Unit operand2Unit) {
		String output = reader.ReadCell(0,operand1Unit.getUnitSpecifier(), operand2Unit.getUnitSpecifier());
		if(output.equals("")){
			return false;
		} else if (units.contains(output)) {
			return true;
		}
		return true;
	}
	
	/**
	 * validation method for checking whether the units are valid or not for subtraction operation.
	 * @param operand1Unit
	 * @param operand2Unit
	 * @return boolean, true if units are valid and false if not valid.
	 */
	public boolean checkIfValidForSubtraction(Unit operand1Unit, Unit operand2Unit) {
		String output = reader.ReadCell(0,operand1Unit.getUnitSpecifier(), operand2Unit.getUnitSpecifier());
		if(output.equals("")){
			return false;
		}
		return true;
	}
	
	/**
	 * validation method for checking whether the units are valid or not for multiplication operation.
	 * @param operand1Unit
	 * @param operand2Unit
	 * @return boolean, true if units are valid and false if not valid.
	 */
	public boolean checkIfValidForMultiplication(Unit operand1Unit, Unit operand2Unit) {
		String output = reader.ReadCell(1,operand1Unit.getUnitSpecifier(), operand2Unit.getUnitSpecifier());
		if(output.equals("")){
			return false;
		}
		return true;
	}
	
	/**
	 * validation method for checking whether the units are valid or not for division operation.
	 * @param operand1Unit
	 * @param operand2Unit
	 * @return boolean, true if units are valid and false if not valid.
	 */
	public boolean checkIfValidForDivision(Unit operand1Unit, Unit operand2Unit) {
		String output = reader.ReadCell(2,operand1Unit.getUnitSpecifier(), operand2Unit.getUnitSpecifier());
		if(output.equals("")){
			return false;
		}
		return true;
	}
	
	/**
	 * validation method for checking whether the units are valid or not for division operation.
	 * @param operand1Unit
	 * @return boolean, true if units are valid and false if not valid.
	 */
	public boolean checkIfValidForSquareRoot(Unit operand1Unit) {
		String output = reader.ReadCell(3,operand1Unit.getUnitSpecifier(),1);
		if(output.equals("")){
			return false;
		}
		return true;
	}
	/**
	 * this method is used to return the unit when addition operation is performed.
	 * @param operand1Unit
	 * @param operand2Unit
	 * @return Unit
	 */
	public Unit unitResultingFromAddition(Unit operand1Unit, Unit operand2Unit) {
		if(checkIfValidForAddition(operand1Unit,operand2Unit)) {
			String output = reader.ReadCell(0,operand1Unit.getUnitSpecifier(), operand2Unit.getUnitSpecifier());
			int ind = units.indexOf(output);
			return new Unit(ind);
			
		}else {
			return new Unit();
		}
		
	}
	
	/**
	 * this method is used to return the unit when subtraction operation is performed.
	 * @param operand1Unit
	 * @param operand2Unit
	 * @return Unit
	 */
	public Unit unitResultingFromSubtraction(Unit operand1Unit, Unit operand2Unit) {
		if(checkIfValidForSubtraction(operand1Unit,operand2Unit)) {
			String output = reader.ReadCell(0,operand1Unit.getUnitSpecifier(), operand2Unit.getUnitSpecifier());
			int ind = units.indexOf(output);
			return new Unit(ind);
		}else {
			return new Unit();
		}
	}
	
	/**
	 * this method is used to return the unit when division operation is performed.
	 * @param operand1Unit
	 * @param operand2Unit
	 * @return Unit
	 */
	public Unit unitResultingFromDivision(Unit operand1Unit, Unit operand2Unit) {
		if(checkIfValidForDivision(operand1Unit,operand2Unit)) {
			String output = reader.ReadCell(2,operand1Unit.getUnitSpecifier(), operand2Unit.getUnitSpecifier());
			int ind = units.indexOf(output);
			return new Unit(ind);
		}else {
			return new Unit();
		}
	}
	
	/**
	 * this method is used to return the unit when multiplication operation is performed.
	 * @param operand1Unit
	 * @param operand2Unit
	 * @return Unit
	 */
	public Unit unitResultingFromMultiplication(Unit operand1Unit, Unit operand2Unit) {
		if(checkIfValidForMultiplication(operand1Unit,operand2Unit)) {
			String output = reader.ReadCell(1,operand1Unit.getUnitSpecifier(), operand2Unit.getUnitSpecifier());
			int ind = units.indexOf(output);
			return new Unit(ind);
		}else {
			return new Unit();
		}
	}
	
	/**
	 * this method is used to return the unit when square root operation is performed.
	 * @param operand1Unit
	 * @return Unit.
	 */
	public Unit unitResultingFromSquareRoot(Unit operand1Unit) {
		if(checkIfValidForSquareRoot(operand1Unit)) {
			String output = reader.ReadCell(3,operand1Unit.getUnitSpecifier(),1);
			int ind = units.indexOf(output);
			return new Unit(ind);
		}else {
			return new Unit();
		}
	}
	
}