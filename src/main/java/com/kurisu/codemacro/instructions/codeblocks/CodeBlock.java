package com.kurisu.codemacro.instructions.codeblocks;

import java.util.LinkedList;
import java.util.List;

import com.kurisu.codemacro.instructions.Instruction;

/**
 * A code block represents a chunk of code, which is nothing but a list of
 * instructions.
 */
public abstract class CodeBlock {
    protected List<Instruction> instructions;

    public CodeBlock() {
        instructions = new LinkedList<>();
    }

    public void addInstruction(Instruction instruction) {
        instructions.add(instruction);
    }

}
