package com.kurisu.codemacro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/*

Function.run // runs all instructions
	Instructions.execute // reduce all its operations
		Operations.reduce by
				1. Define operands qty (Valuables array)
				2. run Functions operands and save its Valuable
				3. load Variable operand and save its Valuable
				4. Determine operations (opQty -1, i.e. 1 + 2 / 3: 3 operands, 2 operations)
				5. Do operations in hierarquical * / - +
					5.1 + with one String operand is last
				6. Execute terminal operation using result



			Operand Types
				- Function
				- String
				- Integer
				- Double
				- Variable




*/


@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {

		Function mainFunc = new Function();
		mainFunc.addInstruction(new Keystroke(69));
		mainFunc.addInstruction(new AssignVar(mainFunc, "$myInt", ValueEvaluator.evaluate(5)));
		mainFunc.addInstruction(new Type( ("$myInt") -> 5 + ""));

		mainFunc.run();

	}

	private String input = "enter" +
			"$myInt = 5" +
			"type $myInt" +
			"$salute = myFunc('chris')" +
			"type $salute" +
			"" +
			"func myFunc(name)" +
			"  return 'hello, '' + name" +
			"end func";

}


class Function {
	private List<Instruction> instructions;
	private 

	public Function(){
		instructions = new ArrayList<>();
	}

	public void addInstruction(Instruction instruction){
		instructions.add(instruction);
	}
}

interface Instruction {
	void execute();
}

class Operation

interface Operator<R> {
	R reduce();
}

// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - VALUES

interface Value<T> {
	T getValue();
}

class IntegerValue implements Value<Integer> {

	public IntegerValue

	@Override
	public Integer getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}