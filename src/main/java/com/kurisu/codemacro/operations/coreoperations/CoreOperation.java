package com.kurisu.codemacro.operations.coreoperations;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperationComponentException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.operations.operands.Operand;

public interface CoreOperation {

    Operand solve() throws InvalidOperationComponentException, OperationException, InstructionException;

}
