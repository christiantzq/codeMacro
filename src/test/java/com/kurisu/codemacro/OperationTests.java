package com.kurisu.codemacro;

import com.kurisu.codemacro.operations.Operation;
import com.kurisu.codemacro.operations.coreoperations.OperationType;
import com.kurisu.codemacro.operations.operands.DoubleOperand;
import com.kurisu.codemacro.operations.operands.IntegerOperand;
import com.kurisu.codemacro.operations.operands.StringOperand;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
public class OperationTests {

    @Test
    void testAdditionAndSubtraction() {
        try {
            Operation operation = new Operation(new IntegerOperand(5));
            operation.addComponent(OperationType.ADD, new IntegerOperand(5));
            operation.addComponent(OperationType.SUBTRACT, new IntegerOperand(1));
            operation.addComponent(OperationType.ADD, new IntegerOperand(5));
            operation.addComponent(OperationType.SUBTRACT, new IntegerOperand(1));
            operation.addComponent(OperationType.ADD, new IntegerOperand(5));
            Assertions.assertThat(operation.getOperand().getValueAsString()).isEqualTo("18");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testMultiplicationAndDivision() {
        try {
            Operation multiplyOperation = new Operation(new IntegerOperand(5));
            multiplyOperation.addComponent(OperationType.MULTIPLY, new DoubleOperand(5.0));
            multiplyOperation.addComponent(OperationType.DIVIDE, new DoubleOperand(5.0));
            Assertions.assertThat(multiplyOperation.getOperand().getValueAsString()).isEqualTo("5.0");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testConcatenation() {
        try {
            Operation concatOperation = new Operation(new StringOperand("Concat result = "));
            concatOperation.addComponent(OperationType.ADD, new IntegerOperand(5));
            Assertions.assertThat(concatOperation.getOperand().getValueAsString()).isEqualTo("Concat result = 5");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
