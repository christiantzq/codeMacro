package com.kurisu.codemacro;

import java.util.HashMap;
import java.util.Map;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperationComponentException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.instructions.codeblocks.Function;
import com.kurisu.codemacro.operations.operands.Operand;

/**
 * Holds the predefined list of Functions and provides a way to execute them by
 * name and params.
 */
public class FunctionManager {
    private static FunctionManager functionManager;
    private Map<String, Function> functions;

    private FunctionManager() {
        functions = new HashMap<>();
    }

    public static FunctionManager getInstance() {
        if (functionManager == null) {
            functionManager = new FunctionManager();
        }
        return functionManager;
    }

    public void declareFunction(String name, Function function) throws InstructionException {
        if (functions.containsKey(name)) {
            throw new InstructionException("The function [" + name + "] is already declared.");
        }
        functions.put(name, function);
    }

    public Function getFunction(String functionName) throws InstructionException {
        if (functions.containsKey(functionName)) {
            return functions.get(functionName);
        } else {
            throw new InstructionException("The function [" + functionName + "] is not declared.");
        }

    }

    public void runFunction(String name, Operand[] paramValues)
            throws InstructionException, OperationException, InvalidOperationComponentException {
        Function func = getFunction(name);
        func.run(paramValues);
    }

}
