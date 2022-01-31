package com.kurisu.codemacro.operations.operands;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperationComponentException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.operations.OperationComponent;

public class IntegerOperand implements OperationComponent, Operand {

    private Integer value;

    public IntegerOperand(Integer value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public String getValueAsString() {
        return "" + value.intValue();
    }

    @Override
    public Operand getOperand() throws OperationException, InvalidOperationComponentException, InstructionException {
        return this;
    }

}
