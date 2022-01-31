package com.kurisu.codemacro.operations.operands;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperationComponentException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.operations.OperationComponent;

/**
 * Wraps a String.
 * Returns a String.
 */
public class StringOperand implements OperationComponent, Operand {
    String value;

    public StringOperand(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public String getValueAsString(){
        return value;
    }

    @Override
    public Operand getOperand() throws OperationException, InvalidOperationComponentException, InstructionException {        
        return this;
    }

}
