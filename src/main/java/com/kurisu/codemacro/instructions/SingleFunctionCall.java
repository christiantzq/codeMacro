package com.kurisu.codemacro.instructions;

import com.kurisu.codemacro.FunctionManager;
import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperationComponentException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.operations.operands.Operand;

/**
 * Maps a script Instruction for Function calls
 * - Single calls only.
 * - It does not return any value.
 */
public class SingleFunctionCall implements Instruction {
    private String functioName;
    private Operand[] paramValues;

    public SingleFunctionCall(String functionName, Operand[] paramValues) throws InstructionException {
        this.functioName = functionName;
        this.paramValues = paramValues;
    }

    @Override
    public void execute() throws InstructionException, OperationException, InvalidOperationComponentException {
        FunctionManager.getInstance().runFunction(functioName, paramValues);
    }

}
