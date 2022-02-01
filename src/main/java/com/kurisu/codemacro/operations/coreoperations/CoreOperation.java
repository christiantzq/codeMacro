package com.kurisu.codemacro.operations.coreoperations;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperationComponentException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.operations.operands.Operand;

/**
 * This interface is ment for simple calculations between two operands.
 * (a + b) or
 * (a - b) or
 * (a / b) etc
 */
public interface CoreOperation {
    Operand solve() throws InvalidOperationComponentException, OperationException, InstructionException;
}
