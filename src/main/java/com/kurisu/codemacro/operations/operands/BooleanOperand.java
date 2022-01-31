package com.kurisu.codemacro.operations.operands;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperationComponentException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.operations.OperationComponent;

public class BooleanOperand implements OperationComponent, Operand {

    Boolean value;

    public BooleanOperand(Boolean value) {
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return value;
    }
    
    @Override
    public String getValueAsString(){
        return "" + value.toString();
    }

    @Override
    public Operand getOperand() throws OperationException, InvalidOperationComponentException, InstructionException {        
        return this;
    }

}
