package com.kurisu.codemacro.operations.operands;

/**
 * Interface for wrapper classes for basic Operation types like
 * Integer, Double, Boolean and String
 */
public interface Operand {
    Object getValue();

    String getValueAsString();
}
