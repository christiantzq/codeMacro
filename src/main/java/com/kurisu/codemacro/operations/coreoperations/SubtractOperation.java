package com.kurisu.codemacro.operations.coreoperations;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperationComponentException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.operations.operands.DoubleOperand;
import com.kurisu.codemacro.operations.operands.IntegerOperand;
import com.kurisu.codemacro.operations.operands.Operand;

/**
 * Subtracs a numeric value from another.
 */
public class SubtractOperation implements CoreOperation {
    private Operand op1;
    private Operand op2;

    public SubtractOperation(Operand op1, Operand op2) {
        this.op1 = op1;
        this.op2 = op2;
    }

    @Override
    public Operand solve() throws InvalidOperationComponentException, OperationException, InstructionException {
        final Object val1 = op1.getValue();
        final Object val2 = op2.getValue();
        if (val1 instanceof Integer && val2 instanceof Integer) {
            return getIntegerValue(val1, val2);
        } else if (val1 instanceof Double && val2 instanceof Double) {
            return getDoubleValue(val1, val2);
        } else if (val1 instanceof Integer && val2 instanceof Double) {
            return getMixedValueIntFirst(val1, val2);
        } else if (val1 instanceof Double && val2 instanceof Integer) {
            return getMixedValueDoubleFirst(val1, val2);
        } else {
            throw new InvalidOperationComponentException(
                    "Cannot subtract " + val1.getClass() + " minus " + val2.getClass());
        }
    }

    private Operand getIntegerValue(final Object val1, final Object val2) throws InvalidOperationComponentException {
        try {
            final Integer value1 = (Integer) val1;
            final Integer value2 = (Integer) val2;
            return new IntegerOperand(value1 - value2);
        } catch (Exception e) {
            throw new InvalidOperationComponentException("Cannot get integer value for subtraction operation.");
        }
    }

    private Operand getDoubleValue(final Object val1, final Object val2) throws InvalidOperationComponentException {
        try {
            final Double value1 = (Double) val1;
            final Double value2 = (Double) val2;
            return new DoubleOperand(value1 - value2);
        } catch (Exception e) {
            throw new InvalidOperationComponentException("Cannot get floating value for subtraction operation.");
        }
    }

    private Operand getMixedValueIntFirst(final Object val1, final Object val2)
            throws InvalidOperationComponentException {
        try {
            final Integer value1 = (Integer) val1;
            final Double value2 = (Double) val2;
            return new DoubleOperand(value1 - value2);
        } catch (Exception e) {
            throw new InvalidOperationComponentException(
                    "Cannot get floating|integer value for subtraction operation.");
        }
    }

    private Operand getMixedValueDoubleFirst(final Object val1, final Object val2)
            throws InvalidOperationComponentException {
        try {
            final Double value1 = (Double) val1;
            final Integer value2 = (Integer) val2;
            return new DoubleOperand(value1 - value2);
        } catch (Exception e) {
            throw new InvalidOperationComponentException(
                    "Cannot get floating|integer value for subtraction operation.");
        }
    }

}
