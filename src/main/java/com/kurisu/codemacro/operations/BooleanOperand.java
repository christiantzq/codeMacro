package com.kurisu.codemacro.operations;

public class BooleanOperand implements Operand {

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

}
