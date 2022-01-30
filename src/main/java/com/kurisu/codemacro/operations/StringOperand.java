package com.kurisu.codemacro.operations;

/**
 * Wraps a String.
 * Returns a String.
 */
public class StringOperand implements Operand {
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

}
