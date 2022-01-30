package com.kurisu.codemacro.instructions;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperandException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.instructions.codeblocks.Function;

public class GlobalConstantDeclaration implements Instruction {
    private String name;
    private Object value;

    public GlobalConstantDeclaration(final String name, final Object value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public void execute() throws OperationException, InvalidOperandException, InstructionException {
        Function.declareGlobalConstant(name, value);
    }

}
