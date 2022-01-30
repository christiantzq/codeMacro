package com.kurisu.codemacro.instructions.codeblocks;

import java.util.HashMap;
import java.util.Map;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperandException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.instructions.Instruction;
import com.kurisu.codemacro.operations.Operand;

public class Function extends CodeBlock {
    private static Map<String, Object> globalHeap = new HashMap<>();

    private String functionName;
    private Map<String, Object> heap;
    private Operand returnValue;
    private String[] paramNames;

    public Function(String name, final String[] paramNames) {
        super();
        heap = new HashMap<>();
        this.functionName = name;
        this.paramNames = paramNames; // Length must match values at runtime
    }

    public void run(final Object[] paramValues)
            throws InstructionException, OperationException, InvalidOperandException {
        heap = new HashMap<>(); // Clean the slate
        setupParameters(paramValues);
        for (Instruction instruction : instructions) {
            instruction.execute();
        }
    }

    private void setupParameters(final Object[] paramValues) throws InstructionException {
        if (paramValues.length != paramNames.length) {
            throw new InstructionException(
                    "The number of parameters values provided do not match the definition in [" + functionName
                            + "] function.");
        }
        for (int i = 0; i < paramValues.length; i++) {
            String name = paramNames[i];
            Object value = paramValues[i];
            assignVariable(name, value);
        }
    }

    // public void declareLocalVariable(String name, Object value) throws
    // InstructionException {
    // if (heap.containsKey(name)) {
    // throw new InstructionException(
    // "Variable [" + name + "] is already declared inside [" + functionName + "]
    // function.");
    // }
    // heap.put(name, value);
    // }

    public void assignVariable(String name, Object value) throws InstructionException {
        if (!heap.containsKey(name) && globalHeap.containsKey(name)) {
            throw new InstructionException("[" + name + "] is declared as Global Constant and cannot be changed.");
        } else {
            heap.put(name, value);
        }
    }

    public static void declareGlobalConstant(String name, Object value) throws InstructionException {
        if (globalHeap.containsKey(name)) {
            throw new InstructionException(
                    "The Global Constant [" + name + "] is already declared and cannot be changed.");
        } else {
            globalHeap.put(name, value);
        }
    }

    public Object readVariable(String name) throws InstructionException {
        if (!heap.containsKey(name)) {
            if (!globalHeap.containsKey(name)) {
                throw new InstructionException("Variable [" + name + "] is not declared.");
            } else {
                return globalHeap.get(name);
            }
        } else {
            return heap.get(name);
        }
    }

    public Operand getReturnValue() {
        return returnValue;
    }

}
