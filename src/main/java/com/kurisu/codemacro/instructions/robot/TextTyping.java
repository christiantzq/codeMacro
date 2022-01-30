package com.kurisu.codemacro.instructions.robot;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperandException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.instructions.Instruction;
import com.kurisu.codemacro.operations.Operand;

public class TextTyping implements Instruction {
    private String text;

    public TextTyping(String text) {
        this.text = text;
    }

    public TextTyping(Operand operation) throws OperationException, InvalidOperandException, InstructionException {
        Object object = operation.getValue();
        if (object instanceof Integer)
            this.text = "" + ((Integer) object).toString();
        else if (object instanceof Double)
            this.text = "" + ((Double) object).toString();
        else if (object instanceof Boolean)
            this.text = "" + ((Boolean) object).toString();
        else if (object instanceof String)
            this.text = ((String) object);
        else
            throw new InstructionException("Type not supported in solved operation.");
    }

    @Override
    public void execute() throws InstructionException {
        LegacyTypingHelper typer = new LegacyTypingHelper();
        typer.type(text);
    }

}
