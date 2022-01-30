package com.kurisu.codemacro.instructions;

import com.kurisu.codemacro.FunctionManager;
import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperandException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.operations.Operand;

/**
 * Single calls only.
 * It does not return any value
 */
public class SingleFunctionCall implements Instruction {
    private String functioName;
    private Operand[] processableParameters;

    public SingleFunctionCall(String functionName, Operand[] processableParameters) throws InstructionException {
        this.functioName = functionName;
        this.processableParameters = processableParameters;
    }

    @Override
    public void execute() throws InstructionException, OperationException, InvalidOperandException {

        Object[] paramValues = new Object[processableParameters.length];
        for (int i = 0; i < paramValues.length; i++) {
            paramValues[i] = processableParameters[i].getValue();
        }

        FunctionManager.getInstance().runFunction(functioName, paramValues);
    }

}
