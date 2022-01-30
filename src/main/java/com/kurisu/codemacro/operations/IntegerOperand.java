package com.kurisu.codemacro.operations;

public class IntegerOperand implements Operand {

    private Integer value;

    public IntegerOperand(Integer value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public String getValueAsString(){
        return "" + value.intValue();
    }

}
