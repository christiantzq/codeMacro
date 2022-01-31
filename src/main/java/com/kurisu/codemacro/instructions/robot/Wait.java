package com.kurisu.codemacro.instructions.robot;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperandException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.instructions.Instruction;
import com.kurisu.codemacro.operations.Operation;

public class Wait implements Instruction {
    private int timeMillies;
    private Operation operation;

    public Wait(Double timeSeconds) {
        Double millies = (timeSeconds * 1000);
        this.timeMillies = millies.intValue();
    }

    public Wait(Integer timeSeconds) {
        Integer millies = (timeSeconds * 1000);
        this.timeMillies = millies;
    }

    public Wait(Operation operation) {
        this.operation = operation;
    }

    @Override
    public void execute() throws OperationException, InvalidOperandException, InstructionException {
        if (operation != null) {
            processOperation();
        }

        System.out.println("-- milliseconds: " + timeMillies);
        CodeBot.getBot().delay(timeMillies);
    }

    private void processOperation() throws OperationException, InvalidOperandException, InstructionException {
        Object result = operation.getResult();
        if (result instanceof Integer) {
            Integer millies = (Integer) result;
            this.timeMillies = millies * 1000;
        } else if (result instanceof Double) {
            Double millies = (Double) (result);
            this.timeMillies = (millies.intValue()) * 1000;
        } else {
            throw new InstructionException("Operation inside [wait] does not return a numeric value.");
        }
    }

}
