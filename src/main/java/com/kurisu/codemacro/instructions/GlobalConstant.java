package com.kurisu.codemacro.instructions;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperationComponentException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.instructions.codeblocks.Function;
import com.kurisu.codemacro.operations.operands.Operand;

/**
 * Maps the script to an Instruction for declaring Global Constants.
 */
public class GlobalConstant implements Instruction {
    private String name;
    private Operand value;

    public GlobalConstant(final String name, final Operand value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public void execute() throws OperationException, InvalidOperationComponentException, InstructionException {
        Function.declareGlobalConstant(name, value);
    }

}
