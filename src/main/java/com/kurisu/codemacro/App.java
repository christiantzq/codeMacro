package com.kurisu.codemacro;

import com.kurisu.codemacro.exceptions.BadSyntaxException;
import com.kurisu.codemacro.instructions.codeblocks.Function;
import com.kurisu.codemacro.instructions.robot.Keystrokes;
import com.kurisu.codemacro.instructions.robot.TextTyping;
import com.kurisu.codemacro.instructions.robot.Wait;
import com.kurisu.codemacro.interpreter.KeystrokeEvents;
import com.kurisu.codemacro.operations.IntegerOperand;
import com.kurisu.codemacro.operations.Operation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

		try {
			Function main = new Function("main", new String[0]);
			Operation op = new Operation(new IntegerOperand(1));
			main.addInstruction(new Wait(op));
			int[] keys = { key("alt"), key("tab") };
			main.addInstruction(new Keystrokes(keys));
			main.addInstruction(new Wait(1));
			int[] keys2 = { key("windows"), key("R") };
			main.addInstruction(new Keystrokes(keys2));
			main.addInstruction(new Wait(0.5));			
			main.addInstruction(new TextTyping("notepad"));
			int[] keys3 = { key("enter") };
			main.addInstruction(new Keystrokes(keys3));

			main.run(new Object[0]);


		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static int key(String key) throws BadSyntaxException {
		return KeystrokeEvents.getKeyEvent("key");
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