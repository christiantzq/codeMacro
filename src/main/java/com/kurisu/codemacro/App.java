package com.kurisu.codemacro;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.kurisu.codemacro.exceptions.BadSyntaxException;
import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperandException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.instructions.VariableUpdate;
import com.kurisu.codemacro.instructions.codeblocks.Function;
import com.kurisu.codemacro.instructions.robot.Keystrokes;
import com.kurisu.codemacro.instructions.robot.TextTyping;
import com.kurisu.codemacro.instructions.robot.Wait;
import com.kurisu.codemacro.interpreter.KeystrokeEvents;
import com.kurisu.codemacro.operations.DoubleOperand;
import com.kurisu.codemacro.operations.IntegerOperand;
import com.kurisu.codemacro.operations.Operand;
import com.kurisu.codemacro.operations.Operation;
import com.kurisu.codemacro.operations.StringOperand;
import com.kurisu.codemacro.operations.VarValueOperand;
import com.kurisu.codemacro.operations.coreoperations.OperationType;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		//SpringApplication.run(App.class, args);

		try {
			Function main = new Function("main", new String[0]);
			Operation op = new Operation(new IntegerOperand(1));
			main.addInstruction(new Wait(op));
			// int[] keys = { key("alt"), key("tab") };
			// main.addInstruction(new Keystrokes(keys));
			main.addInstruction(new Wait(1));
			int[] keys2 = { key("windows"), key("R") };
			main.addInstruction(new Keystrokes(keys2));
			main.addInstruction(new Wait(1.2));
			main.addInstruction(new TextTyping("notepad"));
			main.addInstruction(new Wait(0.5));
			int[] keys3 = { key("enter") };
			main.addInstruction(new Keystrokes(keys3));
			main.addInstruction(new Wait(2));
			main.addInstruction(new VariableUpdate(main, "x1", new IntegerOperand(5)));

			Operation op2 = new Operation(new StringOperand("The result of the sum is: "));
			Operation op3 = new Operation(new VarValueOperand(main, "x1"));
			op3.addOperand(OperationType.ADD, new DoubleOperand(3.5));
			op2.addOperand(OperationType.ADD, op3);
			main.addInstruction(new TextTyping(op2));

			// add two operations in one operation			
			main.addInstruction(new Keystrokes(keys3));
			Operation opA = new Operation(new IntegerOperand(5));
			opA.addOperand(OperationType.MULTIPLY, new IntegerOperand(5));

			Operation opB = new Operation(new IntegerOperand(3));
			opB.addOperand(OperationType.ADD, new IntegerOperand(2));

			Operation opC = new Operation(opA);
			opC.addOperand(OperationType.ADD, opB);

			Operation opD = new Operation(new StringOperand("result : "));
			opD.addOperand(OperationType.ADD, opC);
			
			main.addInstruction(new TextTyping(opD));

			
			main.run(new Object[0]);

			System.out.println("Finished.");

			// javax.swing.SwingUtilities.invokeLater(new Runnable() {
			// 	public void run() {
			// 		createAndShowGUI();
			// 	}
			// });


		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

	private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);

        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


	// Helper method
	private static int key(String key) throws BadSyntaxException {
		return KeystrokeEvents.getKeyEvent(key);
	}

}

/*
 * 
 * wait(1) // Robot instruction
 * alt + tab // Modiffier + key
 * wait(1)
 * windows + R // Modiffier + key
 * wait(0.5)
 * type("notepad") // Robot instruction
 * enter // Reserved Key (not alphanumeric)
 * wait(2)
 * var x = 5 // Local Variable declaration
 * x = x + 1 // Local variable modification (must be already declared)
 * global y = 5 // Global Variable declaration
 * y = y - 1 // Global Variable modification
 * type("The result of the sum is: " + (x + y))
 * 
 */

// TODO corre el script a ver si muy muy!!!