package com.kurisu.codemacro.operations;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperationComponentException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.instructions.codeblocks.Function;
import com.kurisu.codemacro.operations.operands.Operand;

/**
 * Component of an Operation.
 * It loads a value from the heap at runtime and assigns it to an Operand
 */
public class VariableReaderComponent implements OperationComponent {
    private Function containerFunction;
    private String varName;

    public VariableReaderComponent(Function containerFunction, String varName) {
        this.containerFunction = containerFunction;
        this.varName = varName;
    }

    @Override
    public Operand getOperand() throws OperationException, InvalidOperationComponentException, InstructionException {
        return containerFunction.readVariable(varName);
    }

}
