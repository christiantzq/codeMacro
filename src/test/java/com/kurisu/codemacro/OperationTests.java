package com.kurisu.codemacro;

import com.kurisu.codemacro.exceptions.InvalidOperandException;
import com.kurisu.codemacro.exceptions.OperationException;
import com.kurisu.codemacro.operations.DoubleOperand;
import com.kurisu.codemacro.operations.IntegerOperand;
import com.kurisu.codemacro.operations.Operation;
import com.kurisu.codemacro.operations.StringOperand;
import com.kurisu.codemacro.operations.coreoperations.OperationType;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
public class OperationTests {

    @Test
    void testAdditionAndSubtraction() {
        try {
            Operation operation = new Operation(new IntegerOperand(5));
            operation.addOperand(OperationType.ADD, new IntegerOperand(5));
            operation.addOperand(OperationType.SUBTRACT, new IntegerOperand(1));
            operation.addOperand(OperationType.ADD, new IntegerOperand(5));
            operation.addOperand(OperationType.SUBTRACT, new IntegerOperand(1));
            operation.addOperand(OperationType.ADD, new IntegerOperand(5));
            Assertions.assertThat(operation.getValueAsString()).isEqualTo("(5 + 5 - 1 + 5 - 1 + 5)");
            Assertions.assertThat(operation.getValue().getValueAsString()).isEqualTo("18");
        } catch (OperationException e1) {
            e1.printStackTrace();
        } catch (InvalidOperandException e1) {
            e1.printStackTrace();
        }
    }

    @Test
    void testMultiplicationAndDivision() {
        try {
            Operation multiplyOperation = new Operation(new IntegerOperand(5));
            multiplyOperation.addOperand(OperationType.MULTIPLY, new DoubleOperand(5.0));
            multiplyOperation.addOperand(OperationType.DIVIDE, new DoubleOperand(5.0));
            Assertions.assertThat(multiplyOperation.getValue().getValueAsString()).isEqualTo("5.0");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void  testConcatenation(){
        try {            
			Operation concatOperation = new Operation(new StringOperand("Concat result = "));
			concatOperation.addOperand(OperationType.ADD, new IntegerOperand(5));
            Assertions.assertThat(concatOperation.getValueAsString()).isEqualTo("(Concat result =  + 5)");
            Assertions.assertThat(concatOperation.getValue().getValueAsString()).isEqualTo("Concat result = 5");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
