package com.kurisu.codemacro.instructions;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperationComponentException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.instructions.codeblocks.Function;
import com.kurisu.codemacro.operations.operands.Operand;

public class GlobalConstantDeclaration implements Instruction {
    private String name;
    private Operand value;

    public GlobalConstantDeclaration(final String name, final Operand value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public void execute() throws OperationException, InvalidOperationComponentException, InstructionException {
        Function.declareGlobalConstant(name, value);
    }

}
