package com.kurisu.codemacro.operations.operands;

/**
 * Wrapper class for basic operation types like
 * Integer, Double, Boolean and String
 */
public interface Operand {
    Object getValue();

    String getValueAsString();
}
