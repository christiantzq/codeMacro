package com.kurisu.codemacro.operations.coreoperations;

public enum OperationType {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    REMINDER("%"),
    CONCAT("strcat"),
    AND("&&"),
    OR("||"),
    EQUALS("=="),
    GREAT_THAN(">"),
    LESS_THAN("<"),
    GREAT_EQ_THAN(">="),
    LESS_EQ_THAN("<=");    

    private String opStr;

    private OperationType(String opStr){
        this.opStr = opStr;
    }

    public String toString(){
        return opStr;
    }
}
