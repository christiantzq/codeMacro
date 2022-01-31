package com.kurisu.codemacro.operations;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperationComponentException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.operations.operands.Operand;

/**
 * Operand: any object that is capable of being manipulated.
 */
public interface OperationComponent {
    Operand getOperand() throws OperationException, InvalidOperationComponentException, InstructionException;
}
