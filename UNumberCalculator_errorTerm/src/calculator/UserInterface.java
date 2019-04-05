
package calculator;
import java.util.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.ComboBoxListViewSkin;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import java.math.BigDecimal;
import java.util.Arrays;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import calculator.BusinessLogic;

/**
 * <p> Title: UserInterface Class. </p>
 * 
 * <p> Description: The Java/FX-based user interface for the calculator. The class works with 
 * String objects and passes work to other classes to deal with all other aspects of the 
 * computation.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2019 </p>
 * 
 * @author Lynn Robert Carter
 * @author Gayatri Prathyusha
 * @version 4.00	2017-10-17 The JavaFX-based GUI for the implementation of a calculator.
 * @version 4.01	2019-02-08 Minor documentation update.
 * @version 4.02	The JavaFX-based GUI for the implementation of a double calculator.
 * @version 4.03    The JavaFX-based GUI for the implementation of a double calculator with error terms.
 */

public class UserInterface {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	/* Constants used to parameterize the graphical user interface.  We do not use a layout manager
	   for this application. Rather we manually control the location of each graphical element for
	   exact control of the look and feel. */
	
	// These set the width and positioning of buttons
	String[] errorTerm=null; 
	String[] errorMsg=null; 
	private final double BUTTON_WIDTH = 60;
	private final double BUTTON_OFFSET = BUTTON_WIDTH / 2;

	// These are the application values required by the user interface
	private Label label_IntegerCalculator = new Label("This is a UNumber Calculator");
	private Label label_Operand1 = new Label("First operand");
	private TextField text_Operand1 = new TextField();			// No initial value
	private Label label_errorTerm1 = new Label("Error term 1");
	private TextField text_errorTerm1 = new TextField();			// No initial value
	private Label label_Operand2 = new Label("Second operand");
	private TextField text_Operand2 = new TextField();			// No initial value
	private Label label_errorTerm2 = new Label("Error term 2");
	private TextField text_errorTerm2 = new TextField();			// No initial value
	private Label label_Result = new Label("Result");
	private TextField text_Result = new TextField();			// No initial value
	private Label label_errorTermResult = new Label("Result Error Term");
	private TextField text_errorTermResult = new TextField();			// No initial value
	private Label label_plusorminus = new Label("�");
	private Label label_plusorminus1 = new Label("�");
	private Label label_plusorminus2 = new Label("�");
	// These are the buttons that perform the calculator operations when pressed
	private Button button_Add = new Button("+");
	private Button button_Sub = new Button("-");
	private Button button_Mpy = new Button("\u00D7");				// The multiply symbol: \u00D7
	private Button button_Div = new Button("\u00F7");				// The divide symbol: \u00F7
	private Button button_Sqrt = new Button("\u221A");				// The sqrt symbol: \u221A
	// If the multiplication and/or division symbols do not display properly, replace the 
	// quoted strings used in the new Button constructor call with the <backslash>u00xx values
	// shown on the same line. This is the Unicode representation of those characters and will
	// work regardless of the underlying hardware being used. (e.g. new Button("\u00d7"); and 
	// new Button("\u00F78"); )
	
	// These are the labels that are used to display error messages. Since they are empty, nothing
	// shows on the user interface.
	private Label label_errOperand1 = new Label("");
	private Label label_errOperand2 = new Label("");
	private Label label_errResult = new Label("");
	
	private TextFlow errMeasuredValue;
    private Text errMVPart1 = new Text();
    private Text errMVPart2 = new Text();
	private TextFlow errErrorTerm;
    private Text errMV1Part1 = new Text();
    private Text errMV1Part2 = new Text();
	
    private TextFlow errErrorValue;
    private Text errETPart1 = new Text();
    private Text errETPart2 = new Text();
	private TextFlow errTerm;
    private Text errET1Part1 = new Text();
    private Text errET1Part2 = new Text();
    
    private Label label_errErrorTerm1 = new Label("");
    private Label label_errErrorTerm2 = new Label("");
    private Label label_errErrorTermResult = new Label("");
	private double buttonSpace;		// This is the white space between the operator buttons.
	
	
	private Label labelUnit1 = new Label("Select a unit:");
	ComboBox <String> comboboxUnit1 = new ComboBox <String>();
	//for operand 2
	private Label labelUnit2 = new Label("Select a unit:");
	ComboBox <String> comboboxUnit2 = new ComboBox <String>();
	private Label label_Unit1_err = new Label("");
	private Label label_Unit2_err = new Label("");
	
