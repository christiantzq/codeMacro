package com.kurisu.codemacro.operations;

import java.util.LinkedList;
import java.util.List;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.exceptions.InvalidOperationComponentException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.operations.coreoperations.AddOperation;
import com.kurisu.codemacro.operations.coreoperations.CoreOperation;
import com.kurisu.codemacro.operations.coreoperations.DivideOperation;
import com.kurisu.codemacro.operations.coreoperations.MultiplyOperation;
import com.kurisu.codemacro.operations.coreoperations.OperationType;
import com.kurisu.codemacro.operations.coreoperations.SubtractOperation;
import com.kurisu.codemacro.operations.operands.Operand;

import static com.kurisu.codemacro.operations.coreoperations.OperationType.DIVIDE;
import static com.kurisu.codemacro.operations.coreoperations.OperationType.MULTIPLY;
import static com.kurisu.codemacro.operations.coreoperations.OperationType.SUBTRACT;
import static com.kurisu.codemacro.operations.coreoperations.OperationType.ADD;

/**
 * An operation is always meant to return a single reduced value from a given
 * set of components (and/or operands)
 * 
 * i.e. ("Result = " + (myVariable * 5 - myFunction(7)))
 * 
 * - Nested perentesis operations are reduced first along with Function calls
 * and getting variable values.
 * - After converting all components into operands, the algorithm reduces the
 * operand values in pairs using the priority: (/) -> (*) -> (-) -> (+)
 */
public class Operation implements OperationComponent {
    private List<OperationComponent> components;
    private List<Operand> operands;
    private List<OperationType> operators; // (+) (-) (*) (/)

    public Operation(OperationComponent firstOperand) {
        components = new LinkedList<>();
        operands = new LinkedList<>();
        operators = new LinkedList<>();
        components.add(firstOperand);
    }

    public void addComponent(OperationType operationType, OperationComponent component) {
        operators.add(operationType);
        components.add(component);
    }

    /**
     * Reduces all operations into a single Operand (the result)
     * 
     * @throws InvalidOperationComponentException
     * @throws InstructionException
     */
    @Override
    public Operand getOperand() throws OperationException, InvalidOperationComponentException, InstructionException {
        resolveComponentsToOperands();

        solveOperationsOfType(DIVIDE);
        solveOperationsOfType(MULTIPLY);
        solveOperationsOfType(SUBTRACT);
        solveOperationsOfType(ADD); // This also concats

        // TODO: 5. Logic > / < / == / != / || / && / >= / <=

        if (operands.size() > 1) {
            throw new OperationException("Operation did not completed succesfully (unable to reduce).");
        } else {
            return operands.get(0);
        }
    }

    private void resolveComponentsToOperands()
            throws OperationException, InvalidOperationComponentException, InstructionException {
        for (OperationComponent component : components) {
            operands.add(component.getOperand());
        }
    }

    @Deprecated
    public Object getResult() throws OperationException, InvalidOperationComponentException, InstructionException {
        return getOperand().getValue();
    }

    private void solveOperationsOfType(final OperationType targetOperation)
            throws InvalidOperationComponentException, OperationException {
        int leftValueIndex = 0;
        int operationIndex = 0;
        int totalOperations = operators.size();
        while (operationIndex < totalOperations) {
            final Operand op1 = operands.get(leftValueIndex);
            final OperationType currentOperation = operators.get(operationIndex);
            final Operand op2 = operands.get(leftValueIndex + 1); // right value index
            if (currentOperation == targetOperation) {
                CoreOperation coreOperation = createCoreOperation(targetOperation, op1, op2);
                solveAndReduce(coreOperation, leftValueIndex, operationIndex);
                totalOperations--;
            } else {
                leftValueIndex++;
                operationIndex++;
            }
        }
    }

    private CoreOperation createCoreOperation(OperationType targetOperation, Operand op1,
            Operand op2)
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

    private void solveAndReduce(CoreOperation operation, int leftValueIndex, int operationIndex)
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
