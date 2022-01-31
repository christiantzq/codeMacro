package com.kurisu.codemacro.operations.operands;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperationComponentException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.operations.OperationComponent;

public class DoubleOperand implements OperationComponent, Operand {
    private Double value;

    public DoubleOperand(Double value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public String getValueAsString() {
        return "" + value.toString();
    }

    @Override
    public Operand getOperand() throws OperationException, InvalidOperationComponentException, InstructionException {
        return this;
    }

}
