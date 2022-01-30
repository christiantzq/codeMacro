package com.kurisu.codemacro.operations.coreoperations;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperandException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.operations.DoubleOperand;
import com.kurisu.codemacro.operations.Operand;

/**
 * Multiplies two given numeric values. Always returns a Double.
 */
public class MultiplyOperation implements CoreOperation {
    private Operand op1;
    private Operand op2;

    public MultiplyOperation(final Operand op1, final Operand op2) {
        this.op1 = op1;
        this.op2 = op2;
    }

    @Override
    public Operand solve() throws InvalidOperandException, OperationException, InstructionException {
        final Object val1 = op1.getValue();
        final Object val2 = op2.getValue();

        Integer val1Integer = 1;
        Double val1Double = 1.0;
        Integer val2Integer = 1;
        Double val2Double = 1.0;

        if (val1 instanceof Integer) {
            val1Integer = (Integer) val1;
        } else if (val1 instanceof Double) {
            val1Double = (Double) val1;
        } else {
            throw new InvalidOperandException("Multiplication first operand is not valid.");
        }

        if (val2 instanceof Integer) {
            val2Integer = (Integer) val2;
        } else if (val2 instanceof Double) {
            val1Double = (Double) val2;
        } else {
            throw new InvalidOperandException("Multiplication second operand is not valid.");
        }

        Operand result = new DoubleOperand(val1Integer * val1Double * val2Integer * val2Double);
        return result;
    }

}
