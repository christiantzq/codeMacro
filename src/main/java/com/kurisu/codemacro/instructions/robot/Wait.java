package com.kurisu.codemacro.instructions.robot;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperandException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.instructions.Instruction;
import com.kurisu.codemacro.operations.Operand;

public class Wait implements Instruction {
    private int timeMillies;

    public Wait(Double timeSeconds) {
        Double millies = (timeSeconds * 1000);
        this.timeMillies = millies.intValue();
    }

    public Wait(Integer timeSeconds) {
        Integer millies = (timeSeconds * 1000);
        this.timeMillies = millies;
    }

    public Wait(Operand operation) throws OperationException, InvalidOperandException, InstructionException {
        Object result = operation.getValue();
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

    @Override
    public void execute() throws OperationException, InvalidOperandException, InstructionException {
        CodeBot.getBot().delay(timeMillies);
    }

}
