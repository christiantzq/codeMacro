package com.kurisu.codemacro.instructions;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperationComponentException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.instructions.codeblocks.Function;
import com.kurisu.codemacro.operations.Operation;
import com.kurisu.codemacro.operations.operands.Operand;

public class VariableUpdate implements Instruction {

    private Function containerFunction;
    private String varName;
    private Operand value;
    private Operation operation;

    public VariableUpdate(Function containerFunction, String varName, Operation operation) {
        this.containerFunction = containerFunction;
        this.varName = varName;
        this.operation = operation;
    }

    public VariableUpdate(Function containerFunction, String varName, Operand operand) {
        this.containerFunction = containerFunction;
        this.varName = varName;
        this.value = operand;
    }

    @Override
    public void execute() throws OperationException, InvalidOperationComponentException, InstructionException {
        if (operation != null) {
            value = operation.getOperand();
        }
        containerFunction.assignVariable(varName, value);
    }

}
