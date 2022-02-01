package com.kurisu.codemacro.instructions;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperationComponentException;
import com.kurisu.codemacro.exceptions.OperationException;

/**
 * This interface defines types that can be called using a Function.
 */
public interface Instruction {
    void execute() throws OperationException, InvalidOperationComponentException, InstructionException;
}
