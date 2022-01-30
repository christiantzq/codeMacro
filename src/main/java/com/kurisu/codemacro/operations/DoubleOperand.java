package com.kurisu.codemacro.operations;

public class DoubleOperand implements Operand {
    private Double value;

    public DoubleOperand(Double value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public String getValueAsString(){
        return "" + value.toString();
    }

}
