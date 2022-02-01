package com.kurisu.codemacro;

import com.kurisu.codemacro.exceptions.BadSyntaxException;
import com.kurisu.codemacro.instructions.GlobalConstant;
import com.kurisu.codemacro.instructions.SingleFunctionCall;
import com.kurisu.codemacro.instructions.VariableUpdate;
import com.kurisu.codemacro.instructions.codeblocks.Function;
import com.kurisu.codemacro.instructions.robot.Keystrokes;
import com.kurisu.codemacro.instructions.robot.TextTyping;
import com.kurisu.codemacro.instructions.robot.Wait;
import com.kurisu.codemacro.interpreter.KeystrokeEvents;
import com.kurisu.codemacro.operations.Operation;
import com.kurisu.codemacro.operations.VariableReaderComponent;
import com.kurisu.codemacro.operations.coreoperations.OperationType;
import com.kurisu.codemacro.operations.operands.DoubleOperand;
import com.kurisu.codemacro.operations.operands.IntegerOperand;
import com.kurisu.codemacro.operations.operands.Operand;
import com.kurisu.codemacro.operations.operands.StringOperand;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		// SpringApplication.run(App.class, args);

		try {
			int[] enter = { key("enter") };

			// Predefined Functions
			Function myFunc = new Function("myFunc", new String[0]);
			myFunc.addInstruction(new Keystrokes(enter));
			myFunc.addInstruction(new TextTyping("msg from inside my function"));
			FunctionManager.getInstance().declareFunction(myFunc);

			// Main Function
			Function main = new Function("main", new String[0]);
			Operation op = new Operation(new IntegerOperand(1));
			main.addInstruction(new Wait(op));
			int[] keys2 = { key("windows"), key("R") };
			main.addInstruction(new Keystrokes(keys2));
			main.addInstruction(new Wait(1.2));
			main.addInstruction(new TextTyping("notepad"));
			main.addInstruction(new Wait(0.5));
			main.addInstruction(new Keystrokes(enter));
			main.addInstruction(new Wait(2));
			main.addInstruction(new VariableUpdate(main, "x1", new IntegerOperand(5)));
			main.addInstruction(new GlobalConstant("g1", new IntegerOperand(7)));
			Operation op2 = new Operation(new StringOperand("The result of the sum is: "));
			Operation op3 = new Operation(new VariableReaderComponent(main, "x1"));
			op3.addComponent(OperationType.ADD, new VariableReaderComponent(main, "g1"));
			op2.addComponent(OperationType.ADD, op3);
			main.addInstruction(new TextTyping(op2));

			// Pre defined Function call
			main.addInstruction(new SingleFunctionCall("myFunc", new Operand[0]));

			// add two operations in one operation
			main.addInstruction(new Keystrokes(enter));
			Operation opA = new Operation(new IntegerOperand(5));
			opA.addComponent(OperationType.MULTIPLY, new IntegerOperand(5));

			Operation opB = new Operation(new IntegerOperand(3));
			opB.addComponent(OperationType.ADD, new IntegerOperand(2));

			Operation opC = new Operation(opA);
			opC.addComponent(OperationType.ADD, opB);

			Operation opD = new Operation(new StringOperand("result : "));
			opD.addComponent(OperationType.ADD, opC);

			main.addInstruction(new TextTyping(opD));

			// RUN
			main.run(new Operand[0]);
			System.out.println("Finished.");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Helper method
	private static int key(String key) throws BadSyntaxException {
		return KeystrokeEvents.getKeyEvent(key);
	}

}

/*
 * 
 * wait(1) // Robot instruction with operation (Integer)
 * alt + tab // Modiffier + key
 * wait 1 // Robot instruction with value
 * windows + R // Modiffier + key
 * wait(0.5)
 * type("notepad") // Robot instruction
 * enter // Keystroke (Reserved Key) (not alphanumeric)
 * wait(2)
 * x1 = 5 // Local Variable declaration
 * x1 = x1 + 1 // Local variable modification (must be already declared)
 * global y1 = 5 // Global Variable declaration
 * y1 = 5 // Global Variable modification NOT ALLOWED!
 * type("The result of the sum is: " + (x + y))
 * myFunc()
 * type("result : " + ( (5*5) + (3+2) ) )
 * 
 * fun myFunc(){
 *   type "inside my function"
 * }
 * 
 */