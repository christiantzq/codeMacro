package com.kurisu.codemacro.instructions.robot;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperandException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.instructions.Instruction;
import com.kurisu.codemacro.operations.Operation;

public class TextTyping implements Instruction {
    private String text;
    private Operation operation;

    public TextTyping(String text) {
        this.text = text;
    }

    public TextTyping(Operation operation) {
        this.operation = operation;
    }

    @Override
    public void execute() throws InstructionException, OperationException, InvalidOperandException {
        if (operation != null) {
            text = getTextFromOperation();
        }
        LegacyTypingHelper typer = new LegacyTypingHelper();
        typer.type(text);
    }

    private String getTextFromOperation() throws OperationException, InvalidOperandException, InstructionException {
        Object result = operation.getResult();
        if (result instanceof Integer)
            return "" + ((Integer) result).toString();
        else if (result instanceof Double)
            return "" + ((Double) result).toString();
        else if (result instanceof Boolean)
            return "" + ((Boolean) result).toString();
        else if (result instanceof String)
            return ((String) result);
        else
            throw new InstructionException("Type not supported text typing instruction.");
    }

}
