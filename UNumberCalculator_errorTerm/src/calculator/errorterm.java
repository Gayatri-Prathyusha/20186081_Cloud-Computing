package calculator;

import java.util.regex.*;
/**
 * To round the error term value.
 * @author Harshitha Rao
 *
 */
public class errorterm {
	static String resultval = "";
	/**
	 * constructor.
	 * @param val
	 */
	errorterm(double val){
		start(Double.toString(val));
	}
	public errorterm(UNumber err) {
		// TODO Auto-generated constructor stub
		start(err.toDecimalString());
	}
	/**
	 * getting the result after rounding.
	 * @return String representation
	 */
	public String getValue() {
		return resultval;
	}
	/**
	 * 
	 * @param inputval
	 * @return String representation
	 */
	public static String zerosstring(int inputval) {
		String zeros = "";
		for (int i = 0; i < inputval; i++) {
			zeros = zeros + "0";
		}
		return zeros;
	}
	/**
	 * method to modify the value and round the the result.
	 * @param doubleval
	 * @return String representation
	 */
	public static String valuemodification(String doubleval) {
		if(!doubleval.contains(".")) {
			doubleval = doubleval + ".0";
		}
		String[] splitval = doubleval.split("\\.");
		String mainpart = splitval[0];
		String decimalpart = splitval[1];
		boolean matchingstatus = Pattern.matches("^0*$", mainpart);
		if (matchingstatus) {
			String regexp = "[1-9]";
			Pattern pattern = Pattern.compile(regexp);
			Matcher matcher = pattern.matcher(decimalpart);
			if (matcher.find()) {
				int startindex = matcher.start();
				if(startindex == decimalpart.length() - 1) {
					return doubleval;
				}
				int decimalfirstval = Integer.parseInt(decimalpart.charAt(startindex) + "");
				if (decimalfirstval == 9) {
					if (startindex == 0) {
						String finalmain = "1.0";
						return finalmain;
					} else {
						String zeros = zerosstring(startindex - 1);
						String finalmain = mainpart + "." + zeros + "1";
						return finalmain;
					}
				} else {
					String zeros = zerosstring(startindex);
					decimalfirstval = decimalfirstval + 1;
					String finalmain = mainpart + "." + zeros + decimalfirstval;
					return finalmain;
				}
			}
		} else {
			boolean decimalstatus = Pattern.matches("^0*$", decimalpart);
			boolean mainsubstatus = Pattern.matches("^0*$", mainpart.substring(1));
			if(mainsubstatus && decimalstatus) {
				return doubleval;
			}
			int firstval = Integer.parseInt(mainpart.charAt(0) + "");
			firstval++;
			String zeros = zerosstring(mainpart.length() - 1);
			String finalmain = firstval + zeros;
			return finalmain;
		}
		return "";
	}
	/**
	 * method to check the exponential numbers and convert to the decimal number.
	 * @param str
	 */
	public static void start(String str) {
		String doubleval = str;
		if (doubleval.contains("e") || doubleval.contains("E")) {
			if(doubleval.contains("e")) {
			    String[] splits = doubleval.split("e");
				resultval = valuemodification(splits[0]) + "e" +splits[1];
 	
			} else {
				String[] splits = doubleval.split("E");
				resultval = valuemodification(splits[0]) + "E" +splits[1];

			}	
		} else {
			resultval = valuemodification(doubleval);

		}
	}
}
