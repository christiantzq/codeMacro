package com.kurisu.codemacro.operations;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperationComponentException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.operations.operands.Operand;

/**
 * This interface defines the parts of an Operation.
 * 
 * - Operands (Wrapper for Integer, Double, Boolean and String)
 * - Function Calls
 * - Loading variable's values
 * - Other nested Operations
 */
public interface OperationComponent {
    Operand getOperand() throws OperationException, InvalidOperationComponentException, InstructionException;
}
