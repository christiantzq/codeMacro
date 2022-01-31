package com.kurisu.codemacro.instructions;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperationComponentException;
import com.kurisu.codemacro.exceptions.OperationException;

public interface Instruction {
    void execute() throws OperationException, InvalidOperationComponentException, InstructionException;
}
