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
	private Map<String, Valuable<?>> variables;
	private List<Instruction> instructions;

	public Function() {
		variables = new HashMap<>();
		instructions = new ArrayList<>();
	}

	public void mapVariable(String name, Valuable<?> value) {
		variables.put(name, value);
	}

	public Valuable<?> readVariable(String name) {
		return variables.get(name);
	}

	public void run() {
		instructions.stream().forEach(Instruction::execute);
	}

	public void addInstruction(Instruction instruction) {
		this.instructions.add(instruction);
	}

}

interface Instruction {
	void execute();
}

class Keystroke implements Instruction {
	private int keycode;

	public Keystroke(int keycode) {
		this.keycode = keycode;
	}

	@Override
	public void execute() {
		System.out.println("Robot -> keystroke-keycode : " + keycode);
	}

}

class AssignVar implements Instruction {
	private Function containerFunction;
	private String name;
	private Valuable<?> value;

	public AssignVar(Function containerFunction, String name, Valuable<?> value) {
		this.containerFunction = containerFunction;
		this.name = name;
		this.value = value;
	}

	@Override
	public void execute() {
		this.containerFunction.mapVariable(this.name, this.value);
		System.out.println("Assigned Variable " + this.name + " -> " + this.value.getValue());
	}

}

class ReadVar {

	public static String asString(Function containerFunction, String varName) {
		Valuable<?> value = containerFunction.readVariable(varName);

		return value != null ? value.getValue() + "" : "undefined";
	}
}

class Type implements Instruction {
	private String text;
	private Preconfigurable<String, String> func;

	public Type(Preconfigurable<String, String> func) {
		this.func = func;
	}

	public Type(String text) {
		this.text = text;
	}

	@Override
	public void execute() {
		String text2 = func.setup(arg0);
		System.out.println("Robot -> type : " + text);
	}

}

interface Preconfigurable<T, R> {
	public abstract R setup(T arg0);
}

class TypeFromFunction implements Instruction {

	private Function func;
	private String varName;

	public TypeFromFunction(Function func, String varName) {
		this.func = func;
		this.varName = varName;
	}

	@Override
	public void execute() {
		String text = ReadVar.asString(func, varName);
		System.out.println("Robot -> type : " + text);
	}

}

interface Valuable<T> {
	T getValue();
}

class IntegerVal implements Valuable<Integer> {

	private Integer value;

	public IntegerVal(Integer value) {
		this.value = value;
	}

	@Override
	public Integer getValue() {
		return this.value;
	}

}

class StringVal implements Valuable<String> {

	private String value;

	public StringVal(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return this.value;
	}

}

class DoubleVal implements Valuable<Double> {

	private Double value;

	public DoubleVal(Double value) {
		this.value = value;
	}

	@Override
	public Double getValue() {
		return this.value;
	}

}

class ValueEvaluator {
	public static Valuable<?> evaluate(int value) {
		return new IntegerVal(value);
	}

	public static Valuable<?> evaluate(String value) {
		return new StringVal(value);
	}

	public static Valuable<?> evaluate(Double value) {
		return new DoubleVal(value);
	}
}

/*
 * 
 * windows + r // Robot keystrokes
 * type "notepad" // Robot Type hardcoded string
 * enter // Robot keystroke
 * wait 1.5 // Robot delay One and a half second
 * $myInt = 5 // Store variable from hardcoded value
 * $myInt2 = $myInt // Read Variable -> Store Variable
 * $myInt3 = $myInt + 7 // Read variable -> convert to operator -> perform
 * operation -> return value
 * type $myInt3 // Read variable -> Robot Type result text
 * $salute = myFunc('chris') // call function -> Store variable from result
 * type $salute //
 * 
 * if true then // reduce operation to boolean
 * 
 * func myFunc(name) // Create function with predefined variables
 * return 'hello, '' + name // Read variable, Perform operation -> return
 * function
 * end func // delimiter for parser
 * 
 * 
 * 
 * 1. A bunch of instructions
 * 2. Instructions could or could not return a value
 * 3.
 * 
 * 
 */
