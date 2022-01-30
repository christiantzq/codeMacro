package com.kurisu.codemacro.operations;

import java.util.LinkedList;
import java.util.List;

import com.kurisu.codemacro.exceptions.InvalidOperandException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.operations.coreoperations.AddOperation;
import com.kurisu.codemacro.operations.coreoperations.CoreOperation;
import com.kurisu.codemacro.operations.coreoperations.DivideOperation;
import com.kurisu.codemacro.operations.coreoperations.MultiplyOperation;
import com.kurisu.codemacro.operations.coreoperations.OperationType;
import com.kurisu.codemacro.operations.coreoperations.SubtractOperation;

import static com.kurisu.codemacro.operations.coreoperations.OperationType.DIVIDE;
import static com.kurisu.codemacro.operations.coreoperations.OperationType.MULTIPLY;
import static com.kurisu.codemacro.operations.coreoperations.OperationType.SUBTRACT;
import static com.kurisu.codemacro.operations.coreoperations.OperationType.ADD;

/**
 * Operand: Object which value affects an operation's return value.
 * Operator: Interacion type between operands.
 */
public class Operation implements Operand {
    private List<Operand> operands;
    private List<OperationType> operators;

    public Operation(Operand firstOperand) {
        operands = new LinkedList<>();
        operators = new LinkedList<>();
        operands.add(firstOperand);
    }

    public void addOperand(OperationType operationType, Operand newOperand) {
        operators.add(operationType);
        operands.add(newOperand);
    }

    /**
     * Reduces all operations into a single Operand (the result)
     * 
     * @throws InvalidOperandException
     */
    @Override
    public Operand getValue() throws OperationException, InvalidOperandException {
        // 1. Reduce sub-operations and exec funcTionCalls and get var values

        solveOperationsOfType(DIVIDE);
        solveOperationsOfType(MULTIPLY);
        solveOperationsOfType(SUBTRACT);
        solveOperationsOfType(ADD); // Also concats
        
        // 5. Logic > / < / == / != / || / && / >= / <=

        if (operands.size() > 1) {
            throw new OperationException("Operation did not completed succesfully (unable to reduce).");
        } else {
            return operands.get(0);
        }
    }

    public Operand getResult() throws OperationException, InvalidOperandException {
        return getValue();
    }

    @Override
    public String getValueAsString() {
        int operandIndex = 1;
        int operatorIndex = 0;
        StringBuilder str = new StringBuilder("(" + operands.get(0).getValueAsString());
        while (operatorIndex < operators.size()) {
            str.append(
                    " " + operators.get(operatorIndex).toString() + " "
                            + operands.get(operandIndex).getValueAsString());
            operandIndex++;
            operatorIndex++;
        }
        return str.toString() + ")";
    }

    private void solveOperationsOfType(OperationType targetOperation)
            throws InvalidOperandException, OperationException {
        int loopCounter = 0;
        int leftValueIndex = 0;
        int operationIndex = 0;
        int totalOperations = operators.size();
        while (operationIndex < totalOperations) {
            final Operand op1 = operands.get(leftValueIndex);
            final OperationType currentOperation = operators.get(operationIndex);
            final Operand op2 = operands.get(leftValueIndex + 1); // right value index
            if (currentOperation == targetOperation) {
                CoreOperation coreOperation = getRequestedOperation(targetOperation, op1, op2);
                solveCoreOperation(coreOperation, leftValueIndex, operationIndex);
                totalOperations--;
            } else {
                leftValueIndex++;
                operationIndex++;
            }
            loopCounter++;
            if (loopCounter >= 10000) {
                throw new OperationException("Interrupted infinite loop when reducing Addition/Substraction.");
            }
        }
    }

    private CoreOperation getRequestedOperation(OperationType targetOperation, Operand op1, Operand op2)
            throws OperationException {
        switch (targetOperation) {
            case ADD:
                return new AddOperation(op1, op2);
            case SUBTRACT:
                return new SubtractOperation(op1, op2);
            case MULTIPLY:
                return new MultiplyOperation(op1, op2);
            case DIVIDE:
                return new DivideOperation(op1, op2);
            default:
                throw new OperationException("Cannot create requested operation " + targetOperation.name());
        }
    }

    private void solveCoreOperation(CoreOperation operation, int leftValueIndex, int operationIndex)
            throws OperationException {
        try {
            operands.remove(leftValueIndex + 1);
            operands.remove(leftValueIndex);
            operands.add(leftValueIndex, operation.solve());
            operators.remove(operationIndex);
        } catch (Exception e) {
            throw new OperationException("Unable to solve and reduce core Operation. " + e.getMessage());
        }
    }

}