	private Label labelResultUnit = new Label("Resultant unit");
	private TextField text_ResultUnit = new TextField();
	
	
	String [] units = {"no unit", "meter", "kilometer","mile","foot","kilogram", "gram","pound","second","minute","hour","day","kilometer/hour","mile/hour",
			"meter/second","foot/second","meter/second�","kilometer/hour�"," mile/hour�","foot/second�", "kilometer�/second�","meter�/kilogram second�","meter�",
			"kilometer�","mile�","foot�","meter�","kilometer�","mile�","foot�","second�","kilometer/second"};

	/* This is the link to the business logic */
	public BusinessLogic perform = new BusinessLogic();
	private Object String[];
	boolean UserInterfaceIsReady = false;
	boolean flag = false;


	
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/

	/**********
	 * This constructor initializes all of the elements of the graphical user interface. These
	 * assignments determine the location, size, font, color, and change and event handlers for
	 * each GUI object.
	 */
	public UserInterface(Pane theRoot) {
				
		// There are five gaps. Compute the button space accordingly.
		buttonSpace = Calculator.WINDOW_WIDTH / 6;
		
		// Label theScene with the name of the calculator, centered at the top of the pane
		setupLabelUI(label_IntegerCalculator, "Arial", 24, Calculator.WINDOW_WIDTH, Pos.CENTER, 0, 10);
		
		// Label the first operand just above it, left aligned
		setupLabelUI(label_Operand1, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 40);			
				
		// Establish the first text input operand field and when anything changes in operand 1,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand1, "Arial", 18, Calculator.WINDOW_WIDTH-750, Pos.BASELINE_LEFT, 10, 70, true);
		text_Operand1.textProperty().addListener((observable, oldValue, newValue) -> {setOperand1(); });
		
		// Move focus to the second operand when the user presses the enter (return) key
		text_Operand1.setOnAction((event) -> { text_Operand2.requestFocus(); });
		
		
		// Establish an error message for the first operand just above it with, left aligned
		setupLabelUI(label_errOperand1, "Arial", 15, Calculator.WINDOW_WIDTH-160, Pos.BASELINE_LEFT, 22, 120);
		label_errOperand1.setTextFill(Color.RED);
		
		setupLabelUI(label_plusorminus, "Arial", 18, Calculator.WINDOW_WIDTH-750, Pos.BASELINE_LEFT, 380, 75);
				
		// Label the first operand just above it, left aligned
		setupLabelUI(label_errorTerm1, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 420, 40);
		
		// Establish the first text input operand field and when anything changes in operand 1,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_errorTerm1, "Arial", 18, Calculator.WINDOW_WIDTH-750, Pos.BASELINE_LEFT, 415, 70, true);
		text_errorTerm1.textProperty().addListener((observable, oldValue, newValue) -> {setErrorTerm1(); });
		
		// Establish an error message for the first operand just above it with, left aligned
		setupLabelUI(label_errErrorTerm1, "Arial", 15, Calculator.WINDOW_WIDTH-160, Pos.BASELINE_LEFT, 420, 120);
		label_errErrorTerm1.setTextFill(Color.RED);
		
		setupLabelUI(label_Unit1_err, "Arial", 18, Calculator.WINDOW_WIDTH-200, Pos.BASELINE_LEFT, 700, 120);
		label_Unit1_err.setTextFill(Color.RED);
		
		// Label the second operand just above it, left aligned
		setupLabelUI(label_Operand2, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 150);
		
		// Establish the second text input operand field and when anything changes in operand 2,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand2, "Arial", 18, Calculator.WINDOW_WIDTH-750, Pos.BASELINE_LEFT, 10, 180, true);
		text_Operand2.textProperty().addListener((observable, oldValue, newValue) -> {setOperand2(); });
		
		// Move the focus to the result when the user presses the enter (return) key
		text_Operand2.setOnAction((event) -> { text_Result.requestFocus(); });
		
		
		// Establish an error message for the second operand just above it with, left aligned
		setupLabelUI(label_errOperand2, "Arial", 15, Calculator.WINDOW_WIDTH-160, Pos.BASELINE_LEFT, 22, 230);
		label_errOperand2.setTextFill(Color.RED);
		
		setupLabelUI(label_plusorminus1, "Arial", 18, Calculator.WINDOW_WIDTH-750, Pos.BASELINE_LEFT, 380, 185);
		
		// Label the first operand just above it, left aligned
		setupLabelUI(label_errorTerm2, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 420, 150);
		
		// Establish the first text input operand field and when anything changes in operand 1,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_errorTerm2, "Arial", 18, Calculator.WINDOW_WIDTH-750, Pos.BASELINE_LEFT, 415, 180, true);
		text_errorTerm2.textProperty().addListener((observable, oldValue, newValue) -> {setErrorTerm2(); });
				
		// Establish an error message for the second operand just above it with, left aligned
		setupLabelUI(label_errErrorTerm2, "Arial", 15, Calculator.WINDOW_WIDTH-160, Pos.BASELINE_LEFT, 420, 230);
		label_errErrorTerm2.setTextFill(Color.RED);
		
		setupLabelUI(label_Unit2_err, "Arial", 18, Calculator.WINDOW_WIDTH-200, Pos.BASELINE_LEFT, 100, 230);
		label_Unit1_err.setTextFill(Color.RED);
		
		// Label the result just above the result output field, left aligned
		setupLabelUI(label_Result, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 270);
		
		// Establish the result output field.  It is not editable, so the text can be selected and copied, 
		// but it cannot be altered by the user.  The text is left aligned.
		setupTextUI(text_Result, "Arial", 18, Calculator.WINDOW_WIDTH-750, Pos.BASELINE_LEFT, 10, 300, false);
		
		// Establish an error message for the second operand just above it with, left aligned
		setupLabelUI(label_errResult, "Arial", 15, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 100, 270);
		label_errResult.setTextFill(Color.RED);
		

		setupLabelUI(label_plusorminus2, "Arial", 18, Calculator.WINDOW_WIDTH-750, Pos.BASELINE_LEFT, 380, 310);
		
		
		// Label the result just above the result output field, left aligned
		setupLabelUI(label_errorTermResult, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 420, 270);
		
		// Establish the result output field.  It is not editable, so the text can be selected and copied, 
		// but it cannot be altered by the user.  The text is left aligned.
		setupTextUI(text_errorTermResult, "Arial", 18, Calculator.WINDOW_WIDTH-750, Pos.BASELINE_LEFT, 415, 300, false);
		
		
		// Establish the ADD "+" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Add, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 1 * buttonSpace-BUTTON_OFFSET, 370);
		button_Add.setOnAction((event) -> { addOperands(); });
		
		// Establish the SUB "-" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Sub, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 2 * buttonSpace-BUTTON_OFFSET, 370);
		button_Sub.setOnAction((event) -> { subOperands(); });
		
		// Establish the MPY "×" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Mpy, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 3 * buttonSpace-BUTTON_OFFSET, 370);
		button_Mpy.setOnAction((event) -> { mpyOperands(); });
		
		// Establish the DIV "÷" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Div, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 4 * buttonSpace-BUTTON_OFFSET, 370);
		button_Div.setOnAction((event) -> { divOperands(); });
		
		// Establish the SQRT "�." button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Sqrt, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 5 * buttonSpace-BUTTON_OFFSET, 370);
		button_Sqrt.setOnAction((event) -> { SqrtOperands(); });
		
		//for operand 1
		loadComboBox(units, comboboxUnit1);
		setupLabelUI(labelUnit1, "Arial", 18, 210, Pos.BASELINE_LEFT, 800, 40);
        setupComboBoxUI(comboboxUnit1, "Arial", 18, 220, 800, 70); 
        comboboxUnit1.getSelectionModel().selectedItemProperty()
        	.addListener( (ObservableValue<? extends String> observable, String oldvalue, String newValue) -> {
        		setUnit1();
        	});
        
        loadComboBox(units, comboboxUnit2);
        //for operand 2
        setupLabelUI(labelUnit2, "Arial", 18, 210, Pos.BASELINE_LEFT, 800, 150);
        setupComboBoxUI(comboboxUnit2, "Arial", 18, 220, 800, 180); 
        comboboxUnit2.getSelectionModel().selectedItemProperty()
        	.addListener( (ObservableValue<? extends String> observable, String oldvalue, String newValue) -> {
        		setUnit2();
        	});
		//for resultant unit
        
        setupLabelUI(labelResultUnit, "Arial", 18, 210, Pos.BASELINE_LEFT, 800, 270);
        setupTextUI(text_ResultUnit, "Arial", 18, 220 , Pos.BASELINE_LEFT, 800, 300, false);
		        
		        
		        
		        
		errMVPart1.setFill(Color.BLACK);
	    errMVPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    errMVPart2.setFill(Color.RED);
	    errMVPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errMeasuredValue = new TextFlow(errMVPart1, errMVPart2);
		errMeasuredValue.setMinWidth(Calculator.WINDOW_WIDTH-10); 
		errMeasuredValue.setLayoutX(22);  
		errMeasuredValue.setLayoutY(100);
		
		errMV1Part1.setFill(Color.BLACK);
	    errMV1Part1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    errMV1Part2.setFill(Color.RED);
	    errMV1Part2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errErrorTerm = new TextFlow(errMV1Part1, errMV1Part2);
		// Establish an error message for the second operand just above it with, left aligned
		errErrorTerm.setMinWidth(Calculator.WINDOW_WIDTH-10); 
		errErrorTerm.setLayoutX(22);  
		errErrorTerm.setLayoutY(210);
		
		errETPart1.setFill(Color.BLACK);
	    errETPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    errETPart2.setFill(Color.RED);
	    errETPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errErrorValue = new TextFlow(errETPart1, errETPart2);
		errErrorValue.setMinWidth(Calculator.WINDOW_WIDTH-10); 
		errErrorValue.setLayoutX(420);  
		errErrorValue.setLayoutY(100);
		
		errET1Part1.setFill(Color.BLACK);
	    errET1Part1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    errET1Part2.setFill(Color.RED);
	    errET1Part2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errTerm = new TextFlow(errET1Part1, errET1Part2);
		// Establish an error message for the second operand just above it with, left aligned
		errTerm.setMinWidth(Calculator.WINDOW_WIDTH-10); 
		errTerm.setLayoutX(420);  
		errTerm.setLayoutY(210);
		
		
		// Place all of the just-initialized GUI elements into the pane
		theRoot.getChildren().addAll(label_IntegerCalculator, label_Operand1, text_Operand1, label_errOperand1, 
				label_Operand2, text_Operand2, label_errOperand2, label_Result, text_Result, label_errResult, 
				button_Add, button_Sub, button_Mpy, button_Div, button_Sqrt,errMeasuredValue, errErrorTerm,label_errorTerm1,
				text_errorTerm1,label_errorTerm2,text_errorTerm2,label_errorTermResult, text_errorTermResult,label_plusorminus,
				label_plusorminus1,label_plusorminus2, label_errErrorTerm1, label_errErrorTerm2,errErrorValue,errTerm,
				labelUnit1, comboboxUnit1, labelUnit2, comboboxUnit2, labelResultUnit, text_ResultUnit, label_Unit1_err, label_Unit2_err);

	}
	
