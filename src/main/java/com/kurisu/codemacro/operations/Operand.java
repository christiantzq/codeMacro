package com.kurisu.codemacro.operations;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperandException;
import com.kurisu.codemacro.exceptions.OperationException;

/**
 * Operand: any object that is capable of being manipulated.
 */
public interface Operand {
    Object getValue() throws OperationException, InvalidOperandException, InstructionException;
    String getValueAsString();
}
