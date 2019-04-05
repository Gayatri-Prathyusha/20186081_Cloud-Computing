 package calculator;
import java.io.*;

/**
 * class to read the excel file.
 * @author Gayatri Prathyusha
 *
 */
public class ReadExcel {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	private String inputFile; // to store the input file name
	
	/**********************************************************************************************

	Getters and Setters
	
	**********************************************************************************************/
	
	/*****
	 * This method sets the value of the inputfile
	 * @param filename
	 */
	
	public void setInputFile(String filename)
	{
		this.inputFile= filename;
	}
	
	/**********************************************************************************************

	The computation methods
	
	**********************************************************************************************/
	

	/*******************************************************************************************************
	 *   
	 *  This method is for reading the value in a particular cell, It takes in the sheet number, row number and column number as inputs
	 *  and returns the content of the required cell from the inputfile as string
	 *  
	 *  @param sheetNumber
	 *  @param rowNumber
	 *  @param columnNumber
	 */
	
	public String ReadCell(int sheetNumber,int rowNumber,int columnNumber)
	{
		File inputWorkBook = new File(inputFile);
		Workbook w;
		try {
		w = Workbook.getWorkbook(inputWorkBook);
		Sheet sheet = w.getSheet(sheetNumber);// select the work sheet by means of sheet number
		Cell cell = sheet.getCell(columnNumber, rowNumber);// get the contents of the required cell from the selected cell.
		return cell.getContents().toString();
	}
		catch(Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	
}
