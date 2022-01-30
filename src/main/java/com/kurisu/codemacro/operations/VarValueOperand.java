package com.kurisu.codemacro.operations;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperandException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.instructions.codeblocks.Function;

public class VarValueOperand implements Operand {
    private Function containerFunction;
    private String varName;

    public VarValueOperand(Function containerFunction, String varName) {
        this.containerFunction = containerFunction;
        this.varName = varName;
    }

    @Override
    public Object getValue() throws OperationException, InvalidOperandException, InstructionException {
        return containerFunction.readVariable(varName);
    }

    @Override
    public String getValueAsString() {
        try {
            Object object = containerFunction.readVariable(varName);
            if (object instanceof Integer)
                return "" + ((Integer) object).toString();
            else if (object instanceof Double)
                return "" + ((Double) object).toString();
            else if (object instanceof Boolean)
                return "" + ((Boolean) object).toString();
            else if (object instanceof String)
                return ((String) object);
            else
                return "Unable to read value from heap. Type not supported";
        } catch (InstructionException e) {
            return "Unable to read value from heap. " + e.getMessage();
        }
    }

}
