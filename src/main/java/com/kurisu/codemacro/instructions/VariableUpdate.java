package com.kurisu.codemacro.instructions;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperandException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.instructions.codeblocks.Function;
import com.kurisu.codemacro.operations.Operand;

public class VariableUpdate implements Instruction {

    private Function containerFunction;
    private String varName;
    private Operand value;

    public VariableUpdate(Function containerFunction, String varName, Operand value) {
        this.containerFunction = containerFunction;
        this.varName = varName;
        this.value = value;
    }

    @Override
    public void execute() throws OperationException, InvalidOperandException, InstructionException {
        containerFunction.assignVariable(varName, value.getValue());
    }

}