	/**********
	 * Private local method to initialize the standard fields for a label
	 * 
	 * @param l		The Label object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param w		The width of the label
	 * @param p		The alignment (e.g. left, centered, or right)
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 */
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y){
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);		
	}
		
	/**********
	 * Private local method to initialize the standard fields for a text field
	 * 
	 * @param t		The TextField object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param w		The width of the text field
	 * @param p		The alignment (e.g. left, centered, or right)
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 * @param e		true if the field should be editable, else false
	 */
	private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e){
		t.setFont(Font.font(ff, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setAlignment(p);
		t.setLayoutX(x);
		t.setLayoutY(y);		
		t.setEditable(e);
	}
	
	/**********
	 * Private local method to initialize the standard fields for a button
	 * 
	 * @param b		The Button object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param w		The width of the Button
	 * @param p		The alignment (e.g. left, centered, or right)
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 */
	private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y){
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);		
	}
	/**********
	 * Private local method to initialize the standard fields for a ComboBox
	 * 
	 * @param c		The ComboBox object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param w		The width of the ComboBox
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 */
	private void setupComboBoxUI(ComboBox <String> c, String ff, double f, double w, double x, double y){
		c.setStyle("-fx-font: " + f + " " + ff + ";");
		c.setMinWidth(w);
		c.setLayoutX(x);
		c.setLayoutY(y);
	}
	
	/**********************************************************************************************

	User Interface Actions
	
	**********************************************************************************************/

	/**********
	 * Private local method used to set the value of the first operand given a text value. The 
	 * method uses the business logic class to perform the work of checking the string to see it is
	 * a valid value and if so, saving that value internally for future computations. If there is 
	 * an error when trying to convert the string into a value, the called business logic method 
	 * returns false and actions are taken to display the error message appropriately.
	 */
	private void setOperand1() {
		text_Result.setText("");							// Any change of an operand probably
		label_Result.setText("Result");						// invalidates the result, so we clear
		label_Result.setTextFill(Color.BLACK);				// the old result and the error 
		label_errResult.setText("");						// message
		if (perform.setOperand1(text_Operand1.getText())) {	// Set the operand and see if there was
			label_errOperand1.setText("");					// an error. If no error, clear this 
			errMVPart1.setText("");
			errMVPart2.setText("");
			if (text_Operand2.getText().length() == 0)		// operands error. If the other operand 
				label_errOperand2.setText("");				// is empty, clear its error as well.
			
		}
		else {											// If there's an issue with the operand,
			errorMsg = perform.getOperand1ErrorMessage().split("\u21EB");
			//System.out.println(Arrays.deepToString(eror));
			if(errorMsg.length==1) {
			label_errOperand1.setText(errorMsg[0]);
			} else {
				errMVPart1.setText(errorMsg[0]);
				errMVPart2.setText("\u21EB");
				label_errOperand1.setText(errorMsg[1]);
			}
		}
			//label_errOperand1.setText(perform.getOperand1ErrorMessage());	// display the error 
	}														// message that was generated
	
	
	/**********
	 * Private local method to set the value of the second operand given a text value. The logic is
	 * exactly the same as used for the first operand, above.
	 */
	private void setOperand2() {
		text_Result.setText("");							// See setOperand1's comments. The logic
		label_Result.setText("Result");						// is the same!
		label_Result.setTextFill(Color.BLACK);
		label_errResult.setText("");
		if (perform.setOperand2(text_Operand2.getText())) {
			label_errOperand2.setText("");
			errMV1Part1.setText("");
			errMV1Part2.setText("");
			if (text_Operand1.getText().length() == 0)
				label_errOperand1.setText("");
		}
		else {											// If there's an issue with the operand,
			//System.out.println(eror.length + "    LENGTH");
			errorMsg = perform.getOperand2ErrorMessage().split("\u21EB");
			if(errorMsg.length==1) {
			label_errOperand2.setText(errorMsg[0]);
			} else {
				errMV1Part1.setText(errorMsg[0]);
				errMV1Part2.setText("\u21EB");
				label_errOperand2.setText(errorMsg[1]);
			}
		}
	}
	
	/**********
	 * Private local method to set the value of the second operand given a text value. The logic is
	 * exactly the same as used for the first operand, above.
	 */
	private void setErrorTerm1() {
		text_errorTermResult.setText("");							// Any change of an operand probably
		label_errorTermResult.setText("Result");						// invalidates the result, so we clear
		label_errorTermResult.setTextFill(Color.BLACK);				// the old result and the error 
		label_errErrorTermResult.setText("");						// message
		if (perform.setErrorTerm1(text_errorTerm1.getText())) {	// Set the operand and see if there was
			label_errErrorTerm1.setText("");					// an error. If no error, clear this 
			errETPart1.setText("");
			errETPart2.setText("");
			if (text_errorTerm2.getText().length() == 0)		// operands error. If the other operand 
				label_errErrorTerm2.setText("");				// is empty, clear its error as well.
			
		}
		else {											// If there's an issue with the operand,
			errorMsg = perform.getErrorTerm1ErrorMessage().split("\u21EB");
			System.out.println(Arrays.deepToString(errorMsg));
			if(errorMsg.length==1) {
			label_errErrorTerm1.setText(errorMsg[0]);
			} else {
				errETPart1.setText(errorMsg[0]);
				errETPart2.setText("\u21EB");
				label_errErrorTerm1.setText(errorMsg[1]);
			}
		}
	}
	
	/**********
	 * Private local method to set the value of the second operand given a text value. The logic is
	 * exactly the same as used for the first operand, above.
	 */
	private void setErrorTerm2() {
		text_errorTermResult.setText("");							// See setOperand1's comments. The logic
		label_errorTermResult.setText("Result");						// is the same!
		label_errorTermResult.setTextFill(Color.BLACK);
		label_errErrorTermResult.setText("");
		if (perform.setErrorTerm2(text_errorTerm2.getText())) {
			label_errErrorTerm2.setText("");
			errET1Part1.setText("");
			errET1Part2.setText("");
			if (text_errorTerm1.getText().length() == 0)
				label_errErrorTerm1.setText("");
		}
		else {											// If there's an issue with the operand,
			//System.out.println(eror.length + "LENGTH");
			errorMsg = perform.getErrorTerm2ErrorMessage().split("\u21EB");
			if(errorMsg.length==1) {
			label_errErrorTerm2.setText(errorMsg[0]);
			} else {
				errET1Part1.setText(errorMsg[0]);
				errET1Part2.setText("\u21EB");
				label_errErrorTerm2.setText(errorMsg[1]);
			}
		}
	}
	
	private void setUnit1() {
		text_ResultUnit.setText("");
		label_Unit1_err.setText("");
		text_errorTermResult.setText("");
		text_Result.setText("");							// Any change of an operand probably
		label_Result.setText("Result");
		perform.setUnit1(comboboxUnit1.getValue());
	}
	private void setUnit2() {
		text_ResultUnit.setText("");
		label_Unit2_err.setText("");
		text_errorTermResult.setText("");
		text_Result.setText("");							// Any change of an operand probably
		label_Result.setText("Result");
		perform.setUnit2(comboboxUnit2.getValue());
		System.out.println("UI u2 "+comboboxUnit2.getValue());
	}
	
	
	private void loadComboBox(String[] names, ComboBox <String> comboboxSelectUnit) {

		// Define a new list of countries.
		List<String> list = new ArrayList<String>();
			for (int i = 0; i < names.length; i++) {
				list.add(names[i]);
			}
		
		comboboxSelectUnit.setItems(FXCollections.observableArrayList(list));
		comboboxSelectUnit.getSelectionModel().select(0);
        if (UserInterfaceIsReady) {
            // Force the skin to scroll to the same place as the selection.  I have no idea why
        	// one would think that we should have to do this!  The following web page helped me
        	// to get this to work: https://bugs.openjdk.java.net/browse/JDK-8091560
            ComboBoxListViewSkin<?> skin = (ComboBoxListViewSkin<?>)comboboxUnit1.getSkin();
            ( (ListView<?>) skin.getPopupContent()).scrollTo(0);
        }
	}
	
	/***
	 * Method to check weather both elements in the error terms are defined as per requirement or not.
	 * If one of the error term is not given taking the default value.
	 */
	private void check() {
		if(text_errorTerm1.getText().length() == 0 && text_errorTerm2.getText().length() == 0) {
			flag = true;
		} else if(text_errorTerm1.getText().length() == 0 && text_errorTerm2.getText().length() != 0) {
			int len = text_errorTerm2.getText().length();
			if(text_errorTerm2.getText().contains(".")) {
				len = len-2;
				String zero = "";
				for(int i=0;i<len;i++) {
					zero += "0";
				}
				Double temp = Double.parseDouble("0."+zero+"5");
				text_errorTerm2.setText(temp+"");
			} else if(text_errorTerm1.getText().contains("E") || text_errorTerm1.getText().contains("e")){
				BigDecimal b = new BigDecimal(text_errorTerm1.getText()+"");
				Double temp = Double.parseDouble(b.toPlainString());
				if(temp<1) {
					len = (temp+"").length()-2;
					String zero = "";
					for(int i=0;i<len;i++) {
						zero += "0";
					}
					temp = Double.parseDouble("0."+zero+"5");
				}else {
					temp = 0.5;
				}
				text_errorTerm1.setText(temp+"");}
			else {
				text_errorTerm1.setText("0.5");
			}
			flag = false;
			setErrorTerm1();
		}else if(text_errorTerm1.getText().length() != 0 && text_errorTerm2.getText().length() == 0) {
			int len = text_errorTerm1.getText().length();
			if(text_errorTerm1.getText().contains(".")) {
				len = len-2;
				String zero = "";
				for(int i=0;i<len;i++) {
					zero += "0";
				}
				Double temp = Double.parseDouble("0."+zero+"5");
				text_errorTerm2.setText(temp+"");
			} else if(text_errorTerm2.getText().contains("E") || text_errorTerm2.getText().contains("e")){
				BigDecimal b = new BigDecimal(text_errorTerm2.getText()+"");
				Double temp = Double.parseDouble(b.toPlainString());
				if(temp<1) {
					len = (temp+"").length()-2;
					String zero = "";
					for(int i=0;i<len;i++) {
						zero += "0";
					}
					temp = Double.parseDouble("0."+zero+"5");
				}else {
					temp = 0.5;
				}
				text_errorTerm2.setText(temp+"");}
			else {
				text_errorTerm2.setText("0.5");
			}
			flag = false;
			setErrorTerm2();
		} else {
			flag = false;
		}
		
	}
	/*********
	 * This method is called when an binary operation button has been pressed. It assesses if there 
	 * are issues with either of the binary operands or they are not defined. If not return false 
	 * (there are no issues)
	 * 
	 * @return	True if there are any issues that should keep the calculator from doing its work.
	 */
	private boolean binaryOperandIssues() {
		setUnit1();
		setUnit2();
		check();
		label_Result.setText("Result");
		label_Result.setTextFill(Color.BLACK);					// Assume no errors
		String errorMessage1 = perform.getOperand1ErrorMessage();	// Fetch the error messages, if
		String errorMessage2 = perform.getOperand2ErrorMessage();   // any, from the two operands
		if (errorMessage1.length() > 0) {						// Check the first.  If not empty
			//label_errOperand1.setText(errorMessage1);			// there's an error, so display it.
			if (errorMessage2.length() > 0) {					// Do the same with the 2nd operand
			//	label_errOperand2.setText(errorMessage2);
				return true;									// Return true if both have errors
			}
			else {
				return true;									// Return true if only the first
			}													// has an error
		}
		else if (errorMessage2.length() > 0) {					// No error with the first, so check
			//label_errOperand2.setText(errorMessage2);			// the second operand the same way
			return true;										// Return true if only the 2nd has
		}														// an error.
		
		// If the code reaches here, neither the first nor the second has an error condition. The
		// following code check to see if the operands are defined.
		if (!perform.getOperand1Defined()) {					// Is first operand defined? If not,
			label_errOperand1.setText("No value found");		// it is an issue for this operator
			if (!perform.getOperand2Defined()) {				// Check the second operand. If it
				label_errOperand2.setText("No value found");	// is not defined, two messages 
				return true;									// should be displayed. Signal there
			}													// are issues by returning true.
			return true;
		} else if (!perform.getOperand2Defined()) {				// If the first is defined, check the
			label_errOperand2.setText("No value found");		// second. Both operands must be
			return true;										// defined. Signal there are issues
		} else if (perform.getUnit1() == "-1") {
			label_Unit1_err.setText("Units not selected");
			if (perform.getUnit2() == "-1") {
				label_Unit2_err.setText("Units not selected");}
			return true;
		}else if (perform.getUnit2() == "-1") {
			label_Unit2_err.setText("Units not selected");
			return true;
		}
		
		return false;
	}

	/**********************************************************************************************
	 * This portion of the class defines the actions that take place when the various calculator
	 * buttons (add, subtract, multiply, and divide) are pressed.
	 */

	/**********
	 * This add routine is called when the user pressed the add button. It calls the business logic
	 * class to do the actual work. Notice that this method is really more about doing things with
	 * the user interface. That is, it interacts with the user and delegates all of the computation
	 * work to the business logic class and the other classes that it uses. This class and its 
	 * methods are work with Strings.
	 */
	private void addOperands(){
		// Check to see if both operands are defined and valid
		if (binaryOperandIssues()) 								// If there are issues, return 
			return;												// without doing anything
		
		// If the operands are defined and valid, request the business logic method to do the 
		// addition and return the result as a String. If there is a problem with the actual 
		// computation, an empty string is returned. 
		String theAnswer = perform.addition();					// The business logic does the add
		errorTerm = theAnswer.split(",");
		label_errResult.setText("");						// Reset the result error messages
		//System.out.println("unit error:"+errorTerm[2]);
		if (theAnswer.length() > 0 && !errorTerm[2].equals("-1")) {							// See if a result was returned
			text_Result.setText(errorTerm[0]);						// If so, display it and change the
			//text_errorTermResult.setText(errorTerm[1]);
			if(!flag) {
				text_errorTermResult.setText(errorTerm[1]);
			}
			//System.out.println("unit error inside if:"+errorTerm[2]);
			text_ResultUnit.setText(errorTerm[2]);
			label_Result.setText("Sum");						// title of the field to "Sum"
		}
		else {													// There is no result.
			text_Result.setText("");							// Do not display a result.				
			label_Result.setText("Result");						// Reset the result label.
			label_errResult.setText(perform.getResultErrorMessage());	// Display error message.
		}
	}

	/**********
	 * This is the subtract routine
	 * 
	 */
	private void subOperands(){
		if (binaryOperandIssues()) 								// If there are issues, return 
			return;	
		String theAnswer = perform.subtraction();
		errorTerm = theAnswer.split(",");
		label_errResult.setText("");							// Reset the result error messages
		if (theAnswer.length() > 0  && !errorTerm[2].equals("-1")) {							// See if a result was returned
			text_Result.setText(errorTerm[0]);						// If so, display it and change the
			if(!flag) {
				text_errorTermResult.setText(errorTerm[1]);
			}
			text_ResultUnit.setText(errorTerm[2]);
			label_Result.setText("Subtraction");						
		}
		else {													
			text_Result.setText("");							// Do not display a result.				
			label_Result.setText("Result");						// Reset the result label.
			label_errResult.setText(perform.getResultErrorMessage());	// Display error message.
		}
	}

	/**********
	 * This is the multiply routine
	 * 
	 */
	private void mpyOperands(){
		if (binaryOperandIssues()) 								// If there are issues, return 
			return;	
		String theAnswer = perform.multiplication();
		errorTerm = theAnswer.split(",");
		label_errResult.setText("");							// Reset the result error messages
		if (theAnswer.length() > 0 && !errorTerm[2].equals("-1")) {							// See if a result was returned
			text_Result.setText(errorTerm[0]);						// If so, display it and change the
			if(!flag) {
				text_errorTermResult.setText(errorTerm[1]);
			}
			text_ResultUnit.setText(errorTerm[2]);
			label_Result.setText("Multiplication");						
		}
		else {													
			text_Result.setText("");							// Do not display a result.				
			label_Result.setText("Result");						// Reset the result label.
			label_errResult.setText(perform.getResultErrorMessage());	// Display error message.
		}			
	}

	/**********
	 * This is the divide routine.  If the divisor is zero, the divisor is declared to be invalid.
	 * 
	 */
	private void divOperands(){
		if (binaryOperandIssues()) 								// If there are issues, return 
			return;	
		String theAnswer = perform.division();	
		errorTerm = theAnswer.split(",");
		label_errResult.setText("");							// Reset the result error messages
		if (theAnswer.length() > 0  && !errorTerm[2].equals("-1")) {							// See if a result was returned
			text_Result.setText(errorTerm[0]);						// If so, display it and change the
			if(!flag) {
				text_errorTermResult.setText(errorTerm[1]);
			}		
			text_ResultUnit.setText(errorTerm[2]);
			label_Result.setText("Division");						
		}
		else {													
			text_Result.setText("");							// Do not display a result.				
			label_Result.setText("Result");						// Reset the result label.
			label_errResult.setText(perform.getResultErrorMessage());	// Display error message.
		}		
	}
	
	private void SqrtOperands(){
		label_Result.setText("Result");
		label_Result.setTextFill(Color.BLACK);					// Assume no errors
		String errorMessage1 = perform.getOperand1ErrorMessage();	// Fetch the error messages, if
		if (!perform.getOperand1Defined()) {					// Is first operand defined? If not,
			label_errOperand1.setText("No value found");	
			return ;
		}
		String theAnswer = perform.squareRoot();
		errorTerm = theAnswer.split(",");
		label_errResult.setText("");							// Reset the result error messages
		if (theAnswer.length() > 0  && !errorTerm[2].equals("-1")) {							// See if a result was returned
			text_Result.setText(errorTerm[0]);						// If so, display it and change the
			if(!flag) {
				text_errorTermResult.setText(errorTerm[1]);
			}	
			text_ResultUnit.setText(errorTerm[2]);
			label_Result.setText("Square root");						
		}
		else {													
			text_Result.setText("");							// Do not display a result.				
			label_Result.setText("Result");						// Reset the result label.
			label_errResult.setText(perform.getResultErrorMessage());	// Display error message.
		}		
	}
}
