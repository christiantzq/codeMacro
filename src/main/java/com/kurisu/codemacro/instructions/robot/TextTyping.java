package com.kurisu.codemacro.instructions.robot;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperationComponentException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.instructions.Instruction;
import com.kurisu.codemacro.operations.Operation;

/**
 * Maps the script Instruction for Typing a String.
 * 
 * It can receive the hardcoded text or it can be assigned dynamically with an
 * Operation.
 */
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
    public void execute() throws InstructionException, OperationException, InvalidOperationComponentException {
        if (operation != null) {
            text = operation.getOperand().getValueAsString();
        }
        LegacyTypingHelper typer = new LegacyTypingHelper();
        typer.type(text);
    }

}
